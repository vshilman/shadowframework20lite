package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;


	
public class Test0023_MyCloudTurbulenceImg03 extends MainPTClouds {

	private static final String FILENAME="test0023_mycloudTurbulenceImg03";
		
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0023_MyCloudTurbulenceImg03());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		/*
		SFRenderingAlgorithm bitmap=(SFRenderingAlgorithm)SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinTexture01").getResource();
		
		SFTextureViewer.generateFrame(new SFTexture(bitmap,0));
		*/
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer02");
		SFDrawableFrame frame=new SFDrawableFrame("Turbulence Cloud Test Img03", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
		
}