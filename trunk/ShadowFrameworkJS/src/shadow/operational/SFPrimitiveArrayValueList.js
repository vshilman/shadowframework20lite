

function SFPrimitiveArrayValueList(primitiveIndices,array){
	this.primitiveIndices = primitiveIndices;
	this.array = array;
}

SFPrimitiveArrayValueList.prototype["setGridIndex"]=function(gridIndex){
	this.position=this.array.getPrimitive().getIndicesPositions()[gridIndex];
	this.arrayData=this.array.getPrimitiveData(gridIndex);
};
		
SFPrimitiveArrayValueList.prototype["generateValue"]=function(){
	return this.arrayData.generateSample();
};
		
SFPrimitiveArrayValueList.prototype["getDataIndex"]=function(index){
	return this.primitiveIndices[this.position+index];
};
		
SFPrimitiveArrayValueList.prototype["getValue"]=function(index){
	var value=this.arrayData.generateSample();
	this.arrayData.getElement(this.getDataIndex(index), value);
	return value;
};
		
