package shadow.renderer.contents.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFViewer;
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
public class Test0020_Pipeline extends SFAbstractTest{

	private static final String FILENAME = "test0020";

	public static void main(String[] args) {
		execute(new Test0020_Pipeline());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		SFGL20Pipeline.setup();
		
		SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0020.sf", builder2);
	
		builder2.apply(new SFPipelineBuilder());
		
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0010b.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomsScene01b",
				new SFDataCenterListener<SFDataAsset<SFNode>>() {
					@Override
					public void onDatasetAvailable(String name,SFDataAsset<SFNode> dataset) {
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
		
		store(builder, "pipeline");
	}
	
	@Override
	public void setupAmbient() {
		//super.setupAmbient();
		//That's it, an exceptionally bad override, no pipeline should be pre-generated for this test
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
	}
}
