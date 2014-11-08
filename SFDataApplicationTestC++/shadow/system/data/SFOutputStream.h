/*
 * SFOutputStream.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFOUTPUTSTREAM_H_
#define SFOUTPUTSTREAM_H_

#include <string>
using namespace std;


namespace sf{

class SFOutputStream {

public:
	virtual ~SFOutputStream()=0;

	virtual void writeByte(int value)=0;

	virtual void writeShort(short value)=0;

	virtual void writeShorts(short* values,int n)=0;

	virtual void writeFloat(float value)=0;

	virtual void writeFloats(float* values,int n)=0;

	virtual void writeInt(int value)=0;

	virtual void writeInts(int* values,int n)=0;

	virtual void writeString(string s)=0;

	virtual void writeBinaryData(int* values,int n,int bitSize)=0;

	virtual void writeBinaryData(int value,int bitSize)=0;

	virtual void writeName(string s)=0;

};

}

#endif /* SFOUTPUTSTREAM_H_ */
