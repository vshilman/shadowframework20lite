//
//  SFGL20ImageProgram.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20ImageProgram__
#define SFGL20ImageProgram__

#include "SFGL20AbstractProgram.cpp"


namespace sf{
class SFGL20ImageProgram  : public SFGL20AbstractProgram {
    
public:
	void clearVertexShader() {
		//do nothing
	}
    
	void addToVertexShader(SFProgramComponent* component,
                                  SFPipelineRegister* reg) {
		//Do nothing
	}
    
    string loadVertexShaderText() {
        
		ostringstream vShader;
        vShader << "varying vec2 texCoord0;\n" <<
        "varying vec3 normal;\n" <<
        "void main(void){\n" <<
        "\t gl_Position=gl_Vertex;\n" <<
        "\t texCoord0=vec2(0.5,0.5)+gl_Vertex.xy*vec2(0.5,0.5);\n" <<
        "\t normal=vec3(0,0,-1);\n" <<
        "}\n";
		return vShader.str();
	}
    
	
	void setPrimitive(SFPrimitive* primitive) {
		//Do nothing
	}
	
	
	void setTransform(SFProgramModule* transform) {
		// TODO Auto-generated method stub
	}
	
};
}


#endif /* defined(SFGL20ImageProgram__) */
