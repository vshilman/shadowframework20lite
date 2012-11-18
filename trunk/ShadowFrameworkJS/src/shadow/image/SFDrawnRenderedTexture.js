

function SFDrawnRenderedTexture(){
	
	this.initialized=false;
	this.textures=null;
	this.useDefaultDepthBuffer=false;
	this.useDefaultStencilBuffer=false;
	this.depthBuffer=-1;
	this.stencilBuffer=-1;
	this.renderer=new SFRenderer();
	this.node=null;
	this.renderedTexture=new SFRenderedTexture();
}


SFDrawnRenderedTexture.prototype["getTexture"]=function(index){
	return this.textures[index];
};

SFDrawnRenderedTexture.prototype["getTextureSize"]=function(){
	return this.textures.length;
};

SFDrawnRenderedTexture.prototype["init"]=function(){
	if(this.renderer==null || this.node==null)
			return;
		
		if(!this.initialized){
			
			for (var i = 0; i < this.textures.length; i++) {
				if(i!=this.depthBuffer && i!=this.stencilBuffer){
					var data=this.textures[i];
					this.textures[i]=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().
							generateTextureBuffer(data.getWidth(), data.getHeight(), 
							data.getFormat(),  data.getFilters(),
							data.getWrapS(), data.getWrapT());
					this.renderedTexture.addColorData(this.textures[i]);
				}
			}
			
			
			if(this.depthBuffer>=0){
				var data=this.textures[this.depthBuffer];
				this.textures[depthBuffer]=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().
						generateTextureBuffer(this.data.getWidth(), this.data.getHeight(), 
						this.data.getFormat(),  this.data.getFilters(),	this.data.getWrapS(), this.data.getWrapT());
				this.renderedTexture.setDepthBuffer(this.textures[this.depthBuffer]);
			}else if(this.useDefaultDepthBuffer){
				var data=this.textures[0];
				var bufferData=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().
					generatePlainBuffer(this.data.getWidth(), this.data.getHeight());
				this.renderedTexture.setDepthBuffer(bufferData);
			}
			
			if(this.stencilBuffer>=0){
				var data=textures[this.stencilBuffer];
				this.textures[stencilBuffer]=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().
						generateTextureBuffer(this.data.getWidth(), this.data.getHeight(), 
						this.data.getFormat(),  this.data.getFilters(),	this.data.getWrapS(), this.data.getWrapT());
				this.renderedTexture.setStencilBuffer(this.textures[this.stencilBuffer]);
			}else if(this.useDefaultStencilBuffer){
				var data=this.textures[0];
				var bufferData=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().
					generatePlainBuffer(this.data.getWidth(), this.data.getHeight());
				this.renderedTexture.setStencilBuffer(this.bufferData);
			}
			
			SFPipeline_getSfTexturePipeline().beginNewRenderedTexture(this.renderedTexture);

				var matrix=this.renderer.getCamera().extractTransform();
				SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
				this.renderer.render(this.node);
			
				this.renderer.getCamera().getF().set3f(new SFVertex3f(-2.0, 0, 0));
				matrix=this.renderer.getCamera().extractTransform();
				SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
				this.renderer.render(this.node);
			
				this.renderer.getCamera().getF().set3f(new SFVertex3f(+2.0, 0, 0));
				matrix=this.renderer.getCamera().extractTransform();
				SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
				this.renderer.render(this.node);
			
				this.renderer.getCamera().getF().set3f(new SFVertex3f(0, +2.0, 0));
				matrix=this.renderer.getCamera().extractTransform();
				SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
				this.renderer.render(this.node);
			
				this.renderer.getCamera().getF().set3f(new SFVertex3f(0, -2.0, 0));
				matrix=this.renderer.getCamera().extractTransform();
				SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
				this.renderer.render(this.node);
				
			SFPipeline_getSfTexturePipeline().endRenderedTexture(this.renderedTexture);
			
			SFPipeline_getSfTexturePipeline().destroyRenderedTexture(this.renderedTexture);
		}
		this.initialized=true;
};

SFDrawnRenderedTexture.prototype["update"]=function(){
	//
};
	
SFDrawnRenderedTexture.prototype["destroy"]=function(){
	if(this.initialized){
		for (var i = 0; i < this.textures.length; i++) {
			SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().destroyBuffer(this.textures[i]);
		}
	}
};


SFDrawnRenderedTexture.prototype["getDepthBuffer"]=function(){
	return this.depthBuffer;
};	
	
SFDrawnRenderedTexture.prototype["setDepthBuffer"]=function(depthBuffer){
		this.depthBuffer = depthBuffer;
};	
	
SFDrawnRenderedTexture.prototype["stencilBuffer"]=function(depthBuffer){
	return this.stencilBuffer;
};	


SFDrawnRenderedTexture.prototype["setStencilBuffer"]=function(stencilBuffer){
		this.stencilBuffer = stencilBuffer;
};	

SFDrawnRenderedTexture.prototype["setTextures"]=function(textures){
		this.textures = textures;
};	


SFDrawnRenderedTexture.prototype["setRenderer"]=function(renderer){
		this.renderer=renderer;
};	

SFDrawnRenderedTexture.prototype["setNode"]=function(node){
		this.node=node;
};	

SFDrawnRenderedTexture.prototype["isUseDefaultDepthBuffer"]=function(){
		return this.useDefaultDepthBuffer;
};

SFDrawnRenderedTexture.prototype["setUseDefaultDepthBuffer"]=function(useDefaultDepthBuffer){
		this.useDefaultDepthBuffer = useDefaultDepthBuffer;
};

SFDrawnRenderedTexture.prototype["isUseDefaultStencilBuffer"]=function(){
		return this.useDefaultStencilBuffer;
};

SFDrawnRenderedTexture.prototype["setUseDefaultStencilBuffer"]=function(useDefaultStencilBuffer){
		this.useDefaultStencilBuffer = useDefaultStencilBuffer;
};

