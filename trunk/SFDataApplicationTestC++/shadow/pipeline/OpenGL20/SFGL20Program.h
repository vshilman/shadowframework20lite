//
//  SFGL20Program.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20Program__
#define SFGL20Program__


#include "SFGL20AbstractProgram.h"

namespace sf{
class SFGL20Program : public SFGL20AbstractProgram {
    
	vector<SFPrimitiveProgramAssociation> vertexShader;
	SFProgramModule* transforms;
	SFPrimitive* primitive;
	
public:
    
	void clearVertexShader() {
		vertexShader.clear();
	}
	
	void addToVertexShader(SFProgramComponent* component,
                                  SFPipelineRegister* reg) {
		vertexShader.push_back(SFPrimitiveProgramAssociation(reg,component));
	}
    
	string loadVertexShaderText() {
		return getShaderText(vertexShader,true);
	}
    
	/*void write() {
		System.err.println("Vertex Program");
		for (int i=0; i < vertexShader.size(); i++) {
			System.err
            .println("\t\tvertexShader.get(i) " + vertexShader.get(i));
		}
		super.write();
	}*/
    
	
	void setPrimitive(SFPrimitive* primitive) {
		this->primitive=primitive;
		for (unsigned int i=0; i<primitive->getComponents().size(); i++) {
			SFProgramComponent* pr=primitive->getComponents().at(i);
			//this thing is so annoying... we will fix it
			SFPipelineRegister* outputRegister=primitive->getBlocks().at(i)->getRegister();
			addToVertexShader(pr,outputRegister);
		}
	}
	
	void addToVertexShader(SFProgramModule* module){
		for (unsigned int i = 0; i < module->getComponents().size(); i++) {
			addToVertexShader(module->getComponents()[i],0);
		}
	}
    
	
	void setTransform(SFProgramModule* transform) {
		addToVertexShader(transform);
		transforms=transform;
	}
	
	
	SFPrimitive* getPrimitive() {
		return primitive;
	}
	
	
	SFProgramModule* getTransforms() {
		return transforms;
	}
};
}


#endif /* defined(SFGL20Program__) */
