LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES :=   shadow/system/data/cpp/SFInputStreamCpp.cpp SFVertex4fTest001.cpp shadow/math/SFVertex4f.cpp 

include $(BUILD_SHARED_LIBRARY)