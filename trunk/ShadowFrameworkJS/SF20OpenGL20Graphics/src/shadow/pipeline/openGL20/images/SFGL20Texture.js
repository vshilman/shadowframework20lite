
function SFGL20Texture(){
}

SFGL20Texture.prototype = {

	getGLMinFilter:function(){
		switch (getFilters()){
	case NEAREST: return GL.GL_NEAREST;//Warning: Not well Identified 
	case LINEAR: return GL.GL_LINEAR;//Warning: Not well Identified 
	case LINEAR_MIPMAP: return GL.GL_LINEAR_MIPMAP_LINEAR;//Warning: Not well Identified 
	default:			return GL.GL_LINEAR_MIPMAP_LINEAR;//Warning: Not well Identified 
	}
	},

	getGLMagFilter:function(){
		switch (getFilters()){
	case NEAREST: return GL.GL_NEAREST;//Warning: Not well Identified 
	case LINEAR: return GL.GL_LINEAR;//Warning: Not well Identified 
	case LINEAR_MIPMAP: return GL.GL_LINEAR;//Warning: Not well Identified 
	default:			return GL.GL_LINEAR;//Warning: Not well Identified 
	}
	},

	getGLWrap:function(mode){
		switch (mode){
	case REPEAT: return GL.GL_REPEAT;//Warning: Not well Identified 
	case MIRRORED_REPEAT: return GL.GL_MIRRORED_REPEAT;//Warning: Not well Identified 
	case CLAMP_TO_EDGE: return GL.GL_CLAMP_TO_EDGE;//Warning: Not well Identified 
	default:			return GL.GL_REPEAT;//Warning: Not well Identified 
	}
	},

	build:function(){
		 if ( textureObject==-1 ){
		 GL2  gl = SFGL2.getGL();
		 int  txo[] = new  int[1];
		gl.glGenTextures(1,txo,0);
		textureObject  = txo[0];
		gl.glBindTexture(GL.GL_TEXTURE_2D, textureObject);
		setupParameters(gl);
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, SFGL20RenderedTextureFactory.getGLFormat(getFormat()), getWidth(), getHeight(), 0,					GL.GL_RGB,GL.GL_UNSIGNED_BYTE, null);
	}
	},

	setupParameters:function(gl){
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, getGLMinFilter());
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, getGLMagFilter());
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, getGLWrap(getWrapS()));
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, getGLWrap(getWrapS()));
	},

	apply:function(textureLevel){
		 GL2  gl = SFGL2.getGL();
		build();
		gl.glActiveTexture(GL.GL_TEXTURE0+textureLevel);
		gl.glEnable(GL.GL_TEXTURE_2D);
		SFGL2.getGL().glBindTexture(GL.GL_TEXTURE_2D, textureObject);
	},

	loadBitmap:function(bitmap){
		 GL2  gl = SFGL2.getGL();
		 if ( textureObject==-1 ){
		 int  txo[] = new  int[1];
		gl.glGenTextures(1,txo,0);
		textureObject  = txo[0];
	}
		gl.glBindTexture(GL.GL_TEXTURE_2D, textureObject);
		setupParameters(gl);
	//if(bitmap.getFormat()==SFFormat.GRAY8);//Warning: Not well Identified 
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL2.GL_LUMINANCE, 					bitmap.getWidth(), bitmap.getHeight(), 0, GL2.GL_RED, GL.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
	//}
		else{
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 					bitmap.getWidth(), bitmap.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, (ByteBuffer)bitmap.getData());
	}
		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
	},

	destroy:function(){
		 GL2  gl = SFGL2.getGL();
		 int[] textures={
		textureObject;
	}
		gl.glDeleteTextures(GL.GL_TEXTURE_2D, textures, 1);
	},

	getTextureObject:function(){
		return ,textureObject;
	}

};