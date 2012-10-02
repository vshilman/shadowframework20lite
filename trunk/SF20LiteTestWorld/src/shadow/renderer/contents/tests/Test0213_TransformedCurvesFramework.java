package shadow.renderer.contents.tests;

import shadow.renderer.SFObjectModel;
import shadow.renderer.viewer.SFViewer;

public class Test0213_TransformedCurvesFramework extends SFAbstractTest{

	private static final String FILENAME = "test0213";
	
	public static void main(String[] args) {
		execute(new Test0213_TransformedCurvesFramework());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		SFObjectModel model = getAlreadyAvailableDatasetResource("FrameworkModel");
		
		SFViewer viewer=SFViewer.generateFrame(model,SFViewer.getWireframeController(),SFViewer.getLightStepController(),SFViewer.getZoomController());
		viewer.setRotateModel(true, 0.01f);
		
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="test0213Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
}
