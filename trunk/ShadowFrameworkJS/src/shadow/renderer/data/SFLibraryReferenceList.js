

function SFLibraryReferenceList(type){
	this.type=type;
	this.list=new Array();
}


SFLibraryReferenceList.prototype["add"]=function(reference){
	//var libraryReference = this.type.clone();
	//libraryReference.setReference(reference);
	this.list.push(reference);
};

SFLibraryReferenceList.prototype["readFromStream"]=function(stream){
	var n=stream.readShort();
	for (var i = 0; i < n; i++) {
		var reference=this.type.clone();
		this.add(reference);
		reference.readFromStream(stream);
	}
};

SFLibraryReferenceList.prototype["clone"]=function(){
	return new SFLibraryReferenceList(this.type);
};
	

SFLibraryReferenceList.prototype["get"]=function(index){
	return this.list[index];
};

SFLibraryReferenceList.prototype["size"]=function(){
	return this.list.length;
};