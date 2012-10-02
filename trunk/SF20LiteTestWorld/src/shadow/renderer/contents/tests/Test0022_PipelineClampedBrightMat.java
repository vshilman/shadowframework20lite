package shadow.renderer.contents.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFNode;
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
public class Test0022_PipelineClampedBrightMat extends SFAbstractTest{

	private static final String FILENAME = "test0022";

	public static void main(String[] args) {
		execute(new Test0022_PipelineClampedBrightMat());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0022.sf", builder2);
	
		SFGL20Pipeline.setup();
		
		builder2.apply(new SFPipelineBuilder());

		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0010b.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomsScene01b",
				new SFDataCenterListener<SFDataAsset<SFNode>>() {
					@Override
					public void onDatasetAvailable(String name,SFDataAsset<SFNode> dataset) {
						SFNode mainNode=dataset.getResource();
						for (SFNode node : mainNode) {
							SFObjectModel objectModel=(SFObjectModel)node;
							objectModel.getModel().getMaterialComponent().setProgram("ClampedBrightMat");
						}
						SFViewer viewer=SFViewer.generateFrame(dataset.getResource(),
								SFViewer.getRotationController(),SFViewer.getWireframeController(),SFViewer.getZoomController());
						viewer.setRotateModel(true, 0.03f);
					}
				});	
	}
	
	@Override
	public void buildTestData() {

		SFDataPipelineBuilder builder=new SFDataPipelineBuilder();
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline"),builder);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			List<String> string=e.getList();
			for (String string2 : string) {
				System.err.println(string2);
			}
			e.printStackTrace();
		}
		
		//Add a new Component.
		try {
			//@begin Material BrightMat
			builder.generateElement("MaterialComponent", "ClampedBrightMat");
			//@param diffColor,ambColor as Mat01
			List<String> parameters=new ArrayList<String>();
			Collections.addAll(parameters, "diffColor");
			builder.setUseRule("normal");
			builder.buildParamRule("Mat01", parameters);
			//@define bright:3 1.8,1.8,1.8
			builder.buildDefineRule("bright", SFParameteri.GLOBAL_FLOAT, "1.8");
			//@define bright:3 1.8,1.8,1.8
			builder.buildDefineRule("colorTemp", SFParameteri.GLOBAL_FLOAT3, "diffColor*bright");
			//@write color diffColor*bright
			builder.buildWriteRule("color", "clamp(colorTemp,0,1.0)");
			builder.buildWriteRule("matnormal", "normalize(normal)");
			//@end
			builder.closeElement();
		} catch (SFException e1) {
			e1.printStackTrace();
			
		}
		
		store(builder, "pipeline");
	}
	
	@Override
	public void setupAmbient() {
		//super.setupAmbient();
		//That's it, an exceptionally bad override, no pipeline should be pregenerated for this test
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
	}
	

}
