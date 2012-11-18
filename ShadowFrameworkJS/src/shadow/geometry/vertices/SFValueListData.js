

function SFBinaryVListIterator(vertices){
	this.index=0;
	this.vertices=vertices;
}

SFBinaryVListIterator.prototype["getNext"]=function(write){
	this.vertices.getValue(this.index, write);
	this.index++;
};

SFBinaryVListIterator.prototype["hasNext"]=function(){
	return this.index<this.vertices.getSize();
};


function SFBinaryVList(vertices){
	this.vertices=vertices;
}

SFBinaryVList.prototype["getSize"]=function(){
	return this.vertices.getSize();
};


SFBinaryVList.prototype["init"]=function(){
};

SFBinaryVList.prototype["destroy"]=function(){
};

SFBinaryVList.prototype["getValue"]=function(index,write){
	this.vertices.getValue(index, write);
};

SFBinaryVList.prototype["getIterator"]=function(){
	return new SFBinaryVListIterator(this.vertices);
};

function SFValueListData(t){
	this.t=t;
	this.vertices=new SFBinaryVertexList(t);
	var parameters=new SFNamedParametersObject();
	//parameters.addObject("vertexSize", this.vertexSize);
	parameters.addObject(this.vertices);
	this.setData(parameters);
}

inherit(SFValueListData,SFDataAsset);//TODO: verify constructors

SFValueListData.prototype["buildResource"]=function(){
	return new SFBinaryVList(this.vertices);
};

SFValueListData.prototype["generateNewDatasetInstance"]=function(){
	return new SFValueListData(this.t);
};

SFValueListData.prototype["getControlPointSize"]=function(){
			return this.vertices.length;
		};
