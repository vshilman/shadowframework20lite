
function SFGroupMeshGeometry(){
	this.rendering_hint=0;
	this.available_lods=0;
	this.sonGeometries=new Array();
	this.changed=true;
	this.mesh=new SFMesh(-1,-1);
	this.geometries=new Array();
}

inherit(SFGroupMeshGeometry,SFGeometry);
	
		
SFGroupMeshGeometry.prototype["addGeometry"]=function(geometry){
	this.geometries.push(geometry);
	geometry.setArray(SFPipeline_getSfPipelineMemory().generatePrimitiveArrayView(this.getArray(), geometry.getPrimitive()));
};
	
SFGroupMeshGeometry.prototype["compile"]=function(){
	for (var i = 0; i < this.geometries.length; i++) {
		this.geometries[i].compile();
	}
};

SFGroupMeshGeometry.prototype["decompile"]=function(){
	for (var i = 0; i < this.geometries.length; i++) {
		this.geometries[i].decompile();
	}
};


SFGroupMeshGeometry.prototype["destroy"]=function(){
	
};


SFGroupMeshGeometry.prototype["drawGeometry"]=function(lod){
	for (var i = 0; i < this.geometries.length; i++) {
		this.geometries[i].drawGeometry(lod);
	}
};


SFGroupMeshGeometry.prototype["getArray"]=function(){
	return this.mesh.getArray();
};

SFGroupMeshGeometry.prototype["setArray"]=function(array){
	this.mesh.setArray(array);
};
	

SFGroupMeshGeometry.prototype["getPrimitive"]=function(){
	return this.geometries[0].getPrimitive();
};
	
	
SFGroupMeshGeometry.prototype["init"]=function(){
	
};

SFGroupMeshGeometry.prototype["update"]=function(){
	
};
	
	