package com.shadow.android.opengles20.tests;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFRenderer;
import shadow.renderer.SFStructureReference;
import shadow.system.SFArrayElementException;
import shadow.system.SFInitiator;
import shadow.system.SFUpdater;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import com.shadow.android.opengles20.SFGL20Pipeline;
import com.shadow.android.opengles20.SFGL20PipelineGraphics;

public class SFTestRenderer implements Renderer {

	private static float unitVolumeSize=400.0f;
	private SFRenderer renderer;
	private SFNode node;
	
	private float rotation=0;
	
	public SFTestRenderer(SFNode node) {
		super();
		this.node = node;
		
		renderer=new SFRenderer();
		
		//Prepare Camera
		SFCamera camera=new SFCamera(new SFVertex3f(0,0,0), new SFVertex3f(0,0,1), 
				new SFVertex3f(1,0,0), new SFVertex3f(0,1,0), 1, 1, 20);
		//camera.setChanged(true);
		camera.extractTransform();
		renderer.setCamera(camera);
		
		//Prepare Light
		String lightProgramName="BasicLSPN2";
		SFStructureArray lightArray=generateLightData(lightProgramName, 0);
		SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)};
		SFStructureReference lightReference=generateStructureDataReference( lightArray, lightData);
		SFProgramModuleStructures programAsset=new SFProgramModuleStructures("BasicLSPN2");
		programAsset.getData().add(lightReference);
		renderer.setLight(programAsset);
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    	SFGL20Pipeline.setup();
    	((SFGL20PipelineGraphics)(SFPipeline.getSfPipelineGraphics())).init();
    }
    
    public void onDrawFrame(GL10 unused) {
		
		SFInitiator.solveInitiables();
		SFUpdater.refresh();
    	
		float[] matrix=renderer.getCamera().extractTransform();
		
		float[] viewport=new float[4];
		GLES20.glGetFloatv(GLES20.GL_VIEWPORT, viewport, 0);
		
		//Log.e("SFTest ","width:"+viewport[2]+" "+viewport[3]);
		
		float width=viewport[2];
		float height=viewport[3];
		for (int i = 0; i < 4; i++) {
			matrix[i]*=unitVolumeSize/width;
			matrix[i+4]*=unitVolumeSize/height;
		}
		
		GLES20.glClearColor(0.4f,0.6f,0.4f,1);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glEnable(GLES20.GL_BLEND);
//		GLES20.glEnable(GLES20.GL_ALPHA_TEST);
//		GLES20.glAlphaFunc(GLES20.GL_GREATER, 0.1f);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		
		try {
			
			rotation+=0.25f;
			
//			SFMatrix3f mtr=SFMatrix3f.getRotationY(rotation);
//			node.getTransform().setOrientation(mtr);
			
			SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
		
//			if(rotateModelX || rotateModelY)
//				rotateModel();
			
			renderer.render(getNode());
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

	public SFNode getNode() {
		return node;
	}

	public void setNode(SFNode node) {
		this.node = node;
	}
  

	public static SFStructureArray generateLightData(String program,int lightIndex){
		//note it supposes to have only one structure..
		SFPipelineStructure materialStructure=((SFProgramModule)(SFPipeline.getProgramModule(program))).getComponents()[0]
                    .getStructures().get(lightIndex).getStructure();
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructure); 
		return materialData;
	}
	
	private static SFStructureReference generateStructureDataReference(SFStructureArray lightData,SFVertex3f[] data){
		SFStructureReference materialReference=new SFStructureReference(lightData,lightData.generateElement()); 
		SFStructureData mat=new SFStructureData(materialReference.getStructure());
		for (int i = 0; i < data.length; i++) {
			((SFVertex3f)mat.getValue(i)).set(data[i]);
		}
		try {
			materialReference.setStructureData(mat);
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		return materialReference;
	}
}
