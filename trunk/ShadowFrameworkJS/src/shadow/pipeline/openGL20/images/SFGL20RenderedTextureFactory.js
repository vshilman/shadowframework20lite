

function SFGL20RenderedTextureFactory_getGLFormat(format){
	throw new "Not yet implemented";
}


function SFGL20RenderedTextureFactory(){

}


SFGL20RenderedTextureFactory.prototype["generatePlainBuffer"]=function(width,height){
	var renderBuffer = new SFGL20RenderBuffer(width, height, SFImageFormat_ARGB8);
	renderBuffer.build();
	return renderBuffer;
};

SFGL20RenderedTextureFactory.prototype["generatePlainBuffer"]=function(width,height,format){
	var renderBuffer = new SFGL20RenderBuffer(width, height, format);
	renderBuffer.build();
	return renderBuffer;
};

SFGL20RenderedTextureFactory.prototype["generateTextureBuffer"]=function(width,height,format,filters,wrapS,wrapT){
	var texture=new SFGL20Texture(width, height, format, filters, wrapS, wrapT);
	texture.build();
	return texture;
};

	
SFGL20RenderedTextureFactory.prototype["generateBitmapTexture"]=function(bitmap,filters,wrapS,wrapT){
		
	var texture=new SFGL20Texture(bitmap.getWidth(), bitmap.getHeight(), bitmap.getFormat(), filters, wrapS, wrapT);

	texture.loadBitmap(bitmap);
	return texture;
};
	
	
SFGL20RenderedTextureFactory.prototype["destroyBuffer"]=function(bufferData){
	bufferData.destroy();
};	
