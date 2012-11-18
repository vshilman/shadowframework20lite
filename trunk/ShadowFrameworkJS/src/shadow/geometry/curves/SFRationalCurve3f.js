

function SFRationalCurve3f(curve){
	this.curve=curve;
}

inherit(SFRationalCurve3f,SFUnOptimizedCurve);


SFRationalCurve3f.prototype["getDev2Dt"]=function(){
		throw "Not Yet Implemented";	
}

SFRationalCurve3f.prototype["getDevDt"]=function(){
		throw "Not Yet Implemented";	
}


SFRationalCurve3f.prototype["getTMax"]=function(){
		return curve.getTMax();
}	

SFRationalCurve3f.prototype["getTMin"]=function(){
		return curve.getTMax();
}	
	
SFRationalCurve3f.prototype["generateValue"]=function(){
		return new SFVertex4f();
}	
	
	
	
	
	var SFRationalCurve3f_getVertex=new SFVertex4f();
	
SFRationalCurve3f.prototype["getVertex"]=function(t,read){
	this.curve.getVertex(t, SFRationalCurve3f_getVertex);
	var recW=1.0/SFRationalCurve3f_getVertex.getW();
	read.setV(SFRationalCurve3f_getVertex.getX()*recW,
			SFRationalCurve3f_getVertex.getY()*recW,
			SFRationalCurve3f_getVertex.getZ()*recW);
}	
	
		
SFRationalCurve3f.prototype["init"]=function(){
		//Do nothing
}	
	
SFRationalCurve3f.prototype["destroy"]=function(){
		//Do nothing
}	
	
	
	