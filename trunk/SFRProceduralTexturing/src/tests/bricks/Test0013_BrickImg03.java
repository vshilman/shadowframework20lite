package tests.bricks;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;

public class Test0013_BrickImg03 extends MainPTBricks{

	private static final String FILENAME="test0013_brickimg03";

	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0013_BrickImg03());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame = new SFDrawableFrame("Brick Wall 03", 800, 800, drawable);
		frame.setVisible(true);
	}
}
