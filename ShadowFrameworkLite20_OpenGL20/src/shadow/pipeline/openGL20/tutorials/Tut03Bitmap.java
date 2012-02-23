package shadow.pipeline.openGL20.tutorials;

import shadow.image.SFBitmap;
import shadow.image.SFTextureData;
import shadow.image.SFTextureData.Filter;
import shadow.image.SFTextureData.WrapMode;
import shadow.image.bitmaps.SFSpecialPerlinNoise;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;

public class Tut03Bitmap extends SFTutorial{
	
	private SFTextureData texture;
	private SFProgram program;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		
		String[] materials={"TexturedMat"};
		SFProgram program=SFTutorialsUtilities.generateImageProgram("data/pipeline/primitive", materials, "BasicYellowColor");
		
		Tut03Bitmap tut03Bitmap=new Tut03Bitmap();
		tut03Bitmap.program=program;
		tut03Bitmap.prepareFrame("Curved Tube Function", 600, 600);
	}
	
	@Override
	public void init() {
		
		float[] weights={0.1f,0.4f,0.3f};
		float[] colors={1,1,0,
						1,0,0,
						1,0,1};
		SFSpecialPerlinNoise.randomizeFunction();
		SFBitmap bitmap=new SFSpecialPerlinNoise(200, 200, weights, colors);
		
		texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR_MIPMAP,
				WrapMode.REPEAT, WrapMode.REPEAT);
	}
	
	
	@Override
	public void render() {
		
		texture.apply(0);
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);

		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}
}
