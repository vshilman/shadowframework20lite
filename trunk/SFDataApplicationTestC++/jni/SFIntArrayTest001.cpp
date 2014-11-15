#include <jni.h>
#include "shadow\system\data\objects\SFIntArray.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFIntArrayTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib3_getData
  (JNIEnv* env, jobject, jstring string ){

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	SFIntArray array(5);
	SFIntArray array2(5);

	for (int var = 0; var < 5; ++var) {

		array.getIntValues()[var] = var;
	}

		jintArray jResult;
		jResult = env->NewIntArray(5);
		env->SetIntArrayRegion(jResult, 0, 5, array.getIntValues());
		env->ReleaseStringUTFChars(string, fileName);
		env->ReleaseIntArrayElements(jResult, array.getIntValues(), 0);

		ifstream stream;
		SFInputStreamCpp inputStream(&stream);
		stream.open(fileName, std::ifstream::in);

		int value1 = inputStream.readInt();
		int value2 = inputStream.readInt();


		stream.close();

	 return jResult;

}

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib3_getData2
  (JNIEnv* env, jobject){

		SFIntArray array2(5);

		SFIntArray *pointerIntArray;
		pointerIntArray = array2.clone();

		for (int var = 0;  var < 5; ++var) {
			pointerIntArray->getIntValues()[var] = var;
		}

		jintArray jResult;
		jResult = env->NewIntArray(5);
		env->SetIntArrayRegion(jResult, 0, 5, pointerIntArray->getIntValues());
		env->ReleaseIntArrayElements(jResult, pointerIntArray->getIntValues(), 0);

		 return jResult;

}
