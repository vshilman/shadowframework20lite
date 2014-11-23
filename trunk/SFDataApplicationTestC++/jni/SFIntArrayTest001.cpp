#include <jni.h>
#include "shadow\system\data\objects\SFIntArray.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFIntArrayTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;

//Test sulla classe SFIntArray

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib3_getData
  (JNIEnv* env, jobject, jstring string ){

	//Creazione stringa contenente il nome del file (in cui leggerò i valori) passato tramite java nel metodo nativo

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	SFIntArray array(5);
	SFIntArray array2(5);

	for (int var = 0; var < 5; ++var) {

		array.getIntValues()[var] = var;
	}

	//Lettura file

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);

	array.readFromStream(&inputStream);

	    stream.close();

		jintArray jResult;
		jResult = env->NewIntArray(5);
		env->SetIntArrayRegion(jResult, 0, 5, array.getIntValues());
		env->ReleaseStringUTFChars(string, fileName);
		env->ReleaseIntArrayElements(jResult, array.getIntValues(), 0);



	 return jResult;

}

//Altro test sul metodo getIntValues() e su clone()

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib3_getData2
  (JNIEnv* env, jobject){

		SFIntArray array3(5);

		array3.getIntValues()[0] = 11;
		array3.getIntValues()[1] = 128;
		array3.getIntValues()[2] = 10170;
		array3.getIntValues()[3] = -69;
		array3.getIntValues()[4] = -78;

		SFIntArray *pointerIntArray;
		pointerIntArray = array3.clone();

		for (int var = 0;  var < 5; ++var) {
			pointerIntArray->getIntValues()[var] = var;
		}

		jintArray jResult;
		jResult = env->NewIntArray(5);
		env->SetIntArrayRegion(jResult, 0, 5, pointerIntArray->getIntValues());
		env->ReleaseIntArrayElements(jResult, pointerIntArray->getIntValues(), 0);

		 return jResult;

}

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib3_getData3
  (JNIEnv* env, jobject){

		SFIntArray array5(5);
		SFIntArray array6(5);

		array6.getIntValues()[0] = 11;
		array6.getIntValues()[1] = 128;
		array6.getIntValues()[2] = 10170;
		array6.getIntValues()[3] = -69;
		array6.getIntValues()[4] = -78;

		for (int var = 0;  var < 5; ++var) {

				array5.getIntValues()[var] = var;

				}

		//array5.setIntValues(array6.getIntValues()); questo metodo restituisce errore native crash; invalid address or corrupt block.

		jintArray jResult;
		jResult = env->NewIntArray(5);
		env->SetIntArrayRegion(jResult, 0, 5, array5.getIntValues());
		env->ReleaseIntArrayElements(jResult, array5.getIntValues(), 0);

		 return jResult;
}
