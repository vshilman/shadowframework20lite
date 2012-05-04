package shadow.pipeline;

import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.parameters.SFParameteri;

public class SFPipelineGridInstance {

	private SFPipelineGrid grid = new SFPipelineGrid();
	private LinkedList<SFParameteri> params = new LinkedList<SFParameteri>();

	public SFPipelineGridInstance(SFPipelineGrid grid,
			List<SFParameteri> params) {
		super();
		this.grid = grid;
		this.params.addAll(params);
	}

	public SFPipelineGrid getGrid() {
		return grid;
	}

	public LinkedList<SFParameteri> getParameters() {
		return params;
	}
	
	public int size(){
		return params.size();
	}

}

