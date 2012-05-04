package shadow.pipeline;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFProgramComponent extends SFPipelineElement {
	
	private String name;
	private LinkedList<SFFunction> code=new LinkedList<SFFunction>();
	private LinkedList<SFPipelineRegister> registers=new LinkedList<SFPipelineRegister>();
	private LinkedList<SFParameteri> temps=new LinkedList<SFParameteri>();
	
	private LinkedList<SFPipelineStructureInstance> structures=new LinkedList<SFPipelineStructureInstance>();
	private LinkedList<SFPipelineGridInstance> grid=new LinkedList<SFPipelineGridInstance>();
	
	private List<SFParameteri> set=null;
	
	public void addRegister(SFPipelineRegister global){
		if(set!=null)
			set.add(global);
		registers.add(global);
	}
	
	public void addCodeFunction(SFFunction function){
		code.add(function);
	}
	
	public void addGridInstance(SFPipelineGridInstance grid){
		this.grid.add(grid);
	}

	public void addStructureInstance(SFPipelineStructureInstance structure){
		structures.add(structure);
	}
	
	public void addParameter(SFParameter parameter){
		if(set!=null)
			set.add(parameter);
		temps.add(parameter);
	}
	
	public List<SFPipelineStructureInstance> getStructures() {
		return structures;
	}
	
	public List<SFPipelineGridInstance> getGrid() {
		return grid;
	}


	public void loadShaderParameters(List<SFParameteri> set){
		set.addAll(registers);
	}

	public List<SFPipelineRegister> getRegisters(){
		return registers;
	}

	public List<SFFunction> getShaderCodeLines(){
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public synchronized List<SFParameteri> getParameterSet(){
		if(set==null){
			set=new LinkedList<SFParameteri>();
			set.addAll(registers);
			
			for (Iterator<SFPipelineStructureInstance> iterator = structures.iterator(); iterator.hasNext();) {
				set.addAll(((SFPipelineStructureInstance) iterator.next()).getParameters());
			}
			
			for (SFPipelineGridInstance grid : this.grid) {
				set.addAll(((SFPipelineGridInstance) grid).getParameters());
			}
				
		}
		if(temps.size()!=0){
			set.addAll(temps);
			temps.clear();
		}
		
		return set;
	}
}
