package tests.examples;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.viewer.SFDrawableFrame;

/* Draw a bitmap texture viewer */
public class Example0009_TemplatePerlinTexture03 extends MainPTExamples {

	private static final String FILENAME="example0009";
	
	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0009_TemplatePerlinTexture03());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFImageModel imageModel=getAlreadyAvailableDatasetResource("TemplateTextureViewer01");
		
		/* here: we avoid using TextureViewer (basically useless at the moment), 
		 * we use directly a drawable frame, accepting
		 * any kind of SFDrawable, like SFImageModel 
		 */
		SFDrawableFrame frame=new SFDrawableFrame("Rendering Process Test", 800, 800, imageModel);
		
		frame.setVisible(true);
	}
	
}
