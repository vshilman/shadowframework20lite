package deferredShading;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import CoordinatesTrasformations.ProjectionMatrix;
import CoordinatesTrasformations.TransformMatrix;

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
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFStructureReference;
import shadow.utils.SFTutorial;
import shadow.utils.SFTutorialsUtilities;


public class DeferredShading {

	public static SFProgram First(SFPrimitive primitive){
		try {SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());} 
		catch (IOException e) {e.printStackTrace();} 
		catch (SFPipelineModuleWrongException e) {e.printStackTrace();}
		
		SFProgram program=SFPipeline.getStaticProgram(primitive,"BasicPNTransform", "ColorDFMat", "FirstStepDF");
		return program;
	}
	
	public static SFProgram Second(){
		try {SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());} 
		catch (IOException e) {e.printStackTrace();} 
		catch (SFPipelineModuleWrongException e) {e.printStackTrace();}
		
		SFProgram program=SFPipeline.getStaticImageProgram("MoreTexturedMat", "SecondStepDF");
		return program;
	}
	
	public static void firstPass(SFProgram program, ArrayList<SFGeometry> geometries, SFPipelineTexture texture0,SFPipelineTexture texture1,SFPipelineTexture texture2,SFPipelineTexture texture3){
		
		float[] projection= ProjectionMatrix.getRotationX(0);
		float[] transform= TransformMatrix.getRotationX(0);
		
		SFStructureArray materialData;
		SFStructureReference materialReference;
		
		
		materialData=SFTutorialsUtilities.generateMaterialData("ColorDFMat",0);
		SFVertex3f[] materialData1={new SFVertex3f(1,0,0), new SFVertex3f(1,0,0), new SFVertex3f(1,1,0)};
		materialReference=SFTutorialsUtilities.generateStructureDataReference(program, materialData, materialData1);
		
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture0); //diffColor e ambColor
		renderedTexture.addColorData(texture1); //specColor
		renderedTexture.addColorData(texture2); //position
		renderedTexture.addColorData(texture3); //normal
		
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);			
		
			SFPipeline.getSfPipelineGraphics().setupProjection(projection);
			SFPipeline.getSfPipelineGraphics().setupTransform(transform);
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			
			//load material data
			if(materialData!=null)
				SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData,0, materialReference.getIndex());
			
		
			
			for (int i = 0; i < geometries.size(); i++) {
				geometries.get(i).drawGeometry(0);
			}
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	}
	
	public static void secondPass(SFPipelineTexture texture0,SFPipelineTexture texture1,SFPipelineTexture texture2,SFPipelineTexture texture3){
		SFStructureArray lightData;
		SFStructureReference lightReference;
		SFProgram finalprogram= Second();
		
		lightData=SFTutorialsUtilities.generateLightData(finalprogram, 0);
		SFVertex3f[] lightData1={new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)};
		lightReference=SFTutorialsUtilities.generateStructureDataReference(finalprogram,lightData, lightData1);
		
		
		SFPipeline.getSfProgramBuilder().loadProgram(finalprogram);
		
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture0, 0);
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture1, 1);
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture2, 2);
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture3, 3);
		
		//load light data
		if(lightData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData,0, lightReference.getIndex());
		
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}
	
	public static SFPipelineTexture textureSetUp(){
		SFPipelineTexture texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(600, 600, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT); 
		return texture;
	}
}
