//Java to JS on 21/03/2012

function SFFunction(globalV,func){
	this.parameter=globalV;

	if(func!=null)
		this.setFunction(func);
}

SFFunction.prototype["getParameter"]=function(){
			return this.parameter;
		};
		
SFFunction.prototype["setParameter"]=function(globalV){
			this.parameter=globalV;
		};

SFFunction.prototype["compileFunction"]=function(parameters){
			this.func=this.func.cloneAsIndexed(parameters);
		};
		
SFFunction.prototype["getFunction"]=function(){
			return this.func;
		};
		
SFFunction.prototype["setFunction"]=function(func){
			this.func = func;
			if(this.func!=undefined)
				this.func.evaluateType();
		};
		