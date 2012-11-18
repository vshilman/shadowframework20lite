//Java to JS on 21/03/2012

function SFExpressionVector(){
	this.list=new Array();
	this.operatorSymbol = ",";
	this.setElement(this.operatorSymbol);
	this.setType(SFParameteri_GLOBAL_FLOAT);
	this.maxSize = SFExpressionOperator_SIZE_ALL;
}

inherit(SFExpressionVector,SFExpressionOperator);

SFExpressionVector.prototype["evaluateType"]=function(){
			this.updateSubExpressions();
			
			this.setType(this.list[0].getType());
			
		};
		
SFExpressionVector.prototype["cloneOperator"]=function(){
			return new SFExpressionVector();
		};

SFExpressionVector.prototype["evaluate"]=function(values){
			var subValues=new SFValuenf(getElementSize());
			for (var i = 0; i < getElementSize(); i++) {
				subValues.get()[i]=getExpressionElement(i).evaluate(values).get()[0];
			}
			return subValues;
		};