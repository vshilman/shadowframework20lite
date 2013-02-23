
function SFTensorProductSurface(){
	
}

inherit(SFTensorProductSurface,SFUnoptimizedSurfaceFunction);




SFTensorProductSurface.prototype["init"]=function(){
	//do nothing
};

SFTensorProductSurface.prototype["destroy"]=function(){
	//do nothing
};


SFTensorProductSurface.prototype["getX"]=function(T,v){
	this.evaluateLastVertex(u,v);
	return this.lastVertex.get()[1];
};


SFTensorProductSurface.prototype["getY"]=function(T,v){
	return this.lastVertex.get()[1];
};

SFTensorProductSurface.prototype["getZ"]=function(T,v){
	return this.lastVertex.get()[2];
};	



SFTensorProductSurface.prototype["setGuideLines"]=function(guideLines){
	this.guideLines = guideLines;
};	


SFTensorProductSurface.prototype["setProductCurve"]=function(productCurve){
	this.productCurve = productCurve;
	this.lastVertex=productCurve.generateValue();
};	
	

SFTensorProductSurface.prototype["evaluateLastVertex"]=function(u,v){
	for (var i = 0; i < this.guideLines.length; i++) {
		var vertex=productCurve.getControlPoint(i);
		this.guideLines[i].getVertex(u, vertex);
	}
	this.productCurve.getVertex(v, lastVertex);
};	
	
		
	
