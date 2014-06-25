package tests.bricks;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.tests.tools.SFGenericTest;

public abstract class MainPTBricks extends SFGenericTest {

	
	public MainPTBricks() {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		
		root = "bricksData/output";

		rootInput = "bricksData/input";

		storeData = true;
		
		loadLibrariesAsXml = false;
	}

	public void buildTestData() {
		String inputFileName=getFilename();
		
			compileAndLoadBuilderFile(inputFileName);			
				
		store(library);
	}
}
