//
//  SFInputStreamCpp.h
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFInputStreamCpp__
#define SFInputStreamCpp__

#include "../SFInputStream.h"
#include <iostream>
#include <stdlib.h>

using namespace std;

namespace sf{
class SFInputStreamCpp: public SFInputStream{
    
    istream* stream;
    char readBuffer[4];
    
public:
    virtual ~SFInputStreamCpp(){

    }
    
    SFInputStreamCpp(istream* stream){
    	this->stream=stream;
    }

    char readByte();
	
	short readShort();
    
	short* readShorts(int n);
	
	int readInt();
    
	int* readInts(int n);
    
	float readFloat();
    
	float* readFloats(int n);
    
	string readString();
    
	int* readBinaryData(int n,int bitSize);
	
	int readBinaryData(int bitSize);
	
    string readName();
    
};
}


#endif /* defined(SFInputStreamCpp__) */
