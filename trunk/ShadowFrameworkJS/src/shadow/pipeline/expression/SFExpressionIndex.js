


function SFExpressionIndex(index){
	this.element = ""+index;
	this.index=index;
	this.setType(SFParameteri_GLOBAL_GENERIC);
}

inherit(SFExpressionIndex,SFExpressionElement);


SFExpressionIndex.prototype["addSubExpression"]=function(element){
			// Nothing to do
		};

SFExpressionIndex.prototype["evaluateType"]=function(element){
			// Nothing to do
		};

SFExpressionIndex.prototype["cloneAsIndexed"]=function(toBeIndexed){
			return this;
		};


SFExpressionIndex.prototype["evaluate"]=function(values){
			return values.getValue(this.index);
		};

