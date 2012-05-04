package test;


import java.awt.event.KeyEvent;

import shadow.image.SFBitmap;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise1;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise2;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise3;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;

public class Tut04MoreBitmaps extends SFTutorial{
	
	private SFPipelineTexture[] texture;
	private int selectedTexture=0;
	private SFProgram program;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		
		String[] materials={"TexturedMat"};
		SFProgram program=SFTutorialsUtilities.generateImageProgram("data/primitive", materials, "BasicColor");
		
		Tut04MoreBitmaps tut03Bitmap=new Tut04MoreBitmaps();
		tut03Bitmap.program=program;
		tut03Bitmap.prepareFrame("Curved Tube Function", 600, 600);
	}
	
	@Override
	public void init() {
		
		SFBitmap[] bitmap = getAllBitmaps();
		
		texture=new SFPipelineTexture[bitmap.length];
		for (int i = 0; i < bitmap.length; i++) {
			texture[i]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap[i], Filter.LINEAR,
					WrapMode.REPEAT, WrapMode.REPEAT);
		}
		
	}

	private SFBitmap[] getAllBitmaps() {
		SFBitmap[] bitmap = {PerlinNoise1.generateBitmap(),
				PerlinNoise2.generateBitmap(),
				PerlinNoise3.generateBitmap()};
		return bitmap;
	}

	@Override
	public void render() {
		
		texture[selectedTexture].apply(0);
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);

		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(e.getKeyCode()==KeyEvent.VK_PLUS){
			selectedTexture++;
			if(selectedTexture>=texture.length){
				selectedTexture=0;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_MINUS){
			selectedTexture--;
			if(selectedTexture<0){
				selectedTexture=texture.length-1;
			}
		}
	}
}
