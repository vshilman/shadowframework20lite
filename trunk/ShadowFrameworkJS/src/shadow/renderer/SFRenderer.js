
function SFRenderer(){
	this.light=new SFProgramModuleStructures();
	this.camera=null;
}

SFRenderer.prototype["setCamera"]=function(camera){
	this.camera = camera;
};

SFRenderer.prototype["getCamera"]=function(camera){
	return this.camera;
};

SFRenderer.prototype["setLight"]=function(light){
	this.light=light;
};

SFRenderer.prototype["getLod"]=function(){
	return lod;
};

SFRenderer.prototype["setLod"]=function(lod){
	this.lod=lod;
};

SFRenderer.prototype["init"]=function(){
	//Do nothing
};

SFRenderer.prototype["destroy"]=function(){
	//Do nothing
};


SFRenderer.prototype["render"]=function(node){
	this.renderNodeContent(node);
	for(var i=0;i<node.getNodes().length;i++){
		this.render(node.getNodes()[i]);
	}
};


SFRenderer.prototype["renderNodeContent"]=function(node){
	
	if(node.isDrawable()){

		this.setupRenderingData(node.getModel());

		node.getTransform().apply();
		
		var rootGeometry=node.getModel().getRootGeometry();
		this.renderGeometry(rootGeometry);
	}
};
	

SFRenderer.prototype["setupRenderingData"]=function(model){

	
	var program=model.getProgram(this.light);
	SFPipeline_getSfProgramBuilder().loadProgram(program);

	SFRenderer_setupMaterialData(SFPipelineGraphics_Module_TRANSFORM,model.getTransformComponent());
	SFRenderer_setupMaterialData(SFPipelineGraphics_Module_MATERIAL,model.getMaterialComponent());
	SFRenderer_setupMaterialData(SFPipelineGraphics_Module_LIGHT,this.light);
	
};
			
function SFRenderer_setupMaterialData(module,model){
	
	var materials=model.getData();
	
	for (var structureIndex = 0; structureIndex < materials.length; structureIndex++) {
		var sfStructure = materials[structureIndex];
		SFPipeline_getSfPipelineGraphics().loadStructureData(module,sfStructure.getTable(), structureIndex, sfStructure.getIndex());
	}
	
	
	var textures=model.getTextures();
	
	for (var i = 0; i < textures.length; i++) {
		var sfTextureReference=textures[i];
		SFPipeline_getSfPipelineGraphics().loadTexture(module, sfTextureReference.getTexture(), i);
	}
	
}


SFRenderer.prototype["renderGeometry"]=function(geometry){
	// geometry is drawn
	geometry.drawGeometry(this.lod);
	// sons geometry are drawn
	for (var i=0; i < geometry.getSonsCount(); i++) {
		renderGeometry(geometry.getSon(i));
	}
};

