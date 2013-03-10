package shadow.objloader.example;

/*
 * programma che carica vagoncino
 * 
 * todo: girare mondo su punto di vista della luce
 */
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import CoordinatesTrasformations.ProjectionMatrix;
import CoordinatesTrasformations.TransformMatrix;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
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

public class ShadowObjLoaderExample extends SFTutorial{

	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	static float xL= 1;
	static float yL= -1;
	static float zL= -1;
	
	private float[] projection= ProjectionMatrix.identity();// gira la camera
	private float[] transform= TransformMatrix.identity(); //gira l'oggetto e basta
	
	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightData;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		ShadowObjLoaderExample tut03Bitmap=new ShadowObjLoaderExample();
		//String[] materials={"BasicMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());

			ShadowObjLoaderExample.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(),"BasicPNTransform", "BasicMat","BasicLSPN"/*"SpecularColor"*/);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		//material
		ShadowObjLoaderExample.materialData=SFTutorialsUtilities.generateMaterialData("BasicMat",0/*program, 0,0*/);
		SFVertex3f[] materialData1={new SFVertex3f(1,0,0)/*, new SFVertex3f(0.1f,0.1f,0.1f)*/}; //questa riga è sbagliata, non è la struttura che si aspetta
		materialReference=SFTutorialsUtilities.generateStructureDataReference(program, ShadowObjLoaderExample.materialData, materialData1);
		
		//Light
		ShadowObjLoaderExample.lightData=SFTutorialsUtilities.generateLightData(program, 0);
		SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(xL,yL,zL)};
		lightReference=SFTutorialsUtilities.generateStructureDataReference(program, ShadowObjLoaderExample.lightData, lightData);
		
		
		
		tut03Bitmap.prepareFrame("Riferimento", 600, 600);
	}
	
	@Override
	public void init() {
		//loadImageTexture("models/Chrysanthemum.jpg");
		
		
	}
	
	
	@Override
	public void render() {
		
		SFPipeline.getSfPipelineGraphics().setupTransform(transform);
		SFPipeline.getSfPipelineGraphics().setupProjection(projection);
		
		
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		//load material data
		if(materialData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData,0, materialReference.getIndex());
		
		//load light data
		if(lightData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData,0, lightReference.getIndex());
		
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(0);//Lod value is actually ignored.
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}
