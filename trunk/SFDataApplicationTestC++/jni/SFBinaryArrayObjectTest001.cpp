#include <jni.h>
#include "SFBinaryArrayObjectTest001.h"
#include "shadow\system\data\objects\SFBinaryArrayObject.h"
#include "shadow\system\data\objects\SFPrimitiveType.h"

using namespace sf;

//Test sulla classe SFBinaryArrayObject

JNIEXPORT jintArray JNICALL Java_dataObjectTests_NativeLib15_getData
  (JNIEnv* env, jobject){


	SFBinaryArrayObject arrayObject1(5);

	int array1[5] = {1,44,23,-15,100};

	arrayObject1.setBytes(array1);

	//creazione array in cui vado a mettere i risultati da passare al test in java

	int result[6];

	result[0] = arrayObject1.getBytes()[0];
	result[1] = arrayObject1.getBytes()[1];
	result[2] = arrayObject1.getBytes()[2];
	result[3] = arrayObject1.getBytes()[3];
	result[4] = arrayObject1.getBytes()[4];

	//SFPrimitiveType *pointerArray;    //Perchè il metodo clone restituisce un SFPrimitiveType e non un SFBinaryArrayObject?
	//pointerArray = arrayObject1.clone();

	//int array2[5] = {1,1,1,-1,-1};

	//pointerArray->setBytes(array2);

	//result[3] = pointerArray->getBytes()[0];
	//result[4] = pointerArray->getBytes()[1];
	//result[5] = pointerArray->getBytes()[2];

	jintArray jResult;
	jResult = env->NewIntArray(6);
	env->SetIntArrayRegion(jResult, 0, 6, result);
	env->ReleaseIntArrayElements(jResult, result, 0);

		 return jResult;


}
