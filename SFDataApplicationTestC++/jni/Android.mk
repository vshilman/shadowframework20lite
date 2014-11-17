LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Library
LOCAL_SRC_FILES :=   shadow/system/data/cpp/SFInputStreamCpp.cpp  shadow/system/data/objects/SFInt.cpp shadow/system/data/objects/SFShort.cpp shadow/math/SFMatrix3f.cpp SFMatrix3fTest001.cpp shadow/math/SFVertex3f.cpp

include $(BUILD_SHARED_LIBRARY)