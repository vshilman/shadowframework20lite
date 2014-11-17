#include <jni.h>
#include "shadow\system\data\objects\SFFloatArray.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "SFFloatArrayTest001.h"
#include <iostream>
#include <fstream>
#include <stdlib.h>

using namespace sf;

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib6_getData
  (JNIEnv* env, jobject, jstring string){

		const char *fileName;
		fileName = env->GetStringUTFChars(string, 0);

		SFFloatArray array(7);

		array.getFloatValues()[0] = 15.8f;
		array.getFloatValues()[1] = 33.3f;
		array.getFloatValues()[2] = -1.5f;
		array.getFloatValues()[3] = 877.88f;
		array.getFloatValues()[4] = -78.37f;


		jfloatArray jResult;
		jResult = env->NewFloatArray(7);

		ifstream stream;
		SFInputStreamCpp inputStream(&stream);
		stream.open(fileName, std::ifstream::in);

		stream.close();

		env->SetFloatArrayRegion(jResult, 0, 7, array.getFloatValues());
		env->ReleaseStringUTFChars(string, fileName);
		env->ReleaseFloatArrayElements(jResult, array.getFloatValues(), 0);

			 return jResult;
}


JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib6_getData2
  (JNIEnv* env, jobject){

			SFFloatArray array2(6);
			SFFloatArray array3(6);

			array3.getFloatValues()[0] = 0.1f;
			array3.getFloatValues()[1] = 0.1f;
			array3.getFloatValues()[2] = 0.1f;
			array3.getFloatValues()[3] = 33.33f;
			array3.getFloatValues()[4] = -0.1f;

			SFFloatArray *pointerFloatArray;
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
