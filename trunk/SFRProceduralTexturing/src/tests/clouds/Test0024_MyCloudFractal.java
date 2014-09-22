package tests.clouds;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

public class Test0024_MyCloudFractal extends MainPTClouds{

	private static final String FILENAME="test0024_mycloudfractal";
		
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0024_MyCloudFractal());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer02");
		SFDrawableFrame frame=new SFDrawableFrame("Fractal Cloud Test", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
		
}
