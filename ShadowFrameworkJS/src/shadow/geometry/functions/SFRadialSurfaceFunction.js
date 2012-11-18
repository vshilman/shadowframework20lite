

function SFRadialSurfaceFunction(borderCurve,rayCurve){
	this.r0=new SFVertex3f();
	this.r1=new SFVertex3f();
	this.pv=new SFVertex3f();
	if(!(borderCurve===undefined))
		this.setBorderCurve(borderCurve);
	if(!(rayCurve===undefined))	
		this.setRayCurve(rayCurve);	
}

inherit(SFRadialSurfaceFunction,SFUnoptimizedSurfaceFunction);

SFRadialSurfaceFunction.prototype["setBorderCurve"]=function(borderCurve){
	this.borderCurve=borderCurve;
};

SFRadialSurfaceFunction.prototype["setRayCurve"]=function(rayCurve){
		this.rayCurve = rayCurve;
		this.rayCurve.getVertex(0, this.r0);
		this.rayCurve.getVertex(1, this.r1);
		this.r1.subtract3f(this.r0);
};


SFRadialSurfaceFunction.prototype["getDu"]=function(u,v){
		var p=new SFVertex3f();
		this.borderCurve.getDevDt(u, p);
		p.mult(-1);
		return p;
};


SFRadialSurfaceFunction.prototype["init"]=function(){
	
};
	
SFRadialSurfaceFunction.prototype["destroy"]=function(){
	
};

SFRadialSurfaceFunction.prototype["evaluateAB"]=function(u){
	var p=new SFVertex3f();
	this.borderCurve.getVertex(/*u*borderCurve.getTMax()+borderCurve.getTMin()*(1-u)*/u, p);
	
	p.subtract3f(this.r0);
	var dot=this.r1.dot3f(this.r1);
	this.A=(this.r1.getX()*p.getX()-this.r1.getY()*p.getY())/dot;
	this.B=(+this.r1.getY()*p.getX()+this.r1.getX()*p.getY())/dot;
	
};
	
SFRadialSurfaceFunction.prototype["getX"]=function(u,v){
	this.evaluateAB(u);
	this.rayCurve.getVertex(/*u*rayCurve.getTMax()+rayCurve.getTMin()*(1-u)*/v, this.pv);
	this.pv.subtract3f(this.r0);
	
	return this.A*this.pv.getX()-this.B*this.pv.getY()+this.r0.getX();
};

SFRadialSurfaceFunction.prototype["getY"]=function(u,v){
	return this.B*this.pv.getX()+this.A*this.pv.getY()+this.r0.getY();
};

SFRadialSurfaceFunction.prototype["getZ"]=function(u,v){
	return this.pv.getZ();
};
