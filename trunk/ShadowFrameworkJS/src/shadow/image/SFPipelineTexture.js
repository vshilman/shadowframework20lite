

var WrapMode_REPEAT=0;
var WrapMode_MIRRORED_REPEAT=1;
var WrapMode_CLAMP_TO_EDGE=2;

var WrapMode_values=[
	WrapMode_REPEAT,
	WrapMode_MIRRORED_REPEAT,
	WrapMode_CLAMP_TO_EDGE
];

var Filter_NEAREST=0;
var Filter_LINEAR=1;
var Filter_LINEAR_MIPMAP=2;

var Filter_values=[
	Filter_NEAREST,
	Filter_LINEAR,
	Filter_LINEAR_MIPMAP
];

function SFPipelineTexture( width, height, format, filters,  wrapS, wrapT){
	this.filters = filters;
	this.WrapS = wrapS;
	this.WrapT = wrapT;
	this.width=width;
	this.height=height;
	this.format=format;
}

inherit(SFPipelineTexture,SFBufferData);

SFPipelineTexture.prototype["getFilters"]=function(){
	return this.filters;
};

SFPipelineTexture.prototype["getWrapS"]=function(){
	return this.WrapS;
};

SFPipelineTexture.prototype["getWrapT"]=function(){
	return this.WrapT;
};