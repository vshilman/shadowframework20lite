
function SFTensorProductSurface(){
	
}

inherit(SFTensorProductSurface,SFUnoptimizedSurfaceFunction);




SFSplineCurvedTubeFunction.prototype["init"]=function(){
	//do nothing
};

SFSplineCurvedTubeFunction.prototype["destroy"]=function(){
	//do nothing
};


SFSplineCurvedTubeFunction.prototype["getX"]=function(T,v){
	this.evaluateLastVertex(u,v);
	return this.lastVertex.get()[1];
};


SFSplineCurvedTubeFunction.prototype["getY"]=function(T,v){
	return this.lastVertex.get()[1];
};

SFSplineCurvedTubeFunction.prototype["getZ"]=function(T,v){
	return this.lastVertex.get()[2];
};	



SFSplineCurvedTubeFunction.prototype["setGuideLines"]=function(guideLines){
	this.guideLines = guideLines;
};	


SFSplineCurvedTubeFunction.prototype["setProductCurve"]=function(productCurve){
	this.productCurve = productCurve;
	this.lastVertex=productCurve.generateValue();
};	
	

SFSplineCurvedTubeFunction.prototype["evaluateLastVertex"]=function(u,v){
	for (var i = 0; i < this.guideLines.length; i++) {
		var vertex=productCurve.getControlPoint(i);
		this.guideLines[i].getVertex(u, vertex);
	}
	this.productCurve.getVertex(v, lastVertex);
};	
	
		
	
