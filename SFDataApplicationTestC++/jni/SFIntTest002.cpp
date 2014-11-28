#include <jni.h>
#include "shadow\system\data\objects\SFInt.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFIntTest002.h"
#include <iostream>
#include <fstream>

using namespace sf;

JNIEXPORT jintArray JNICALL Java_comparisonTests_NativeLib_getData
  (JNIEnv* env, jobject, jstring string){

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	int result[10];

	SFInt intero1(6);
	SFInt intero2(-15);
	SFInt intero3(11);

	result[0] = intero1.getIntValue();
	result[1] = intero2.getIntValue();
	result[2] = intero3.getIntValue();

	intero1.setIntValue(11);
	intero2.setIntValue(-17);
	intero3.setIntValue(0);

	result[3] = intero1.getIntValue();
	result[4] = intero2.getIntValue();
	result[5] = intero3.getIntValue();

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);

	intero1.setIntValue(111);
	intero2.setIntValue(111);
	intero3.setIntValue(-111);

	intero1.readFromStream(&inputStream);
	intero2.readFromStream(&inputStream);
	intero3.readFromStream(&inputStream);

	stream.close();

	result[6] = intero1.getIntValue();
	result[7] = intero2.getIntValue();
	result[8] = intero3.getIntValue();

	jintArray jResult;
	jResult = env->NewIntArray(10);
	env->SetIntArrayRegion(jResult, 0, 10, result);
	env->ReleaseStringUTFChars(string, fileName);
	env->ReleaseIntArrayElements(jResult, result, 0);

		 return jResult;
}
