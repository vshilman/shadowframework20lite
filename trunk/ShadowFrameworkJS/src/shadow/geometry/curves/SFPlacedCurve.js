

function SFPlacedCurve(tMin, tMax, curve){

	this.tMin=tMin;
	this.tMax=tMax;
	this.curve=curve;	
}

inherit(SFPlacedCurve,SFUnOptimizedCurve);


SFPlacedCurve.prototype["gettMin"]=function(){
	return this.tMin;
}

SFPlacedCurve.prototype["generateValue"]=function(){
	return this.curve.generateValue();
}

SFPlacedCurve.prototype["settMin"]=function(tMin){
	this.tMin = tMin;
}	
	
SFPlacedCurve.prototype["gettMax"]=function(){
	return this.tMax;
}

SFPlacedCurve.prototype["settMax"]=function(tMax){
	this.tMax = tMax;
}
	
SFPlacedCurve.prototype["getCurve"]=function(){
	return this.curve;
}
	
SFPlacedCurve.prototype["setCurve"]=function(curve){
	this.curve = curve;
}

SFPlacedCurve.prototype["getDev2Dt"]=function(t,read){
	this.curve.getDev2Dt(t*(this.tMax-this.tMin)+this.tMin, read);
}	

SFPlacedCurve.prototype["getDevDt"]=function(t,read){
	this.curve.getDevDt(t*(this.tMax-this.tMin)+this.tMin, read);
}	

SFPlacedCurve.prototype["getVertex"]=function(t,read){
	this.curve.getVertex(t*(this.tMax-this.tMin)+this.tMin, read);
}		
	
SFPlacedCurve.prototype["getTMin"]=function(){
		return this.curve.getTMin()*(this.tMax-this.tMin)+this.tMin;
}		
	
SFPlacedCurve.prototype["getTMax"]=function(){
		return this.curve.getTMax()*(this.tMax-this.tMin)+this.tMin;
}		
	
SFPlacedCurve.prototype["init"]=function(){
		//Do nothing
}		
	
SFPlacedCurve.prototype["destroy"]=function(){
		//Do nothing
}	
	