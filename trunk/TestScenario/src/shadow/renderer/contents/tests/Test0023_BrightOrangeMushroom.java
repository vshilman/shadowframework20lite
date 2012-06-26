package shadow.renderer.contents.tests;

import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFObjectModel;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0023_BrightOrangeMushroom {

	private static final String root = "testsData";

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
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root, "test0017.sf"));

		// Add a new Component.
		try {
			SFPipelineBuilder builder = new SFPipelineBuilder();
			// @begin Material BrightMat
			builder.generateElement("Material", "BrightOrangeMaterial");
			builder.setUseRule("color");
			// @end
			builder.buildDefineRule("bright", SFParameteri.GLOBAL_FLOAT3, "1.4f,1.2f,1.0f");
			builder.buildWriteRule("color", "color*bright");
			builder.closeElement();
		} catch (SFException e1) {
			e1.printStackTrace();
		}

		SFDataCenter.getDataCenter().makeDatasetAvailable("StoneMushroom",
				new SFDataCenterListener<SFDataAsset<SFObjectModel>>() {
					@Override
					public void onDatasetAvailable(String name, SFDataAsset<SFObjectModel> dataset) {
						SFObjectModel model = dataset.getResource();
						model.getModel().addMaterial("BrightOrangeMaterial");
						SFViewer viewer = SFViewer.generateFrame(model,SFViewer.getLightStepController(),
								SFViewer.getRotationController(),SFViewer.getWireframeController(),SFViewer.getZoomController());
						viewer.setRotateModel(true, 0.03f);
					}
				});
	}

	private static void loadPipeline() {
		SFDataPipelineBuilder builder = new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0020.sf", builder);
		SFGL20Pipeline.setup();
		builder.apply(new SFPipelineBuilder());
	}

}
