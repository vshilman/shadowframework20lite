package shadow.pipeline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import shadow.pipeline.loader.parser.SFPipelineGridInstance;
import shadow.pipeline.loader.parser.SFPipelineStructureInstance;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFProgramComponent extends SFPipelineElement {
	

	private String name;
	private LinkedList<SFFunction> code=new LinkedList<SFFunction>();
	private LinkedList<SFPipelineRegister> registers=new LinkedList<SFPipelineRegister>();
	private LinkedList<SFParameteri> temps=new LinkedList<SFParameteri>();
	
	private LinkedList<SFPipelineStructureInstance> structures=new LinkedList<SFPipelineStructureInstance>();
	private LinkedList<SFPipelineGridInstance> grids=new LinkedList<SFPipelineGridInstance>();
	
	private Collection<SFParameteri> set=null;
	
	public void addRegister(SFPipelineRegister global){
		if(set!=null)
			set.add(global);
		registers.add(global);
	}
	
	public void addCodeString(SFFunction function){
		code.add(function);
	}
	
	public void addGridInstance(SFPipelineGridInstance grid){
		grids.add(grid);
	}

	public void addStructureInstance(SFPipelineStructureInstance structure){
		structures.add(structure);
	}
	
	public void addParameter(SFParameter parameter){
		if(set!=null)
			set.add(parameter);
		temps.add(parameter);
	}
	
	public Collection<SFPipelineStructureInstance> getStructures() {
		return structures;
	}
	
	public Collection<SFPipelineGridInstance> getGrids() {
		return grids;
	}


	public void loadShaderParameters(Collection<SFParameteri> set){
		set.addAll(registers);
	}

	public Collection<SFPipelineRegister> getRegisters(){
		return registers;
	}

	public Collection<SFFunction> getShaderCodeLines(){
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<SFParameteri> getParameterSet(){
		if(set==null){
			set=new LinkedList<SFParameteri>();
			set.addAll(registers);
			set.addAll(temps);
			
			for (Iterator<SFPipelineStructureInstance> iterator = structures.iterator(); iterator.hasNext();) {
				set.addAll(((SFPipelineStructureInstance) iterator.next()).getParameters());
			}
			
			for (Iterator<SFPipelineGridInstance> iterator = grids.iterator(); iterator.hasNext();) {
				set.addAll(((SFPipelineGridInstance) iterator.next()).getParameters());
			}
		}
		return set;
	}
}
