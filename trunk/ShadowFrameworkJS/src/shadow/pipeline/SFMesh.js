
function SFMesh(firstElement, lastElement) {
	this.setFirstElement(firstElement);
	this.setLastElement(lastElement);
}

SFMesh.prototype["getArray"]=function(){
	return this.array;
};

SFMesh.prototype["getPrimitive"]=function(){
	return this.array.getPrimitive();
};

SFMesh.prototype["getFirstElement"]=function(){
	return this.firstElement;
};

SFMesh.prototype["getLastElement"]=function(){
	return this.lastElement;
};

SFMesh.prototype["setArray"]=function(array){
	this.array = array;
	this.positions=new Array();
};

SFMesh.prototype["setFirstElement"]=function(firstElement){
	this.firstElement = firstElement;
};

SFMesh.prototype["setLastElement"]=function(lastElement){
	this.lastElement = lastElement;
};
		
SFMesh.prototype["getSize"]=function(){
	return this.lastElement-this.firstElement;
};

SFMesh.prototype["getPrimitiveDataRanges"]=function(){
	return this.positions;
};	

SFMesh.prototype["setPrimitiveDataRanges"]=function(ranges){
	this.positions = ranges;
};

SFMesh.prototype["evaluateRanges"]=function(){

	var primitive=this.array.getPrimitive();
	
	this.positions=new Array();
	
	for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
		this.positions[gridIndex]=new SFIndexRange(-1, -1);
	}
	
	var indices=this.array.generateSample();
	var positions=primitive.getIndicesPositions();
	var sizes=primitive.getIndicesSizes();
	
	for (var i = this.firstElement; i < this.lastElement; i++) {
		this.array.getElement(i, indices);
		
		for (var gridIndex = 0; gridIndex < this.positions.length; gridIndex++) {
			for (var j = 0; j < sizes[gridIndex]; j++) {
				var index=indices.getPrimitiveIndices()[j+positions[gridIndex]];
				this.positions[gridIndex].insertIndex(index);
			}
		}
	}
};

	