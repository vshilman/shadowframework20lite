LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES :=   shadow/system/data/cpp/SFInputStreamCpp.cpp SFIntArrayTest002.cpp shadow/system/data/objects/SFIntArray.cpp 
include $(BUILD_SHARED_LIBRARY)