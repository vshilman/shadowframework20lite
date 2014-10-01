package tests.soil;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test0032_SoilImg02  extends MainPTSoil {
	
	private static final String FILENAME="test0032_soilimg02";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0032_SoilImg02());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer01");
		SFDrawableFrame frame=new SFDrawableFrame("Soil Test Img02", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
	
}
