

function SFStructureArrayData(t){
	this.t=t;
	this.values=new SFBinaryVertexArrayList(t);
	this.structure=new SFString();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.structure);
	parameters.addObject(this.values);
	this.setData(parameters);
}

inherit(SFStructureArrayData,SFDataAsset);


SFDataAsset.prototype["setStructure"]=function(structureName){
			this.structure.setString(structureName);
		};


SFStructureArrayData.prototype["generateNewDatasetInstance"]=function(){
	var array=new SFStructureArrayData(this.t);
	return array;
};

SFStructureArrayData.prototype["getArray"]=function(){
			if(this.array===undefined){
				var structureName=this.structure.getString();
				var materialStructureInstance=SFPipeline_getModule(structureName);
				this.array=SFPipeline_getSfPipelineMemory().generateStructureData(materialStructureInstance); 
				var n=this.values.getSize();
				var data=new SFStructureData(materialStructureInstance);
				for (var i = 0; i < n; i++) {
					var value=data.getValues();
					for (var j = 0; j < value.length; j++) {
						this.values.getValue(i, j, value[j]);
					}
					this.array.generateElement();
					this.array.setElement(i, data);
				}
			}
			return this.array;
		};

SFStructureArrayData.prototype["setArray"]=function(array){
		this.array = array;
		this.values.getDataObject().clear();
		var data=new SFStructureData(array.getPipelineStructure());
		for (var i = 0; i < array.getElementsCount(); i++) {
			array.getElement(i, data);
			var value=data.getValues();
			values.addValue(value);
		}
	};


SFStructureArrayData.prototype["buildResource"]=function(){
		return this.getArray();
	};

SFStructureArrayData.prototype["invalidate"]=function(){
		//Nothing to do.
	};

