#include <jni.h>
#include "shadow\system\data\objects\SFInt.h"
#include "SFIntTest001.h"

using namespace sf;

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib_getData

  (JNIEnv* env, jobject object) {

	int result[3];
	SFInt *pointerInt;
	SFInt intero(23);
	SFInt intero2(1);
	pointerInt = intero.clone();
	result[0] = intero.getIntValue();
	intero.setIntValue(-8);
	result[1] = intero.getIntValue();
	result[2] = pointerInt->getIntValue();

	jintArray jResult;
	jResult = env->NewIntArray(3);
	env->SetIntArrayRegion(jResult, 0, 3, result);

	 return jResult;
}



