
function SFGL20RenderedTexture(){
	this.fbo=0;
}

SFGL20RenderedTexture.prototype["initShadowTexture"]=function(data){
	
		var gl=SFGL2_getGL();
		
		
		//Step 5: generate frame buffer. Bind to buffer
		//int nfbo[]=new int[1];
		//gl.glGenFramebuffers(1, nfbo,0);
		this.fbo=gl.createFramebuffer();

		gl.bindFramebuffer(gl.FRAMEBUFFER,this.fbo);
		
		this.fbo.width=data.getColorsData()[0].getWidth();
		this.fbo.height=data.getColorsData()[0].getHeight();
		
		for(var i=0;i<1;i++){
			var colorBuffer = data.getColorsData()[i];
			colorBuffer.build();
		}

	    var renderbuffer = gl.createRenderbuffer();
	    gl.bindRenderbuffer(gl.RENDERBUFFER, renderbuffer);
	    gl.renderbufferStorage(gl.RENDERBUFFER, gl.DEPTH_COMPONENT16, this.fbo.width, this.fbo.height);
		
			var colorBuffer = data.getColorsData()[0];
			var texture_object=colorBuffer.getTextureObject();
			this.texture_object=texture_object;
		gl.framebufferTexture2D(gl.FRAMEBUFFER, gl.COLOR_ATTACHMENT0, gl.TEXTURE_2D, texture_object, 0);
    	gl.framebufferRenderbuffer(gl.FRAMEBUFFER, gl.DEPTH_ATTACHMENT, gl.RENDERBUFFER, renderbuffer);

		/*for(var index=0;index<1;index++){
			var colorBuffer = data.getColorsData()[index];
			var texture_object=colorBuffer.getTextureObject();
			gl.framebufferTexture2D(gl.FRAMEBUFFER,
					gl.COLOR_ATTACHMENT0+index,
					gl.TEXTURE_2D,texture_object,0);
			//colorBuffer.build();
			index++;
		}*/		
	
		        gl.bindTexture(gl.TEXTURE_2D, null);
		        gl.bindRenderbuffer(gl.RENDERBUFFER, null);
		        gl.bindFramebuffer(gl.FRAMEBUFFER, null);

		gl.bindFramebuffer(gl.FRAMEBUFFER,this.fbo);

		//Step 6: Viewport storing
		//this.vp=new Array();
		this.vp=gl.getParameter(gl.VIEWPORT);
		
		//Step 7: New Viewport for texture generation

		gl.viewport(0, 0, this.fbo.width,this.fbo.height);
		
		gl.disable(gl.TEXTURE_2D);
		gl.clearColor(1,1,1,0);
		gl.clearDepth(2);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
		gl.enable(gl.DEPTH_TEST);
};

SFGL20RenderedTexture.prototype["closeShadowTexture"]=function(){
	
		var gl=SFGL2_getGL();

			gl.bindTexture(gl.TEXTURE_2D,this.texture_object);
			gl.generateMipmap(gl.TEXTURE_2D);

    		gl.bindFramebuffer(gl.FRAMEBUFFER, null);
			
			gl.viewport(0,0,500,500);
		//gl.disable(gl.DEPTH_TEST);

		gl.bindFramebuffer(gl.FRAMEBUFFER,null);

		//gl.deleteFramebuffer(this.fbo);
		
		//restoring of the viewport
		gl.viewport(this.vp[0], this.vp[1],this.vp[2], this.vp[3]);
};

SFGL20RenderedTexture.prototype["getFbo"]=function(data){
	return this.fbo;
};