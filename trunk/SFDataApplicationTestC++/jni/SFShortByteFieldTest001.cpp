#include <jni.h>
#include "shadow\system\data\objects\SFShortByteField.h"
#include "SFShortByteFieldTest001.h"

using namespace sf;

//Test sulla classe SFShortByteField

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib9_getData
  (JNIEnv* env, jobject){

	SFShortByteField intero1(4);
	SFShortByteField intero2(888);

	int result[4];

	result[0] = intero1.getByte(0);   //leggo solo i primi 8 bit(1 byte)meno significativi di uno short (16 bit = 2 byte)
	result[1] = intero1.getByte(1);   //leggo solo gli ultimi 8 bit(1 byte)più significativi di uno short
	result[2] = intero2.getByte(0);
	result[3] = intero2.getByte(1);

	jintArray jResult;
	jResult = env->NewIntArray(4);
	env->SetIntArrayRegion(jResult, 0, 4, result);
	env->ReleaseIntArrayElements(jResult, result, 0);

			return jResult;

}
