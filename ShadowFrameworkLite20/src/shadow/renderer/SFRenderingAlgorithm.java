package shadow.renderer;

import java.util.ArrayList;
import java.util.List;

import shadow.material.SFLightStep;

public class SFRenderingAlgorithm {

	private List<SFLightStep> steps=new ArrayList<SFLightStep>();
	private List<SFLodFilter> filters=new ArrayList<SFLodFilter>();

	public SFRenderingAlgorithm(List<SFLightStep> steps) {
		super();
		this.steps.addAll(steps);
		System.out.println("(Debug) GLRenderingAlgorithm \t\t : \t\t steps "+steps.size());
	}

	public SFRenderingAlgorithm(List<SFLightStep> steps,
			List<SFLodFilter> filters) {
		super();
		this.steps.addAll(steps);
		this.filters.addAll(filters);
	}

	public List<SFLightStep> getSteps() {
		System.out.println("(Debug) GLRenderingAlgorithm \t\t : \t\t steps "+steps.size());
		return steps;
	}

	public List<SFLodFilter> getFilters() {
		return filters;
	} 

}
