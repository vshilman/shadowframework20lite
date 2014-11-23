#include <jni.h>
#include "SFVertex3fTest001.h"
#include "shadow\math\SFVertex3f.h"

using namespace sf;

//Test sulla classe SFVertex3f

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib12_getData
  (JNIEnv* env, jobject){

			float x1 = 7.8;
			float y1 = -99;
			float z1 = 8;
			float x2 = 10;
			float y2 = 10;
			float z2 = 10;

			//Test praticamente identico a quello sulla classe SFVertex2f.Stesse componenti testate, cambia solo il numero di parametri del vettore

			SFVertex3f vertex1 = SFVertex3f();
			SFVertex3f vertex2(x1,y1,z1);
			SFVertex3f vertex3(x2,y2,z2);

			float result[30];

			result[0] = vertex1.getSize();
			result[1] = vertex2.getV()[0];
			result[2] = vertex2.getV()[1];
			result[3] = vertex2.getV()[2];
			result[4] = vertex1.getX();
			result[5] = vertex1.getY();
			result[6] = vertex1.getZ();
			result[7] = vertex2.getX();
			result[8] = vertex2.getY();
			result[9] = vertex2.getZ();

			vertex2.add3f(vertex3);

			result[10] = vertex2.getX();
			result[11] = vertex2.getY();
			result[12] = vertex2.getZ();

			vertex2.addMult3f(10, vertex3);

			result[13] = vertex2.getX();
			result[14] = vertex2.getY();
			result[15] = vertex2.getZ();


			float x3 = 1.5;
			float y3 = -1.5;
			float z3 = 1.5;
			float x4 = 2;
			float y4 = 2;
			float z4 = 2;

			SFVertex3f vertex4(x3,y3,z3);
			SFVertex3f vertex5(x4,y4,z4);
			vertex4.setX(2.5f);
			vertex4.setY(-2.5f);
			vertex4.setZ(2.5f);

			result[16] = vertex4.getX();
			result[17] = vertex4.getY();
			result[18] = vertex4.getZ();

			vertex4.subtract3f(vertex5);

			result[19] = vertex4.getX();
			result[20] = vertex4.getY();
			result[21] = vertex4.getZ();

			vertex5.set3f(3.5f,3.5f,3.5f);

			result[22] = vertex5.getX();
			result[23] = vertex5.getY();
			result[24] = vertex5.getZ();

			vertex5.mult3f(2);

			result[25] = vertex5.getX();
			result[26] = vertex5.getY();
			result[27] = vertex5.getZ();

			jfloatArray jResult;
			jResult = env->NewFloatArray(30);
			env->SetFloatArrayRegion(jResult, 0, 30, result);
			env->ReleaseFloatArrayElements(jResult, result, 0);

			 return jResult;



}
