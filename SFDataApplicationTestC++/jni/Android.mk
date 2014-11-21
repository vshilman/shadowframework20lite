LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES :=   shadow/system/data/cpp/SFInputStreamCpp.cpp shadow/math/SFVertex4f.cpp shadow/system/data/objects/SFVertex4fData.cpp SFVertex4fDataTest001.cpp shadow/system/data/objects/SFVectorData.cpp shadow/math/SFValue.cpp
include $(BUILD_SHARED_LIBRARY)