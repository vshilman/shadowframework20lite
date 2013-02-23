

function SFVerticesParameters(){
	this.parameters=new Array();
}

SFVerticesParameters.prototype["clear"]=function(){
	this.parameters.length=0;
};

SFVerticesParameters.prototype["setParameter"]=function(parameter,value){
	this.parameters[parameter]=value;
};

SFVerticesParameters.prototype["getValue"]=function(parameter){
	return this.parameters[parameter];
};

var SFVerticesParameters_verticesParameters=new SFVerticesParameters();

function SFVerticesParameters_getParameters(){
	return SFVerticesParameters_verticesParameters;
}