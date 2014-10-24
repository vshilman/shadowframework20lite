package tests.soil;


import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;


public class Test0034b_SoilImg04b extends MainPTSoil {
	

	//snow	
	private static final String FILENAME="test0034b_soilimg04b";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0034b_SoilImg04b());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame=new SFDrawableFrame("Soil Test Img04", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
		
	

}
