

function SFTextureData(){
	this.textureIndex = 0;
	this.reference=new SFLibraryReference();
}

inherit(SFTextureData,SFPrimitive);

SFTextureData.prototype["getReference"]=function(){
			return this.reference;
		};
		
SFTextureData.prototype["clone"]=function(){
			return new SFTextureData();
		};

SFTextureData.prototype["buildTextureReference"]=function(){
	
	var texture=new SFTexture(null, this.textureIndex);
	
	var dataset=this.getReference().retrieveDataset();
	if(dataset===undefined)
		throw "UNDEFINED";
	texture.setTexturesSet(dataset.getResource());
	
	return texture;
};

SFTextureData.prototype["readFromStream"]=function(stream){
	this.textureIndex=stream.readByte();
	this.reference.setReference(stream.readName());
};

SFTextureData.prototype["writeOnStream"]=function(stream){
	stream.writeByte(this.textureIndex);
	stream.writeName(this.reference.getReference());
};
