package tests.examples;

import shadow.tests.tools.SFGenericTest;

public abstract class MainPTExamples extends SFGenericTest {

	public MainPTExamples() {
		
		root = "examplesData/output";

		rootInput = "examplesData/input";

		storeData = true;
		
		loadLibrariesAsXml = false;
	}

	public void buildTestData() {
		String inputFileName=getFilename();

		compileAndLoadBuilderFile(inputFileName);	
		
		store(library);
	}
}
