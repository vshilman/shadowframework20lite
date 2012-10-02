package shadow.renderer.contents.tests;

import shadow.renderer.SFObjectModel;
import shadow.renderer.viewer.SFViewer;

public class Test0214_TensorProductSurface extends SFAbstractTest{

	private static final String FILENAME = "test0214";
	
	public static void main(String[] args) {
		execute(new Test0214_TensorProductSurface());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		SFObjectModel model = getAlreadyAvailableDatasetResource("CubicPatchModel");
		
		SFViewer viewer=SFViewer.generateFrame(model,SFViewer.getWireframeController(),SFViewer.getLightStepController(),SFViewer.getZoomController());
		viewer.setRotateModel(true, 0.01f);
		
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="test0214Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
}
