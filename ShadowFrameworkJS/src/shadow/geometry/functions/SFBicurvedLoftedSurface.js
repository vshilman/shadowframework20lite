

function SFBicurvedLoftedSurface(a,b){
	if(a===undefined || b===undefined)
		return;
	this.A=a;
	this.B=b;
	this.maxTA.A.getTMax();
	this.maxTB.B.getTMax();
	this.tmp=new SFVertex3f();
}

inherit(SFBicurvedLoftedSurface,SFUnoptimizedSurfaceFunction);

SFUnoptimizedSurfaceFunction.prototype["getX"]=function(u,v){
	this.A.getVertex(u*maxTA, tmp);
	var tmp2=new SFVertex3f();
	this.B.getVertex(u*this.maxTB, tmp2);
	this.tmp.mult(1-v);
	this.tmp.addMult(v, tmp2);
	return this.tmp.getX();
};

SFUnoptimizedSurfaceFunction.prototype["getY"]=function(u,v){
	return this.tmp.getY();
};

SFUnoptimizedSurfaceFunction.prototype["getZ"]=function(u,v){
	return this.tmp.getZ();
};	

SFUnoptimizedSurfaceFunction.prototype["init"]=function(){
	
};	
	
SFUnoptimizedSurfaceFunction.prototype["destroy"]=function(){
	
};	
	
SFUnoptimizedSurfaceFunction.prototype["getA"]=function(){
	return this.A;
};	

SFUnoptimizedSurfaceFunction.prototype["getB"]=function(){
	return this.B;
};	

SFUnoptimizedSurfaceFunction.prototype["setA"]=function(a){
	this.A=a;
};
	
SFUnoptimizedSurfaceFunction.prototype["setB"]=function(b){
	this.B=b;
};

SFUnoptimizedSurfaceFunction.prototype["getMaxTA"]=function(){
	return this.maxTA;
};	


SFUnoptimizedSurfaceFunction.prototype["getMaxTB"]=function(){
	return this.maxTB;
};	


SFUnoptimizedSurfaceFunction.prototype["setMaxTA"]=function(maxTa){
	this.maxTA=maxTa;
};

SFUnoptimizedSurfaceFunction.prototype["setMaxTB"]=function(maxTb){
	this.maxTB=maxTb;
};	
