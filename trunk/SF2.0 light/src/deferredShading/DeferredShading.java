package deferredShading;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import objLoader.SimpleObjFile;
import CoordinatesTrasformations.ProjectionMatrix;
import CoordinatesTrasformations.TransformMatrix;
import shadow.geometry.SFGeometry;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFRenderedTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.math.SFVertex3f;
import shadow.objloader.ShadowObjLoader;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFStructureReference;
import shadow.utils.SFTutorial;
import shadow.utils.SFTutorialsUtilities;

public class DeferredShading extends SFTutorial{

	
	public DeferredShading(String obj,SFVertex3f[] colors,SFVertex3f[] light){
	this.obj=obj;
	this.colors=colors;
	this.light=light;
	
	}
	
	private static String obj;
	
	private static SFVertex3f[] colors;
	private static SFVertex3f[] light;
	
	private  SFPipelineTexture texture0; 
	private  SFPipelineTexture texture1;
	private  SFPipelineTexture texture2;
	private  SFPipelineTexture texture3;
	
	@Override
	public void init() {
		
		texture0 = textureSetUp();
		texture1 = textureSetUp();
		texture2 = textureSetUp();
		texture3 = textureSetUp();
		
		/*
		 * primo step dell'algoritmo:salvataggio delle informazioni presenti nel mondo 3D in 4 texture differenti:
		 * 1. colore ambientale e diffuso
		 * 2. colore speculare
		 * 3. vettore posizione
		 * 4. vettore normale
		 * 
		 * oltre alle texture devo caricare il modello 3D da visualizzare e le componenti del colore 
		 */
		
		firstPass(texture0,texture1,texture2,texture3);
		
	}
	
	@Override
	public void render() {
		
		/*
		 * secondo step dell'algoritmo: utilizzo le informazione presenti nelle texture precedenti
		 * e le metto in relazione con la posizione e l'intensità della luce
		 * in modo da visualizzare l'immagine finale
		 */
		
		secondPass(texture0,texture1,texture2,texture3);
		
	}
	
	
	
	public static void firstPass(SFPipelineTexture texture0,SFPipelineTexture texture1,SFPipelineTexture texture2,SFPipelineTexture texture3){
		
		ArrayList<SFGeometry> geometries;
	    
		//matrici per la trasformazione di coordinate
		float[] projection= ProjectionMatrix.getRotationX(0);
		float[] transform= TransformMatrix.getRotationX(0);
		
		SFStructureArray materialData;
		SFStructureReference materialReference;
		
		//carico l'oggetto
		SimpleObjFile file=SimpleObjFile.getFromFile(obj);
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		System.err.println("Number of geometries is "+geometries.size());
		
		//scelgo i programmi da caricare nella pipeline
		try {SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());} 
		catch (IOException e) {e.printStackTrace();} 
		catch (SFPipelineModuleWrongException e) {e.printStackTrace();}
		
		SFProgram program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(),"BasicPNTransform", "ColorDFMat", "FirstStepDF");
		
		//creo la struttura dati relativa alle componenti del colore
		materialData=SFTutorialsUtilities.generateMaterialData("ColorDFMat",0);
		SFVertex3f[] materialData1={colors[0],colors[1],colors[2]};
		materialReference=SFTutorialsUtilities.generateStructureDataReference(program, materialData, materialData1);
		
		//creo le texture in cui salverò successivamente i dati
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture0); //diffColor e ambColor
		renderedTexture.addColorData(texture1); //specColor
		renderedTexture.addColorData(texture2); //position
		renderedTexture.addColorData(texture3); //normal
		
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);			
		
			//carico i programmi nella pipeline di rendering
			SFPipeline.getSfPipelineGraphics().setupProjection(projection);
			SFPipeline.getSfPipelineGraphics().setupTransform(transform);
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			
			//load material data
			if(materialData!=null)
				SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData,0, materialReference.getIndex());
			
			//disegno il mondo 3D
			for (int i = 0; i < geometries.size(); i++) {
				geometries.get(i).drawGeometry(0);
			}
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	}
	
	public static void secondPass(SFPipelineTexture texture0,SFPipelineTexture texture1,SFPipelineTexture texture2,SFPipelineTexture texture3){
		
		SFStructureArray lightData;
		SFStructureReference lightReference;
		
		//scelgo i nuovi programmi da caricare nella pipeline per il secondo passo
		try {SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());} 
		catch (IOException e) {e.printStackTrace();} 
		catch (SFPipelineModuleWrongException e) {e.printStackTrace();}
		
		SFProgram finalprogram=SFPipeline.getStaticImageProgram("MoreTexturedMat", "SecondStepDF");
		
		//creo la struttura dati per le informazioni della luce
		lightData=SFTutorialsUtilities.generateLightData(finalprogram, 0);
		SFVertex3f[] lightData1={light[0],light[1] /*new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)*/};
		lightReference=SFTutorialsUtilities.generateStructureDataReference(finalprogram,lightData, lightData1);
		
		//carico il programma nella pipeline e recupero i dati dalle texture
		SFPipeline.getSfProgramBuilder().loadProgram(finalprogram);
		
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture0, 0);
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture1, 1);
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture2, 2);
		SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture3, 3);
		
		//load light data
		if(lightData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData,0, lightReference.getIndex());
		
		//disegno la scena finale
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}
	
	//setting delle texture
	public static SFPipelineTexture textureSetUp(){
		SFPipelineTexture texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(600, 600, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT); 
		return texture;
	}
		
	//carico informazioni sulle componenti di colore	
	public static SFVertex3f[] setupColor(int cdR,int cdG,int cdB,int caR,int caG,int caB,int csR,int csG,int csB){
			SFVertex3f diffColor = new SFVertex3f(cdR,cdG,cdB);
			SFVertex3f ambColor = new SFVertex3f(caR,caG,caB);
			SFVertex3f specColor= new SFVertex3f(csR,csG,csB);
			
			SFVertex3f[] colors={diffColor,ambColor,specColor};
			return colors;

	}
	
	//carico informazioni sulla luce
	public static SFVertex3f[] setupLight(int iR,int iG,int iB,int xL,int yL,int zL){
			SFVertex3f intensity = new SFVertex3f(iR, iG, iB);
			SFVertex3f lPosition = new SFVertex3f(xL, yL, zL);
			
			SFVertex3f[] light={intensity,lPosition};
			return light;

	}
}
