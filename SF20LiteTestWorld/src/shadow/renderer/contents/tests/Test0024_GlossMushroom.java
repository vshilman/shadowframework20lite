package shadow.renderer.contents.tests;

import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFObjectModel;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

/**
 * If you need to align this test Data, run the {@link SFGenerateAllTestData} utility once.
 * No data will be generated (so nothing will work) until you do that; as an
 * alternative, you can set SFAbstractTest.storeData to true, and then run each test
 * one by one in test number order.
 * <br/> 
 * Go to {@link SFAbstractTest} for general informations about this tests.
 * <br/>
 * Open the related FILENAME.xml file for a detailed view of this test contents. 
 * <br/>
 * Objective: TODO 
 * 
 * @author Alessandro Martinelli
 */
public class Test0024_GlossMushroom  extends SFAbstractTest{

	private static final String FILENAME = "test0024";

	public static void main(String[] args) {
		execute(new Test0024_GlossMushroom());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root, "test0017.sf"));

		// Add a new Component.
		try {
			SFPipelineBuilder builder = new SFPipelineBuilder();
			// @begin Material BrightMat
			builder.generateElement("MaterialComponent", "GlossLightEffectComponent");
			builder.setUseRule("color");
			builder.setUseRule("normal");
			// @end
			builder.buildDefineRule("secondLight", SFParameteri.GLOBAL_FLOAT3, "(-1.0),1.0,0.0");
			builder.buildDefineRule("secondIntensity", SFParameteri.GLOBAL_FLOAT3, "0.8*dot(normal,secondLight)");
			builder.buildDefineRule("bright", SFParameteri.GLOBAL_FLOAT3, "1.4,1.2,1.0");
			builder.buildWriteRule("color", "color*bright*secondIntensity*secondIntensity");
			builder.closeElement();
			
			builder.generateElement("Material", "GlossLightEffect");
			builder.buildComponent("ImprovedBumpMappedMat");
			builder.buildComponent("GlossLightEffectComponent");
			builder.closeElement();
			
		} catch (SFException e1) {
			e1.printStackTrace();
		}
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("StoneMushroom",
				new SFDataCenterListener<SFDataAsset<SFObjectModel>>() {
					@Override
					public void onDatasetAvailable(String name, SFDataAsset<SFObjectModel> dataset) {
						SFObjectModel model = dataset.getResource();
						model.getModel().getMaterialComponent().setProgram("GlossLightEffect");
						SFViewer viewer = SFViewer.generateFrame(model,SFViewer.getLightStepController(),
								SFViewer.getRotationController(),SFViewer.getWireframeController(),SFViewer.getZoomController());
						viewer.setRotateModel(true, 0.03f);
					}
				});
	}
	
	@Override
	public void buildTestData() {
		//Nothing to do, built data is the same as test0020
	}
	
	@Override
	public void setupAmbient() {
		//super.setupAmbient();
		//That's it, an exceptionally bad override, no pipeline should be pre-generated for this test
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFDataPipelineBuilder builder = new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0020.sf", builder);
		SFGL20Pipeline.setup();
		builder.apply(new SFPipelineBuilder());
	}

}
