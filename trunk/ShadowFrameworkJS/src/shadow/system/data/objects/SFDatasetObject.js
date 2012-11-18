


function SFDatasetObject(dataset){
	this.dataset = dataset;
}

inherit(SFDatasetObject,SFPrimitiveType);

SFDatasetObject.prototype["getDataset"]=function(){
	return this.dataset;
};

SFDatasetObject.prototype["setDataset"]=function(dataset){
	this.dataset = dataset;
};

SFDatasetObject.prototype["clone"]=function(dataset){
	return new SFDatasetObject(null);
};

SFDatasetObject.prototype["readFromStream"]=function(stream){
	this.dataset=SFDataCenter_getDataCenter().readDataset(stream);
};

SFDatasetObject.prototype["writeOnStream"]=function(stream){
	SFDataCenter_getDataCenter().writeDataset(stream, this.dataset);
};	
	