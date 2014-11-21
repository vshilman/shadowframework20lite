#include <jni.h>
#include "SFVertex3fTest001.h"
#include "SFVertex3fDataTest001.h"
#include "shadow\math\SFVertex2f.h"
#include "shadow\system\data\objects\SFVertex3fData.h"
#include "shadow\system\data\objects\SFVectorData.h"
#include "shadow\math\SFValue.h"

using namespace sf;

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib17_getData
  (JNIEnv* env, jobject){

	SFVertex3fData vertex1 = SFVertex3fData();

		float result[10];

	result[0] = vertex1.getVertex3f().getX();
	result[1] = vertex1.getVertex3f().getY();
	result[2] = vertex1.getVertex3f().getZ();

	SFVertex3fData vertex2 = SFVertex3fData(1.5f,-1.5f,1.5f);

	result[3] = vertex2.getVertex3f().getX();
	result[4] = vertex2.getVertex3f().getY();
	result[5] = vertex2.getVertex3f().getZ();

	SFVectorData *pointer;
	pointer = vertex2.clone();   //Perchè clone() restituisce un SFVectorData?

	result[6] = pointer->getFloatValues()[0];


	jfloatArray jResult;
	jResult = env->NewFloatArray(10);
	env->SetFloatArrayRegion(jResult, 0, 10, result);
	env->ReleaseFloatArrayElements(jResult, result, 0);

			 return jResult;

}
