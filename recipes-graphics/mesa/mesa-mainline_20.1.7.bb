require mesa.inc

DEFAULT_PREFERRENCE = "-1"

SRC_URI = " \
	git://gitlab.freedesktop.org/mesa/mesa;branch=20.1;protocol=http \
"

S = "${WORKDIR}/git"
SRCREV = "c0e5cf9a874dc573a8e012216179c4b94e19ef13"

PV = "20.1.7"

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#elif defined(__unix__) && defined(EGL_NO_X11)$/#elif defined(__unix__) \&\& (defined(EGL_NO_X11) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)})/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
