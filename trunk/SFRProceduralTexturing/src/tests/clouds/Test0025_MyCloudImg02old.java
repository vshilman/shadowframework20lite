package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test0025_MyCloudImg02old extends MainPTClouds{
	

private static final String FILENAME="test0025_mycloudImg02old";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0025_MyCloudImg02old());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		/*
		SFRenderingAlgorithm bitmap=(SFRenderingAlgorithm)SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinTexture01").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
		*/
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer02");
		SFDrawableFrame frame=new SFDrawableFrame("Cloud Test Img02 Old", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
}