
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
	//alert("readDataset "+(dataset.generateNewDatasetInstance)+" "+index);
	dataset.getSFDataObject().readFromStream(stream);
	//alert("It's been read!! "+index);
	return dataset;
};	

SFGenericDatasetFactory.prototype["writeDataset"]=function(stream,dataset){
			stream.writeString(dataset.getType());
			dataset.getSFDataObject().writeOnStream(stream);
		};
	
SFGenericDatasetFactory.prototype["createDataset"]=function(typeName){
			//TODO : there is an error here... this is not used...
			var asset=this.datasets[typeName];
			if(asset != undefined){
				return asset.generateNewDatasetInstance();
			}
			throw "Cannot instantiate "+asset;
		};

