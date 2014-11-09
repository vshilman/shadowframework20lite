//
//  SFGL20AbstractProgram.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20AbstractProgram__
#define SFGL20AbstractProgram__

#include "SFGLSLSet.h"
#include "SFGL20GenericProgram.h"
#include "SFGL20UniformData.h"
#include "SFProgramDataModel.h"
#include "SFPrimitiveProgramAssociation.h"
#include "SFGlobalVSetGL20Implementor.h"
#include "SFGL20ExpressionGenerator.h"
#include <string>
#include <vector>
#include <sstream>

using namespace std;

namespace sf{
class SFGL20AbstractProgram : public SFGLSLSet,public SFGL20GenericProgram,
public SFProgramDataModel{
    
public:
	vector<SFPrimitiveProgramAssociation> fragmentShader;
	SFProgramModule* materials;
	SFProgramModule* light;
	bool registeredUniforms;

	SFGL20UniformData data;

	SFGL20AbstractProgram(){
		registeredUniforms=false;
		materials=0;
		light=0;
	}
    
    
	void clearFragmentShader() {
		fragmentShader.clear();
	}
    
	void addToFragmentShader(SFProgramComponent* component,
                                    SFPipelineRegister* reg) {
		fragmentShader.push_back(SFPrimitiveProgramAssociation(reg,component));
	}
    
	string loadFragmentShaderText() {
		return getShaderText(fragmentShader,false);
	}
	
    
	void write() {
		//System.err.println("Fragment Program");
		for (unsigned int i=0; i < fragmentShader.size(); i++) {
			//System.err
            //.println("\t\tfragmentShader(i) " + fragmentShader.get(i));
		}
	}
    
	string getShaderText(vector<SFPrimitiveProgramAssociation> list,bool vertex) {
		return getShaderText(this,list,vertex);
	}
	
	void addToFragmentShader(SFProgramModule* module){
		for (unsigned int i = 0; i < module->getComponents().size(); i++) {
			addToFragmentShader(module->getComponents()[i],0);
		}
	}
	
	

	void setLightStep(SFProgramModule* lightStep){//}
	//void setLightStep(SFProgramModule* lightStep) {
		addToFragmentShader(lightStep);
		this->light=lightStep;
	}
	
	void setMaterial(SFProgramModule* material){
	//}
	//void setMaterial(SFProgramModule* material) {
		addToFragmentShader(material);
		this->materials=material;
	}
    
    
	/*string tostring() {
		string vShader=loadVertexShaderText();
		string fShader=loadFragmentShaderText();
		string tostring="---------\nvShader\n---------:\n" + vShader
        + "---------\nfShader\n---------:\n" + fShader;
		return tostring;
	}*/
	
    
	static string getRegisterName(vector<SFPrimitiveProgramAssociation> list, int i) {
		SFPipelineRegister* reg=list.at(i).getRegister();
		string suffix="";
		if (reg != 0)
			suffix=reg->getName();
		return suffix;
	}
    
	static SFPipelineRegister* getRegister(vector<SFPrimitiveProgramAssociation> list, unsigned int i) {
		SFPipelineRegister* reg=list.at(i).getRegister();
		
		return reg;
	}
	
    
	
	void load() {
        
        //	this->checkComponent();
        
        SFGLSLSet::init();
		this->apply();
        
		if (!registeredUniforms) {
			getData()->evaluateUniforms(this);
			registeredUniforms=true;
		}
	}
    
	
	void setTextureData(SFPipelineGraphics::Module module, int index, SFPipelineTexture* texture) {
		this->data.setTextureData(module,index,texture);
	}
	
	
	void setData(SFPipelineGraphics::Module module, int index, SFStructureData* data) {
		this->data.setData(module,index, data);
	}
	
	
	void setIndexedData(SFPrimitiveIndices indices,
                               SFGL20ValuenfArray** datas,SFPrimitive* primitive) {
		this->getData()->setIndexedData(indices,datas,primitive);
	}
    
	
	void sendVertex(SFValuenf value) {
		this->getData()->sendVertex(value);
	}
    
	
	void setTransformData(float transform[16]) {
		this->getData()->setTransformData(transform);
	}
	
	void setupProjection(float projection[]){
		this->getData()->setupProjetion(projection);
	}
    
    
	static string generateShaderParameters(vector<SFParameter*> shaderParametersSet) {
		return SFGlobalVSetGL20Implementor::generateShaderParameters(shaderParametersSet);
	}
    
	static string translateExpression(SFExpressionElement expression,
                                             SFParameteri* parameter,SFParameteri* parameterExpression, vector<SFParameteri*> functionParameters) {
        
        SFGL20ExpressionGenerator::setRefParameter(parameter);
        
		map<string, string> renameMap;
        
		//System.err.println("parameter "+parameter.getName());
		//System.err.println("Is Parameter WroteByTransfom??"+(((SFPipelineRegister)parameter).getType()==SFPipelineRegister.WROTE_BY_TRANSFORM));
        
		if (dynamic_cast<SFPipelineRegister*>(parameterExpression)
            && ((SFPipelineRegister*) parameterExpression)->getUse() == SFPipelineRegister::WROTE_BY_PRIMITIVE) {
            
            for(unsigned int i=0;i<functionParameters.size();i++){
                SFParameteri* sfParameteri=functionParameters.at(i);
				if(!SFGL20GlobalV::requiresDeclaration(sfParameteri)){
					renameMap[sfParameteri->getName()]=parameterExpression->getName()
                                  + sfParameteri->getName();
				}
			}
		}
        SFGL20ExpressionGenerator::setRenameMap(renameMap);
        
		//try {
        expression.traverse(SFGL20ExpressionGenerator::getGenerator(parameter));
		//} catch (Exception e) {
		//	System.err.println(parameter);
		//	e.printStackTrace();
		//}
        
		string expr=SFGL20ExpressionGenerator::getFunctionstring();
        
		return expr;
	}
    
	static string getShaderText(SFGL20AbstractProgram* program,
                                       vector<SFPrimitiveProgramAssociation> list,bool vertex) {
		ostringstream writer;
        
		vector<SFPipelineRegister*> alreadySetRegisters;
		vector<string> alreadyDeclaredElements;
		vector<SFParameter> alreadyMappedParameters;
		for (unsigned int i=0; i < list.size(); i++) {
            
			SFProgramComponent* programComponent=list.at(i).getProgram();
			vector<SFPipelineRegister*> reg=programComponent->getRegisters();
            
			vector<SFParameter*> regSet;
            for(unsigned int k=0;k<reg.size();k++){
                SFPipelineRegister* r=reg.at(k);
                for(unsigned int j=0;j<alreadySetRegisters.size();j++){
                	if(alreadySetRegisters[j]==r){
                		regSet.push_back(r);
                		alreadySetRegisters.push_back(r);
                		j=alreadySetRegisters.size();
                	}
                }
			}
            
            writer << SFGL20AbstractProgram::generateShaderParameters(regSet);
            
			SFParameter* global=getRegister(list,i);
			string globalName="";
			if (global != 0)
				globalName=global->getName();
			vector<SFPipelineStructureInstance*> instances=programComponent->getStructures();
			
            for(unsigned int i=0;i<instances.size();i++){
                SFPipelineStructureInstance* instance=instances.at(i);
				writer << SFGlobalVSetGL20Implementor::generateInstancedStructures(instance,getRegister(list,i),globalName,alreadyMappedParameters);
			}
            
			vector<SFPipelineGrid*> gridInstance=programComponent->getGrid();
			
            for(unsigned int i=0;i<gridInstance.size();i++){
                SFPipelineGrid* sfPipelineGridInstance=gridInstance.at(i);
				short type=sfPipelineGridInstance->getParameterType(0);
				if(type==SFParameteri::GLOBAL_GENERIC)
					type=getRegister(list, i)->getType();//TODO
				writer << SFGlobalVSetGL20Implementor
                    ::generateInstancedGrids(*sfPipelineGridInstance,
                                                     type,globalName);
			}
            
		}
        
		writer << ("\n");
        
		writer << ("\n\nvoid main(void){\n");
		bool doneTessellation=false;
		for (unsigned int i=0; i < list.size(); i++) {
			// System.err.println(i+" "+list.get(i)+" list.get(i).getShaderCode() "
			// + list.get(i).getShaderCode());
            
			SFProgramComponent* programComponent=list.at(i).getProgram();
			string writeSuffix=getRegisterName(list,i);
            
			vector<SFPipelineRegister*> params=programComponent->getRegisters();
            for(unsigned int i=0;i<params.size();i++){
                SFPipelineRegister* param=params[i];
				if ( dynamic_cast<SFPipelineRegister*>(param) && !doneTessellation) {
					SFPipelineRegister* reg=(SFPipelineRegister*) param;
					if (reg->getName().compare("uvw")==0) {
						writer << ("\tfloat u=gl_Vertex.x;\n");
						writer << ("\tfloat v=gl_Vertex.y;\n");
						writer << ("\tfloat w=1.0-gl_Vertex.x-gl_Vertex.y;\n");
					}
					if (reg->getName().compare("uv")==0) {
						writer << ("\tfloat u=gl_Vertex.x;\n");
						writer << ("\tfloat v=gl_Vertex.y;\n");
					}
					doneTessellation=true;
				}
			}
            
            
			vector<SFFunction*> lines=programComponent->getShaderCodeLines();
            for(unsigned int i=0;i<lines.size();i++){
                SFFunction* sfFunction=lines.at(i);
				
				SFPipelineRegister* reg=getRegister(list,i);
				
                SFParameteri* global=(sfFunction->getParameter());
				if(global->getName().compare("<>")==0)
					global=reg;
				SFExpressionElement* function=sfFunction->getFunction();
				if (function->getType() != SFParameteri::GLOBAL_GENERIC) {
					string declaration="";
					string name=global->getName();
					if(reg!=0 && global!=reg){
						name=reg->getName()+name;
					}
					if (SFGL20GlobalV::declaredOnWrite(global)) {
						for(unsigned int k=0;k<alreadyDeclaredElements.size();k++){
							if(alreadyDeclaredElements[i].compare(name)==0){
								string def=SFGlobalVSetGL20Implementor::getDeclarationstring(global->getType());
								declaration=def + " ";
								alreadyDeclaredElements.push_back(name);
								k=alreadyDeclaredElements.size();
							}
						}
					}
					global=getRegister(list,i);// sfFunction.getParameter();
					if (global == 0){
                        global=(sfFunction->getParameter());
                    }
						
					
					if(sfFunction->getParameter()->getType()!=SFParameteri::GLOBAL_GENERIC){
						writer << "\t";
                                     writer << declaration+"";
                        writer << name;
                                     writer << "=";
                                     writer << SFGL20AbstractProgram::translateExpression(*function,sfFunction->getParameter(),global,programComponent->getParameterSet());

//
//                                 	static string translateExpression(SFExpressionElement expression,
//                                                                              SFParameter parameter,SFParameteri* parameterExpression, vector<SFParameter*> functionParameters) {
                                     writer <<  ";\n";
					}else{
						writer << "\t";
                        writer <<  declaration;
                        writer <<  name;
                        writer <<  "=";
                        writer << SFGL20AbstractProgram::translateExpression(*function,
                                                                             global,global,programComponent->getParameterSet());
                                     writer <<  ";\n";
					}
				} else {
                    
					string type="vec3";
					if (reg->getType() == SFParameteri::GLOBAL_FLOAT2) {
						type="vec2";
					}
					
					if(sfFunction->getParameter()->getType()!=SFParameteri::GLOBAL_GENERIC){
                        global=(sfFunction->getParameter());
					}
                    
					writer << "\t";
                                 writer << type;
                                 writer << " ";
                                 writer << writeSuffix;
                                 writer << "=";
                                 writer << SFGL20AbstractProgram::translateExpression(*function,
                                                                             global,global,programComponent->getParameterSet());
                                 writer << ";\n";
				}
			}
            
		}
		
		if(vertex){
			writer << "\n\tgl_Position=vec4(position,1);";//this is a big trouble i think
		}
		
        
        for(unsigned int i=0;i<list.size();i++){
            
            SFPrimitiveProgramAssociation prAssoc=list.at(i);
            vector<SFPipelineRegister*> regs=prAssoc.getProgram()->getRegisters();
            for(unsigned int k=0;k<regs.size();k++){
				if(regs[i]==SFPipelineRegister::getFromName("fColor")){
					writer << "\n\tgl_FragColor=fColor;";//this is a big trouble i think
				}
			}
			for (unsigned int j = 0; j < 4; j++) {
				for(unsigned int k=0;k<regs.size();k++){
					if(regs[i]==SFPipelineRegister::getFromName("fColor"+i)){
						writer <<"\n\tgl_FragData["<<i<<"]=fColor"<<i<<";";//this is a big trouble i think
					}
				}
			}
		}
		
		
		
		//writer.write("\n\t fragmen");//this is a big trouble i think
		writer << "\n}";
        
		return writer.str();
	}
	
	
	
	SFProgramModule* getMaterials() {
		return materials;
	}
    
	SFProgramModule* getLight() {
		return light;
	}
    
	SFProgramModule* getTransforms(){
		return 0;
	}
	
	SFPrimitive* getPrimitive(){
		return 0;
	}
    
	void setData(SFGL20UniformData data) {
		this->data=data;
	}
    
    SFGL20UniformData* getData() {
		return &data;
	}
};
}

#endif /* defined(SFGL20AbstractProgram__) */
