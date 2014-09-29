package tests.soil;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.tests.tools.SFGenericTest;


public abstract class MainPTSoil extends SFGenericTest{
		
	public MainPTSoil() {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		
		root = "soilData/output";

		rootInput = "soilData/input";

		storeData = true;
		
		loadLibrariesAsXml = false;
	}

	public void buildTestData() {
		String inputFileName=getFilename();
		
			compileAndLoadBuilderFile(inputFileName);			
				
		store(library);
	}


}
