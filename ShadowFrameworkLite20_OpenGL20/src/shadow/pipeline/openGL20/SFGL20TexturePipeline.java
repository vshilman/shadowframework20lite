
package shadow.pipeline.openGL20;

import shadow.image.SFBufferData;
import shadow.image.SFRenderedTexture;
import shadow.image.SFRenderedTextureFactory;
import shadow.pipeline.SFTexturePipeline;
import shadow.pipeline.openGL20.images.SFGL20RenderedTexture;
import shadow.pipeline.openGL20.images.SFGL20RenderedTextureFactory;


public class SFGL20TexturePipeline implements SFTexturePipeline{

	//private HashMap<Integer, Integer> frameBuffers=new HashMap<Integer, Integer>();
	
	static SFGL20RenderedTexture texture=new SFGL20RenderedTexture();
	
	private SFGL20RenderedTextureFactory factory=new SFGL20RenderedTextureFactory();
	
	@Override
	public void beginNewRenderedTexture(SFRenderedTexture textureData) {
		texture.initShadowTexture(textureData);
		
		//what should i do?
	}
	
//	@Override
//	public int beginNewRenderedTexture(SFTextureData textureData) {
//
//		texture.initShadowTexture(textureData);
//		int fbo=texture.getFbo();
//		int txo=texture.getTexture_object();
//		frameBuffers.put(txo,fbo);
//		
//		return txo;
//	}
	
	@Override
	public void destroyRenderedTexture(SFRenderedTexture textureData) {
		// TODO Auto-generated method stub	
		
		//Rendered texture are still not destroyed...
	}
	
//	@Override
//	public void destroyRenderedTexture(int texture) {
//
//		GL2 gl=SFGL2.getGL();
//		int frameBuffer[]=new int[1];
//		frameBuffer[0]=frameBuffers.get(texture);
//		gl.glDeleteFramebuffers(1,frameBuffer,0);
//		frameBuffer[0]=texture;
//		gl.glDeleteTextures(1,frameBuffer,0);
//		
//	}
	
	@Override
	public void endRenderedTexture(SFRenderedTexture textureData) {
		// TODO Auto-generated method stub
		SFGL20TexturePipeline.texture.closeShadowTexture();
	}
	
//	@Override
//	public void endRenderedTexture(int texture) {
//		SFGL20TexturePipeline.texture.closeShadowTexture();
//	}

	@Override
	public void destroyBufferData(SFBufferData bufferData) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public SFRenderedTextureFactory getRenderedTextureFactory() {
		return factory;
	}
}

