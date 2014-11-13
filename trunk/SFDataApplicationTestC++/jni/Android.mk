LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES := SFIntTest001.cpp shadow/system/data/objects/SFInt.cpp SFShortTest001.cpp shadow/system/data/objects/SFShort.cpp

include $(BUILD_SHARED_LIBRARY)