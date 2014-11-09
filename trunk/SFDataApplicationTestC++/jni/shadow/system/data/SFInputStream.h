/*
 * SFInputStream.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFINPUTSTREAM_H_
#define SFINPUTSTREAM_H_

#include <string>
using namespace std;

namespace sf{
class SFInputStream {

public:

	virtual ~SFInputStream(){

	}

	virtual char readByte()=0;

	virtual short readShort()=0;

	virtual short* readShorts(int n)=0;

	virtual int readInt()=0;

	virtual int* readInts(int n)=0;

	virtual float readFloat()=0;

	virtual float* readFloats(int n)=0;

	virtual string readString()=0;

	virtual int* readBinaryData(int n,int bitSize)=0;

	virtual int readBinaryData(int bitSize)=0;

	virtual string readName()=0;
};
}

#endif /* SFINPUTSTREAM_H_ */
