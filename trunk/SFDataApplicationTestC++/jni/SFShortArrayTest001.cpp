#include <jni.h>
#include "shadow\system\data\objects\SFShortArray.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFShortArrayTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;


JNIEXPORT jshortArray JNICALL Java_dataObjectTests_NativeLib5_getData
  (JNIEnv* env, jobject, jstring string){

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	SFShortArray array(5);


	for (int var = 0; var < 5; ++var) {

			array.getShortValues()[var] = -158;
		}

	jshortArray jResult;
	jResult = env->NewShortArray(5);

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);

	array.readFromStream(&inputStream);

	stream.close();

	//array.getShortValues()[5] = value;
	//array.getShortValues()[6] = array2.getShortValues()[1];

	env->SetShortArrayRegion(jResult, 0, 5, array.getShortValues());
	env->ReleaseStringUTFChars(string, fileName);
	env->ReleaseShortArrayElements(jResult, array.getShortValues(), 0);

		 return jResult;

}


JNIEXPORT jshortArray JNICALL Java_dataObjectTests_NativeLib5_getData2
  (JNIEnv* env, jobject){

			SFShortArray array2(6);

			//Non ho il metodo setShortValues. E' una dimenticanza?

			SFShortArray *pointerShortArray;
			pointerShortArray = array2.clone();

			for (int var = 0;  var < 6; ++var) {
				pointerShortArray->getShortValues()[var] = var;
			}
			short dimension = pointerShortArray->getN();
			pointerShortArray->getShortValues()[5] = dimension;

			jshortArray jResult;
			jResult = env->NewShortArray(6);
			env->SetShortArrayRegion(jResult, 0, 6, pointerShortArray->getShortValues());
			env->ReleaseShortArrayElements(jResult, pointerShortArray->getShortValues(), 0);

			 return jResult;
}
