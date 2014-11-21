#include <jni.h>
#include "SFVertex2fTest001.h"
#include "SFVertex2fDataTest001.h"
#include "shadow\math\SFVertex2f.h"
#include "shadow\system\data\objects\SFVertex2fData.h"
#include "shadow\system\data\objects\SFVectorData.h"
#include "shadow\math\SFValue.h"

using namespace sf;

//Test che non funziona.Non restituisce valori giusti.

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib16_getData
  (JNIEnv* env, jobject){

	SFVertex2fData vertex1 = SFVertex2fData();  //Manca il costruttore con cui settare direttamente i valori.Vedi esempio SFVertex3fData
												//Modificato il costruttore di default come nel caso 3f?

	float result[6];

	result[0] = vertex1.getVertex2f().getX();
	result[1] = vertex1.getVertex2f().getY();

	vertex1.getVertex2f().setX(1.5);
	vertex1.getVertex2f().setY(-1.5);

	result[2] = vertex1.getVertex2f().getX();
	result[3] = vertex1.getVertex2f().getY();

	jfloatArray jResult;
	jResult = env->NewFloatArray(6);
	env->SetFloatArrayRegion(jResult, 0, 6, result);
	env->ReleaseFloatArrayElements(jResult, result, 0);

			 return jResult;

}
