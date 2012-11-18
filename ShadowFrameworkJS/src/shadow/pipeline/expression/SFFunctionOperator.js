
function SFFunctionOperator(func){
	this.list=new Array();
	this.setElement(func);
	this.setType(SFParameteri_GLOBAL_FLOAT);
	this.operatorSymbol="func";
}


inherit(SFFunctionOperator,SFExpressionOperator);



SFFunctionOperator.prototype["evaluateType"]=function(){
			this.updateSubExpressions();
			if(this.getElement()==="sample")
				this.setType(SFParameteri_GLOBAL_FLOAT4);
			else if(this.getElement()==="clamp")
				this.setType(this.list[0].getType());
			else if(this.getElement()==="normalize")
				this.setType(this.list[0].getType());
			else if(this.getElement()==="cross")
				this.setType(this.list[0].getType());
			else
				this.setType(SFParameteri_GLOBAL_FLOAT);
		};

	
SFFunctionOperator.prototype["cloneOperator"]=function(){
		return new SFFunctionOperator(getElement());
		};

SFFunctionOperator.prototype["evaluate"]=function(values){
			throw  ("Yet not implemented");
		};

