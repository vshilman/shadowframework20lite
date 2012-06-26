package shadow.renderer.contents.tests;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0025_MixMushroom {

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
			builder.generateElement("Material", "GlossLightEffect");
			builder.setUseRule("color");
			builder.setUseRule("normal");
			// @end
			builder.buildDefineRule("secondLight", SFParameteri.GLOBAL_FLOAT3, "-1,1,0");
			builder.buildDefineRule("secondIntensity", SFParameteri.GLOBAL_FLOAT3, "0.3+dot(normal,secondLight)");
			builder.buildDefineRule("bright", SFParameteri.GLOBAL_FLOAT3, "0.5f,0.5f,0.5f");
			builder.buildWriteRule("color", "color*(bright+secondIntensity*secondIntensity)");
			builder.closeElement();
			
			builder = new SFPipelineBuilder();
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
						
						SFObjectModel objectModel=dataset.getResource();
						
						SFObjectModel objectModel01=new SFObjectModel();
						SFMatrix3f matrix=SFMatrix3f.getRotationY((float)(Math.PI));
						matrix.mult(1.5f);
						objectModel01.getTransform().setOrientation(matrix);
						objectModel01.getTransform().setPosition(new SFVertex3f(0.4f,-0.1f,0));
						objectModel01.setModel(objectModel.getModel().clone());
						
						SFObjectModel objectModel02=new SFObjectModel();
						matrix=SFMatrix3f.getRotationY(1.66f*(float)(Math.PI));
						matrix.mult(1.5f);
						objectModel02.getTransform().setOrientation(matrix);
						objectModel02.getTransform().setPosition(new SFVertex3f(-0.33f,0,-0.33f));
						objectModel02.setModel(objectModel.getModel().clone());
						objectModel02.getModel().addMaterial("GlossLightEffect");
						
						SFObjectModel objectModel03=new SFObjectModel();
						matrix=SFMatrix3f.getRotationY(2.333f*(float)(Math.PI));
						matrix.mult(1.5f);
						objectModel03.getTransform().setOrientation(matrix);
						objectModel03.getTransform().setPosition(new SFVertex3f(-0.33f,-0.2f,0.33f));
						objectModel03.setModel(objectModel.getModel().clone());
						objectModel03.getModel().addMaterial("BrightOrangeMaterial");
						
						SFReferenceNode mainNode=new SFReferenceNode();
							float[] matrix3f={
									1.5f,0,0,
									0,2,0,
									0,0,0.5f
							};
							matrix.set(matrix3f);
						mainNode.getTransform().setOrientation(matrix);
						mainNode.getTransform().setPosition(new SFVertex3f(0,-0.4f,-0.5f));
						mainNode.addNode(objectModel01);
						mainNode.addNode(objectModel02);
						mainNode.addNode(objectModel03);
						
						SFViewer viewer = SFViewer.generateFrame(mainNode,SFViewer.getLightStepController(),
								SFViewer.getRotationController(),SFViewer.getWireframeController(),SFViewer.getZoomController());
						viewer.setRotateModel(true, 0.005f);
						
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
