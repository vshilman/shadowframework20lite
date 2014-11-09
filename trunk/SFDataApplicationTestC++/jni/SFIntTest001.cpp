#include <jni.h>
#include "shadow\system\data\objects\SFInt.h"
#include "SFIntTest001.h"

using namespace sf;

JNIEXPORT jint JNICALL Java_dataObjectTests_NativeLib_getData
(JNIEnv *env, jobject thisObj) {

	SFInt int1(29);
	int1.setIntValue(-15);
	return int1.getIntValue();

}
