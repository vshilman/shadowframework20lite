package tests.bricks;

import shadow.nodes.SFObjectModel;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFViewer;
import tests.sfdatalevel.MainPTTest;

public class Test0004_MyPaper extends MainPTBricks {
	private static final String FILENAME="test0006_mypaper";
		
		public static void main(String[] args) {
			SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
			setFilename(FILENAME);
			execute(new Test0004_MyPaper());
			
		}

		public void viewTestData() {
			loadLibraryAsDataCenter();
			SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
			SFViewer.generateFrame(model,generateColoursController(model),SFViewer.getLightStepController());
		
		}

}
