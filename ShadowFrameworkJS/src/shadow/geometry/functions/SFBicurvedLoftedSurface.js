

function SFBicurvedLoftedSurface(a,b){
	this.tmp=new SFVertex3f();
	if(a===undefined || b===undefined)
		return;
	this.A=a;
	this.B=b;
	this.maxTA=A.getTMax();
	this.maxTB=B.getTMax();
}

inherit(SFBicurvedLoftedSurface,SFUnoptimizedSurfaceFunction);

SFBicurvedLoftedSurface.prototype["getX"]=function(u,v){	
	this.A.getVertex(u*this.A.getTMax(), this.tmp);
	var tmp2=new SFVertex3f();
	this.B.getVertex(u*this.B.getTMax(), tmp2);
	this.tmp.mult(1-v);
	this.tmp.addMult(v, tmp2);
	return this.tmp.getX();
};

SFBicurvedLoftedSurface.prototype["getY"]=function(u,v){
	return this.tmp.getY();
};

SFBicurvedLoftedSurface.prototype["getZ"]=function(u,v){
	return this.tmp.getZ();
};	

SFBicurvedLoftedSurface.prototype["init"]=function(){
	
};	
	
SFBicurvedLoftedSurface.prototype["destroy"]=function(){
	
};	
	
SFBicurvedLoftedSurface.prototype["getA"]=function(){
	return this.A;
};	

SFBicurvedLoftedSurface.prototype["getB"]=function(){
	return this.B;
};	

SFBicurvedLoftedSurface.prototype["setA"]=function(a){
	this.A=a;
};
	
SFBicurvedLoftedSurface.prototype["setB"]=function(b){
	this.B=b;
};

SFBicurvedLoftedSurface.prototype["getMaxTA"]=function(){
	return this.maxTA;
};	


SFBicurvedLoftedSurface.prototype["getMaxTB"]=function(){
	return this.maxTB;
};	


SFBicurvedLoftedSurface.prototype["setMaxTA"]=function(maxTa){
	this.maxTA=maxTa;
};

SFBicurvedLoftedSurface.prototype["setMaxTB"]=function(maxTb){
	this.maxTB=maxTb;
};	
