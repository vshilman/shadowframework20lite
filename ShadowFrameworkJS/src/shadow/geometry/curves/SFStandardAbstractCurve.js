

function SFStandardAbstractCurve(/*n??*/){
	this.vertices=new Array();
}

inherit(SFStandardAbstractCurve,SFUnOptimizedCurve);

SFStandardAbstractCurve.prototype["getControlPointSize"]=function(){
	this.vertices.length;
}

SFStandardAbstractCurve.prototype["setControlPoint"]=function(value,index){
	this.vertices[index]=value;
}

SFStandardAbstractCurve.prototype["getControlPoint"]=function(index){
	return this.vertices[index];
}

SFStandardAbstractCurve.prototype["generateValue"]=function(){
	return new SFValuenf(this.vertices[0].get().length);
}	
	
SFStandardAbstractCurve.prototype["getTMin"]=function(){
	return 0;
}	

SFStandardAbstractCurve.prototype["getTMax"]=function(){
	return 1;
}	

SFStandardAbstractCurve.prototype["init"]=function(){
	//Do nothing
}

SFStandardAbstractCurve.prototype["destroy"]=function(){
	//Do nothing
}
	