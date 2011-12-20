package shadow.pipeline.openGL20.tutorials;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.image.SFBitmap;
import shadow.image.SFFormat;
import shadow.image.SFRenderedTexture;
import shadow.image.SFTextureData;
import shadow.image.SFTextureData.Filter;
import shadow.image.SFTextureData.WrapMode;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise3;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Mushroom;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeCylinder;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class Tut06TexturedGeometry extends SFTutorial{

	private SFTextureData texture;
	private SFTextureData texture1;
	private SFTextureData texture2;
	private int shownTexture;
	private SFProgram programGenerate;
	private SFProgram programShow;
	
	private SFMeshGeometry geometry;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		Tut06TexturedGeometry tut06TexturedGeometry=new Tut06TexturedGeometry();
		String[] materials={"TexturedMat"};
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));

			SFPrimitive primitive=new SFPrimitive();
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("Tx0"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));
			
			tut06TexturedGeometry.programGenerate=SFPipeline.getStaticImageProgram(materials, "BasicGrayAndBright");
			tut06TexturedGeometry.programShow=SFPipeline.getStaticProgram(primitive,materials, "BasicColor");
			
			tut06TexturedGeometry.geometry = (new StrangeCylinder()).generateGeometry(primitive);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		} catch (SFException exception) {
			exception.printStackTrace();
		}
		
		tut06TexturedGeometry.prepareFrame("Textured Geometry", 600, 600);
	}
	
	@Override
	public void init() {
		
		SFBitmap bitmap=PerlinNoise3.generateBitmap();
		
		texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		texture1 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		texture2 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFFormat.RGB8,  Filter.LINEAR,
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

		SFPipeline.getSfPipelineGraphics().drawPrimitives(geometry.getArray(), geometry.getFirstElement(), geometry.getElementsCount());
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
