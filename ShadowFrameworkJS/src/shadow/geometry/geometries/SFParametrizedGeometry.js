



function SFParametrizedGeometry(parametersGeometry){
	this.parametersGeometry=parametersGeometry;
	this.functions=new Array();
	this.mesh=new SFMesh(-1,-1);
	this.compiledIndex=-1;
	this.rendering_hint=0;
	this.available_lods=0;
	this.sonGeometries=new Array();
	this.changed=true;
}

inherit(SFParametrizedGeometry,SFMeshGeometry);
	
		
SFParametrizedGeometry.prototype["addMeshGeometry"]=function(geometry){
	this.parametersGeometry=geometry;
};


SFParametrizedGeometry.prototype["compile"]=function(){
	
	this.parametersGeometry.init();
	
	//TODO: check parametersGeometry to be, effectively, a parameters Geometry
	//so, i think i should have all...
	
	var parameters=this.parametersGeometry.getArray().getPrimitiveData(0);
	
	var range=this.parametersGeometry.getMesh().getPrimitiveDataRanges()[0];
	
	var deltaPosition=new Array();

	for (var gridIndex = 0; gridIndex < this.getPrimitive().getGridsCount(); gridIndex++) {
		var block=this.getPrimitive().getBlock(gridIndex);
		var func=this.functions[gridIndex];
		
		var position=func.extractParametrizedModel(parameters, range, this.getArray(), block, gridIndex);
		deltaPosition[gridIndex]=position-range.getPosition();
	}

	
	var elementsPosition=this.getArray().generateElements(this.parametersGeometry.getElementsCount());
	
	var indices=this.getArray().generateSample();
	var paramIndices=this.parametersGeometry.getArray().generateSample();
	
	for (var i = 0; i < this.parametersGeometry.getElementsCount(); i++) {
		this.parametersGeometry.getArray().getElement(i+this.parametersGeometry.getFirstElement(), paramIndices);
		
		for (var gridIndex = 0; gridIndex < this.getPrimitive().getGridsCount(); gridIndex++) {
			var paramIndicesVec=paramIndices.getPrimitiveIndices();
			for (var j = 0; j < paramIndicesVec.length; j++) {
				indices.getPrimitiveIndices()[gridIndex*paramIndicesVec.length+j]=paramIndicesVec[j]+deltaPosition[gridIndex];	
			}
		}
		this.getArray().setElement(i+elementsPosition, indices);
	}
	
	
	//this.setFirstElement(elementsPosition);
	//this.setLastElement(elementsPosition+parametersGeometry.getElementsCount());
	
	//getMesh().evaluateRanges();
};


SFParametrizedGeometry.prototype["update"]=function(){
	if(this.getArray()==null)
		return;
	
	for (var gridIndex = 0; gridIndex < this.getPrimitive().getGridsCount(); gridIndex++) {
		var parameters=this.parametersGeometry.getArray().getPrimitiveData(0);
		var block=this.getPrimitive().getBlock(gridIndex);
		var func=this.functions[gridIndex];
		var position=this.getMesh().getPrimitiveDataRanges()[gridIndex].getPosition();
		var range=this.parametersGeometry.getMesh().getPrimitiveDataRanges()[0];
		func.updateParametrizedModel(position, parameters, range, this.getArray(), block, gridIndex);
	}
	//DO NOT CORRECT VALUES ON THIS VERSION.... :)
	//SFGridEngine.correctValues(getMesh());
	
	//this.setFirstElement(elementsPosition);
	//this.setLastElement(elementsPosition+parametersGeometry.getElementsCount());
	
	//getMesh().evaluateRanges();
};


SFParametrizedGeometry.prototype["setFunction"]=function(block,func){
	var blocks=this.getPrimitive().getBlocks();
	for (var i = 0; i < blocks.length; i++) {
		if(blocks[i]==block){
			this.functions[i]=func;
			return;
		}
	}
	throw new SFException("Cannot assign a function to a QuadsSurfaceGeometry on the Primitive Block '"+block+"' because this QuadsSurfaceGeometry primitive does not use " +
			"the Primitive Block '"+block+"'");
};

SFParametrizedGeometry.prototype["getFunctions"]=function(block,func){
	return this.functions;
};
	

SFParametrizedGeometry.prototype["setTexCoordGeometry"]=function(func){
	this.setFunction(SFPrimitiveBlock_TXO, func);
};	


SFParametrizedGeometry.prototype["setMainGeometryFunction"]=function(func){
	var blocks=this.getPrimitive().getBlocks();
	if(this.functions==null)
		this.functions=new Array();
	for (var i = 0; i < blocks.length; i++) {
		if(blocks[i]==SFPrimitiveBlock_POSITION ||
				blocks[i]==SFPrimitiveBlock_NORMAL ||
				blocks[i]==SFPrimitiveBlock_DU ||
				blocks[i]==SFPrimitiveBlock_DV){
			this.functions[i]=func;
		}
	}
};	