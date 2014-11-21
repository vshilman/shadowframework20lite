#include <jni.h>
#include "SFBinaryFloatArrayObjectTest001.h"
#include "shadow\system\data\objects\SFBinaryFloatArrayObject.h"

using namespace sf;

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib14_getData
  (JNIEnv* env, jobject){

	SFBinaryFloatArrayObject floatArray1(3,3);
	float array1[3] = {1.5f,-1.5f,1.5f};
	floatArray1.setValues(array1);

	float result[6];

	result[0] = floatArray1.getValues()[0];
	result[1] = floatArray1.getValues()[1];
	result[2] = floatArray1.getValues()[2];

	SFBinaryFloatArrayObject *pointerArray;
	pointerArray = floatArray1.clone();

	pointerArray->setValues(array1);

	result[3] = pointerArray->getValues()[0];
	result[4] = pointerArray->getValues()[1];
	result[5] = pointerArray->getValues()[2];

	jfloatArray jResult;
	jResult = env->NewFloatArray(6);
	env->SetFloatArrayRegion(jResult, 0, 6, result);
	env->ReleaseFloatArrayElements(jResult, result, 0);

		 return jResult;
}
