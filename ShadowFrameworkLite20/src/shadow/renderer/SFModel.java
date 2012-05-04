package shadow.renderer;


import java.util.HashMap;

import shadow.geometry.SFGeometry;
import shadow.material.SFLightStep;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;


public class SFModel extends SFRenderable {

	private HashMap<SFLightStep, SFProgram> programs = new HashMap<SFLightStep, SFProgram>();
	
	//private ArrayList<SFProgram> programs = new ArrayList<SFProgram>();
	private SFGeometry rootGeometry=null;
	
	public SFGeometry getRootGeometry() {
		return rootGeometry;
	}

	public void setRootGeometry(SFGeometry rootGeometry) {
		this.rootGeometry = rootGeometry;
	}
	
	public SFProgram evaluateProgram(SFLightStep step){
		
		SFProgram program=SFPipeline.getStaticProgram(getRootGeometry().getPrimitive()
				,getMaterialsNames(),step.getProgramName());
		programs.put(step,program);
		
		return program;
	}

	public void cleanPrograms() {
		programs.clear();
	}

	public SFProgram getProgram(SFLightStep step){
		SFProgram program=programs.get(step);
		if(program!=null){
			return program;
		}
		return evaluateProgram(step);
	}
	
	public SFModel clone(){
		SFModel model=new SFModel();
		model.rootGeometry=rootGeometry;
		model.getMaterialsComponents().addAll(getMaterialsComponents());
		model.getMaterialsStructures().addAll(getMaterialsStructures());
		model.getTextures().addAll(getTextures());
		return model;
	}
}
