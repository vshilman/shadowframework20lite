



function SFPrimitiveArrayData(){
	this.primitive=new SFString();
	this.primitiveData=new SFLibraryReferenceList(new SFLibraryReference());
	var shortValues=new Array();
	this.primitives=new SFDataObjectsList(new SFShortArray(shortValues));
	this.sample=new SFValueListData(new SFFixedFloat16());
	this.array=null;
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.primitive);
	parameters.addObject(this.primitiveData);
	parameters.addObject(this.primitives);
	this.setData(parameters);
}

inherit(SFPrimitiveArrayData,SFDataAsset);


SFPrimitiveArrayData.prototype["buildResource"]=function(){

	var primitive=SFPipeline_getPrimitive(this.primitive.getString());
	
	this.array = SFPipeline_getSfPipelineMemory().generatePrimitiveArray(primitive);

	for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
		
		var values = this.array.getPrimitiveData(gridIndex);
		
		var dataset=this.primitiveData.get(gridIndex).retrieveDataset();
		var list=dataset.getResource();
		values.generateElements(list.getSize());
		var sample = values.generateSample();
		
		for (var i = 0; i < list.getSize(); i++) {
			list.getValue(i, sample);
			values.setElement(i, sample);
		}
		
	}
	
	var indices = this.array.generateSample();
	this.array.generateElements(this.primitives.size());
	for (var i = 0; i < this.primitives.size(); i++) {
		var primitiveIndices=this.primitives.get(i).getShortValues();
		for (var j = 0; j < primitiveIndices.length; j++) {
			indices.getPrimitiveIndices()[j]=primitiveIndices[j];
		}
		this.array.setElement(i, indices);
	}
	
	return this.array;
};

SFPrimitiveArrayData.prototype["generateNewDatasetInstance"]=function(){
	return new SFPrimitiveArrayData();
};
