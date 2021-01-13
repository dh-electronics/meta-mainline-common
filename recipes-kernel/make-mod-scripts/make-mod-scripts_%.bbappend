# OE-core 740d87766c ("kernel.bbclass: Configuration for environment with HOSTCXX")
EXTRA_OEMAKE += " HOSTCXX="${BUILD_CXX} ${BUILD_CXXFLAGS} ${BUILD_LDFLAGS}" "
