#!/bin/bash


export NDK=/Users/whs/Downloads/android-ndk-r14b
export TOOLCHAIN=$NDK/toolchains/llvm/prebuilt/linux-x86_64
export SYSROOT=$NDK/toolchains/llvm/prebuilt/linux-x86_64/sysroot
export HOST="aarch64-linux-android"

# fdk-aac 源码目录
#FDK_AAC_SOURCE=${ROOT_SOURCE}/build/fdk-aac
FDK_AAC_SOURCE=/Users/whs/Downloads/fdk-aac-2.0.2
# 输出路径
PREFIX=${FDK_AAC_SOURCE}/android/arm64-v8a

export AS=$TOOLCHAIN/bin/aarch64-linux-android-as

CFLAGS=" "

FLAGS="--enable-static --enable-shared zlib-dynamic --host=aarch64-linux-android --target=android --disable-asm"

export CXXFLAGS=$CFLAGS
export CFLAGS=$CFLAGS

cd ${FDK_AAC_SOURCE}
./configure $FLAGS \
--with-png=no \
--with-zlib=no \
#--enable-static \
#--disable-shared \
--prefix=${PREFIX} \
--with-sysroot=$SYSROOT \
--host=$HOST \


make clean
make -j4
make install