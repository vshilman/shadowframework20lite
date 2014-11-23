#include <jni.h>
#include "shadow/system/data/objects/SFShort.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFShortTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;

//Test sulla classe SFShort

JNIEXPORT jshortArray JNICALL Java_dataObjectTests_NativeLib1_getData
  (JNIEnv* env, jobject object,jstring string){

	//Creazione stringa contenente il nome del file (in cui leggerò i valori) passato tramite java nel metodo nativo

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	//Test dei metodi clone(), get(), set()

	short result[5];
	SFShort *pointerShort;
	SFShort short1(12);
	SFShort short2(-1);
	pointerShort = short1.clone();
	result[0] = short1.getShortValue();
	short1.setShortValue(111);
	result[1] = short1.getShortValue();
	result[2] = pointerShort->getShortValue();

	//lettura da file dei valori

	ifstream stream;
	SFInputStreamCpp inputStream(&stream);
	stream.open(fileName, std::ifstream::in);
	short value1 = inputStream.readShort();
	short value2 = inputStream.readShort();
	stream.close();

	//Salvo i valori letti in un vettore
	result[3] = value1;
	result[4] = value2;

	jshortArray jResult;
	jResult = env->NewShortArray(5);
	env->SetShortArrayRegion(jResult, 0, 5, result);
	env->ReleaseStringUTFChars(string, fileName);
	env->ReleaseShortArrayElements(jResult, result, 0);

	 return jResult;
}
