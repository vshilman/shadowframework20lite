package tests.sfdatalevel;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test1005_MyFilteredTexture extends MainPTTest{

	//snow	
	private static final String FILENAME="test1005_myfilteredtexture";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test1005_MyFilteredTexture());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame=new SFDrawableFrame("Soil Test Img04", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
			
	
}
