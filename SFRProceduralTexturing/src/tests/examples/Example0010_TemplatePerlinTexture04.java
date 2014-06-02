package tests.examples;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.SFImageModel;
import shadow.renderer.SFProxyDrawable;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.renderer.viewer.controllers.SFProxyDrawableController;
import shadow.system.SFDrawable;

/* Draw a bitmap texture viewer */
public class Example0010_TemplatePerlinTexture04 extends MainPTExamples {

	private static final String FILENAME="example0010";
	
	public static void main(String[] args) {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0010_TemplatePerlinTexture04());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		String[] names={"TemplateTextureViewer01",
					"TemplateTextureViewer02",
					"TemplateTextureViewer03",
					"TemplateTextureViewer04"};

		SFDrawable[] drawables=new SFDrawable[names.length];
		
		for (int i = 0; i < drawables.length; i++) {
			SFImageModel imageModel01=getAlreadyAvailableDatasetResource(names[i]);
			drawables[i]=imageModel01;
		}

		SFProxyDrawable proxyDrawable=new SFProxyDrawable(drawables[0]);
		
		SFProxyDrawableController controller=new SFProxyDrawableController(proxyDrawable, drawables, names, "Texture");
		
		SFDrawableFrame frame=new SFDrawableFrame("Rendering Process Test", 800, 800, proxyDrawable, controller);
		
		frame.setVisible(true);
		
	}
	
}
