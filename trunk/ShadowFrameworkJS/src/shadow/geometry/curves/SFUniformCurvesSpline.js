

function SFUniformCurvesSpline(){
	this.curves=new Array();
	this.controlPoints=new Array();	
}

inherit (SFUniformCurvesSpline,SFUnOptimizedCurve);

SFUniformCurvesSpline.prototype["addCurve"]=function(curve){
	this.curves.push(curve);
};

SFUniformCurvesSpline.prototype["getControlPointSize"]=function(){
	return this.controlPoints.length;
};

SFUniformCurvesSpline.prototype["getControlPoint"]=function(index){
	return this.controlPoints[index];
};	
	
SFUniformCurvesSpline.prototype["addControlPoint"]=function(vertex){
	this.controlPoints.push(vertex);
};	
	
SFUniformCurvesSpline.prototype["getDev2Dt"]=function(T,read){
	if(T<0)
		T=0;
	var t=T*this.curves.length;
		var index=Math.floor(t);
		if(index==this.curves.length)
			index=this.curves.length-1;
		this.curves[index].getDev2Dt(t-index,read);
};	
	
SFUniformCurvesSpline.prototype["getDevDt"]=function(T,read){
	if(T<0)
		T=0;
	var t=T*this.curves.length;
		var index=Math.floor(t);
		if(index==this.curves.length)
			index=this.curves.length-1;
	this.curves[index].getDevDt(t-index,read);
};	
	
	
SFUniformCurvesSpline.prototype["generateValue"]=function(){
	return curves[0].generateValue();
};	
	
SFUniformCurvesSpline.prototype["getTMax"]=function(){
	return 1;
};	

SFUniformCurvesSpline.prototype["getTMin"]=function(){
	return 0;
};	
	
SFUniformCurvesSpline.prototype["getVertex"]=function(T,read){
	if(T<0)
		T=0;
	var t=T*this.curves.length;
		var index=Math.floor(t);
		if(index==this.curves.length)
			index=this.curves.length-1;
	this.curves[index].getVertex(t-index,read);
};	


SFUniformCurvesSpline.prototype["init"]=function(){
	//Do nothing
};		
	
SFUniformCurvesSpline.prototype["destroy"]=function(){
	//Do nothing
};		
