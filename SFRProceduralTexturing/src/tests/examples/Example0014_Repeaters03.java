package tests.examples;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;

/* Draw a bitmap texture viewer */
public class Example0014_Repeaters03 extends MainPTExamples {

	private static final String FILENAME="example0014";
	
	public static void main(String[] args) {
		
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline01");
		setFilename(FILENAME);
		execute(new Example0014_Repeaters03());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDrawable drawable=getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");

		SFDrawableFrame frame=new SFDrawableFrame("Rendering Process Test", 800, 800, drawable);
		
		frame.setVisible(true);
		
	}
	
}
