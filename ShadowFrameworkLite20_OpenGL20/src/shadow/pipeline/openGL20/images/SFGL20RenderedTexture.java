package shadow.pipeline.openGL20.images;

import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.image.SFBufferData;
import shadow.image.SFRenderedTexture;
import shadow.pipeline.openGL20.SFGL2;

public class SFGL20RenderedTexture{

	private int fbo;
	private int depthReference;
	private int stencilReference;
	private int[] colorsReference;
	private float vp[]=new float[4];
	
	public void initShadowTexture(SFRenderedTexture data) {

		GL2 gl=SFGL2.getGL();
		
		SFBufferData depthData=data.getDepthBuffer();
		
		if(depthData!=null)
			((SFGL20ImageObject)depthData).build();
		
		SFBufferData stencilData=data.getStencilBuffer();
		if(stencilData!=null)
			((SFGL20ImageObject)stencilData).build();
		
		for (Iterator<SFBufferData> iterator = data.getColorsData().iterator(); iterator.hasNext();) {
			SFBufferData colorBuffer = iterator.next();
			((SFGL20ImageObject)colorBuffer).build();
		}

		//Step 5: generate frame buffer. Bind to buffer
		int nfbo[]=new int[1];
		gl.glGenFramebuffers(1, nfbo,0);
		fbo=nfbo[0];

		gl.glBindFramebuffer(GL.GL_FRAMEBUFFER,fbo);
		
		//Step 6: Viewport storing
		gl.glGetFloatv(GL.GL_VIEWPORT,vp,0);
		
		//Step 7: New Viewport for texture generation

		gl.glViewport(0, 0, data.getColorsData().get(0).getWidth(),data.getColorsData().get(0).getHeight());

		int buffers[] = new int[data.getColorsData().size()];
		for (int i = 0; i < data.getColorsData().size(); i++) {
			buffers[i]= GL2.GL_COLOR_ATTACHMENT0+i;	
		}
		gl.glDrawBuffers(buffers.length, buffers,0);

		int index=0;
		for (Iterator<SFBufferData> iterator = data.getColorsData().iterator(); iterator.hasNext();) {
			SFBufferData colorBuffer = iterator.next();
			int texture_object=((SFGL20Texture)colorBuffer).getTextureObject();
			gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,
					GL2.GL_COLOR_ATTACHMENT0+index,
					GL2.GL_TEXTURE_2D,texture_object,0);
			((SFGL20ImageObject)colorBuffer).build();
			index++;
		}

//		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,
//				GL.GL_COLOR_ATTACHMENT0,
//				GL2.GL_TEXTURE_2D,texture_object,0);

		//Step 8: texture bindings (txo and txo4) 	
//		gl.glActiveTexture(GL.GL_TEXTURE0);
//		gl.glBindTexture(GL.GL_TEXTURE_2D, depthBuffer);
//		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_COMPARE_MODE, GL2.GL_COMPARE_R_TO_TEXTURE);
//		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_GENERATE_MIPMAP, GL.GL_TRUE);
//		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL2.GL_DEPTH_COMPONENT,data.getWidth(), data.getHeight(), 0,
//	             GL2.GL_DEPTH_COMPONENT,GL.GL_FLOAT, null);

		if(depthData!=null){
			int textureObject=((SFGL20RenderBuffer)depthData).renderBuffer;
			gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, GL.GL_DEPTH_ATTACHMENT,
                   GL. GL_RENDERBUFFER, textureObject);
		}

		if(stencilData!=null){
			int textureObject=((SFGL20RenderBuffer)stencilData).renderBuffer;
			gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, GL.GL_STENCIL_ATTACHMENT,
                   GL. GL_RENDERBUFFER, textureObject);
		}
		
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glClearColor(1,1,1,0);
		gl.glClearDepth(2);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_DEPTH_TEST);
		//gl.glDisable(GL.GL_DEPTH_TEST);
	
	}
	
	
	public void closeShadowTexture() {

		GL2 gl=SFGL2.getGL();

		gl.glDisable(GL.GL_DEPTH_TEST);

		gl.glBindFramebuffer(GL2.GL_FRAMEBUFFER,0);

//		/*
//		 * FIXME : actually i can't use mipmapping on texture, must be solved, which is too important.
//		 */
//		gl.glActiveTexture(GL.GL_TEXTURE0);
//		gl.glBindTexture(GL.GL_TEXTURE_2D, texture_object);
//		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
		
		//restoring of the viewport
		gl.glViewport((int)vp[0], (int)vp[1], (int)vp[2], (int)vp[3]);
	}

	public int getFbo() {
		return fbo;
	}

}
