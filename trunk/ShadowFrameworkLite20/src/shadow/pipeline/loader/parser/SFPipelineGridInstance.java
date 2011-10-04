package shadow.pipeline.loader.parser;

import java.util.Collection;
import java.util.LinkedList;

import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.parameters.SFParameteri;

public class SFPipelineGridInstance {

	private SFPipelineGrid grid = new SFPipelineGrid();
	private LinkedList<SFParameteri> params = new LinkedList<SFParameteri>();

	public SFPipelineGridInstance(SFPipelineGrid grid,
			Collection<SFParameteri> params) {
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
}
