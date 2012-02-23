package shadow.pipeline;

import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.parameters.SFParameteri;

public class SFPipelineStructureInstance {

	private SFPipelineStructure structure;
	private LinkedList<SFParameteri> parameters=new LinkedList<SFParameteri>();
	
	public SFPipelineStructureInstance(SFPipelineStructure structure,
			List<SFParameteri> parameters) {
		super();
		this.structure = structure;
		this.parameters.addAll(parameters);
	}
	
	public SFPipelineStructureInstance(SFPipelineStructure structure) {
		super();
		this.structure = structure;
		this.parameters.addAll(structure.getAllParameters());
	}

	public SFPipelineStructure getStructure() {
		return structure;
	}

	public List<SFParameteri> getParameters() {
		return parameters;
	}
	
	public List<SFParameteri> getAllParameters(){
		return parameters;
	}
	
	public int size(){
		return structure.size();
	}
}
