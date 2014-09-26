package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test0024_MyCloudFractalImg02 extends MainPTClouds{

	private static final String FILENAME="test0024_mycloudfractalImg02";
		
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0024_MyCloudFractalImg02());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer02");
		SFDrawableFrame frame=new SFDrawableFrame("Fractal Cloud Test Img02", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
		
}
