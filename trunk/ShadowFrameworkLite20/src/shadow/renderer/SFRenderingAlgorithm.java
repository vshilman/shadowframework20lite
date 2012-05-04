package shadow.renderer;

import java.util.ArrayList;
import java.util.List;

import shadow.material.SFLightStep;
import shadow.system.SFInitiable;

public class SFRenderingAlgorithm implements SFInitiable{

	private List<SFLightStep> steps=new ArrayList<SFLightStep>();
	private List<SFLodFilter> filters=new ArrayList<SFLodFilter>();

	public SFRenderingAlgorithm(List<SFLightStep> steps) {
		super();
		this.steps.addAll(steps);
	}
	
	@Override
	public void init() {
		
	}

	public SFRenderingAlgorithm(List<SFLightStep> steps,
			List<SFLodFilter> filters) {
		super();
		this.steps.addAll(steps);
		this.filters.addAll(filters);
	}

	public List<SFLightStep> getSteps() {
		return steps;
	}

	public List<SFLodFilter> getFilters() {
		return filters;
	} 

}
