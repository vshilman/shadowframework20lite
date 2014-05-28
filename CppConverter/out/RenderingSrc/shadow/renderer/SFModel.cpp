#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "java/util/HashMap.h"

#include "shadow/geometry/SFGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFProgram.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/SFUpdatable.h"

class SFModel implements SFUpdatable {

//	SFProgramModuleStructures transform=new SFProgramModuleStructures();

//	SFProgramModuleStructures material=new SFProgramModuleStructures();

//	HashMap<String, SFProgram> programs = new HashMap<String, SFProgram>();

//	SFGeometry rootGeometry=null;

//	SFResource resource=new SFResource(1,this);


//	SFGeometry getRootGeometry() {
//		return rootGeometry;
	}

//	SFProgramModuleStructures getMaterialComponent() {
//		return material;
	}

//	void setMaterialComponent(SFProgramModuleStructures material) {
//		destroyPrograms();
		this->material = material;
	}

//	void setRootGeometry(SFGeometry rootGeometry) {
//		destroyPrograms();
		this->rootGeometry = rootGeometry;
//		resource.setResource(0, rootGeometry.getResource());
	}

//	void setTransformComponent(SFProgramModuleStructures transformComponent) {
//		destroyPrograms();
		this->transform = transformComponent;
	}

//	SFProgramModuleStructures getTransformComponent() {
//		return transform;
	}

//	synchronized void destroyPrograms() {
//		for (String lightName : programs.keySet()) {
//			SFProgram program=programs.get(lightName);
//			//TODO : yes destroy programs please :)
//			//SFInitiator.addDestroyable(program);
		}

//		programs.clear();
	}

//	SFProgram evaluateProgram(SFProgramModuleStructures light){
//		SFProgram program=SFPipeline.getStaticProgram(getRootGeometry().getPrimitive(),
//				transform.getProgram(),material.getProgram(),light.getProgram());
//		programs.put(light.getProgram(),program);

//		return program;
	}

//	void cleanPrograms() {
//		programs.clear();
	}

//	SFProgram getProgram(SFProgramModuleStructures light){
//		//System.err.println("Getting light Program "+light.getProgram());
//		SFProgram program=programs.get(light.getProgram());
//		if(program!=null){
//			return program;
		}
//		return evaluateProgram(light);
	}

	
//	void update() {
//		cleanPrograms();
	}

//	SFModel clone(){
//		SFModel model=new SFModel();
//		model.rootGeometry=rootGeometry;
//		model.transform=transform.clone();
//		model.setMaterialComponent(getMaterialComponent().clone());
//		return model;
	}
}
;
}
#endif
