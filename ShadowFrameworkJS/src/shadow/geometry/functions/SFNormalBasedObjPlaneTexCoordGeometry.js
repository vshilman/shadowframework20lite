
function SFNormalBasedObjPlaneTexCoordGeometry(a,da,av,bvv,cv,dv){

		this.a = a;
		this.da = da;
		this.av = av;
		this.bv = bv;
		this.cv = cv;
		this.dv = dv;	
}

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getTexCoord"]=function(x,y,z,nx,ny,nz){
	var dirV=new SFVertex3f(this.av,this.bv,this.cv);
	var normal=new SFVertex3f(nx,ny,nz);
	var dirU=normal.cross(dirV);
	dirU.normalize3f();
	return new SFVertex2f(this.a*(dirU.getX() * x + dirU.getY() * y + dirU.getZ()*z)+this.da, 
	this.av * x + this.bv * y + this.cv * z + this.dv);	
};



SFNormalBasedObjPlaneTexCoordGeometry.prototype["destroy"]=function(){
	
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["init"]=function(){
	
};	

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getAv"]=function(){
	return this.av;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getDa"]=function(){
	return this.da;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getBv"]=function(){
	return this.bv;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getCv"]=function(){
	return this.cv;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getDv"]=function(){
	return this.dv;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["getA"]=function(){
	return this.a;
};


SFNormalBasedObjPlaneTexCoordGeometry.prototype["setA"]=function(a){
	this.a=a;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["setAv"]=function(av){
	this.av=av;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["setDa"]=function(da){
	this.da=da;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["setBv"]=function(bv){
	this.bv=bv;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["setCv"]=function(cv){
	this.cv=cv;
};

SFNormalBasedObjPlaneTexCoordGeometry.prototype["setDv"]=function(dv){
	this.dv=dv;
};
