

function SFCurvedTubeFunction(){
	
	this.Ccv=new SFVertex3f();
	this.Vec1v=new SFVertex3f();
	this.Vec2v=new SFVertex3f();
	this.DVec1v=new SFVertex3f();
	this.DVec2v=new SFVertex3f();
	this.dCcdv=new SFVertex3f();
	this.lastV=-1;
	this.cos=0;
	this.sin=0;
	
}

inherit(SFCurvedTubeFunction,SFUnoptimizedSurfaceFunction);

/*		
SFCurvedTubeFunction.prototype["getDu"]=function(f){
	var du=new SFVertex3f(this.getDXDu(),this.getDYDu(),this.getDZDu());
	du.normalize3f();
	return du;
};

SFCurvedTubeFunction.prototype["getDv"]=function(f){
	var dv=new SFVertex3f(this.getDXDv(),this.getDYDv(),this.getDZDv());
	dv.normalize3f();
	return dv;
};
*/

SFCurvedTubeFunction.prototype["init"]=function(){
	//DO nothing
	;
};


SFCurvedTubeFunction.prototype["destroy"]=function(){
	//DO nothing
	;
};

SFCurvedTubeFunction.prototype["destroy"]=function(){
	//DO nothings
	;
};

SFCurvedTubeFunction.prototype["evalAll"]=function(v){
	if(v!=this.lastV){

		this.centralCurve.getVertex(v, this.Ccv);

		this.centralCurve.getDevDt(v, this.dCcdv);
		
		this.rayCurve.getVertex(v, this.Vec1v);
		
		this.Vec1v.subtract3f(this.Ccv);
		this.Vec2v.set3f(this.dCcdv.cross(this.Vec1v));
		this.Vec2v.mult((Math.sqrt(this.Vec1v.dot3f(this.Vec1v)/this.Vec2v.dot3f(this.Vec2v))));
		
		this.dCcdv2=new SFVertex3f();
		this.centralCurve.getDev2Dt(v, this.dCcdv2);
		this.rayCurve.getDevDt(v, this.DVec1v);
		this.DVec1v.subtract3f(this.dCcdv);
		
		//DVec1v.normalize3f();
		this.DVec2v.set3f(this.dCcdv.cross(this.DVec1v));
		
		this.DVec2v.add3fv(this.dCcdv2.cross(this.Vec1v));
		
		//DVec2v.normalize3f();
		
		
		this.lastV=v;
	}
};

SFCurvedTubeFunction.prototype["getX"]=function(u,v){
	this.evalAll(v);
	this.cos=(Math.cos(2*Math_PI*u));
	this.sin=(Math.sin(2*Math_PI*u));
	
	return this.Ccv.getX()+this.cos*this.Vec1v.getX()+this.sin*this.Vec2v.getX();
};

SFCurvedTubeFunction.prototype["getY"]=function(u,v){
	return this.Ccv.getY()+this.cos*this.Vec1v.getY()+this.sin*this.Vec2v.getY();
};

SFCurvedTubeFunction.prototype["getZ"]=function(u,v){
	return this.Ccv.getZ()+this.cos*this.Vec1v.getZ()+this.sin*this.Vec2v.getZ();
};


SFCurvedTubeFunction.prototype["getDXDv"]=function(u,v){
	return this.dCcdv.getX()+this.cos*this.DVec1v.getX()+this.sin*this.DVec2v.getX();
};

SFCurvedTubeFunction.prototype["getDYDv"]=function(u,v){
	return this.dCcdv.getY()+this.cos*this.DVec1v.getY()+this.sin*this.DVec2v.getY();
};

SFCurvedTubeFunction.prototype["getDZDv"]=function(u,v){
	return this.dCcdv.getZ()+this.cos*this.DVec1v.getZ()+this.sin*this.DVec2v.getZ();
};


SFCurvedTubeFunction.prototype["getDXDu"]=function(u,v){
	return -this.sin*this.Vec1v.getX()+this.cos*this.Vec2v.getX();
};

SFCurvedTubeFunction.prototype["getDYDu"]=function(u,v){
	return -this.sin*this.Vec1v.getY()+this.cos*this.Vec2v.getY();
};

SFCurvedTubeFunction.prototype["getDZDu"]=function(u,v){
	return -this.sin*this.Vec1v.getZ()+this.cos*this.Vec2v.getZ();
};


SFCurvedTubeFunction.prototype["getCentralCurve"]=function(){
	return this.centralCurve;
};

SFCurvedTubeFunction.prototype["setCentralCurve"]=function(centralCurve){
	this.centralCurve=centralCurve;
};

SFCurvedTubeFunction.prototype["getRayCurve"]=function(){
	return this.rayCurve;
};

SFCurvedTubeFunction.prototype["setRayCurve"]=function(rayCurve){
	this.rayCurve=rayCurve;
};
