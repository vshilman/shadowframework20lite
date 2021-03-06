package tests.bricks;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFObjectModel;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFViewer;

public class Test0001_MyBrick extends MainPTBricks{
	
	private static final String FILENAME="test0001_mybrick";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0001_MyBrick());
		
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		SFViewer.generateFrame(model,generateColoursController(model),SFViewer.getLightStepController());	
	}

}
