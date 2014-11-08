//
//  SFGL20UniformData.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20UniformData__
#define SFGL20UniformData__

#include "SFGL20Genericprogram.h"
#include "SFProgramDataModel.h"
#include "SFGLSLSet.h"


namespace sf{


class SFGL20UniformData :public SFProgramDataModel {
	
	SFGLSLSet* program;
	
	GLuint** transformStructureUniforms;
    int transformStructureUniformsSize;
	GLuint** materialStructureUniforms;
    int materialStructureUniformsSize;
	GLuint** lightStructureUniforms;
    int lightStructureUniformsSize;
	vector<GLuint> transformTextures;
	vector<GLuint> materialTextures;
	vector<GLuint> lightTextures;
	
	vector<GLuint*> gridUniforms;//In what world would you use a pointer for this and not for textures vectors?
	
	GLint* mainUniforms;
	
public:
    ~SFGL20UniformData();
    
	GLuint* getUniforms(string prefix,SFPipelineStructureInstance* instance,vector<GLuint>* textures);
	
	GLuint* getUniforms(string prefix,SFPipelineGrid instance);
    
	void evaluateUniforms(void* pro);
	
	static GLuint* listToInts(vector<GLuint> list);
    
	GLuint** evaluateStructureUniforms(SFProgramModule* module,vector<GLuint>* textures);
	
	void setTextureData(SFPipelineGraphics::Module module, int index, SFPipelineTexture* texture);
	
	
	void setData(SFPipelineGraphics::Module module, int index, SFStructureData* data);
	
	void setData(GLuint* uniforms,int size,SFStructureData data);
	
	
	void sendVertex(SFValuenf value);
	
	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setPrimitiveData(shadow.pipeline.SFPrimitiveIndices, shadow.pipeline.openGL20.SFGL20ListData, shadow.pipeline.openGL20.SFGL20ListData)
	 */
	
	void setIndexedData(SFPrimitiveIndices indices,
                               SFGL20ValuenfArray** datas,SFPrimitive* primitive);
	
	
	void setTransformData(float transform[16]);
    
	void setupProjetion(float projection[]);
  	
};
}



#endif /* defined(SFGL20UniformData__) */
