LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := IntLib
LOCAL_SRC_FILES := SFIntTest001.cpp shadow\system\data\objects\SFInt.cpp

include $(BUILD_SHARED_LIBRARY)