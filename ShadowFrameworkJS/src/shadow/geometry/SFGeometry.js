
var SFGeometry_LOD_HINT_DISCARD=1;

function SFGeometry(){
	this.rendering_hint=0;
	this.available_lods=0;
	this.sonGeometries=new Array();
	this.changed=true;
}
	
		
SFGeometry.prototype["getRendering_hint"]=function(){
	return this.rendering_hint;
};


SFGeometry.prototype["getAvailable_lods"]=function(){
	return this.available_lods;
};
	
SFGeometry.prototype["setRendering_hint"]=function(rendering_hint){
	this.rendering_hint=rendering_hint;
};

SFGeometry.prototype["setAvailable_lods"]=function(available_lods){
	this.available_lods=available_lods;
};

SFGeometry.prototype["getSonsCount"]=function(){
	return this.sonGeometries.length;
};

SFGeometry.prototype["addSon"]=function(son){
	 this.sonGeometries.push(son);
	 this.son.setFatherGeometry(this);
	return this.sonGeometries.length-1;
};


SFGeometry.prototype["getSon"]=function(index){
	return this.sonGeometries[index];
};

SFGeometry.prototype["setSon"]=function(index){
	return this.sonGeometries[index];
};

SFGeometry.prototype["getFatherGeometry"]=function(){
	return this.fatherGeometry;
};

SFGeometry.prototype["setFatherGeometry"]=function(fatherGeometry){
	this.fatherGeometry=fatherGeometry;
};

SFGeometry.prototype["rebuild"]=function(){
	this.compile();
	for (var i = 0; i < this.sonGeometries.length; i++) {
		this.sonGeometries[i].rebuild();
	}
};
		