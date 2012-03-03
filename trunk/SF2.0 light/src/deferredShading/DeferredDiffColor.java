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

public class DeferredDiffColor extends SFTutorial {
	
	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	private static SFStructureArray materialArray;
	private static SFStructureReference materialReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		DeferredDiffColor test=new DeferredDiffColor();
		String[] materials={"BasicMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"));

			DeferredDiffColor.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(), materials, "NoLights");
		
		//material pass: color diff e color amb
		test.materialArray=SFTutorialsUtilities.generateMaterialData(test.program, 0, 0);
		SFVertex3f[] materialData={new SFVertex3f(1,0,0),new SFVertex3f(0.1f,0.1f,0.1f)};
		test.materialReference=SFTutorialsUtilities.generateStructureDataReference(test.program, test.materialArray, materialData);
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		test.prepareFrame("Deferred Shading: Diff Color", 600, 600);
	}
	@Override
	public void init() {

	}
	
	
	@Override
	public void render() {

		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		SFPipeline.getSfPipelineGraphics().loadStructureData(materialArray, materialReference.getMaterialIndex());
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(0);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}