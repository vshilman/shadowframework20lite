package tests.sfdatalevel;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;



public class Test01004b_MyBrick extends MainPTTest {
	
		private static final String FILENAME="test0004b_mybrick";

		public static void main(String[] args) {
			SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
			setFilename(FILENAME);
			execute(new Test01004b_MyBrick());
		}

		public void viewTestData() {
			loadLibraryAsDataCenter();
			
			SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
			SFDrawableFrame frame = new SFDrawableFrame("Brick Wall", 800, 800, drawable);
			frame.setVisible(true);
		}
}
