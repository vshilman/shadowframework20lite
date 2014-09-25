package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test0022_MyCloudImg01 extends MainPTClouds {

private static final String FILENAME="test0022_mycloudimg01";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0022_MyCloudImg01());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		/*
		SFRenderingAlgorithm bitmap=(SFRenderingAlgorithm)SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinTexture01").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
		*/
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer02");
		SFDrawableFrame frame=new SFDrawableFrame("Cloud Test Img01", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
}
