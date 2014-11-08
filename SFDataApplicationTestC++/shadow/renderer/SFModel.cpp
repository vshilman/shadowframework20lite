
#include "SFModel.h"

namespace sf{

  	SFGeometry* SFModel::getRootGeometry() {
  		return rootGeometry;
	}

  	SFProgramModuleStructures SFModel::getMaterialComponent() {
  		return material;
	}

  	void SFModel::setMaterialComponent(SFProgramModuleStructures* material) {
  		destroyPrograms();
		this->material = *material;
	}

  	void SFModel::setRootGeometry(SFGeometry* rootGeometry) {
  		destroyPrograms();
		this->rootGeometry = rootGeometry;
  		resource.setResource(0, rootGeometry->getResource());
	}

  	void SFModel::setTransformComponent(SFProgramModuleStructures transformComponent) {
  		destroyPrograms();
		this->transform = transformComponent;
	}

  	SFProgramModuleStructures SFModel::getTransformComponent() {
  		return transform;
	}

  	void SFModel::destroyPrograms() {
//  		for(unsigned int i=0;i<programs.size();i++){
//  			pair<string, SFProgram*> pair=programs[i];
//  			//Nothing to do atm
//  		}

  		programs.clear();
	}

  	SFProgram* SFModel::evaluateProgram(SFProgramModuleStructures* light){
  		SFProgram* program=SFPipeline::getStaticProgram(getRootGeometry()->getPrimitive(),
  				transform.getProgram(),material.getProgram(),light->getProgram());
  		programs[light->getProgram()]=program;

  		return program;
	}

  	void SFModel::cleanPrograms() {
  		programs.clear();
	}

  	SFProgram* SFModel::getProgram(SFProgramModuleStructures* light){
  		//System.err.println("Getting light Program "+light.getProgram());

  		map<string, SFProgram*>::iterator program=programs.find(light->getProgram());
  		//SFProgram* program=programs.at(light->getProgram());
  		if(program!=programs.end()){
  			return program->second;
		}
  		return evaluateProgram(light);
	}

	
  	void SFModel::update() {
  		cleanPrograms();
	}

  	SFModel* SFModel::clone(){
  		SFModel* model=new SFModel();
  		model->rootGeometry=rootGeometry;
  		SFProgramModuleStructures* structures=transform.clone();
  		model->transform=*structures;
  		delete structures;
  		model->setMaterialComponent(getMaterialComponent().clone());
  		return model;
	}

}
