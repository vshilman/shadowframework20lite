package oldversion;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
import shadow.objloader.ShadowObjLoader;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.utils.SFTutorial;

public class DeferredNormal extends SFTutorial {
	
	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	private float[] projection={1,0,0,0,  
			0,1,0,0,	
			0,0,1,0,
			0,0,0,1};
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		DeferredNormal test=new DeferredNormal();
		//String[] materials={"NormalMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"),new SFPipelineBuilder());

			DeferredNormal.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(),  "BasicTess", "NormalMat", "NoLights");
		
	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		test.prepareFrame("Deferred Shading:Normal", 600, 600);
	}
	@Override
	public void init() {
	}
	
	
	@Override
	public void render() {
		
		SFPipeline.getSfPipelineGraphics().setupProjection(projection);

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