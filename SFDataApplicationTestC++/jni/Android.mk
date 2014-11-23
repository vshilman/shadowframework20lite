LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES :=   shadow/system/data/cpp/SFInputStreamCpp.cpp shadow/system/data/SFNamedParametersObject.cpp SFNamedParametersObjectTest001.cpp 
include $(BUILD_SHARED_LIBRARY)