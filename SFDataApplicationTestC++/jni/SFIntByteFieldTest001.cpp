#include <jni.h>
#include "shadow\system\data\objects\SFIntByteField.h"
#include "SFIntByteFieldTest001.h"

using namespace sf;

//Test sulla classe SFIntByteField

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib7_getData
  (JNIEnv* env, jobject){

	//creazione degli oggetti

	SFIntByteField intero1(269);
	SFIntByteField intero2(4);

	int result[4];

	result[0] = intero1.getByte(0);  //vado a leggere solamente il byte nella posizione meno significativa
	result[1] = intero1.getByte(1);  //leggo solamente il byte nella posizione pi� significativa
	result[2] = intero2.getByte(0);
	result[3] = intero2.getByte(1);

	jintArray jResult;
	jResult = env->NewIntArray(4);
	env->SetIntArrayRegion(jResult, 0, 4, result);
	env->ReleaseIntArrayElements(jResult, result, 0);

		 return jResult;

}
