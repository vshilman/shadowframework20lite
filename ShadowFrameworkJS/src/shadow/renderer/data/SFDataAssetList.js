
function SFDataAssetList(){
	this.vector=new Array();
}

SFDataAssetList.prototype["readFromStream"]=function(stream){
	var n=stream.readShort();
	for (var i = 0; i < n; i++) {
		this.vector.push(SFDataCenter_getDataCenter().readDataset(stream));
	}
}

SFDataAssetList.prototype["size"]=function(stream){
	return this.vector.length;
}

SFDataAssetList.prototype["get"]=function(index){
	return this.vector[index];
}

SFDataAssetList.prototype["add"]=function(element){
	this.vector.push(element);
}

SFDataAssetList.prototype["clone"]=function(){
	return new SFDataAssetList();
}
