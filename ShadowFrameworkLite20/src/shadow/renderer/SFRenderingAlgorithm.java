package shadow.renderer;

import java.util.ArrayList;
import java.util.Collection;

import shadow.material.SFLightStep;

public class SFRenderingAlgorithm {

	private Collection<SFLightStep> steps=new ArrayList<SFLightStep>();
	private Collection<SFLodFilter> filters=new ArrayList<SFLodFilter>();

	public SFRenderingAlgorithm(Collection<SFLightStep> steps) {
		super();
		this.steps.addAll(steps);
		System.out.println("(Debug) GLRenderingAlgorithm \t\t : \t\t steps "+steps.size());
	}

	public SFRenderingAlgorithm(Collection<SFLightStep> steps,
			Collection<SFLodFilter> filters) {
		super();
		this.steps.addAll(steps);
		this.filters.addAll(filters);
	}

	public Collection<SFLightStep> getSteps() {
		System.out.println("(Debug) GLRenderingAlgorithm \t\t : \t\t steps "+steps.size());
		return steps;
	}

	public Collection<SFLodFilter> getFilters() {
		return filters;
	} 

}
