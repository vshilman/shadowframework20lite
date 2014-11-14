#include <jni.h>
#include "shadow\system\data\objects\SFFloat.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFFloatTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;


JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib2_getData
  (JNIEnv* env, jobject object, jstring string){

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	SFFloat float1(0.3f);
	SFFloat float2(-11.8f);

	float result[5];
	SFFloat *pointerFloat;
	pointerFloat = float1.clone();
	result[0] = float2.getFloatValue();
	float1.setFloatValue(88.8f);
	result[1] = float1.getFloatValue();
	result[2] = pointerFloat->getFloatValue();

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);
	float value1 = inputStream.readFloat();
	float value2 = inputStream.readFloat();
	stream.close();
	result[3] = value1;
	result[4] = value2;

	jfloatArray jResult;
	jResult = env->NewFloatArray(5);
	env->SetFloatArrayRegion(jResult, 0, 5, result);
	env->ReleaseStringUTFChars(string, fileName);
	env->ReleaseFloatArrayElements(jResult, result, 0);

	 return jResult;

}
