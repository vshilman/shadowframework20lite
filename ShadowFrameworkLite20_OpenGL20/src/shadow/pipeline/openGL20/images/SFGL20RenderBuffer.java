package shadow.pipeline.openGL20.images;

import javax.media.opengl.GL;

import shadow.image.SFBufferData;
import shadow.image.SFImageFormat;
import shadow.pipeline.openGL20.SFGL2;

/**
 * An object wrapping most of RenderBuffers functionalities
 * 
 * @author Alessandro
 */
public class SFGL20RenderBuffer extends SFBufferData implements
		SFGL20ImageObject {

	int renderBuffer = -1;

	public SFGL20RenderBuffer(int width, int height, SFImageFormat format) {
		super(width, height, format);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.pipeline.openGL20.images.SFGL20ImageObject#build()
	 */
	@Override
	public void build() {

		if (renderBuffer == -1) {
			int rbo[] = new int[1];
			SFGL2.getGL().glGenRenderbuffers(1, rbo, 0);
			renderBuffer = rbo[0];
			SFGL2.getGL().glRenderbufferStorage(GL.GL_RENDERBUFFER,
					SFGL20RenderedTextureFactory.getGLFormat(getFormat()),
					getWidth(), getHeight());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.pipeline.openGL20.images.SFGL20ImageObject#destroy()
	 */
	@Override
	public void destroy() {
		int[] textures = { renderBuffer };
		SFGL2.getGL().glDeleteRenderbuffers(GL.GL_TEXTURE_2D, textures, 1);
	}
}
