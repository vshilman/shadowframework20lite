

function SFSimpleObjPlaneTexCoordGeometry( au,  bu,  cu,  du,  av,
			 bv,  cv,  dv) {
		
		this.au = au;
		this.bu = bu;
		this.cu = cu;
		this.du = du;
		this.av = av;
		this.bv = bv;
		this.cv = cv;
		this.dv = dv;
	
}


SFSimpleObjPlaneTexCoordGeometry.prototype["getTexCoord"]=function(x, y, z, nx, ny, nz){
		return new SFVertex2f(this.au * x + this.bu * y + this.cu * z + this.du, this.av * x + this.bv * y + this.cv * z + this.dv);
};

SFSimpleObjPlaneTexCoordGeometry.prototype["init"]=function(){
	//do nothing
};

SFSimpleObjPlaneTexCoordGeometry.prototype["destroy"]=function(){
	//do nothing
};

SFSimpleObjPlaneTexCoordGeometry.prototype["getAu"]=function(){
	return this.au;
};
	
SFSimpleObjPlaneTexCoordGeometry.prototype["getBu"]=function(){
	return this.bu;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["getCu"]=function(){
	return this.cu;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["getDu"]=function(){
	return this.du;
};


SFSimpleObjPlaneTexCoordGeometry.prototype["setAu"]=function(au){
	this.au=au;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["setBu"]=function(bu){
	this.bu=bu;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["setCu"]=function(cu){
	this.cu=cu;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["setDu"]=function(du){
	this.du=du;
};




SFSimpleObjPlaneTexCoordGeometry.prototype["getAv"]=function(){
	return this.av;
};
	
SFSimpleObjPlaneTexCoordGeometry.prototype["getBv"]=function(){
	return this.bv;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["getCv"]=function(){
	return this.cv;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["getDv"]=function(){
	return this.dv;
};


SFSimpleObjPlaneTexCoordGeometry.prototype["setAv"]=function(av){
	this.av=av;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["setBv"]=function(bv){
	this.bv=bv;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["setCv"]=function(cv){
	this.cv=cv;
};

SFSimpleObjPlaneTexCoordGeometry.prototype["setDv"]=function(dv){
	this.dv=dv;
};