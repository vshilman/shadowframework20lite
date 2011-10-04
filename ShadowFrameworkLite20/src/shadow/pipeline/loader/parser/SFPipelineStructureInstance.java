package shadow.pipeline.loader.parser;

import java.util.Collection;
import java.util.LinkedList;

import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.parameters.SFParameteri;

public class SFPipelineStructureInstance {

	private SFPipelineStructure structure;
	private LinkedList<SFParameteri> parameters=new LinkedList<SFParameteri>();
	
	public SFPipelineStructureInstance(SFPipelineStructure strucutre,
			Collection<SFParameteri> parameters) {
		super();
		this.structure = strucutre;
		this.parameters.addAll(parameters);
	}

	public SFPipelineStructure getStructure() {
		return structure;
	}

	public Collection<SFParameteri> getParameters() {
		return parameters;
	}
	
	
}
