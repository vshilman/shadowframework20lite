package tests.examples;

import shadow.image.SFTexture;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFRenderingAlgorithm;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFTextureViewer;

/* Draw a bitmap texture viewer */
public class Example0002_NearestTextureFilter extends MainPTExamples {

	private static final String FILENAME="example0002";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0002_NearestTextureFilter());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFRenderingAlgorithm bitmap=(SFRenderingAlgorithm)SFDataCenter.getDataCenter().makeDatasetAvailable("NearestFilterTexture01").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
	}
	
}
