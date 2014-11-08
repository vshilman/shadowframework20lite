//
//  SFInputStreamCpp.cpp
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFInputStreamCpp.h"
#include <stdlib.h>
#include <cstring>

#define SHORT_RANGE 32768
#define SHORT_SIZE 65536

#define INT_RANGE 1073741824
#define INT_SIZE 2147483648

#define BIG_ENDIAN_LITTLE_ENDIAN_CONVERSION

namespace sf {

void SFInputStreamCpp_switch4ByteData(void* d) {
	char* data = (char*) d;
	char tmp = data[0];
	data[0] = data[3];
	data[3] = tmp;
	tmp = data[1];
	data[1] = data[2];
	data[2] = tmp;
}

char SFInputStreamCpp::readByte() {
	stream->read(readBuffer, 1);
	return readBuffer[0];
}

short SFInputStreamCpp::readShort() {
	stream->read(readBuffer, 2);
#ifdef BIG_ENDIAN_LITTLE_ENDIAN_CONVERSION
	int value = (readBuffer[1]) + (readBuffer[0] << 8);
#else
	int value = (readBuffer[0])+(readBuffer[1]<<8);
#endif
	//cout << "short value = n = " << value << " " << (int) (readBuffer[0]) << " "
	//		<< (int) (readBuffer[1]) << endl;
	if (value >= SHORT_RANGE) {
		value -= SHORT_SIZE;
	}
	return value;
}

short* SFInputStreamCpp::readShorts(int n) {
	short* data = new short[n];
	char* buffer = new char[n * 2];
	stream->read(buffer, n * 2);
	for (int i = 0; i < n; i++) {
		int index = i * 2;
		int value = (buffer[index]) + (buffer[index + 1] << 8);
		if (value >= SHORT_RANGE) {
			value -= SHORT_SIZE;
		}
		data[i] = value;
	}
	delete buffer;
	return data;
}

int SFInputStreamCpp::readInt() {
	int value;
	stream->read((char*) (&value), 4);
    
    //char* cvalue=(char*) (&value);
    //cout << (int)cvalue[0] << "-" << (int)cvalue[1] << "-" << (int)cvalue[2] << "-" << (int)cvalue[3] << " ";

#ifdef  BIG_ENDIAN_LITTLE_ENDIAN_CONVERSION
	SFInputStreamCpp_switch4ByteData(&value);
#endif

	return value;
}

int* SFInputStreamCpp::readInts(int n) {
	int* data = new int[n];
	stream->read((char*) data, n * 4);
#ifdef  BIG_ENDIAN_LITTLE_ENDIAN_CONVERSION
	for (int i = 0; i < n; i++) {
		SFInputStreamCpp_switch4ByteData(data + i);
	}
#endif
	return data;
}

float SFInputStreamCpp::readFloat() {
	float f;
	stream->read((char*) (&f), 4);
	//char* data = (char*) (&f);
	//cout << "Reading float " << (int) data[0] << (int) data[1] << (int) data[2]
	//		<< (int) data[3] << endl;
	#ifdef  BIG_ENDIAN_LITTLE_ENDIAN_CONVERSION
		SFInputStreamCpp_switch4ByteData(&f);
	#endif
	//memcpy(&f, readBuffer, sizeof(float));
	return f;
}

float* SFInputStreamCpp::readFloats(int n) {
	//cout << "SFInputStreamCpp::readFloats" << n << endl;
	float* data = new float[n];
	stream->read((char*) (data), 4 * n);
#ifdef  BIG_ENDIAN_LITTLE_ENDIAN_CONVERSION
	for (int i = 0; i < n; i++) {
		SFInputStreamCpp_switch4ByteData(data + i);
	}
#endif
	return data;
}

string SFInputStreamCpp::readString() {
//	cout << " Before reading " << (int)(stream->tellg()) <<"]"<<endl;
//	cout << " After reading " << (int)(stream->gcount()) << " "<<0 <<"]"<<endl;
		int n=stream->get();
		//stream->read(readBuffer, 1);//tellg
		//cout << " After reading " << (int)(stream->gcount()) << " "<<n <<"]"<<endl;
//		if(n==97){
//			for(int i=0;i<20;i++){
//				n=stream->get();
//				cout << " After reading " << (int)(stream->gcount()) << " "<<n <<"]"<<endl;
//			}
//		}

//	cout << " After reading " << (int)(stream->gcount()) << " "<<n <<"]"<<endl;
//	cout << " After reading " << (int)(stream->tellg()) <<"]"<<endl;

    //cout << n << endl;
    char* data = new char[n];
	stream->read(data, n);
	string temp(data, n);
	delete data;
	return temp;
}

int* SFInputStreamCpp::readBinaryData(int n, int bitSize) {

	int byteSize = ((bitSize - 1) >> 3) + 1;

	int* data = new int[n];
	char* bytes = new char[n * byteSize];
	stream->read(bytes, n * byteSize);

	for (int i = 0; i < n; i++) {
		int value = 0;
		for (int j = 0; j < byteSize; j++) {
			int byteValue = bytes[i * byteSize + j];
			value +=
					((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j)); // is
		}
		data[i] = value;
	}

	delete bytes;
	return data;
}

int SFInputStreamCpp::readBinaryData(int bitSize) {
	int byteSize = ((bitSize - 1) >> 3) + 1;

	char* bytes = new char[byteSize];
	stream->read(bytes, byteSize);
	int value = 0;
	for (int j = 0; j < byteSize; j++) {
		int byteValue = bytes[j];
		value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j)); // is
	}
	delete bytes;

	return value;
}

string SFInputStreamCpp::readName() {
	return this->readString();
}
}
