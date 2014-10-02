package tests.soil;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

//sand
public class Test0033_SoilImg03 extends MainPTSoil{

		
		private static final String FILENAME="test0033_soilimg03";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0033_SoilImg03());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer01");
		SFDrawableFrame frame=new SFDrawableFrame("Soil Test Img03", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
	
}
