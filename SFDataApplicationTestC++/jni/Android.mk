LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES :=   shadow/system/data/cpp/SFInputStreamCpp.cpp SFFloatTest002.cpp shadow/system/data/objects/SFFloat.cpp 
include $(BUILD_SHARED_LIBRARY)