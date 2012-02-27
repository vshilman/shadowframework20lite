package shadow.objloader.example;


import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
import shadow.math.SFVertex3f;
import shadow.objloader.ShadowObjLoader;
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
import shadow.renderer.data.SFStructureReference;
import shadow.system.SFArrayElementException;

public class ShadowObjLoaderExample extends SFTutorial{

	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightData;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		ShadowObjLoaderExample tut03Bitmap=new ShadowObjLoaderExample();
		String[] materials={"TexturedMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/cubo.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"));

			ShadowObjLoaderExample.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(), materials, "BasicLSPN");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		//Light
		SFPipelineStructure lighStructure=SFPipeline.getStructure("PLight01");
		SFPipelineStructureInstance lightStructureInstance=((List<SFPipelineStructureInstance>)(program.getLightStep().getStructures())).get(0);
		lightData=SFPipeline.getSfPipelineMemory().generateStructureData(lightStructureInstance.getStructure()); 
		lightReference=new SFStructureReference(lightData,lightData.generateElement()); 
		SFStructureData lit=new SFStructureData(lightStructureInstance.getStructure());
		((SFVertex3f)lit.getValue(0)).set3f(1, 1, 1);
		((SFVertex3f)lit.getValue(1)).set3f(0, 0, -1);
		try {
			lightReference.setStructureData(lit);
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	}
	
	@Override
	public void init() {
	loadImageTexture("models/Chrysanthemum.jpg");
	}
	
	
	@Override
	public void render() {

		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		//load material data
		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());
		
		//load light data
		SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(0);//Lod value is actually ignored.
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}
