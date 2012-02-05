
function SFGL20RenderedTextureFactory(){
}

SFGL20RenderedTextureFactory.prototype = {

	getGLFormat:function(format){
		switch (format){
	case ARGB8: return GL.GL_RGBA8;//Warning: Not well Identified 
	case DEPTH16: return GL.GL_DEPTH_COMPONENT16;//Warning: Not well Identified 
	case DEPTH8: return GL.GL_DEPTH_COMPONENT16;//Warning: Not well Identified 
	case RGB565: return GL.GL_RGB565;//Warning: Not well Identified 
	case STENCIL8: return GL.GL_STENCIL_INDEX8;//Warning: Not well Identified 
	case ARGB4: return GL.GL_RGBA4;//Warning: Not well Identified 
	case GRAY8: return GL.GL_LUMINANCE;//Warning: Not well Identified 
	case RGB8: return GL.GL_RGB;//Warning: Not well Identified 
	default:			return GL.GL_RGBA8;//Warning: Not well Identified 
	}
	},

	generatePlainBuffer:function(width, height){
		 SFGL20RenderBuffer  renderBuffer   = new  SFGL20RenderBuffer(width, height, SFFormat.ARGB8);
		renderBuffer.build();
		return ,renderBuffer;
	}

};