#include <jni.h>
#include "SFVertex2fTest001.h"
#include "shadow\math\SFVertex2f.h"

using namespace sf;

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib11_getData
  (JNIEnv* env, jobject){

		float x1 = 7.8;
		float y1 = -99.6;
		float x2 = 10;
		float y2 = 10;

		SFVertex2f vertex1 = SFVertex2f();
		SFVertex2f vertex2(x1,y1);
		SFVertex2f vertex3(x2,y2);

		float result[20];

		result[0] = vertex1.getSize();
		result[1] = vertex2.getV()[0];
		result[2] = vertex2.getV()[1];
		result[3] = vertex1.getX();
		result[4] = vertex1.getY();
		result[5] = vertex2.getX();
		result[6] = vertex2.getY();

		vertex2.add2f(vertex3);

		result[7] = vertex2.getX();
		result[8] = vertex2.getY();

		vertex2.addMult2f(10, vertex3);

		result[9] = vertex2.getX();
		result[10] = vertex2.getY();

		float x3 = 1.5;
		float y3 = -1.5;
		float x4 = 2;
		float y4 = 2;

		SFVertex2f vertex4(x3,y3);
		SFVertex2f vertex5(x4,y4);
		vertex4.setX(2.5f);
		vertex4.setY(-2.5f);

		result[11] = vertex4.getX();
		result[12] = vertex4.getY();

		vertex4.subtract2f(vertex5);

		result[13] = vertex4.getX();
		result[14] = vertex4.getY();

		vertex5.set2f(3.5f,3.5f);

		result[15] = vertex5.getX();
		result[16] = vertex5.getY();

		vertex5.mult2f(2);

		result[17] = vertex5.getX();
		result[18] = vertex5.getY();

		jfloatArray jResult;
		jResult = env->NewFloatArray(20);
		env->SetFloatArrayRegion(jResult, 0, 20, result);
		env->ReleaseFloatArrayElements(jResult, result, 0);

		 return jResult;
}

