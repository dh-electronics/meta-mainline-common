require mesa.inc

DEFAULT_PREFERRENCE = "-1"

SRC_URI = " \
	git://gitlab.freedesktop.org/mesa/mesa;branch=20.3;protocol=http \
"

S = "${WORKDIR}/git"
SRCREV = "6d2e76d2425901dec7bb21da62b5f0f2b81f1e8a"

PV = "20.3.2"

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#elif defined(__unix__) && defined(EGL_NO_X11)$/#elif defined(__unix__) \&\& (defined(EGL_NO_X11) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)})/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
