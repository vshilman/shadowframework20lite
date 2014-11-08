//
//  SFPipelineRegister.h
//  
//
//  Created by Alessandro Martinelli on 14/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineRegister__
#define SFPipelineRegister__


#include "SFParameter.h"
#include <map>
#include <string>

using namespace std;


namespace sf{

class SFPipelineRegister: public SFParameter{
    
public:
    
	static const short READ_ON_TESSELLATION=1;
	static const short WRITE_ON_TESSELLATION=2;
	static const short READ_ON_PRIMITIVE=4;
	static const short WRITE_ON_PRIMITIVE=8;
	static const short READ_ON_TRANSFORM=16;
	static const short WRITE_ON_TRANSFORM=32;
	static const short READ_ON_MATERIAL=64;
	static const short WRITE_ON_MATERIAL=128;
	static const short READ_ON_LIGHTING=256;
	static const short WRITE_ON_LIGHTING=512;
	static const short READ_ALL=READ_ON_LIGHTING+READ_ON_MATERIAL+READ_ON_PRIMITIVE+READ_ON_TESSELLATION+READ_ON_TRANSFORM;
	static const short WROTE_BY_TESSELLATION=READ_ON_LIGHTING+READ_ON_MATERIAL+READ_ON_PRIMITIVE+READ_ON_TRANSFORM+WRITE_ON_TESSELLATION;
	static const short WROTE_BY_PRIMITIVE=READ_ON_LIGHTING+READ_ON_MATERIAL+READ_ON_TRANSFORM+WRITE_ON_PRIMITIVE;
	static const short WROTE_BY_TRANSFORM=READ_ON_LIGHTING+READ_ON_MATERIAL+WRITE_ON_TRANSFORM;
	static const short WROTE_BY_MATERIAL=READ_ON_LIGHTING+WRITE_ON_MATERIAL;
	
private:
    
	static map<string,SFPipelineRegister*> predefinedGlobalV;
	
	static void prepare();
	
private:
    
    int use;

public:
    SFPipelineRegister(short type, string name,int use);
    
    static void destroyAll(){
            //TODO destroy all registerd predefinedGlobalV;
    }
	
	int getUse();
	
	static SFPipelineRegister* getFromName(string name);
};

}


#endif /* defined(SFPipelineRegister__) */
