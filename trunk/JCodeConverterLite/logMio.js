

//#include "SFParameter.h";


//SFParameter::SFParameter() {
//this->type=GLOBAL_GENERIC;
//}


function SFParameter( name) {
	this.name = name;
}

function SFParameter( name,type) {
	this.name = name;
	this.type = type;
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

