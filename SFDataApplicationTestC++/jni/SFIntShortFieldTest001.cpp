#include <jni.h>
#include "shadow\system\data\objects\SFIntShortField.h"
#include "SFIntShortFieldTest001.h"

using namespace sf;

//Test sulla classe SFShortField

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib8_getData
  (JNIEnv* env, jobject){

	//Creazione oggetti

	SFIntShortField intero1(89452);
	SFIntShortField intero2(888);

	int result[4];

		result[0] = intero1.getShort(0);    //leggo solamente lo short (16 bit) meno significativo
		result[1] = intero1.getShort(1);    //leggo solamente lo short (16 bit) più significativo
		result[2] = intero2.getShort(0);
		result[3] = intero2.getShort(1);

		jintArray jResult;
		jResult = env->NewIntArray(4);
		env->SetIntArrayRegion(jResult, 0, 4, result);
		env->ReleaseIntArrayElements(jResult, result, 0);

			 return jResult;

}
