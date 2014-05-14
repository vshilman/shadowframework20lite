package tests.sfdatalevel;

import shadow.nodes.SFObjectModel;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;

public class Test01004_MyBrick extends MainPTTest{
private static final String FILENAME="test0004_mybrick";
	
	public static void main(String[] args) {
		setFilename(FILENAME);
		execute(new Test01004_MyBrick());
		
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		SFViewer.generateFrame(model,generateColoursController(model),SFViewer.getLightStepController());
	
	}

}
