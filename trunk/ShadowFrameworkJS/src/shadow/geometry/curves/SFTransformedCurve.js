


function SFTransformedCurve(curve,transform){
	
}


SFTransformedCurve.prototype["setCurve"]=function(curve){
		this.curve = curve;
};

SFTransformedCurve.prototype["setTransform"]=function(curve){
		this.transfom = transfom;
};

SFTransformedCurve.prototype["destroy"]=function(){
		
};

SFTransformedCurve.prototype["generateValue"]=function(){
	return curve.generateValue();
};  
	
SFTransformedCurve.prototype["get"]=function(ts,vertices,derivatives){
	this.curve.get(ts, vertices, derivatives);
	this.transfomValues(vertices, derivatives);
};  
	
SFTransformedCurve.prototype["transfomValues"]=function(vertices,derivatives){
	for (var i = 0; i < derivatives.length; i++) {
		this.transfom.transformDir(derivatives[i]);
	}
	for (var i = 0; i < vertices.length; i++) {
		this.transfom.transform(vertices[i]);
	}
};  
	

SFTransformedCurve.prototype["get"]=function(ts,vertices,derivatives,derivatives2){
	this.curve.get2(ts, vertices, derivatives,derivatives2);
	for (var i = 0; i < derivatives2.length; i++) {
		this.trasnform.transformDir(derivatives2[i]);
	}
	this.transfomValues(vertices, derivatives);
};  
	

SFTransformedCurve.prototype["getDev2Dt"]=function(ts,read){
	this.curve.getDev2Dt(ts,read);
	this.trasnform.transformDir(read);
};  	
	

SFTransformedCurve.prototype["getDev2DtV"]=function(ts,read){
	this.curve.getDev2Dt(ts, read);
	for (var i = 0; i < read.length; i++) {
		this.transform.transformDir(read[i]);
	}
};  	
	
	
SFTransformedCurve.prototype["getDevDt"]=function(t,read){
	this.curve.getDevDt(ts,read);
	this.trasnform.transformDir(read);
};  

SFTransformedCurve.prototype["getDevDtV"]=function(ts,read){
	this.curve.getDevDt(ts, read);
	for (var i = 0; i < read.length; i++) {
		this.transform.transformDir(read[i]);
	}
};  		
	
SFTransformedCurve.prototype["getTMax"]=function(){
	return curve.getTMax();
};  
	
SFTransformedCurve.prototype["getTMin"]=function(){
	return curve.getTMin();
};  
	
SFTransformedCurve.prototype["getVertex"]=function(t,read){
	this.curve.getVertex(t, read);
	this.transform.transform(read);
};  

SFTransformedCurve.prototype["getVertices"]=function(ts,read){
	this.curve.getVertices(ts, read);
	for (var i = 0; i < read.length; i++) {
		this.transform.transform(read[i]);
	}
};  

SFTransformedCurve.prototype["init"]=function(){
	//nothing to do
};  
	
