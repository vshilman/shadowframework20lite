package tests.water;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.tests.tools.SFGenericTest;


public abstract class MainPTWater extends SFGenericTest{
		
	public MainPTWater() {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		
		root = "waterData/output";

		rootInput = "waterData/input";

		storeData = true;
		
		loadLibrariesAsXml = false;
	}

	public void buildTestData() {
		String inputFileName=getFilename();
		
			compileAndLoadBuilderFile(inputFileName);			
				
		store(library);
	}


}
