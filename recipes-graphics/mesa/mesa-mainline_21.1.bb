require mesa-mainline.inc

DEFAULT_PREFERRENCE = "-1"

SRC_URI = " \
	git://gitlab.freedesktop.org/mesa/mesa.git;branch=21.1;protocol=https \
"

S = "${WORKDIR}/git"
SRCREV = "165816fd9613154f6822558be6d2c43914fda40c"

PV = "21.1.6"

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#elif defined(__unix__) && defined(EGL_NO_X11)$/#elif defined(__unix__) \&\& (defined(EGL_NO_X11) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)})/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
