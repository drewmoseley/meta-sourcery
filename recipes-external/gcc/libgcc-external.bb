SUMMARY = "The GNU Compiler Collection - libgcc"
HOMEPAGE = "http://www.gnu.org/software/gcc/"
SECTION = "devel"
GCC_VERSION := "${@external_run(d, 'gcc', '-dumpversion').rstrip()}"
PV = "${GCC_VERSION}"

inherit external-toolchain

LICENSE = "GPL-3.0-with-GCC-exception"
PACKAGES =+ "libgcov-dev"

external_libroot = "${@os.path.realpath('${EXTERNAL_TOOLCHAIN_LIBROOT}').replace(os.path.realpath('${EXTERNAL_TOOLCHAIN}') + '/', '/')}"
FILES_MIRRORS =. "${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/|${external_libroot}/\n"

FILES_${PN} = "${base_libdir}/libgcc_s.so.*"
FILES_${PN}-dev = "${base_libdir}/libgcc_s.so \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtbegin.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtbeginS.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtbeginT.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtend.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtendS.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtfastmath.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/crtprec*.o \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/libgcc.a \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/libgcc_eh.a \
                   ${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/include/unwind.h \
                   "
INSANE_SKIP_${PN}-dev += "staticdev"
FILES_${PN}-dbg += "${base_libdir}/.debug/libgcc_s.so.*.debug"
FILES_libgcov-dev = "${libdir}/gcc/${TARGET_SYS}/${GCC_VERSION}/libgcov.a"
INSANE_SKIP_libgcov-dev += "staticdev"

do_package[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
do_package_write_ipk[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
do_package_write_deb[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
do_package_write_rpm[depends] += "virtual/${MLPREFIX}libc:do_packagedata"
