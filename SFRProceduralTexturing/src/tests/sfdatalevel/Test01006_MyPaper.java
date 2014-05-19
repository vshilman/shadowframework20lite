package tests.sfdatalevel;

import shadow.nodes.SFObjectModel;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;

public class Test01006_MyPaper extends MainPTTest {
	private static final String FILENAME="test0006_mypaper";
		
		public static void main(String[] args) {
			SFCommonPipeline.setPipelineLocation("pipeline/","wallcoveringspipeline.txt");
			setFilename(FILENAME);
			execute(new Test01006_MyPaper());
			
		}

		public void viewTestData() {
			loadLibraryAsDataCenter();
			SFObjectModel model=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
			SFViewer.generateFrame(model,generateColoursController(model),SFViewer.getLightStepController());
		
		}

}
