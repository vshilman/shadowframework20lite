package tests.soil;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

//ground
//per ottenere l'effetto sassolini decommentare una riga dello shader
public class Test0031_MySoil extends MainPTSoil {
	
	private static final String FILENAME="test0031_mysoil";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0031_MySoil());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TextureViewer01");
		SFDrawableFrame frame=new SFDrawableFrame("Soil Test", 800, 800, imageModel);
		frame.setVisible(true);
		
	}
	
}
