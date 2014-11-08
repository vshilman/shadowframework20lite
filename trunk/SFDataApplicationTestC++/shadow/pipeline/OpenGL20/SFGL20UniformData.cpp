//
//  SFGL20UniformData.cpp
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFGL20UniformData.h"
#include "SFGL20AbstractProgram.h"

namespace sf{

SFGL20UniformData::~SFGL20UniformData(){
        for(unsigned int i=0;i<gridUniforms.size();i++){
            delete gridUniforms[i];
        }
        delete transformStructureUniforms;
        delete materialStructureUniforms;
        delete lightStructureUniforms;
        delete mainUniforms;
    }

	GLuint* SFGL20UniformData::getUniforms(string prefix,SFPipelineStructureInstance* instance,vector<GLuint>* textures){
		GLuint* data=new GLuint[instance->size()];
		SFParameter* parameters=instance->getParameters();
		for (int i = 0; i < instance->getParametersLength(); i++) {
			SFParameter* param=parameters+i;
			string name=prefix+param->getName();
//            GL_API int          GL_APIENTRY glGetUniformLocation (GLuint program, const GLchar* name)  __OSX_AVAILABLE_STARTING(__MAC_NA,__IPHONE_3_0);
            const GLchar* nm=name.c_str();
			data[i]=glGetUniformLocation(program->getProgram(),nm);
			if(param->getType()==SFParameteri::GLOBAL_TEXTURE){
				textures->push_back(data[i]);
			}

		}
		return data;
	}

	GLuint* SFGL20UniformData::getUniforms(string prefix,SFPipelineGrid instance){
		GLuint* data=new GLuint[instance.size()];
		SFParameteri** parameters=instance.getParameters();
		for (int i = 0; i < instance.size(); i++) {
			SFParameteri* param=parameters[i];
			string name=prefix;
            name+=param->getName();
            const GLchar* nm=name.c_str();
			data[i]=glGetUniformLocation(program->getProgram(),nm);
		}
		return data;
	}


	void SFGL20UniformData::evaluateUniforms(void* prog) {
		SFGL20AbstractProgram* program=(SFGL20AbstractProgram*)prog;
		this->program=(SFGLSLSet*)program;

		gridUniforms.clear();

		SFPrimitive* primitive=program->getPrimitive();
		if(primitive!=0){
			for (int i = 0; i < primitive->getBlocks().size(); i++) {
				SFProgramComponent* component=primitive->getComponents().at(i);
				SFPipelineRegister* reg=primitive->getBlocks()[i]->getRegister();
				vector<SFPipelineGrid*> grids=component->getGrid();
                for(int j=0;j<grids.size();i++){
                      gridUniforms.push_back(getUniforms(reg->getName(), *grids.at(i)));
                }
			}
		}

		//vector<GLuint> textures;
		this->transformTextures.clear();
        transformStructureUniforms=evaluateStructureUniforms(program->getTransforms(),&transformTextures);
		//this->transformTextures=listToInts(textures);
		//textures.clear();
		this->materialTextures.clear();
        materialStructureUniforms=evaluateStructureUniforms(program->getMaterials(),&materialTextures);

        this->lightTextures.clear();
		lightStructureUniforms=evaluateStructureUniforms(program->getLight(),&this->lightTextures);
		//this->lightTextures=listToInts(textures);

		mainUniforms=new int[3];
		SFGLSLSet* set=program;
       mainUniforms[0]=glGetUniformLocation(set->getProgram(),"projection");
		mainUniforms[1]=glGetUniformLocation(set->getProgram(),"modelview");
		mainUniforms[2]=glGetUniformLocation(set->getProgram(),"vectorsModelview");

	}

	GLuint* SFGL20UniformData::listToInts(vector<GLuint> list){
		GLuint* values=new GLuint[list.size()];
		for (unsigned int i = 0; i < list.size(); i++) {
			values[i]=list.at(i);
		}
		return values;
	}

	GLuint** SFGL20UniformData::evaluateStructureUniforms(SFProgramModule* module,vector<GLuint>* textures) {
		if(module!=0){
			vector<SFPipelineStructureInstance*> allStructures;
			for (unsigned int i = 0; i < module->getComponents().size(); i++) {
				SFProgramComponent* component=module->getComponents()[i];
				allStructures.insert(allStructures.end(),
                    component->getStructures().begin(),component->getStructures().end());
			}

			GLuint** uniforms=new GLuint*[allStructures.size()];

			for (int i = 0; i < allStructures.size(); i++) {
				SFPipelineStructureInstance* sfPipelineStructureInstance=allStructures.at(i);

                //int* getUniforms(string prefix,SFPipelineStructureInstance instance,vector<int>* textures);
                uniforms[i]=getUniforms(string(""), sfPipelineStructureInstance,textures);
			}
			return uniforms;
		}

		return new GLuint*[0];
	}


//	void evaluateUniforms(SFGL20GenericProgram* program) {
//		this->program=program;
//		//Primitive Uniforms
//
//		gridUniforms.clear();
//		vector<Integer> textures=new vector<Integer>();
//		materialStructureUniforms=evaluateStructureUniforms(program->getMaterials(),textures);
//		this->materialTextures=listToInts(textures);
//		textures.clear();
//		lightStructureUniforms=evaluateStructureUniforms(program->getLight(),textures);
//		this->lightTextures=listToInts(textures);
//	}


