

function SFDataAssetObject(dataset){
	this.dataset = dataset;
}

inherit(SFDataAssetObject,SFDatasetObject);

SFDataAssetObject.prototype["getResource"]=function(){
			return this.getDataset().getResource();
		};

