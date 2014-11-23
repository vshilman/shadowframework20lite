#include <jni.h>
#include "shadow\system\data\SFNamedParametersObject.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFNamedParametersObjectTest001.h"
#include "shadow\system\data\SFDataObject.h"

using namespace sf;


JNIEXPORT jfloatArray JNICALL Java_dataTests_NativeLib1_getData
  (JNIEnv* env, jobject){

	SFNamedParametersObject parameterObject;



	float result[5];

	result[0] = parameterObject.size();

	jfloatArray jResult;
	jResult = env->NewFloatArray(5);
	env->SetFloatArrayRegion(jResult, 0, 5, result);
	env->ReleaseFloatArrayElements(jResult, result, 0);

				 return jResult;

}

