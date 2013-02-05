package shadow.renderer;

import java.util.HashMap;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.system.SFInitiable;
import shadow.system.SFInitiator;

public class SFModel  {

	private SFProgramModuleStructures transform=new SFProgramModuleStructures();

	private SFProgramModuleStructures material=new SFProgramModuleStructures();
	
	private HashMap<String, SFProgram> programs = new HashMap<String, SFProgram>();
	
	private SFGeometry rootGeometry=null;
	
	public SFGeometry getRootGeometry() {
		return rootGeometry;
	}

	public SFProgramModuleStructures getMaterialComponent() {
		return material;
	}

	public void setMaterialComponent(SFProgramModuleStructures material) {

		destroyPrograms();
		
		this.material = material;
	}
	
	public void setRootGeometry(SFGeometry rootGeometry) {
		
		destroyPrograms();
		
		this.rootGeometry = rootGeometry;
	}

	private void destroyPrograms() {
		for (String lightName : programs.keySet()) {
			SFProgram program=programs.get(lightName);
			SFInitiator.addDestroyable(program);
		}
		
		programs.clear();
	}
	
	public void setTransformComponent(SFProgramModuleStructures transformComponent) {
		
		destroyPrograms();
		
		this.transform = transformComponent;
	}

	public SFProgramModuleStructures getTransformComponent() {
		return transform;
	}

	public SFProgram evaluateProgram(SFProgramModuleStructures light){
		SFProgram program=SFPipeline.getStaticProgram(getRootGeometry().getPrimitive(),
				transform.getProgram(),material.getProgram(),light.getProgram());
		programs.put(light.getProgram(),program);
		
		return program;
	}

	public void cleanPrograms() {
		programs.clear();
	}

	public SFProgram getProgram(SFProgramModuleStructures light){
		SFProgram program=programs.get(light.getProgram());
		if(program!=null){
			return program;
		}
		return evaluateProgram(light);
	}
	
	public SFModel clone(){
		SFModel model=new SFModel();
		model.rootGeometry=rootGeometry;
		model.transform=transform.clone();
		model.setMaterialComponent(getMaterialComponent().clone());
		return model;
	}
}
