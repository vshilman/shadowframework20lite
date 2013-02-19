package shadow.renderer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;

public class SFModel  {

	private SFProgramModuleStructures transform=new SFProgramModuleStructures();

	private SFProgramModuleStructures material=new SFProgramModuleStructures();
	
	private Map<String, SFProgram> programs = Collections.synchronizedMap(new HashMap<String, SFProgram>());
	
	private SFGeometry rootGeometry=null;
	
	public SFGeometry getRootGeometry() {
		return rootGeometry;
	}

	public SFProgramModuleStructures getMaterialComponent() {
		return material;
	}

	public synchronized void setMaterialComponent(SFProgramModuleStructures material) {

		destroyPrograms();
		
		this.material = material;
	}
	
	public synchronized void setRootGeometry(SFGeometry rootGeometry) {
		
		destroyPrograms();
		
		this.rootGeometry = rootGeometry;
	}

	private synchronized void destroyPrograms() {
		for (String lightName : programs.keySet()) {
			SFProgram program=programs.get(lightName);
			//SFInitiator.addDestroyable(program);
			//program.destroy();
		}
		
		programs.clear();
	}
	
	public synchronized void setTransformComponent(SFProgramModuleStructures transformComponent) {
		
		destroyPrograms();
		
		this.transform = transformComponent;
	}

	public SFProgramModuleStructures getTransformComponent() {
		return transform;
	}

	public synchronized SFProgram evaluateProgram(SFProgramModuleStructures light){
		System.err.println("Time:" + System.currentTimeMillis() + " evaluateProgram()"+ getRootGeometry().getPrimitive().getName());
		SFProgram program=SFPipeline.getStaticProgram(getRootGeometry().getPrimitive(),
				transform.getProgram(),material.getProgram(),light.getProgram());
		programs.put(light.getProgram(),program);
		
		return program;
	}

	public synchronized void cleanPrograms() {
		programs.clear();
	}

	public synchronized SFProgram getProgram(SFProgramModuleStructures light){
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
