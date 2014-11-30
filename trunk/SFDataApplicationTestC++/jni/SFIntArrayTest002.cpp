#include <jni.h>
#include "shadow\system\data\objects\SFIntArray.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFIntArrayTest002.h"
#include <iostream>
#include <fstream>

using namespace sf;


JNIEXPORT jintArray JNICALL Java_comparisonTests_NativeLib3_getData
  (JNIEnv* env, jobject, jstring string){

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	int result[5];

	SFIntArray array(5);
	SFIntArray array2(3);

	for (int var = 0; var < 5; ++var) {

			array.getIntValues()[var] = var;
		}

	array2.getIntValues()[0] = 11;
	array2.getIntValues()[1] = 128;
	array2.getIntValues()[2] = 10170;

	result[0] = array.getIntValues()[0];
	result[1] = array.getIntValues()[1];
	result[2] = array.getIntValues()[2];
	result[3] = array.getIntValues()[3];
	result[4] = array.getIntValues()[4];

	result[5] = array2.getIntValues()[0];
	result[6] = array2.getIntValues()[1];
	result[7] = array2.getIntValues()[2];


	array2.getIntValues()[0] = -1;
	array2.getIntValues()[0] = -1;
	array2.getIntValues()[0] = -1;

	//Lettura valori da file

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);

	array.readFromStream(&inputStream);
	array2.readFromStream(&inputStream);

	stream.close();

	result[8] = array.getIntValues()[0];
	result[9] = array.getIntValues()[1];
	result[10] = array.getIntValues()[2];
	result[11] = array.getIntValues()[3];
	result[12] = array.getIntValues()[4];

	result[13] = array2.getIntValues()[0];
	result[14] = array2.getIntValues()[1];
	result[15] = array2.getIntValues()[2];

	jintArray jResult;
	jResult = env->NewIntArray(16);
	env->SetIntArrayRegion(jResult, 0, 16, result);
	env->ReleaseStringUTFChars(string, fileName);
	env->ReleaseIntArrayElements(jResult, result, 0);

		 return jResult;
}
