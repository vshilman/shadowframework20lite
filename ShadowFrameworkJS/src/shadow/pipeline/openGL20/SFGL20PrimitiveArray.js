

function SFGL20PrimitiveArray(primitive){
	this.data=new Array();
	if(!(primitive===undefined)){
		this.primitive = primitive;
		this.primitiveData=new Array();
		for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			var type=primitive.getType(gridIndex);
			if(type==SFParameteri_GLOBAL_FLOAT){
				this.primitiveData[gridIndex]=new SFGL20ValuenfArray(1);
			}else if(type==SFParameteri_GLOBAL_FLOAT2){
				this.primitiveData[gridIndex]=new SFGL20ValuenfArray(2);
			}else if(type==SFParameteri_GLOBAL_FLOAT3){
				this.primitiveData[gridIndex]=new SFGL20ValuenfArray(3);
			}
		}
	}
}

inherit(SFGL20PrimitiveArray,SFGL20ListData);



function SFGL20PrimitiveArray_mixArrays(arrays,mixPrimitive){
	

	var totalComponents=0;
	var totalGrids=0;
	for (var i = 0; i < arrays.length; i++) {
		totalComponents+=arrays[i].getPrimitive().getComponents().length;
		totalGrids+=arrays[i].getPrimitive().getGridSize();
	}
	
	var components=new Array();
	var blocks=new Array();
	
	var primitiveIndex=0;
	var inPrimitiveIndex=0;
	for (var componentIndex = 0; componentIndex < totalComponents; componentIndex++) {
		components[componentIndex]=arrays[primitiveIndex].getPrimitive().getComponents()[inPrimitiveIndex];
		blocks[componentIndex]=arrays[primitiveIndex].getPrimitive().getBlocks()[inPrimitiveIndex];
		inPrimitiveIndex++;
		if(inPrimitiveIndex>=arrays[primitiveIndex].getPrimitive().getComponents().length){
			primitiveIndex++;
			inPrimitiveIndex=0;
		}
	}
	
	mixPrimitive.setPrimitiveElements(blocks, components);
	
	var array=new SFGL20PrimitiveArray();
	array.primitive=mixPrimitive;
	
	array.primitiveData=new SFGL20ValuenfArray[totalGrids];
	
	primitiveIndex=0;
	inPrimitiveIndex=0;
	for (var gridIndex = 0; gridIndex < totalGrids; gridIndex++) {

		array.primitiveData[gridIndex]=(SFGL20ValuenfArray)(arrays[primitiveIndex].getPrimitiveData(inPrimitiveIndex));
		inPrimitiveIndex++;
		if(inPrimitiveIndex>=arrays[primitiveIndex].getPrimitive().getComponents().length){
			primitiveIndex++;
			inPrimitiveIndex=0;
		}
	}
	
	for (var i = 0; i < arrays[0].getElementsCount(); i++) {
		var  indices=new int[mixPrimitive.getIndicesCount()];
		
		var indicesIndex=0;
		for (var j = 0; j < arrays.length; j++) {
			var oldIndices=(arrays[j]).data[i].getPrimitiveIndices();
			for (var k = 0; k < oldIndices.length; k++) {
				indices[indicesIndex+k]=oldIndices[k];
			}
			indicesIndex+=oldIndices.length;
		}
			 
		var prIndices=new SFPrimitiveIndices();
		prIndices.setPrimitiveIndices(indices);
		array.data.add(prIndices);
	}
	
	return array;
}

	
SFGL20PrimitiveArray.prototype["assignValues"]=function(writing,reading){
	writing.set(reading);
};
		
SFGL20PrimitiveArray.prototype["setElementData"]=function(index,element,gridIndex){
	
	this.data[i].setData(element,this.primitive.getIndicesPositions()[gridIndex],
			this.primitive.getIndicesSizes()[gridIndex]);
};

SFGL20PrimitiveArray.prototype["generateGenericElement"]=function(){
	return new SFPrimitiveIndices(this.primitive);
};

	
SFGL20PrimitiveArray.prototype["getPrimitiveData"]=function(gridIndex){
	if(gridIndex===undefined)
		return this.primitiveData;
	return this.primitiveData[gridIndex];
};	
	
SFGL20PrimitiveArray.prototype["init"]=function(){
};	

SFGL20PrimitiveArray.prototype["destroy"]=function(){
};	

	
SFGL20PrimitiveArray.prototype["getPrimitive"]=function(){
	return this.primitive;
};	
	