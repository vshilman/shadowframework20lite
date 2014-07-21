package tests.bricks;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;

public class Test0006_MyBrickBumpPerlin extends MainPTBricks{

	private static final String FILENAME="test0006_brickbumpperlin";

	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test0006_MyBrickBumpPerlin());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame = new SFDrawableFrame("Brick Wall Bump Mapping", 800, 800, drawable);
		frame.setVisible(true);
	}
	
}
