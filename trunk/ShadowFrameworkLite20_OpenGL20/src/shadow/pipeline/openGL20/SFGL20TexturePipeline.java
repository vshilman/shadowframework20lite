
package shadow.pipeline.openGL20;

import java.util.HashMap;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.pipeline.SFTextureData;
import shadow.pipeline.SFTexturePipeline;
import shadow.pipeline.openGL20.textures.SFGPUTexture;


public class SFGL20TexturePipeline implements SFTexturePipeline{

	private HashMap<Integer, Integer> frameBuffers=new HashMap<Integer, Integer>();
	private float[] viewport=new float[4];

	static SFGPUTexture texture=new SFGPUTexture();
	
	@Override
	public int beginNewRenderedTexture(SFTextureData textureData) {

		texture.initShadowTexture(textureData);
		int fbo=texture.getFbo();
		int txo=texture.getTexture_object();
		frameBuffers.put(txo,fbo);
		
		return txo;
	}
	
	@Override
	public void destroyRenderedTexture(int texture) {

		GL2 gl=SFGL2.getGL();
		int frameBuffer[]=new int[1];
		frameBuffer[0]=frameBuffers.get(texture);
		gl.glDeleteFramebuffers(1,frameBuffer,0);
		frameBuffer[0]=texture;
		gl.glDeleteTextures(1,frameBuffer,0);
		
	}
	
	
	@Override
	public void endRenderedTexture(int texture) {
		SFGL20TexturePipeline.texture.closeShadowTexture();
	}

}

