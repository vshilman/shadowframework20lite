#include <jni.h>
#include "shadow\system\data\objects\SFFloatArray.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFFloatArrayTest001.h"
#include <iostream>
#include <fstream>
#include <stdlib.h>

using namespace sf;

//Test sulla classe SFFloatArray

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib6_getData
  (JNIEnv* env, jobject, jstring string){


		const char *fileName;
		fileName = env->GetStringUTFChars(string, 0);   //nome del file in cui andrò a leggere i valori

		SFFloatArray array(2);

		array.getFloatValues()[0] = 15.8f;
		array.getFloatValues()[1] = 33.3f;

		jfloatArray jResult;
		jResult = env->NewFloatArray(2);

		//test sulla lettura dei valori da file passato tramite java

		ifstream stream;
		SFInputStreamCpp inputStream(&stream);
		stream.open(fileName, std::ifstream::in);

		float value1 = inputStream.readFloat();   //leggo i float da file
		float value2 = inputStream.readFloat();

		stream.close();

		array.getFloatValues()[0] = value1;
		array.getFloatValues()[1] = value2;

		env->SetFloatArrayRegion(jResult, 0, 2, array.getFloatValues());
		env->ReleaseStringUTFChars(string, fileName);
		env->ReleaseFloatArrayElements(jResult, array.getFloatValues(), 0);

			 return jResult;
}


//Altro test in cui provo altri metodi

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib6_getData2
  (JNIEnv* env, jobject){

			SFFloatArray array2(6);
			SFFloatArray array3(6);

			array3.getFloatValues()[0] = 0.1f;
			array3.getFloatValues()[1] = 0.1f;
			array3.getFloatValues()[2] = 0.1f;
			array3.getFloatValues()[3] = 33.33f;
			array3.getFloatValues()[4] = -0.1f;

			SFFloatArray *pointerFloatArray;           //creazione puntatore da usare per il metodo clone()
			pointerFloatArray = array2.clone();

			for (int var = 0;  var < 6; ++var) {
					pointerFloatArray->getFloatValues()[var] = 155.5f;
				}

			pointerFloatArray->setFloatValues(array3.getFloatValues());

			jfloatArray jResult;
			jResult = env->NewFloatArray(6);
			env->SetFloatArrayRegion(jResult, 0, 6, pointerFloatArray->getFloatValues());
			env->ReleaseFloatArrayElements(jResult, pointerFloatArray->getFloatValues(), 0);

			 return jResult;

}
