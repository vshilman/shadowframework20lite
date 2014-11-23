#include <jni.h>
#include "SFVertex4fTest001.h"
#include "shadow\math\SFVertex4f.h"

using namespace sf;

//Test sulla classe SFVertex4f

JNIEXPORT jfloatArray JNICALL Java_dataObjectTests_NativeLib13_getData
  (JNIEnv* env, jobject){

				float x1 = 7.8;
				float y1 = -99;
				float z1 = 8;
				float w1 = 8;
				float x2 = 10;
				float y2 = 10;
				float z2 = 10;
				float w2 = 10;

				SFVertex4f vertex1 = SFVertex4f();   //nella libreria manca un metodo nel costruttore di default.Aggiunto da me.Dimenticanza?
				SFVertex4f vertex2(x1,y1,z1,w1);
				SFVertex4f vertex3(x2,y2,z2,w2);

				float result[40];

				result[0] = vertex1.getSize();
				result[1] = vertex2.getV()[0];
				result[2] = vertex2.getV()[1];
				result[3] = vertex2.getV()[2];
				result[4] = vertex2.getV()[3];
				result[5] = vertex1.getX();
				result[6] = vertex1.getY();
				result[7] = vertex1.getZ();
				result[8] = vertex1.getW();
				result[9] = vertex2.getX();
				result[10] = vertex2.getY();
				result[11] = vertex2.getZ();
				result[12] = vertex2.getW();

				vertex2.add4f(vertex3);

				result[13] = vertex2.getX();
				result[14] = vertex2.getY();
				result[15] = vertex2.getZ();
				result[16] = vertex2.getW();

				vertex2.addMult4f(10, vertex3);

				result[17] = vertex2.getX();
				result[18] = vertex2.getY();
				result[19] = vertex2.getZ();
				result[20] = vertex2.getW();


				float x3 = 1.5;
				float y3 = -1.5;
				float z3 = 1.5;
				float w3 = 1.5;
				float x4 = 2;
				float y4 = 2;
				float z4 = 2;
				float w4 = 2;

				SFVertex4f vertex4(x3,y3,z3,w3);
				SFVertex4f vertex5(x4,y4,z4,w4);

				vertex4.setX(2.5f);
				vertex4.setY(-2.5f);
				vertex4.setZ(2.5f);
				vertex4.setW(2.5f);

				result[21] = vertex4.getX();
				result[22] = vertex4.getY();
				result[23] = vertex4.getZ();
				result[24] = vertex4.getW();

				vertex4.subtract4f(vertex5);

				result[25] = vertex4.getX();
				result[26] = vertex4.getY();
				result[27] = vertex4.getZ();
				result[28] = vertex4.getW();

				vertex5.set4f(3.5f,3.5f,3.5f,3.5f);

				result[29] = vertex5.getX();
				result[30] = vertex5.getY();
				result[31] = vertex5.getZ();
				result[32] = vertex5.getW();

				vertex5.mult4f(2);

				result[33] = vertex5.getX();
				result[34] = vertex5.getY();
				result[35] = vertex5.getZ();
				result[36] = vertex5.getW();

				jfloatArray jResult;
				jResult = env->NewFloatArray(40);
				env->SetFloatArrayRegion(jResult, 0, 40, result);
				env->ReleaseFloatArrayElements(jResult, result, 0);

				 return jResult;




}
