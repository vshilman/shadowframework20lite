#include <jni.h>
#include "shadow\system\data\objects\SFFloat.h"
#include "shadow\system\data\cpp\SFInputStreamCpp.h"
#include "StressingCTest001.h"
#include <iostream>
#include <fstream>

using namespace sf;

JNIEXPORT jfloat JNICALL Java_comparisonTests_NativeLib40_getData
  (JNIEnv* env, jobject, jstring string){

	//Creazione stringa contenente il nome del file (in cui leggerò i valori) passato tramite java nel metodo nativo

			const char *fileName;
			fileName = env->GetStringUTFChars(string, 0);

			float value1;
			ifstream stream;
			SFInputStreamCpp inputStream(&stream);

				SFFloat float1(-33.5f);

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);


				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);
				float1.setFloatValue(-33.5f);

				stream.open(fileName, std::ifstream::in);
				value1 = inputStream.readFloat();
				stream.close();

				return value1;

}
