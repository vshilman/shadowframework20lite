package sfogl.integration;

import java.util.HashMap;

public class Pipeline {

	private static Pipeline pipeline=new Pipeline();
	
	private Pipeline(){
		
	}
	
	public static Pipeline getPipeline() {
		return pipeline;
	}
	
	private HashMap<String, ShadingProgram> programs=new HashMap<String, ShadingProgram>();
	
	public void put(String name,ShadingProgram program){
		programs.put(name, program);
	}
	
	public ShadingProgram get(String name){
		return programs.get(name);
	}
}
