require mesa-mainline.inc

DEFAULT_PREFERENCE = "-1"

SRC_URI = " \
	git://gitlab.freedesktop.org/mesa/mesa.git;branch=21.3;protocol=https \
"

S = "${WORKDIR}/git"
SRCREV = "9da08702b0cfde97ea2ef990ff888cf90d3dff4f"

PV = "21.3.1"

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#elif defined(__unix__) && defined(EGL_NO_X11)$/#elif defined(__unix__) \&\& (defined(EGL_NO_X11) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)})/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
