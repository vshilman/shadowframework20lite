
function SFParametricData(){
	this.valueSize=0;
}

inherit(SFParametricData,SFPrimitiveType);

SFParametricData.prototype["getValue"]=function(index,value){
	for (var i = 0; i < this.valueSize; i++) {
		var vString=this.getVValue(index*this.valueSize+i);
		var fValue=parseFloat(vString);
		if(fValue!=fValue){
			var stringValue=SFVerticesParameters_getParameters().getValue(vString);
			value.get()[i]=stringValue;
		}else{
			value.get()[i]=fValue;
		}
	}
};

SFParametricData.prototype["getVValue"]=function(index){
	return this.stringsVector[this.dataVector[index]];
};

SFParametricData.prototype["getSize"]=function(){
	return this.dataVector.length/this.valueSize;
};


SFParametricData.prototype["readFromStream"]=function(stream){
	this.valueSize=stream.readByte();
	this.stringsVector=new Array();
	var svLength=stream.readShort();
	for (var i = 0; i < svLength; i++) {
		this.stringsVector[i]=stream.readString();
	}
	var dataVectorLength=stream.readShort();
	this.dataVector=stream.readShorts(dataVectorLength);
};

SFParametricData.prototype["clone"]=function(){
	return new SFParametricData();
};

	
function SFParametricVListIterator(vertices){
	this.index=0;
	this.vertices=vertices;
}

SFParametricVListIterator.prototype["getNext"]=function(write){
	this.vertices.getValue(this.index, write);
	this.index++;
};

SFParametricVListIterator.prototype["hasNext"]=function(){
	return this.index<this.vertices.getSize();
};


function SFParametricVList(vertices){
	this.vertices=vertices;
}

SFParametricVList.prototype["init"]=function(){
};

SFParametricVList.prototype["destroy"]=function(){
};

SFParametricVList.prototype["getSize"]=function(){
	return this.vertices.getSize();
};

SFParametricVList.prototype["getValue"]=function(index,write){
	this.vertices.getValue(index, write);
};

SFParametricVList.prototype["getIterator"]=function(){
	return new SFParametricVListIterator(this.vertices);
};


function SFParametricValuesList(){
	this.vertices=new SFParametricData();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.vertices);
	this.setData(parameters);
}

inherit(SFParametricValuesList,SFDataAsset);

SFParametricValuesList.prototype["buildResource"]=function(){
	return new SFParametricVList(this.vertices);
};


SFParametricValuesList.prototype["generateNewDatasetInstance"]=function(){
	return new SFParametricValuesList();
};