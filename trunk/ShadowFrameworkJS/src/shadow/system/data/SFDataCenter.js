
function SFDataCenter(){
	this.dataCenterImplementation="";
	this.datasetFactory="";
}

var sfDataCenterSingletonInstance=new SFDataCenter();

function SFDataCenter_setDataCenterImplementation(dataCenter){
	sfDataCenterSingletonInstance.dataCenterImplementation=dataCenter;
};
		
function SFDataCenter_setDatasetFactory(datasetFactory){
	sfDataCenterSingletonInstance.datasetFactory = datasetFactory;
};

function SFDataCenter_getDataCenter(){
	return sfDataCenterSingletonInstance;
};

SFDataCenter.prototype["getDataCenterImplementation"]=function(){
	return this.dataCenterImplementation;
};
		
SFDataCenter.prototype["makeDatasetAvailable"]=function(object,memoryPointer){
	this.dataCenterImplementation.makeDatasetAvailable(object,memoryPointer);
};
		
SFDataCenter.prototype["createDataset"]=function(typeName){
	return this.datasetFactory.createDataset(typeName);
};
		
SFDataCenter.prototype["releaseDataset"]=function(name,listener){
	this.dataCenterImplementation.releaseDataset(name, listener);
};

SFDataCenter.prototype["readDataset"]=function(stream){
	return sfDataCenterSingletonInstance.datasetFactory.readDataset(stream);
};
		
