package shadow.renderer.data;

import shadow.renderer.SFCamera;
import shadow.renderer.SFRenderer;
import shadow.renderer.SFRenderingAlgorithm;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFRendererData extends SFDataAsset<SFRenderer> implements SFDataCenterListener<SFDataAsset<SFRenderingAlgorithm>>{

	private SFDatasetObject<SFDataAsset<SFCamera>> camera = 
		new SFDatasetObject<SFDataAsset<SFCamera>>(null);
	private SFLibraryReference<SFDataAsset<SFRenderingAlgorithm>> algorithm = 
		new SFLibraryReference<SFDataAsset<SFRenderingAlgorithm>>();
	
	private SFRenderer renderer;

	public SFRendererData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(camera,
				algorithm));
	}
	
	public SFRendererData(SFDataAsset<SFRenderingAlgorithm> algorithm,SFDataAsset<SFCamera> camera) {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(this.camera,
				this.algorithm));
		setCamera(camera);
		setAlgorithm(algorithm);
	}
	
	@Override
	public void onDatasetAvailable(String name,
			SFDataAsset<SFRenderingAlgorithm> dataset) {
		renderer.setAlgorithm(dataset.getResource());
	}

	@Override
	protected SFRenderer buildResource() {
		renderer = new SFRenderer();
		algorithm.retrieveDataset(this);
		renderer.setCamera(this.camera.getDataset().getResource());
		return renderer;
	}

	public void setCamera(SFDataAsset<SFCamera> camera) {
		this.camera.setDataset(camera);
	}

	public void setAlgorithm(SFDataAsset<SFRenderingAlgorithm> algorithm) {
		this.algorithm.setDataset(algorithm);
	}
	
	public void setAlgorithm(String algorithmName) {
		this.algorithm.setReference(algorithmName);
	}
}
