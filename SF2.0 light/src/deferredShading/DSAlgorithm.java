package deferredShading;

/*
 * Deferred Shading: algoritmo a 2 passi
 * 
 * 1. salvataggio texture
 * (nell'init carico programma con materiali e mondo 3d e salvo in texture)
 * 1a. diffcolor
 * 1b. speccolor
 * 1c. position
 * 1d. normal
 * 
 * 2. utilizzo texture per computo illumminazione
 * (nel render c'è il secondo passo, leggo le texture e le informazioni della luce)
 * 
 * problemi:
 * 1. la projection non funziona
 * 2. viene salvata solo la texture con apply(0)
 */
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
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFStructureReference;
import shadow.utils.SFTutorial;
import shadow.utils.SFTutorialsUtilities;
import deferredShading.DeferredShading;

public class DSAlgorithm extends SFTutorial{

	private static ArrayList<SFGeometry> geometries;
    private static SFProgram program;
	
	private  SFPipelineTexture texture0; 
	private  SFPipelineTexture texture1;
	private  SFPipelineTexture texture2;
	private  SFPipelineTexture texture3;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		DSAlgorithm example=new DSAlgorithm();
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		System.err.println("Number of geometries is "+geometries.size());
		
		DSAlgorithm.program= DeferredShading.First(shadowObjLoader.getPrimitive());
		
		example.prepareFrame("Deferred Shading", 600, 600);
	}
	
	@Override
	public void init() {
		
		texture0 = DeferredShading.textureSetUp();
		texture1 = DeferredShading.textureSetUp();
		texture2 = DeferredShading.textureSetUp();
		texture3 = DeferredShading.textureSetUp();
		
		
		DeferredShading.firstPass(program,geometries,texture0,texture1,texture2,texture3);
		
	}
	
	
	@Override
	public void render() {
		
		DeferredShading.secondPass(texture0,texture1,texture2,texture3);
		
	}
}
