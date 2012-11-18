

function SFTextureDataObject(){
	this.dataObject = new Array();
	this.generateData();	
}

inherit(SFTextureDataObject,SFCompositeDataArray);

SFTextureDataObject.prototype["generateData"]=function(){
	
		this.widthHeight=new SFIntShortField(0);
		this.params=new SFIntByteField(0);
		
		this.addDataObject(this.widthHeight);
		this.addDataObject(this.params);
		
};


SFTextureDataObject.prototype["clone"]=function(){	
		return new SFTextureDataObject();
};


SFTextureDataObject.prototype["getWidth"]=function(){
		return this.widthHeight.getShort(0);
};

SFTextureDataObject.prototype["getHeight"]=function(){
		return this.widthHeight.getShort(1);
};

SFTextureDataObject.prototype["getFormat"]=function(){
		var format=this.params.getByte(0);
		return SFImageFormat_values[format];
};

SFTextureDataObject.prototype["getFilter"]=function(){
		var filter=this.params.getByte(1);
		return Filter_values[filter];
};

SFTextureDataObject.prototype["getWrapModeS"]=function(){
		var filter=this.params.getByte(2);
		return WrapMode_values[filter];
};

SFTextureDataObject.prototype["getWrapModeT"]=function(){
		var filter=this.params.getByte(3);
		return WrapMode_values[filter];
};
	
SFTextureDataObject.prototype["getTexture"]=function(){
		var token=new SFTextureDataToken(this.getWidth(),this.getHeight(),
			this.getFormat(),this.getFilter(),this.getWrapModeS(),this.getWrapModeT());
		return token;
};
	
	