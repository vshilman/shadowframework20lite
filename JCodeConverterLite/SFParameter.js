//Java to JS on 21/03/2012

function SFParameter(name,type){
	this.name=name;
	if(type!=undefined){
		this.type=type;
	}else{
		this.type=SFParameteri_GLOBAL_GENERIC;
	}
}


SFParameter.prototype["getName"]=function(){
			return this.name;
		};

SFParameter.prototype["getType"]=function(){
			return this.type;
		};

SFParameter.prototype["setType"]=function(type){
			this.type=type;
		};
