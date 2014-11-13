#include <jni.h>
#include "shadow/system/data/objects/SFShort.h"
#include "SFShortTest001.h"

using namespace sf;

JNIEXPORT jshortArray JNICALL Java_dataObjectTests_NativeLib1_getData
  (JNIEnv* env, jobject object){

	short result[3];
	SFShort *pointerShort;
	SFShort short1(12);
	SFShort short2(-1);
	pointerShort = short1.clone();
	result[0] = short1.getShortValue();
	short1.setShortValue(111);
	result[1] = short1.getShortValue();
	result[2] = pointerShort->getShortValue();

	jshortArray jResult;
	jResult = env->NewShortArray(3);
	env->SetShortArrayRegion(jResult, 0, 3, result);

		 return jResult;
}
