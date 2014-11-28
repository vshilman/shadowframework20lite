#include <jni.h>
#include "shadow\system\data\objects\SFFloat.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFFloatTest002.h"
#include <iostream>
#include <fstream>

using namespace sf;


JNIEXPORT jfloatArray JNICALL Java_comparisonTests_NativeLib2_getData
  (JNIEnv* env, jobject, jstring string){

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	float result[6];

	SFFloat float1(10.5f);
	SFFloat float2(-78.8f);

	result[0] = float1.getFloatValue();
	result[1] = float2.getFloatValue();

	float1.setFloatValue(1.5f);
	float2.setFloatValue(-1.5f);

	result[2] = float1.getFloatValue();
	result[3] = float2.getFloatValue();

	float1.setFloatValue(11.1f);
	float2.setFloatValue(11.1f);

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);

	float1.readFromStream(&inputStream);
	float2.readFromStream(&inputStream);

	stream.close();

	result[4] = float1.getFloatValue();
	result[5] = float2.getFloatValue();

	jfloatArray jResult;
	jResult = env->NewFloatArray(6);
	env->SetFloatArrayRegion(jResult, 0, 6, result);
	env->ReleaseStringUTFChars(string, fileName);
	env->ReleaseFloatArrayElements(jResult, result, 0);

		 return jResult;

}
