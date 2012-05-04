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
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0020_Pipeline {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		SFDataPipelineBuilder builder=new SFDataPipelineBuilder();
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"),builder);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			List<String> string=e.getList();
			for (String string2 : string) {
				System.err.println(string2);
			}
			e.printStackTrace();
		}

		// 2) Store the Surface Function in file 'testsData\test0001.sf'
		SFDataUtility.saveDataObject(root, "test0020.sf", builder);
		
		SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0020.sf", builder2);
	
		SFGL20Pipeline.setup();
		
		builder2.apply(new SFPipelineBuilder());

		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
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


}
