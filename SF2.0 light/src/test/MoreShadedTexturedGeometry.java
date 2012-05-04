package test;
/*
 * inserito array di modelli
 * inserito secondo oggetto a lato
 * 
 * da fare
 * cambiare illuminazione/materiali
 */

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.image.SFBitmap;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexture;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.bitmapsExample.PerlinNoise3;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Cone;
import shadow.pipeline.openGL20.tutorials.geometriesExample.LateralTube;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Mushroom;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeCylinder;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeGlass;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;
import shadow.renderer.SFStructureReference;
import shadow.system.SFException;

public class MoreShadedTexturedGeometry extends SFTutorial{

	private SFPipelineTexture texture;
	private SFPipelineTexture texture1;
	private SFPipelineTexture texture2;
	private SFPipelineTexture texture3;
	private int shownTexture;
	private SFProgram programDraw;
	private SFProgram programGenerate;
	private SFProgram programShow;
	
	private SFMeshGeometry geometry;
	private SFMeshGeometry[] geometries;
	private int geometriesIndex=0;
	
	private SFStructureReference lightReference;
	
	private SFStructureArray lightArray;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		MoreShadedTexturedGeometry tut06TexturedGeometry=new MoreShadedTexturedGeometry();
		String[] materials={"TexturedMat"};
		String[] materials2={"BlackMat"};

		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"), new SFPipelineBuilder());
			
			//recupero info di normali, posizione e coord.text
			SFPrimitive primitive=new SFPrimitive();
			primitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));
			
			tut06TexturedGeometry.programDraw=SFPipeline.getStaticImageProgram(materials, "DepthStep1");
			tut06TexturedGeometry.programGenerate=SFPipeline.getStaticImageProgram(materials, "BasicGrayAndBright");
			tut06TexturedGeometry.programShow=SFPipeline.getStaticProgram(primitive,materials, "BasicLSPN");
			
			tut06TexturedGeometry.geometries=generateGeometries(primitive);
			tut06TexturedGeometry.geometry = (new Mushroom()).generateGeometry(primitive);
			
			tut06TexturedGeometry.lightArray=SFTutorialsUtilities.generateLightData(tut06TexturedGeometry.programShow, 0);
			SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(0, 1, 0)};
			tut06TexturedGeometry.lightReference=SFTutorialsUtilities.generateStructureDataReference(tut06TexturedGeometry.programShow, 
					tut06TexturedGeometry.lightArray, lightData);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		} catch (SFException exception) {
			exception.printStackTrace();
		}
		
		tut06TexturedGeometry.prepareFrame("Textured Geometry", 600, 600);
	}
		
		public static SFMeshGeometry[] generateGeometries(SFPrimitive primitive){
			SFMeshGeometry[] geometries= {
					(new StrangeCylinder()).generateGeometry(primitive),
					(new Cone()).generateGeometry(primitive),
					(new LateralTube()).generateGeometry(primitive),
					(new StrangeGlass()).generateGeometry(primitive),
					(new Mushroom()).generateGeometry(primitive)
			};
			return geometries;
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
		texture3 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture1);
		renderedTexture.addColorData(texture2);
		renderedTexture.addColorData(texture3);
		
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
		else if(shownTexture==2)
			texture3.apply(0);
		else 
			texture.apply(0);
		
		SFPipeline.getSfProgramBuilder().loadProgram(programShow);
		
		
			SFPipeline.getSfPipelineGraphics().loadStructureData(lightArray, lightReference.getMaterialIndex());
			
			SFVertex3f position = new SFVertex3f(-0.5, 0.0, 0.0);
			SFPipeline.getSfPipelineGraphics().translateModel(position);
			SFPipeline.getSfPipelineGraphics().drawPrimitives(geometries[geometriesIndex].getArray(), geometries[geometriesIndex].getFirstElement(), geometries[geometriesIndex].getElementsCount());
			
			SFVertex3f position1 = new SFVertex3f(0.5, 0.0, 0.0);
			SFPipeline.getSfPipelineGraphics().translateModel(position1);
			SFPipeline.getSfPipelineGraphics().drawPrimitives(geometry.getArray(), geometry.getFirstElement(), geometry.getElementsCount());
	}
//cambiato modo di cambiare le texture: vanno con più e meno
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(e.getKeyCode()==KeyEvent.VK_A){
			if(shownTexture>2){
				shownTexture=0;
			}else
			shownTexture=shownTexture+1;
		}
		if(e.getKeyCode()==KeyEvent.VK_PLUS){
			geometriesIndex++;
			if(geometriesIndex>=geometries.length){
				geometriesIndex=0;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_MINUS){
			geometriesIndex--;
			if(geometriesIndex<0){
				geometriesIndex=geometries.length-1;
			}
		}
	}
}
