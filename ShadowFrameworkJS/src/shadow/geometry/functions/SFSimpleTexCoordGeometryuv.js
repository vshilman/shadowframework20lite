

function SFSimpleTexCoordGeometryuv(du,dv){
	this.du=du;
	this.dv=dv;	
}

inherit(SFSimpleTexCoordGeometryuv,SFUnoptimizedSurfaceFunctionUV);


SFSimpleTexCoordGeometryuv.prototype["getTexCoord"]=function(u,v){
	return new SFVertex2f(u*this.du,v*this.dv);
};

SFSimpleTexCoordGeometryuv.prototype["init"]=function(){
	//Do nothing
};

SFSimpleTexCoordGeometryuv.prototype["destroy"]=function(){
	//Do nothing
};	

SFSimpleTexCoordGeometryuv.prototype["getDu"]=function(){
	return this.du;
};	

SFSimpleTexCoordGeometryuv.prototype["getDv"]=function(){
	return this.dv;
};

SFSimpleTexCoordGeometryuv.prototype["setDu"]=function(du){
	this.du=du;
};	

SFSimpleTexCoordGeometryuv.prototype["setDv"]=function(dv){
	this.dv=dv;
};	
