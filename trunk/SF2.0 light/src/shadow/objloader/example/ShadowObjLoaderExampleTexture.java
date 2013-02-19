package shadow.objloader.example;

/*
 * programma che carica vagoncino
 */
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFRenderedTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.math.SFVertex3f;
import shadow.objloader.ShadowObjLoader;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFStructureReference;
import shadow.utils.SFTutorial;
import shadow.utils.SFTutorialsUtilities;
import shadowMap.ShadowImage;

public class ShadowObjLoaderExampleTexture extends SFTutorial{

	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	private static SFProgram finalprogram;
	
	private float[] projection={1,0,0,0,  
			0,1,0,0,	
			0,0,1,0,
			0,0,0,1};
	
	private float[] transform={1,0,0,0,  
			0,1,0,0,	
			0,0,1,0,
			0,0,0,1};
	
	private SFPipelineTexture texture0;
	
	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightData;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		ShadowObjLoaderExampleTexture tut03Bitmap=new ShadowObjLoaderExampleTexture();
		//String[] materials={"BasicMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());

			ShadowObjLoaderExampleTexture.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(),"BasicPNTransform", "BasicMat", "BasicLSPN");
			ShadowObjLoaderExampleTexture.finalprogram=SFPipeline.getStaticImageProgram("TexturedMat", "BasicColor");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		
		//material
		ShadowObjLoaderExampleTexture.materialData=SFTutorialsUtilities.generateMaterialData("BasicMat",0/*program, 0,0*/);
		SFVertex3f[] materialData1={new SFVertex3f(1,1,0)/*, new SFVertex3f(0.1f,0.1f,0.1f)*/}; //questa riga è sbagliata, non è la struttura che si aspetta
		materialReference=SFTutorialsUtilities.generateStructureDataReference(program, ShadowObjLoaderExampleTexture.materialData, materialData1);
		
		//Light
		ShadowObjLoaderExampleTexture.lightData=SFTutorialsUtilities.generateLightData(program, 0);
		SFVertex3f[] lightData={new SFVertex3f(2, 1, 1),new SFVertex3f(1, 1, -1)};
		lightReference=SFTutorialsUtilities.generateStructureDataReference(program, ShadowObjLoaderExampleTexture.lightData, lightData);
		
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	}
	
	@Override
	public void init() {
		
		texture0 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(600, 600, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture0);
		
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);			
		
			SFPipeline.getSfPipelineGraphics().setupProjection(projection);
			SFPipeline.getSfPipelineGraphics().setupTransform(transform);
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			
			//load material data
			if(materialData!=null)
				SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData,0, materialReference.getIndex());
			
			//load light data
			if(lightData!=null)
				SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData,0, lightReference.getIndex());
			
			for (int i = 0; i < geometries.size(); i++) {
				geometries.get(i).drawGeometry(0);
			}
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	}
	
	
	@Override
	public void render() {
		
		
		
		texture0.apply(0);
		
		SFPipeline.getSfProgramBuilder().loadProgram(finalprogram);
		
		
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}
