package tests.examples;

import shadow.image.SFTexture;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFRenderingAlgorithm;
import shadow.system.data.SFDataCenter;
import shadow.tests.tools.SFTextureViewer;

/* Draw a bitmap texture viewer */
public class Example0004_CubicTextureFilter extends MainPTExamples {

	private static final String FILENAME="example0004";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0004_CubicTextureFilter());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFRenderingAlgorithm bitmap=(SFRenderingAlgorithm)SFDataCenter.getDataCenter().makeDatasetAvailable("CubicFilterTexture01").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
	}
	
}
