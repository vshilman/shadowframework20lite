

function SFDataObjectsList(type){
	this.array=new Array();
	this.type=type;
}

inherit(SFDataObjectsList,SFDataAsset);

SFDataObjectsList.prototype["get"]=function(index){
	return this.array[index];
};

SFDataObjectsList.prototype["add"]=function(element){
	return this.array.push(element);
};

SFDataObjectsList.prototype["size"]=function(){
	return this.array.length;
};

SFDataObjectsList.prototype["readFromStream"]=function(stream){
	var n=stream.readShort();
	for (var i = 0; i < n; i++) {
		this.add(this.type.clone());
		this.get(i).readFromStream(stream);
	}
};

SFDataObjectsList.prototype["writeOnStream"]=function(stream){
	stream.writeShort(this.size());
	for (var i = 0; i < this.size(); i++) {
		this.get(i).writeOnStream(stream);
	}
};

SFDataObjectsList.prototype["clone"]=function(){
	return new SFDataObjectsList();
};
	