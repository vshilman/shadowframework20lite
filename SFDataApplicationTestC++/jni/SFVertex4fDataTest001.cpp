#include <jni.h>
#include "SFVertex4fTest001.h"
#include "SFVertex4fDataTest001.h"
#include "shadow\math\SFVertex2f.h"
#include "shadow\system\data\objects\SFVertex4fData.h"
#include "shadow\system\data\objects\SFVectorData.h"
#include "shadow\math\SFValue.h"

using namespace sf;

//QUESTO TEST CONTIENE ANCHE IL TESTING DELLA CLASSE "SFVectorData"

//Test che non funziona (come SFVertex2fDataTest001).

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib18_getData
  (JNIEnv* env, jobject){

	SFVertex4fData vertex8 = SFVertex4fData();

	float array1[4] = {1.5f,1.5f,0.0f,0.0f};
	int size = 4;
	SFVectorData vectorData = SFVectorData(array1,size);

	float result[10];


	vertex8.getVertex4f().setX(1.5f);
	vertex8.getVertex4f().setY(1.5f);
	vertex8.getVertex4f().setZ(0.0f);
	vertex8.getVertex4f().setW(0.0f);

	result[0] = vertex8.getVertex4f().getX();
	result[1] = vertex8.getVertex4f().getY();
	result[2] = vertex8.getVertex4f().getZ();
	result[3] = vertex8.getVertex4f().getW();
	result[4] = vectorData.getFloatValues()[0];
	result[5] = vectorData.getFloatValues()[1];
	result[6] = vectorData.getFloatValues()[2];
	result[7] = vectorData.getFloatValues()[3];

	jfloatArray jResult;
	jResult = env->NewFloatArray(10);
	env->SetFloatArrayRegion(jResult, 0, 10, result);
	env->ReleaseFloatArrayElements(jResult, result, 0);

				 return jResult;
}
