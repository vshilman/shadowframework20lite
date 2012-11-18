
function SFGenericDatasetFactory(){
	this.datasets=new Array();
	this.assetsList=new Array();
}

SFGenericDatasetFactory.prototype["addSFDataset"]=function(sfDataset){
	var typeName=getType(sfDataset);
	this.datasets[typeName]=sfDataset;
	this.assetsList.push(sfDataset);
};

SFGenericDatasetFactory.prototype["readDataset"]=function(stream){
	var index=stream.readInt();
	var dataset = this.assetsList[index];
	dataset = dataset.generateNewDatasetInstance();
	dataset.getSFDataObject().readFromStream(stream);
	return dataset;
};	

SFGenericDatasetFactory.prototype["writeDataset"]=function(stream,dataset){
			stream.writeString(dataset.getType());
			dataset.getSFDataObject().writeOnStream(stream);
		};
	
SFGenericDatasetFactory.prototype["createDataset"]=function(typeName){
			var asset=this.datasets[typeName];
			if(asset != undefined){
				return dataset.generateNewDatasetInstance();
			}
			throw "Cannot instantiate "+asset;
		};

