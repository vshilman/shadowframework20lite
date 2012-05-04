package shadow.renderer.data;

import java.util.LinkedList;
import java.util.List;

import shadow.material.SFLightStep;
import shadow.material.SFNoDataLightStep;
import shadow.material.SFPassAllLodFilter;
import shadow.renderer.SFLodFilter;
import shadow.renderer.SFRenderingAlgorithm;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFString;

public class SFOneStepAlgorithmData extends SFDataAsset<SFRenderingAlgorithm>{

	private SFString data=new SFString("");

	public SFOneStepAlgorithmData() {
		setData(data);
	}
	
	public SFOneStepAlgorithmData(String program) {
		setData(data);
		this.data.setString(program);
	}
	

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFOneStepAlgorithmData();
	}
	
	@Override
	protected SFRenderingAlgorithm buildResource() {
		List<SFLightStep> steps=new LinkedList<SFLightStep>();
		steps.add(new SFNoDataLightStep(data.getString()));
		List<SFLodFilter> filters=new LinkedList<SFLodFilter>();
		filters.add(new SFPassAllLodFilter());
		SFRenderingAlgorithm algorithm=new SFRenderingAlgorithm(steps,filters);
		return algorithm;
	}
	
	public void setProgram(String program){
		this.data.setString(program);
	}
}
