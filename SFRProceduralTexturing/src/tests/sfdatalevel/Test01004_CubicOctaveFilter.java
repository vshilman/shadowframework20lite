package tests.sfdatalevel;

import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;


public class Test01004_CubicOctaveFilter extends MainPTTest  {

	private static final String FILENAME="test0004_mycubicoctavefilter";

	public static void main(String[] args) {
		SFCommonPipeline.setPipelineLocation("pipeline/","examplePipeline02");
		setFilename(FILENAME);
		execute(new Test01004_CubicOctaveFilter());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		SFDrawable drawable = getAlreadyAvailableDatasetResource("RenderedTextureViewer0002");
		SFDrawableFrame frame = new SFDrawableFrame("Textured Rectangle", 800, 800, drawable);
		frame.setVisible(true);
	}
	
}
