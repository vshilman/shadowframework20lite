//
//  SFGL20GlobalV.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20GlobalV__
#define SFGL20GlobalV__

#include <string>
#include "../parameters/SFPipelineRegister.h"

using namespace std;

namespace sf{
class SFGL20GlobalV {
	
public:
	static string getregName(SFPipelineRegister* reg){
		if(reg->getName().compare("vertex")==0)
			return "gl_Vertex";
		
		return reg->getName();
	}
	
	static bool requiresDeclaration(SFParameteri* global){
		
		if(global->getType()==SFParameteri::GLOBAL_GENERIC){
			return false;
		}
		if(!(dynamic_cast<SFParameter*>(global))){
			return false;
		}else{
			SFPipelineRegister* gl=(SFPipelineRegister*)global;
			if(gl->getUse()==SFPipelineRegister::WRITE_ON_TESSELLATION)
				return false;
			if(gl->getUse()==SFPipelineRegister::READ_ALL)
				return true;
			if(gl->getUse()==SFPipelineRegister::WROTE_BY_TRANSFORM){
				return true;
			}
			if(gl->getUse()==SFPipelineRegister::READ_ON_TESSELLATION)
				return false;
			if(gl->getUse()==SFPipelineRegister::WROTE_BY_TESSELLATION)
				return false;
			if(gl->getUse()==SFPipelineRegister::WROTE_BY_PRIMITIVE)
				return false;
			if(gl->getUse()==SFPipelineRegister::WROTE_BY_MATERIAL)
				return false;
		}
		return true;
	}
	
	static bool declaredOnWrite(SFParameteri* global){
		return !requiresDeclaration(global);
	}
	
	static string getModifiers(SFParameter* global){
		
		if(!(dynamic_cast<SFPipelineRegister*>(global))){
			return "uniform";
		}else{
			SFPipelineRegister* gl=(SFPipelineRegister*)global;
			if(gl->getUse()==SFPipelineRegister::WROTE_BY_TRANSFORM)
				return "varying";
		}
		return "uniform";
	}
	
};
}


#endif /* defined(SFGL20GlobalV__) */
