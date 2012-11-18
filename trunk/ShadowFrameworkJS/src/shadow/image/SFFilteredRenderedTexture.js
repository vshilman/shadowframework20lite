

function SFFilteredRenderedTexture(){
	this.initialized=false;
	this.textures=null;
	this.renderedTexture=new SFRenderedTexture();
	this.lightComponent=new SFProgramModuleStructures();
	this.material=new SFProgramModuleStructures();
}


SFFilteredRenderedTexture.prototype["getMaterialComponent"]=function(){
	return this.material;
};


SFFilteredRenderedTexture.prototype["getTexture"]=function(index){
	return this.textures[index];
};


SFFilteredRenderedTexture.prototype["getTextureSize"]=function(){
	return this.textures.length;
};

SFFilteredRenderedTexture.prototype["setMaterialComponent"]=function(material){
	this.material = material;
};

SFFilteredRenderedTexture.prototype["init"]=function(){
	
		if(!this.initialized){
			for (var i = 0; i < this.textures.length; i++) {
				var data=this.textures[i];
				this.textures[i]=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().
						generateTextureBuffer(data.getWidth(), data.getHeight(), 
						data.getFormat(),  data.getFilters(),
						data.getWrapS(), data.getWrapT());
				this.renderedTexture.addColorData(this.textures[i]);
			}
			this.update();
			
		}
		initialized=true;
};
	
	
SFFilteredRenderedTexture.prototype["update"]=function(){
	
		
		SFPipeline_getSfTexturePipeline().beginNewRenderedTexture(this.renderedTexture);

			var program=SFPipeline_getStaticImageProgram(this.getMaterialComponent().getProgram(),
				this.lightComponent.getProgram());
			SFPipeline_getSfProgramBuilder().loadProgram(program);
		
			SFRenderer_setupMaterialData(SFPipelineGraphics_Module_MATERIAL,this.material);
			SFRenderer_setupMaterialData(SFPipelineGraphics_Module_LIGHT,this.lightComponent);
			
			SFPipeline_getSfPipelineGraphics().drawBaseQuad();
			
		SFPipeline_getSfTexturePipeline().endRenderedTexture(this.renderedTexture);
		
		SFPipeline_getSfTexturePipeline().destroyRenderedTexture(this.renderedTexture);
};
	
	
SFFilteredRenderedTexture.prototype["destroy"]=function(){
	if(this.initialized){
		for (var i = 0; i < this.textures.length; i++) {
			SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().destroyBuffer(this.textures[i]);
		}
	}
};
	
SFFilteredRenderedTexture.prototype["getRenderedTexture"]=function(){
		return this.renderedTexture;
};
		
SFFilteredRenderedTexture.prototype["getLightComponent"]=function(){
		return this.lightComponent;
};
	
SFFilteredRenderedTexture.prototype["setTextures"]=function(textures){
		this.textures = textures;
};
	
SFFilteredRenderedTexture.prototype["setLightComponent"]=function(lightComponent){
		this.lightComponent = lightComponent;
};
	

	