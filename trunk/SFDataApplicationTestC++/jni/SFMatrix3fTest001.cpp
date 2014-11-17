#include <jni.h>
#include <cmath>
#define _USE_MATH_DEFINES
#include "shadow\math\SFMatrix3f.h"
#include "SFMatrix3fTest001.h"
#include "shadow\math\SFVertex3f.h"
using namespace sf;


JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib10_getData
  (JNIEnv* env, jobject){

		SFMatrix3f matrix1(0,0,0,0,0,0,0,0,0); //Se la dichiaro così SFMatrix3f matrix1() non riesco a fare i set e i get;
											  //Devo dichiararla così SFMatrix3f matrix1 = SFMatrix3f(); (STILE JAVA)
		matrix1.setA(1);
		matrix1.setB(1);
		matrix1.setC(1);
		matrix1.setD(0);
		matrix1.setE(0);
		matrix1.setF(0);
		matrix1.setG(14.4f);
		matrix1.setH(-14.4f);
		matrix1.setI(14.4f);

		float result[10];

		result[0] = matrix1.getSize();
		result[1] = matrix1.getA();
		result[2] = matrix1.getB();
		result[3] = matrix1.getC();
		result[4] = matrix1.getD();
		result[5] = matrix1.getE();
		result[6] = matrix1.getF();
		result[7] = matrix1.getG();
		result[8] = matrix1.getH();
		result[9] = matrix1.getI();

		jfloatArray jResult;
		jResult = env->NewFloatArray(10);
		env->SetFloatArrayRegion(jResult, 0, 10, result);
		env->ReleaseFloatArrayElements(jResult, result, 0);

		 return jResult;


}

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib10_getData2
  (JNIEnv* env, jobject){

		float result[20];

		SFMatrix3f matrix1 = SFMatrix3f();
		SFMatrix3f matrix2(1,2,3,0,1,4,5,6,0);

		matrix1.getIdentity();

		result[0] = matrix1.getIdentity().getA();
		result[1] = matrix1.getIdentity().getE();
		result[2] = matrix1.getIdentity().getI();

		result[3] = matrix1.getScale(10,10.5f,-100.69f).getA();
		result[4] = matrix1.getScale(10,10.5f,-100.69f).getE();
		result[5] = matrix1.getScale(10,10.5f,-100.69f).getI();

		result[6] = matrix2.getInverse(matrix2).getA();
		result[7] = matrix2.getInverse(matrix2).getB();
		result[8] = matrix2.getInverse(matrix2).getC();
		result[9] = matrix2.getInverse(matrix2).getD();
		result[10] = matrix2.getInverse(matrix2).getE();
		result[11] = matrix2.getInverse(matrix2).getF();
		result[12] = matrix2.getInverse(matrix2).getG();
		result[13] = matrix2.getInverse(matrix2).getH();
		result[14] = matrix2.getInverse(matrix2).getI();
		result[12] = matrix2.getInverse(matrix2).getG();
		result[13] = matrix2.getInverse(matrix2).getH();
		result[14] = matrix2.getInverse(matrix2).getI();

		result[15] = matrix2.getRotationZ(M_PI).getA();
		//result[16] = matrix2.getRotationY(M_PI).getA();   valori strani
		//result[17] = matrix2.getRotationX(M_PI).getE();

		jfloatArray jResult;
		jResult = env->NewFloatArray(20);
		env->SetFloatArrayRegion(jResult, 0, 20, result);
		env->ReleaseFloatArrayElements(jResult, result, 0);

			 return jResult;

}
