package deferredShading;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
import shadow.math.SFVertex3f;
import shadow.objloader.ShadowObjLoader;
import shadow.objloader.example.ShadowObjLoaderExample;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;
import shadow.renderer.data.SFStructureReference;
import shadow.system.SFArrayElementException;

public class DeferredFinal extends SFTutorial {
	
	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	private static SFStructureArray materialArray;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightArray;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		DeferredFinal test=new DeferredFinal();
		String[] materials={"BasicMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		//geometric pass: carico oggetto e ne calcolo i vettori di posizione, normali e coord texture
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"));
			//'Deferred'=shader che unisce i tre pasi dell'algoritmo e calcola il valore del colore finale
			DeferredFinal.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(), materials, "Deferred");
		
		//material pass: diffColor, ambColor
		test.materialArray=SFTutorialsUtilities.generateMaterialData(test.program, 0, 0);
		SFVertex3f[] materialData={new SFVertex3f(1,0,0),new SFVertex3f(0.1f,0.1f,0.1f)};
		test.materialReference=SFTutorialsUtilities.generateStructureDataReference(test.program, test.materialArray, materialData);
		
		//light pass: intensità e posizione della luce
		test.lightArray=SFTutorialsUtilities.generateLightData(test.program, 0);
		SFVertex3f[] lightData={new SFVertex3f(1,1,1),new SFVertex3f(0,1,0)};
		test.lightReference=SFTutorialsUtilities.generateStructureDataReference(test.program, test.lightArray, lightData);
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		test.prepareFrame("Deferred Shading", 600, 600);
	}
	@Override
	public void init() {
	
	}
	
	
	@Override
	public void render() {

		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		SFPipeline.getSfPipelineGraphics().loadStructureData(materialArray, materialReference.getMaterialIndex());
		
		SFPipeline.getSfPipelineGraphics().loadStructureData(lightArray, lightReference.getMaterialIndex());
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(0);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}