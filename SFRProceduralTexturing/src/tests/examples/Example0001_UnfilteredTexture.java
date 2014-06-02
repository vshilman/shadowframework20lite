package tests.examples;

import shadow.image.SFBitmapTexture;
import shadow.image.SFTexture;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFTextureViewer;

/* Draw a bitmap texture viewer */
public class Example0001_UnfilteredTexture extends MainPTExamples {

	private static final String FILENAME="example0001";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0001_UnfilteredTexture());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFBitmapTexture bitmap=(SFBitmapTexture)SFDataCenter.getDataCenter().makeDatasetAvailable("BitmapTexture0001").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
	}
	
}
