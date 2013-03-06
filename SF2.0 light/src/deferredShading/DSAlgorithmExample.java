package deferredShading;

/*
 * todo. possibilitÓ di scelta di modello geometrico, colori materiali, luci
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
import deferredShading.DSAlgorithm;

public class DSAlgorithmExample extends SFTutorial{
	
	private  SFPipelineTexture texture0; 
	private  SFPipelineTexture texture1;
	private  SFPipelineTexture texture2;
	private  SFPipelineTexture texture3;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		DSAlgorithmExample example=new DSAlgorithmExample();
		example.prepareFrame("Deferred Shading", 600, 600);
	}
	
	@Override
	public void init() {
		
		texture0 = DSAlgorithm.textureSetUp();
		texture1 = DSAlgorithm.textureSetUp();
		texture2 = DSAlgorithm.textureSetUp();
		texture3 = DSAlgorithm.textureSetUp();
		
		
		DSAlgorithm.firstPass(texture0,texture1,texture2,texture3);
		
	}
	
	
	@Override
	public void render() {
		
		DSAlgorithm.secondPass(texture0,texture1,texture2,texture3);
		
	}
}
