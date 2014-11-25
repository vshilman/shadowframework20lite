package tests.bricks;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;

public class Test0011_BrickImg01 extends MainPTBricks{
	private static final String FILENAME="test0011_brickimg01";

	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		
		System.out.println("Start " + System.nanoTime());
		execute(new Test0011_BrickImg01());
		System.out.println("End " + System.nanoTime());
		
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame = new SFDrawableFrame("Brick Wall 01", 800, 800, drawable);
		frame.setVisible(true);
	}
}
