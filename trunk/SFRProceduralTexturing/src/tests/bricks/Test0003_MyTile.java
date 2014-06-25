package tests.bricks;

import shadow.nodes.SFObjectModel;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFViewer;
import tests.sfdatalevel.MainPTTest;

public class Test0003_MyTile extends MainPTBricks{

	
	private static final String FILENAME="test0005_mytile";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0003_MyTile());
		
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		SFViewer.generateFrame(model,generateColoursController(model),SFViewer.getLightStepController());
	
	}

}
