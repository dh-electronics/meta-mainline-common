require recipes-kernel/linux/linux-yocto.inc

# board specific branches
KBRANCH ?= "linux-5.10.y"

SRCREV_machine ?= "0c245c5fe93f0e9769de4a8b31f129b2759bf802"
SRCREV_meta ?= "2fb47d8ffbcb2e5d3b00e40e0957f5a1d70e0462"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "5.10.13"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/scsi/scsi-debug.scc", "" ,d)}"

# OE-core 740d87766c ("kernel.bbclass: Configuration for environment with HOSTCXX")
EXTRA_OEMAKE += " HOSTCXX="${BUILD_CXX} ${BUILD_CXXFLAGS} ${BUILD_LDFLAGS}" "
