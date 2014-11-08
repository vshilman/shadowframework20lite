LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := IntLib
LOCAL_SRC_FILES := SFIntTest001.cpp

include $(BUILD_SHARED_LIBRARY)