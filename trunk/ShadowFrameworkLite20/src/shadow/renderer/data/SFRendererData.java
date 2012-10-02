package shadow.renderer.data;

import shadow.renderer.SFCamera;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFRenderer;
import shadow.system.data.SFNamedParametersObject;

public class SFRendererData extends SFDataAsset<SFRenderer>{

	private SFDataAssetObject<SFCamera> camera = new SFDataAssetObject<SFCamera>(null);
	protected SFDataAssetObject<SFProgramModuleStructures> light=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());
	
	public SFRendererData() {
		prepare();
	}
	
	public SFRendererData(String lightProgramName,SFDataAsset<SFCamera> camera) {
		prepare();
		this.camera.setDataset(camera);
		((SFProgramAssetData)this.light.getDataset()).setProgram(lightProgramName);
	}
	

	private void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("camera", camera);
		parameters.addObject("light", light);
		setData(parameters);
	}
	
	@Override
	protected SFRenderer buildResource() {
		final SFRenderer renderer = new SFRenderer();
		renderer.setLight(light.getResource());
		renderer.setCamera(this.camera.getDataset().getResource());
		return renderer;
	}

}
