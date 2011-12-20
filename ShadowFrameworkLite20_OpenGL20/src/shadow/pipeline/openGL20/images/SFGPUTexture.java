/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.pipeline.openGL20.images;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.image.SFTextureData;
import shadow.pipeline.openGL20.SFGL2;

/**
 * 
 * This class represent some texture which can be managed from the GPU
 * 
 * @author Alessandro Martinelli
 */
public class SFGPUTexture {

	private int fbo;
	private int texture_object;
	private float vp[]=new float[4];
	
	public void initShadowTexture(SFTextureData data) {

		GL2 gl=SFGL2.getGL();
		
		int txo[]=new int[1];
		gl.glGenTextures(1,txo,0);
		texture_object=txo[0];
		
		//Step 5: generate frame buffer. Bind to buffer
		int nfbo[]=new int[1];
		gl.glGenFramebuffers(1, nfbo,0);
		fbo=nfbo[0];
		
		//Step 8: texture bindings (txo and txo4)
		gl.glBindTexture(GL.GL_TEXTURE_2D, texture_object);
		
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA8, data.getWidth(), data.getHeight(), 0,
	             GL.GL_RGB,GL.GL_UNSIGNED_BYTE, null);	
		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
		gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
		
		gl.glBindFramebuffer(GL.GL_FRAMEBUFFER,fbo);
		
		//Step 6: Viewport storing
		float vp[]=new float[4];
		gl.glGetFloatv(GL.GL_VIEWPORT,vp,0);
		
		//Step 7: New Viewport for texture generation
		
		gl.glViewport(0, 0, data.getWidth(),data.getHeight());
		
		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,
				GL.GL_COLOR_ATTACHMENT0,
				GL2.GL_TEXTURE_2D,texture_object,0);

		//Step 1: generate a texture object to store the buffer.
		int n[]=new int[1];
		gl.glGenTextures(1, n,0);
		int depthBuffer=n[0];

		//Step 8: texture bindings (txo and txo4) 	
		gl.glActiveTexture(GL.GL_TEXTURE0);
		gl.glBindTexture(GL.GL_TEXTURE_2D, depthBuffer);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_COMPARE_MODE, GL2.GL_COMPARE_R_TO_TEXTURE);
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_GENERATE_MIPMAP, GL.GL_TRUE);
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL2.GL_DEPTH_COMPONENT,data.getWidth(), data.getHeight(), 0,
	             GL2.GL_DEPTH_COMPONENT,GL.GL_FLOAT, null);
		
		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,
				GL.GL_DEPTH_ATTACHMENT,
				GL.GL_TEXTURE_2D,depthBuffer,0);
		
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glClearColor(1,1,1,0);
		gl.glClearDepth(2);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_DEPTH_TEST);
	}
	
	
	public void closeShadowTexture() {

		GL2 gl=SFGL2.getGL();

		gl.glDisable(GL.GL_DEPTH_TEST);

		gl.glBindFramebuffer(GL2.GL_FRAMEBUFFER,0);

		/*
		 * FIXME : actually i can't use mipmapping on texture, must be solved, which is too important.
		 */
		gl.glActiveTexture(GL.GL_TEXTURE0);
		gl.glBindTexture(GL.GL_TEXTURE_2D, texture_object);
		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
		
		//restoring of the viewport
		gl.glViewport((int)vp[0], (int)vp[1], (int)vp[2], (int)vp[3]);
	}

	public int getFbo() {
		return fbo;
	}

	public int getTexture_object() {
		return texture_object;
	}
	
}
