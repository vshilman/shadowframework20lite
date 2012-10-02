package shadow.renderer.contents.tests;

import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.java.SFObjectsLibraryDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0307_DataXmlImportLibraryDecoder {

	//private static final String root = "testsData";
	
	public static void main(String argv[]) {
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//... and a complete ShadowFramework Pipeline must be prepared.
		/*CommonPipeline pipeline=new CommonPipeline();*/
		CommonPipeline.prepare();
		
		SFViewerObjectsLibrary library=new SFViewerObjectsLibrary();
		
		SFObjectsLibraryDecoder decoder=new SFObjectsLibraryDecoder(library.getLibrary());
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation("testsData/test0002.xml");
		
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(library);
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("RedMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				SFViewer.generateFrame(model,CommonMaterial.generateColoursController(model),SFViewer.getLightStepController());
			}
		});
	}
	
}
