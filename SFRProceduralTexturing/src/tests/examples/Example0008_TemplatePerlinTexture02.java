package tests.examples;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.SFProxyDrawable;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.renderer.viewer.controllers.SFProxyDrawableController;
import shadow.system.SFDrawable;

/* Draw a bitmap texture viewer */
public class Example0008_TemplatePerlinTexture02 extends MainPTExamples {

	private static final String FILENAME="example0008";
	
	public static void main(String[] args) {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0008_TemplatePerlinTexture02());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();

		SFImageModel imageModel01=getAlreadyAvailableDatasetResource("TemplateTextureViewer01");
		SFImageModel imageModel02=getAlreadyAvailableDatasetResource("TemplateTextureViewer02");
		SFImageModel imageModel03=getAlreadyAvailableDatasetResource("TemplateTextureViewer03");
		SFImageModel imageModel04=getAlreadyAvailableDatasetResource("TemplateTextureViewer04");
		SFImageModel imageModel05=getAlreadyAvailableDatasetResource("TemplateTextureViewer05");

		SFProxyDrawable proxyDrawable=new SFProxyDrawable(imageModel01);
		SFDrawable[] drawables={imageModel01,imageModel02,imageModel03,imageModel04,imageModel05};
		String[] names={"TemplateTextureViewer01","TemplateTextureViewer02","TemplateTextureViewer03",
				"TemplateTextureViewer04","TemplateTextureViewer05"};
		
		SFProxyDrawableController controller=new SFProxyDrawableController(proxyDrawable, drawables, names, "Texture");
		
		SFDrawableFrame frame=new SFDrawableFrame("Rendering Process Test", 800, 800, proxyDrawable, controller);
		
		frame.setVisible(true);
		
	}
	
}
