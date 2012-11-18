//Java to JS on 21/03/2012

var StencilFunction_NEVER=0;
var StencilFunction_LESS=0;
var StencilFunction_LEQUAL=0;
var StencilFunction_GREATER=0;
var StencilFunction_GEQUAL=0;
var StencilFunction_EQUAL=0;
var StencilFunction_NOTEQUAL=0;
var StencilFunction_ALWAYS=0;


var StencilOperation_KEEP=0;
var StencilOperation_ZERO=0;
var StencilOperation_REPLACE=0;
var StencilOperation_INCR=0;
var StencilOperation_INCR_WRAP=0;
var StencilOperation_DECR=0;
var StencilOperation_DECR_WRAP=0;
var StencilOperation_INVERT=0;


var AccumulatorOperation_ACCUM=0;
var AccumulatorOperation_LOAD=0;
var AccumulatorOperation_ADD=0;
var AccumulatorOperation_MULT=0;
var AccumulatorOperation_RETURN=0;


function SFPipelineRenderingState(stencilFunc,functionValue,mask,stencilFail,depthFail,depthPass){
	this.stencilTest = false;
	this.stencilTest = true;
	this.stencilFunc=stencilFunc;
	this.functionValue=functionValue;
	this.mask=mask;
	this.stencilFail=stencilFail;
	this.depthFail=depthFail;
	this.depthPass=depthPass;
}


SFPipelineRenderingState.prototype["disableStencilTest"]=function(stencilTest){
		this.stencilTest = false;
	};

SFPipelineRenderingState.prototype["enableDepthTest"]=function(){
		this.depthTest = true;
	};		

SFPipelineRenderingState.prototype["disableDepthTest"]=function(){
		this.depthTest = false;
	};		

SFPipelineRenderingState.prototype["isDepthTest"]=function(){
		return this,depthTest;
	};	
	
SFPipelineRenderingState.prototype["isStencilTest"]=function(){
		return this.stencilTest;
	};	
	
SFPipelineRenderingState.prototype["getFunction"]=function(){
		return stencilFunction;
	};	
	
SFPipelineRenderingState.prototype["getStencilValue"]=function(){
		return this.functionValue;
	};	
	
SFPipelineRenderingState.prototype["getStencilMask"]=function(){
		return this.mask;
	};	

SFPipelineRenderingState.prototype["getStencilFail"]=function(){
		return this.stencilFail;
	};	
	
SFPipelineRenderingState.prototype["getDepthFail"]=function(){
		return this.depthFail;
	};	
	
SFPipelineRenderingState.prototype["getDepthPass"]=function(){
		return this.depthPass;
	};	
