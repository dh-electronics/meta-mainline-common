require ${BPN}.inc

SRC_URI = " \
	git://gitlab.freedesktop.org/mesa/mesa;branch=master;protocol=http \
"

S = "${WORKDIR}/git"
SRCREV = "f86bf2e90a9ab0d76c7f8e322c07eeee4df31a7b"

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#elif defined(__unix__) && defined(EGL_NO_X11)$/#elif defined(__unix__) \&\& (defined(EGL_NO_X11) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)})/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
