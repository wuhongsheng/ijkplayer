#!/bin/bash


export NDK=/Users/whs/Downloads/android-ndk-r14b
export TOOLCHAIN=$NDK/toolchains/llvm/prebuilt/linux-x86_64
export SYSROOT=$NDK/toolchains/llvm/prebuilt/linux-x86_64/sysroot
export HOST="aarch64-linux-android"

# fdk-aac 源码目录
FDK_AAC_SOURCE=/Users/whs/Downloads/freetype-2.12.1
# 输出路径
PREFIX=${FDK_AAC_SOURCE}/android/arm64-v8a

#export AS=$TOOLCHAIN/bin/aarch64-linux-android-as

CFLAGS="-fPIC -marm"

FLAGS=""

export CXXFLAGS=$CFLAGS
export CFLAGS=$CFLAGS

cd ${FDK_AAC_SOURCE}
./configure $FLAGS \
--with-png=no \
--enable-static \
--enable-shared \
--prefix=${PREFIX} \
--target=$HOST
--host=$HOST \


make clean
make -j4
make install