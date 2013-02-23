
function SFDataParameter(){
	
}

inherit(SFDataParameter,SFPrimitiveType);

SFDataParameter.prototype["clone"]=function(){
	return new SFDataParameter();
};

SFDataParameter.prototype["readFromStream"]=function(stream){
	this.name=stream.readString();
	this.value=stream.readFloat();
	//alert("reading Data Parameters "+this.name+" "+this.value);
};

function SFDataParametersSet(){
	this.array=new Array();
	this.type=new SFDataParameter();
}

inherit(SFDataParametersSet,SFDataObjectsList);


SFDataParametersSet.prototype["apply"]=function(){
	SFVerticesParameters_getParameters().clear();
	for (var i = 0; i < this.size(); i++) {
		var parameter=this.get(i);
		SFVerticesParameters_getParameters().setParameter(parameter.name, parameter.value);
	}
};
