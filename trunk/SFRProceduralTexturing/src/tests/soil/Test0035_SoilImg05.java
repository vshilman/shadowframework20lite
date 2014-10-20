package tests.soil;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;


public class Test0035_SoilImg05 extends MainPTSoil{
	

	//gravel
	private static final String FILENAME="test0035_soilimg05";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0035_SoilImg05());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame=new SFDrawableFrame("Soil Test Img05", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
		
	

}
