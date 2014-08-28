package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.tests.tools.SFGenericTest;

public abstract class MainPTClouds extends SFGenericTest {

	
	public MainPTClouds() {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		
		root = "cloudsData/output";

		rootInput = "cloudsData/input";

		storeData = true;
		
		loadLibrariesAsXml = false;
	}

	public void buildTestData() {
		String inputFileName=getFilename();
		
			compileAndLoadBuilderFile(inputFileName);			
				
		store(library);
	}
}
