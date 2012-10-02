package shadow.pipeline.openGL20.images;

import javax.media.opengl.GL;

import shadow.image.SFBitmap;
import shadow.image.SFBufferData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTextureFactory;

public class SFGL20RenderedTextureFactory implements SFRenderedTextureFactory{

	public static int getGLFormat(SFImageFormat format){
		switch (format) {
			case ARGB8: return GL.GL_RGBA8;
			case DEPTH16: return GL.GL_DEPTH_COMPONENT16;
			case DEPTH8: return GL.GL_DEPTH_COMPONENT16;
			case RGB565: return GL.GL_RGB565;
			case STENCIL8: return GL.GL_STENCIL_INDEX8;
			case ARGB4: return GL.GL_RGBA4;
			case GRAY8: return GL.GL_LUMINANCE;
			case RGB8: return GL.GL_RGB;
	
		default:
			return GL.GL_RGBA8;
		}
	}

	@Override
	public SFBufferData generatePlainBuffer(int width, int height) {
		SFGL20RenderBuffer renderBuffer = new SFGL20RenderBuffer(width, height, SFImageFormat.ARGB8);
		renderBuffer.build();
		return renderBuffer;
	}
	
	@Override
	public SFBufferData generatePlainBuffer(int width, int height,
			SFImageFormat format) {
		SFGL20RenderBuffer renderBuffer = new SFGL20RenderBuffer(width, height, format);
		renderBuffer.build();
		return renderBuffer;
	}
	
	@Override
	public SFPipelineTexture generateTextureBuffer(int width, int height,
			SFImageFormat format, Filter filters, WrapMode wrapS, WrapMode wrapT) {
		SFGL20Texture texture=new SFGL20Texture(width, height, format, filters, wrapS, wrapT);
		texture.build();
		return texture;
	}
	
	@Override
	public SFPipelineTexture generateBitmapTexture(SFBitmap bitmap, Filter filters,
			WrapMode wrapS, WrapMode wrapT) {
		SFGL20Texture texture=new SFGL20Texture(bitmap.getWidth(), bitmap.getHeight(), bitmap.getFormat(), filters, wrapS, wrapT);
		texture.loadBitmap(bitmap);
		return texture;
	}
	
	@Override
	public void destroyBuffer(SFBufferData bufferData) {
		if(bufferData instanceof SFGL20Texture)
			((SFGL20Texture)bufferData).destroy();
		if(bufferData instanceof SFGL20RenderBuffer)
			((SFGL20RenderBuffer)bufferData).destroy();	
	}
}
