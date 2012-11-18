

function SFMeshGeometry(){
	this.mesh=new SFMesh(-1,-1);
	this.compiledIndex=-1;
	this.rendering_hint=0;
	this.available_lods=0;
	this.sonGeometries=new Array();
	this.changed=true;
}

inherit(SFMeshGeometry,SFGeometry);
	
		
SFMeshGeometry.prototype["setPrimitive"]=function(primitive){
	this.primitive=primitive;
};

SFMeshGeometry.prototype["getPrimitive"]=function(primitive){
	return this.primitive;
};

SFMeshGeometry.prototype["drawGeometry"]=function(lod){
	if(this.compiledIndex!=-1)
		SFPipeline_getSfPipelineGraphics().drawCompiledPrimitives(this.getArray(), this.compiledIndex);
	else if(this.mesh.getFirstElement()!=-1)
		SFPipeline_getSfPipelineGraphics().drawPrimitives(this.mesh.getArray(),this.mesh.getFirstElement(),this.mesh.getLastElement()-this.mesh.getFirstElement());
};

SFMeshGeometry.prototype["compile"]=function(){
	//Nothing to do
};

SFMeshGeometry.prototype["update"]=function(){
	//Nothing to do
};


SFMeshGeometry.prototype["decompile"]=function(){
	var array=this.getArray();
	SFPipeline_getSfPipelineMemory().destroyPrimitiveArray(array);
};


SFMeshGeometry.prototype["init"]=function(){
	if(this.mesh.getFirstElement()==-1){

		this.verifyArray();
		
		this.setFirstElement(this.getArray().getElementsCount());
		var rangePositions=new Array();
		for (var i = 0; i < this.getPrimitive().getGridsCount(); i++) {
			rangePositions[i]=this.getArray().getPrimitiveData(i).getElementsCount();
		}
		
		this.compile();
		
		this.setLastElement(this.getArray().getElementsCount());
		var ranges=new Array();
		for (var i = 0; i < this.getPrimitive().getGridsCount(); i++) {
			var rangePosition=this.getArray().getPrimitiveData(i).getElementsCount();
			ranges[i]=new SFIndexRange(rangePositions[i], rangePosition-rangePositions[i]);
		}
		
		this.getMesh().setPrimitiveDataRanges(ranges);

		this.update();

		//TODO: this will be set back some day
		//this.compiledIndex=SFPipeline_getSfPipelineGraphics().compilePrimitiveArray(
		//		this.getArray(),this.getFirstElement(), this.getLastElement());
		
	}
};

	

SFMeshGeometry.prototype["verifyArray"]=function(){
	if(this.getArray()==null)			
		this.setArray(SFPipeline_getSfPipelineMemory().generatePrimitiveArray(this.getPrimitive()));
};
	

SFMeshGeometry.prototype["destroy"]=function(){
	if(this.mesh.getFirstElement()!=-1){
		this.decompile();	
	}
};	
	
	

SFMeshGeometry.prototype["getCompiledIndex"]=function(){
	return this.compiledIndex;
};	
	

SFMeshGeometry.prototype["getMesh"]=function(){
	return this.mesh;
};	


SFMeshGeometry.prototype["getFirstElement"]=function(){
	return this.mesh.getFirstElement();
};	

SFMeshGeometry.prototype["getElementsCount"]=function(){
	return this.mesh.getLastElement()-this.mesh.getFirstElement();
};	


SFMeshGeometry.prototype["setFirstElement"]=function(firstElement){
	this.mesh.setFirstElement(firstElement);
};	
	


SFMeshGeometry.prototype["getLastElement"]=function(){
	return this.mesh.getLastElement();
};	


SFMeshGeometry.prototype["setLastElement"]=function(lastElement){
	this.mesh.setLastElement(lastElement);
};	
	


SFMeshGeometry.prototype["getArray"]=function(){
	return this.mesh.getArray();
};	


SFMeshGeometry.prototype["setArray"]=function(array){
	this.mesh.setArray(array);
};
	