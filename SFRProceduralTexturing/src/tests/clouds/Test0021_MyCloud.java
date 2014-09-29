package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test0021_MyCloud extends MainPTClouds {

/* Draw a bitmap texture viewer */

	private static final String FILENAME="test0021_mycloud";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0021_MyCloud());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		/*
		SFRenderingAlgorithm bitmap=(SFRenderingAlgorithm)SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinTexture01").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
		*/
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TemplateTextureViewer01");
		SFDrawableFrame frame=new SFDrawableFrame("Cloud Test", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
	
}