	void SFGL20UniformData::setTextureData(SFPipelineGraphics::Module module, int index, SFPipelineTexture* texture) {
		int level=index;
		vector<GLuint> textures=transformTextures;
		if(module==SFPipelineGraphics::LIGHT){
			level+=transformTextures.size()+materialTextures.size();
			textures=lightTextures;
		}else if(module==SFPipelineGraphics::MATERIAL){
			level+=transformTextures.size();
			textures=materialTextures;
		}

		texture->apply(level);
		//with its related uniform..
		glUniform1i(textures[index], level);
	}


    //	void evaluateTextureUniforms(SFGLSLSet program) {
    //		int index=0;
    //		while(index<8){
    //
    //			int textureLevel=glGetUniformLocation(program->getProgram(),"texture"+index);
    //			if(textureLevel>=0)
    //				glUniform1i(textureLevel, index);
    //
    //			index++;
    //		}
    //	}


	void SFGL20UniformData::setData(SFPipelineGraphics::Module module, int index, SFStructureData* data) {
		switch (module) {
			case SFPipelineGraphics::LIGHT:
                setData(lightStructureUniforms[index],lightStructureUniformsSize, *data);
				break;
			case SFPipelineGraphics::MATERIAL:
				setData(materialStructureUniforms[index],materialStructureUniformsSize, *data);
				break;
			case SFPipelineGraphics::TRANSFORM:
				setData(transformStructureUniforms[index],transformStructureUniformsSize, *data);
				break;
		}
	}


	void SFGL20UniformData::setData(GLuint* uniforms,int size,SFStructureData data){

		if(uniforms!=0 && size>0){
            int length=data.getValue(0)->getSize();
			for (int i=0; i < size; i++) {
				float* v=data.getValue(i)->getV();
				glUniform3fv(uniforms[i],length,v);
			}
		}
	}


	void SFGL20UniformData::sendVertex(SFValuenf value) {
		int uniformIndex=0;
		GLuint* uniform=gridUniforms.at(uniformIndex);
		switch (value.getSize()) {
			case 1:
				glUniform1f(uniform[0],value.getV()[0]);
                break;
			case 2:
				glUniform2f(uniform[0],value.getV()[0],value.getV()[1]);
				break;
			case 3:
				glUniform3f(uniform[0],value.getV()[0],value.getV()[1],value.getV()[2]);
				break;
			default:
				break;
		}
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setPrimitiveData(shadow.pipeline.SFPrimitiveIndices, shadow.pipeline.openGL20.SFGL20ListData, shadow.pipeline.openGL20.SFGL20ListData)
	 */

	void SFGL20UniformData::setIndexedData(SFPrimitiveIndices indices,
                               SFGL20ValuenfArray** datas,SFPrimitive* primitive) {

		int uniformIndex=0;

		int* idx=indices.getPrimitiveIndices();
		int* positions=primitive->getIndicesPositions();
		int* sizes=primitive->getIndicesSizes();

		for (int i = 0; i < primitive->getGridsCount(); i++) {

			int position=positions[i];
			int size=sizes[i];
			SFGL20ValuenfArray* data=datas[i];

			GLuint* uniform=gridUniforms.at(uniformIndex);
			short type=primitive->getType(i);
			switch (type) {
				case SFPipelineRegister::GLOBAL_FLOAT:
					for (int j=0; j < size; j++) {
						SFValuenf pv=*(data->getValue(idx[j+position]));
						glUniform1f(uniform[j],pv.getV()[0]);
					}
                    break;
				case SFPipelineRegister::GLOBAL_FLOAT3:
                    for (int j=0; j < size; j++) {
                        SFValuenf pv=*(data->getValue(idx[j+position]));
                        glUniform3f(uniform[j],pv.getV()[0],pv.getV()[1],pv.getV()[2]);
                    }
					break;
				case SFPipelineRegister::GLOBAL_FLOAT2:
					for (int j=0; j < size; j++) {
						SFValuenf pv=*(data->getValue(idx[j+position]));
						glUniform2f(uniform[j],pv.getV()[0],pv.getV()[1]);
					}
                    break;
				default:
					break;
			}
			uniformIndex++;
		}

	}


	void SFGL20UniformData::setTransformData(float transform[16]) {

		SFVertex3f v1(transform[0],transform[1],transform[2]);
		SFVertex3f v2(transform[3],transform[4],transform[5]);
		SFVertex3f v3(transform[6],transform[7],transform[8]);
		//TODO : work on this normalize!
        //		v1.normalize3f();
        //		v2.normalize3f();
        //		v3.normalize3f();

		float vModelview[]={//you know this is not going to work properly, but let's say it's right most of the times..
            v1.getX(),	v2.getX(),	v3.getX(),0,
            v1.getY(),	v2.getY(),	v3.getY(),0,
            v1.getZ(),	v2.getZ(),	v3.getZ(),0,
            0,0,0,1
		};
		float modelview[]={//you know this is not going to work properly, but let's say it's right most of the times..
            transform[0],	transform[3],	transform[6],0,
            transform[1],	transform[4],	transform[7],0,
            transform[2],	transform[5],	transform[8],0,
            transform[9],	transform[10],	transform[11],1
		};

		//All transforms are identity no more..
//        GL_API void         GL_APIENTRY glUniformMatrix4fv (GLint location, GLsizei count, GLboolean transpose, const GLfloat* value)  __OSX_AVAILABLE_STARTING(__MAC_NA,__IPHONE_3_0);

		glUniformMatrix4fv(mainUniforms[1],(GLsizei)1,GL_FALSE,modelview);
		glUniformMatrix4fv(mainUniforms[2],(GLsizei)1,GL_FALSE,vModelview);
	}

	void SFGL20UniformData::setupProjetion(float projection[]){
		glUniformMatrix4fv(mainUniforms[0],(GLsizei)1,GL_FALSE,projection);
	}

	}
