
function SFGL20RenderBuffer(){
}

SFGL20RenderBuffer.prototype = {

	build:function(){
		if ( renderBuffer == -1 ){
		 	int  rbo[]   = new  int[1];
			SFGL2.getGL().glGenRenderbuffers(1, rbo, 0);
			renderBuffer    = rbo[0];
			SFGL2.getGL().glRenderbufferStorage(GL.GL_RENDERBUFFER,SFGL20RenderedTextureFactory.getGLFormat(getFormat()),					getWidth(), getHeight());
		}
	},

	destroy:function(){
		int[] textures  ={
			renderBuffer;
		}
		SFGL2.getGL().glDeleteRenderbuffers(GL.GL_TEXTURE_2D, textures, 1);
	}

};