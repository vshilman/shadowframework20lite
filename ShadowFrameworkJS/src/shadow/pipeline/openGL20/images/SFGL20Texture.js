

function SFGL20Texture(width, height, format, filters, wrapS, wrapT){
	this.textureObject=-1;
	this.width = width;
	this.height = height;
	this.format = format;
	this.filters = filters;
	this.WrapS = wrapS;
	this.WrapT = wrapT;
}

inherit(SFGL20Texture,SFPipelineTexture);

function SFGL20Texture_getGLFormat(format){
	switch (format) {
			case SFImageFormat_ARGB8: return gl.RGBA8;
			case SFImageFormat_DEPTH16: return gl.DEPTH_COMPONENT16;
			case SFImageFormat_DEPTH8: return gl.DEPTH_COMPONENT16;
			case SFImageFormat_RGB565: return gl.RGB565;
			case SFImageFormat_STENCIL8: return gl.STENCIL_INDEX8;
			case SFImageFormat_ARGB4: return gl.RGBA4;
			case SFImageFormat_GRAY8: return gl.LUMINANCE;
			case SFImageFormat_RGB8: return gl.RGB;
	
			default:
				return gl.RGBA8;
		}
}


SFGL20Texture.prototype["getGLMinFilter"]=function(){
	var gl=SFGL2_getGL();
	switch (this.getFilters()) {
		case Filter_NEAREST: return gl.NEAREST;
		case Filter_LINEAR: return gl.LINEAR;
		case Filter_LINEAR_MIPMAP: return gl.LINEAR_MIPMAP_LINEAR;
	default:
		return gl.LINEAR_MIPMAP_LINEAR;
	}
};


SFGL20Texture.prototype["getGLMagFilter"]=function(){
	var gl=SFGL2_getGL();
	switch (this.getFilters()) {
		case Filter_NEAREST: return gl.NEAREST;
		case Filter_LINEAR: return gl.LINEAR;
		case Filter_LINEAR_MIPMAP: return gl.LINEAR;
	default:
		return GL.GL_LINEAR;
	}
};

	
SFGL20Texture.prototype["getGLWrap"]=function(mode){
	var gl=SFGL2_getGL();
	switch (mode) {
		case WrapMode_REPEAT: return gl.REPEAT;
		case WrapMode_MIRRORED_REPEAT: return gl.MIRRORED_REPEAT;
		case WrapMode_CLAMP_TO_EDGE: return gl.CLAMP_TO_EDGE;
	default:
		return gl.REPEAT;
	}
};
	
SFGL20Texture.prototype["build"]=function(){
	
	if(this.textureObject==-1){
		
		var gl=SFGL2_getGL();
		
		this.textureObject=gl.createTexture();
		
		gl.bindTexture(gl.TEXTURE_2D, this.textureObject);
		//this.setupParameters(gl);
		
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
		
		var w=this.getWidth();
		var h=this.getHeight();
		gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, w, h, 0, gl.RGB, gl.UNSIGNED_BYTE, null);
		
		//gl.texImage2D(gl.TEXTURE_2D, 0, SFGL20Texture_getGLFormat(this.getFormat()),w,h, 0,
		//		gl.RGB,gl.UNSIGNED_BYTE, null);
		//gl.generateMipmap(gl.TEXTURE_2D);
  				
		/*gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, SFGL20RenderedTextureFactory.getGLFormat(getFormat()), getWidth(), getHeight(), 0,
					GL.GL_RGB,GL.GL_UNSIGNED_BYTE, null);
			gl.glGenerateMipmap(GL.GL_TEXTURE_2D);*/
	}
};

SFGL20Texture.prototype["setupParameters"]=function(gl){
	
	gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, this.getGLMinFilter());
	gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, this.getGLMagFilter());
	gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, this.getGLWrap(this.getWrapS()));
	gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, this.getGLWrap(this.getWrapS()));
};


SFGL20Texture.prototype["apply"]=function(textureLevel){
	
	var gl=SFGL2_getGL();
	this.build();
	gl.activeTexture(gl.TEXTURE0+textureLevel);
	gl.bindTexture(gl.TEXTURE_2D, this.textureObject);

};


SFGL20Texture.prototype["loadBitmap"]=function(bitmap){
	
	var gl=SFGL2_getGL();
	
	//if(this.textureObject==-1){
	//	this.textureObject=gl.createTexture();
	//}
	//gl.bindTexture(gl.TEXTURE_2D, this.textureObject);
	//this.setupParameters(gl);
	
	
	var texture = gl.createTexture();
	gl.bindTexture(gl.TEXTURE_2D, texture);
    //gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, bitmap.getWidth(), bitmap.getHeight(), 0, gl.RGB, gl.UNSIGNED_BYTE, bitmap.getData());
    
    if(bitmap.getFormat()==SFImageFormat_GRAY8){
		gl.texImage2D(gl.TEXTURE_2D, 0, gl.LUMINANCE, 
				bitmap.getWidth(), bitmap.getHeight(), 0, gl.LUMINANCE, gl.UNSIGNED_BYTE,bitmap.getData());
	}else{
		gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, 
				bitmap.getWidth(), bitmap.getHeight(), 0, gl.RGB, gl.UNSIGNED_BYTE,bitmap.getData());
	}
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
	gl.texParameteri ( gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT ) ;
	gl.texParameteri ( gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT ) ;
	gl.generateMipmap ( gl.TEXTURE_2D ) ;
  
    this.textureObject=texture;
	//gl.generateMipmap(GL.TEXTURE_2D);
};
	
	
SFGL20Texture.prototype["destroy"]=function(){
	
	var gl=SFGL2_getGL();
	gl.deleteTexture(this.textureObject);
};
	
SFGL20Texture.prototype["getTextureObject"]=function(){
	return this.textureObject;
};

