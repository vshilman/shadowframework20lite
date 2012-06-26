package shadow.renderer.contents.tests;

import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFNode;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFTextureViewer;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Launcher {

	private static final String root = "testsData";
	private static final String fileName = "test0010b.sf";
	private static final String resourceToBeLaunched = "MushroomsScene01b";
	
	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		// contain a valid DatasetFactory
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		loadPipeline();
		
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, fileName));

		SFDataCenter.getDataCenter().makeDatasetAvailable(resourceToBeLaunched,
				new SFDataCenterListener<SFDataAsset<?>>() {
					@Override
					public void onDatasetAvailable(String name,SFDataAsset<?> dataset) {
						SFInitiable resource=dataset.getResource();
						if(resource instanceof SFNode){
							SFViewer viewer=SFViewer.generateFrame((SFNode)resource);
							viewer.setRotateModel(true, 0.03f);
						}else if(resource instanceof SFRenderedTexturesSet){
							SFRenderedTexturesSet set=(SFRenderedTexturesSet)resource;
							SFTexture texture=new SFTexture(set, 0);
							SFTextureViewer.generateFrame(texture,
									CommonTextures.generateTextureSelectionController(texture, set.getTextureSize()));
						}else{
							throw new RuntimeException(name+" is not a valid resource");
						}
					}
				});	
	}

	private static void loadPipeline() {
		SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0020.sf", builder2);
		SFGL20Pipeline.setup();
		builder2.apply(new SFPipelineBuilder());
	}


}
