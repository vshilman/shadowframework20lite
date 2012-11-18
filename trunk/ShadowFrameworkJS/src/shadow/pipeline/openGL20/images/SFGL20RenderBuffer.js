

function SFGL20RenderBuffer(width,height,format){
	this.width=width;
	this.height=height;
	this.format=format;
	this.renderBuffer=-1;
}


inherit(SFGL20RenderBuffer,SFBufferData);


SFGL20ListData.prototype["build"]=function(){
	if (renderBuffer == -1) {
		throw "Not yet translated";
		/*
		int rbo[] = new int[1];
		SFGL2.getGL().glGenRenderbuffers(1, rbo, 0);
		renderBuffer = rbo[0];
		SFGL2.getGL().glRenderbufferStorage(GL.GL_RENDERBUFFER,
				SFGL20RenderedTextureFactory.getGLFormat(getFormat()),
				getWidth(), getHeight());*/
	}
};


SFGL20ListData.prototype["destroy"]=function(){
	throw "Not yet implemented";
	/*
	int[] textures = { renderBuffer };
	SFGL2.getGL().glDeleteRenderbuffers(GL.GL_TEXTURE_2D, textures, 1);*/
};
