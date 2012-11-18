function SFMeshData(array,firstElement, lastElement,dataIndex) {

	this.array = array;
	this.firstElement = firstElement;
	this.lastElement = lastElement;
	this.gridIndex = dataIndex;
}

SFMeshData.prototype["getGridN"]=function(){
	return this.array.getPrimitive().getGrid(gridIndex).getN();
};


SFMeshData.prototype["getPrimitive"]=function(){
	return this.getArray().getPrimitive();
};

SFMeshData.prototype["generateSample"]=function(){
	return this.array.getPrimitiveData(gridIndex).generateSample();
};

SFMeshData.prototype["getIndex"]=function(index){
	if(this.indices==undefined)
		return index+this.firstElement;
	else
		return this.indices[index];
};

SFMeshData.prototype["setElement"]=function(index,value){
	this.array.getPrimitiveData(gridIndex).setElement(getIndex(index), value);
};

SFMeshData.prototype["getElement"]=function(index,value){
	this.array.getPrimitiveData(gridIndex).getElement(getIndex(index), value);
};


SFMesh.prototype["getArrayData"]=function(){
	return this.array.getPrimitiveData(this.gridIndex);
};

SFMesh.prototype["getArray"]=function(){
	return this.array;
};

SFMesh.prototype["getFirstElement"]=function(){
	return this.firstElement;
};

SFMesh.prototype["getLastElement"]=function(){
	return this.lastElement;
};

SFMesh.prototype["setArray"]=function(array){
	this.array = array;
};

SFMesh.prototype["setFirstElement"]=function(firstElement){
	this.firstElement = firstElement;
};

SFMesh.prototype["setLastElement"]=function(lastElement){
	this.lastElement = lastElement;
};

SFMesh.prototype["setGridIndex"]=function(gridIndex){
	this.gridIndex = gridIndex;
};

SFMesh.prototype["getGridIndex"]=function(){
	return this.gridIndex;
};

SFMesh.prototype["setIndices"]=function(indices){
	this.indices = indices;
};

SFMesh.prototype["getSize"]=function(){
	return this.lastElement-this.firstElement;
};
