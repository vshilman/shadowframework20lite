package shadow.pipeline.openGL20.tutorials;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import shadow.image.SFBitmap;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexture;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise3;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;

public class Tut05RenderedTextures extends SFTutorial{

	private static final long serialVersionUID=0;
	private SFPipelineTexture texture;
	private SFPipelineTexture texture1;
	private SFPipelineTexture texture2;
	private int shownTexture;
	private SFProgram programGenerate;
	private SFProgram programShow;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		Tut05RenderedTextures tut03Bitmap=new Tut05RenderedTextures();
		String[] materials={"TexturedMat"};
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"),new SFPipelineBuilder());

			tut03Bitmap.programGenerate=SFPipeline.getStaticImageProgram(materials, "BasicGrayAndBright");
			tut03Bitmap.programShow=SFPipeline.getStaticImageProgram(materials, "BasicColor");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	}
	
	@Override
	public void init() {
		
		SFBitmap bitmap=PerlinNoise3.generateBitmap();
		
		texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		texture1 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		texture2 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture1);
		renderedTexture.addColorData(texture2);
		
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);

			texture.apply(0);
		
			SFPipeline.getSfProgramBuilder().loadProgram(programGenerate);
	
			SFPipeline.getSfPipelineGraphics().drawBaseQuad();
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	}
	
	
	@Override
	public void render() {
		
		if(shownTexture==0)
			texture1.apply(0);
		else if(shownTexture==1)
			texture2.apply(0);
		else
			texture.apply(0);
		
		SFPipeline.getSfProgramBuilder().loadProgram(programShow);

		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(e.getKeyCode()==KeyEvent.VK_A){
			shownTexture=0;
		}
		if(e.getKeyCode()==KeyEvent.VK_B){
			shownTexture=1;
		}
		if(e.getKeyCode()==KeyEvent.VK_C){
			shownTexture=2;
		}
	}
}
