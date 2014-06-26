package tests.bricks;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;
import tests.sfdatalevel.MainPTTest;



public class Test0002_MyBrickTex extends MainPTBricks {
	
		private static final String FILENAME="test0002_mybricktex";

		public static void main(String[] args) {
			SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
			setFilename(FILENAME);
			execute(new Test0002_MyBrickTex());
		}

		public void viewTestData() {
			loadLibraryAsDataCenter();
			
			SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
			SFDrawableFrame frame = new SFDrawableFrame("Brick Wall", 800, 800, drawable);
			frame.setVisible(true);
		}
}
