//
//  SFGlobalVSetGL20Implementor.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGlobalVSetGL20Implementor__
#define SFGlobalVSetGL20Implementor__

#include <string>
#include <vector>
#include "../SFPipelineStructureInstance.h"
#include "../SFPipelineGrid.h"
#include "SFGL20GlobalV.h"

using namespace std;

namespace sf{


class SFGlobalVSetGL20Implementor{

private:
    static map<short, string> declarations;
	
    //TODO: how about this?
    /*static{
		declarations.put(SFParameteri.GLOBAL_FLOAT, "float");
		declarations.put(SFParameteri.GLOBAL_FLOAT2, "vec2");
		declarations.put(SFParameteri.GLOBAL_FLOAT3, "vec3");
		declarations.put(SFParameteri.GLOBAL_FLOAT4, "vec4");
		declarations.put(SFParameteri.GLOBAL_MATRIX4, "mat4");
		declarations.put(SFParameteri.GLOBAL_TEXTURE, "sampler2D");
	}*/
	
public:
    
	static string generateShaderParameters(vector<SFParameter*> set);
    
	static string getDeclarationstring(short param_);
	
	static string generateInstancedStructures(SFPipelineStructureInstance* instance,
                                                     SFParameteri* functionParameter,string suffix,vector<SFParameter> parameters);
    
	static string generateInstancedGrids(SFPipelineGrid instance,short type,string suffix);
    
};


}



#endif /* defined(SFGlobalVSetGL20Implementor__) */
