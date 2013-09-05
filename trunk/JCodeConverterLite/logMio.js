

var name;

/*package shadow.pipeline.parameters;*/



/*
protected short type=GLOBAL_GENERIC;
*/

function SFParameter( ) {
}

function SFParameter( name,type) {
	this.name = name;
	this.type = type;
}

function SFParameter( name) {
	this.name = name;
}

SFParameter.prototype["getName"]=function(){
	return this.name;
};

SFParameter.prototype["getType"]=function(){
	return this.type;
};

SFParameter.prototype["setType"]=function(type){
	this.type=type;
}

/*@Override	public String toString() {
return name+"("+type+")";
}
*/

