package tests.sfdatalevel;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.tests.tools.SFGenericTest;

public abstract class MainPTTest extends SFGenericTest {

	public static final boolean COMPILE_WITH_BUILDER = true;
	
	public MainPTTest() {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		
		root = "testsData/output";

		rootInput = "testsData/input";

		storeData = true;
		
		loadLibrariesAsXml = false;
	}

	public void buildTestData() {
		String inputFileName=getFilename();
		
		if(COMPILE_WITH_BUILDER){
			compileAndLoadBuilderFile(inputFileName);			
		}else{
			compileAndLoadXmlFile(inputFileName);	
		}
		
		store(library);
	}
}
