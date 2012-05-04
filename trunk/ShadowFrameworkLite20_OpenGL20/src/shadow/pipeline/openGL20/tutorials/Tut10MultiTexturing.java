package shadow.pipeline.openGL20.tutorials;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import shadow.image.SFBitmap;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise1;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise3;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;

public class Tut10MultiTexturing extends SFTutorial{

	private static final long serialVersionUID=0;
	private static final int FIRST_TEXTURE=0;
	private static final int SECOND_TEXTURE=1;
	private static final int BOTH=2;
	
	private SFPipelineTexture texture1;
	private SFPipelineTexture texture2;
	private int shownTexture=0;
	private SFProgram programShow1Texture;
	private SFProgram programMultiTextures;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		Tut10MultiTexturing tut03Bitmap=new Tut10MultiTexturing();
		String[] materials1={"TexturedMat"};
		String[] materials2={"TexturedMat2"};
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"),new SFPipelineBuilder());

			/*
			 * Generate two programs, the first allows to show 1 single texture,
			 * the second evaluates the difference of the two textures colors
			 */
			tut03Bitmap.programShow1Texture=SFPipeline.getStaticImageProgram(materials1, "BasicColor");
			tut03Bitmap.programMultiTextures=SFPipeline.getStaticImageProgram(materials2, "ColorSubtract");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	}
	
	@Override
	public void init() {
		
		/* Generates a Texture as in PerlinNoise3
		 */
		SFBitmap bitmap1=PerlinNoise3.generateBitmap();
		texture1=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap1, Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		/* Generates a Texture as in PerlinNoise1
		 */
		SFBitmap bitmap2=PerlinNoise1.generateBitmap();
		texture2=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap2, Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
	}
	
	
	@Override
	public void render() {
		
		if(shownTexture==FIRST_TEXTURE){
			//apply texture 1
			texture1.apply(0);
			//use the program to show only 1 texture
			SFPipeline.getSfProgramBuilder().loadProgram(programShow1Texture);
		}else if(shownTexture==SECOND_TEXTURE){
			//apply texture 2
			texture2.apply(0);
			//use the program to show only 1 texture
			SFPipeline.getSfProgramBuilder().loadProgram(programShow1Texture);
		}else{
			//apply texture 1 as 'texture0', the index in the brackets is indeed the texture index
			texture1.apply(0);
			//apply texture 2 as 'texture1', the index in the brackets is indeed the texture index
			texture2.apply(1);
			SFPipeline.getSfProgramBuilder().loadProgram(programMultiTextures);
		}
		
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		if(e.getKeyCode()==KeyEvent.VK_A){
			shownTexture=FIRST_TEXTURE;
		}else if(e.getKeyCode()==KeyEvent.VK_B){
			shownTexture=SECOND_TEXTURE;
		}else if(e.getKeyCode()==KeyEvent.VK_C){
			shownTexture=BOTH;
		} 
	}
	
}
