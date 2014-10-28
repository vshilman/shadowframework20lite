package tests.water;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

//ground
//per ottenere l'effetto sassolini decommentare una riga dello shader
public class Test0041_MyWater extends MainPTWater {
	
	private static final String FILENAME="test0041_mywater";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0041_MyWater());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer01");
		SFDrawableFrame frame=new SFDrawableFrame("Water Test", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
	
}
