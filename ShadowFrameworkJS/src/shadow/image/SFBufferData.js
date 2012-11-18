//Java to JS on 06/02/2012

function SFBufferData(width,height,format){
	this.width=width;
	this.height=height;
	this.format=format;
}

SFBufferData.prototype["getWidth"]=function(){
	return this.width;
};

SFBufferData.prototype["getHeight"]=function(){
	return this.height;
};

SFBufferData.prototype["getFormat"]=function(){
	return this.format;
};
