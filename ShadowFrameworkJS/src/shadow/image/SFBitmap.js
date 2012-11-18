//Java to JS on 06/02/2012

function SFBitmap(){
	this.width=0;
	this.height=0;
	this.format=SFImageFormat_ARGB8;
}

function SFBitmap_generateRGBImage(width, height){
	var  ret = new  SFBitmap();
	ret.setWidth(width);
	ret.setHeight(height);
	return ret;
};

function SFBitmap_generateRGBImage(width, height, format){
	var  ret = new  SFBitmap();
	ret.setWidth(width);
	ret.setHeight(height);
	ret.setFormat(format);
	return ret;
};


SFBitmap.prototype["getGray"]=function(x,y){
	//alert("index is "+(this.getSize()*(x+this.width*y)));
	var b=this.data[(x+this.width*y)];
	return (b>=0?b:b+256);
};

SFBitmap.prototype["getWidth"]=function(){
	return this.width;
};

SFBitmap.prototype["getHeight"]=function(){
	return this.height;
};

SFBitmap.prototype["getFormat"]=function(){
	return this.format;
};

SFBitmap.prototype["setWidth"]=function(width){
	this.width   = width;
};

SFBitmap.prototype["setHeight"]=function(height){
	this.height   = height;
};

SFBitmap.prototype["setFormat"]=function(format){
	this.format   = format;
};

SFBitmap.prototype["getData"]=function(){
	return this.data;
};

SFBitmap.prototype["setData"]=function(data){
	this.data   = data;
};

SFBitmap.prototype["init"]=function(){
};

SFBitmap.prototype["destroy"]=function(){
};

SFBitmap.prototype["destroy"]=function(){
};


SFBitmap.prototype["getSize"]=function(){
	var size=1;
	if(this.getFormat()==SFImageFormat_RGB8){
		size=3;
	}
	return size;
};

SFBitmap.prototype["generateBitmap"]=function(width, height, values, rgb){
	
	this.setWidth(width);
	this.setHeight(height);
	if(rgb){
		this.setFormat(SFImageFormat_RGB8);
	}else{
		this.setFormat(SFImageFormat_GRAY8);
	}
	
	//if(this.width*this.height!=values.length)
	//	throw new SFException("An SFBitmapArrayData must have an array of "+(width*height)+" values, there are "+values.length+"values");
	
	var size=1;
	if(rgb){
		size=3;
	}
	
	size=4;
	
	var buffer = new Uint8Array(values);
	this.setData(buffer);
	
};

