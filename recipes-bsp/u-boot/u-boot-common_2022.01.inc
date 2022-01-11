require u-boot-common.inc

# tools/binman/binman needs python3-setuptools now
DEPENDS:append = " python3-setuptools-native"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "d637294e264adfeb29f390dfc393106fd4d41b17"
