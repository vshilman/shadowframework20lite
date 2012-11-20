package com.shadow.android.opengles20.tests;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import shadow.image.SFTexture;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFProgram;
import shadow.system.SFInitiator;
import shadow.system.SFUpdater;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import com.shadow.android.opengles20.SFGL20Pipeline;
import com.shadow.android.opengles20.SFGL20PipelineGraphics;

public class SFTestTextureRenderer implements Renderer {

	private SFTexture texture;
	private SFProgram program;
	
	public SFTestTextureRenderer(SFTexture texture) {
		this.texture=texture;
		program=SFPipeline.getStaticImageProgram("TexturedMat", "BasicColor");
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    	SFGL20Pipeline.setup();
    	((SFGL20PipelineGraphics)(SFPipeline.getSfPipelineGraphics())).init();
    }
    
    public void onDrawFrame(GL10 unused) {

		SFInitiator.solveInitiables();
		SFUpdater.refresh();
		
		GLES20.glClearColor(1,1,0,1);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		
		try {
			
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			
			SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture.getTexture(), 0);
			
			SFPipeline.getSfPipelineGraphics().drawBaseQuad();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

}
