package deferredShading;

/*
 * todo. possibilità di scelta di modello geometrico, colori materiali, luci
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
	
	private  SFPipelineTexture texture0; 
	private  SFPipelineTexture texture1;
	private  SFPipelineTexture texture2;
	private  SFPipelineTexture texture3;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		DSAlgorithm example=new DSAlgorithm();
		example.prepareFrame("Deferred Shading", 600, 600);
	}
	
	@Override
	public void init() {
		
		texture0 = DeferredShading.textureSetUp();
		texture1 = DeferredShading.textureSetUp();
		texture2 = DeferredShading.textureSetUp();
		texture3 = DeferredShading.textureSetUp();
		
		
		DeferredShading.firstPass(texture0,texture1,texture2,texture3);
		
	}
	
	
	@Override
	public void render() {
		
		DeferredShading.secondPass(texture0,texture1,texture2,texture3);
		
	}
}
