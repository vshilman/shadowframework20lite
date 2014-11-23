#include <jni.h>
#include "shadow\system\data\objects\SFInt.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFIntTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;

//Test sulla classe SFInt

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib_getData

  (JNIEnv* env, jobject object,jstring string) {

	//Creazione stringa contenente il nome del file (in cui leggerò i valori) passato tramite java nel metodo nativo

	const char *fileName;
	fileName = env->GetStringUTFChars(string, 0);

	//Test su alcuni metodi della classe

		int result[5];
		SFInt *pointerInt;
		SFInt intero(23);
		SFInt intero2(1);
		pointerInt = intero.clone();
		result[0] = intero.getIntValue();
		intero.setIntValue(-8);
		result[1] = intero.getIntValue();
		result[2] = pointerInt->getIntValue();

		//Lettura valori interi da file

		ifstream stream;
		SFInputStreamCpp inputStream(&stream);
		stream.open(fileName, std::ifstream::in);

		int value1 = inputStream.readInt();
		int value2 = inputStream.readInt();

		stream.close();
		result[3] = value1;   //Salvo i valori letti nel vettore result che passerò in java
		result[4] = value2;

		jintArray jResult;
		jResult = env->NewIntArray(5);
		env->SetIntArrayRegion(jResult, 0, 5, result);
		env->ReleaseStringUTFChars(string, fileName);
		env->ReleaseIntArrayElements(jResult, result, 0);

	 return jResult;

}



