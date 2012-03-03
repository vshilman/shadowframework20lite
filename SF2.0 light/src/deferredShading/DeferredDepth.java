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

public class DeferredDepth extends SFTutorial {
	
	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		DeferredDepth test=new DeferredDepth();
		String[] materials={"BlackMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"));

			DeferredDepth.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(), materials, "DepthStep");
		
	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		test.prepareFrame("Deferred Shading:Depth", 600, 600);
	}
	@Override
	public void init() {
	}
	
	
	@Override
	public void render() {

		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(0);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}