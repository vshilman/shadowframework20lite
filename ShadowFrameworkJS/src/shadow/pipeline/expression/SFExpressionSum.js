
//Java to JS on 21/03/2012

function SFExpressionSum(){
	this.list=new Array();
	this.operatorSymbol = "+";
	this.setElement(this.operatorSymbol);
	this.setType(SFParameteri_GLOBAL_FLOAT);
	this.maxSize = SFExpressionOperator_SIZE_ALL;
}

inherit(SFExpressionSum,SFExpressionOperator);

SFExpressionSum.prototype["evaluateType"]=function(){
			this.updateSubExpressions();
				
			var cElements = this.getTypesSeparatorList();
			if(cElements.length>1){
				var onlyVectors = this.hasOnlyVectors(cElements);
				if(onlyVectors){
					var maxElement=this.separateAndWrap(cElements);
					this.setType(maxElement);
					return;
				}else{
					//this.throwBadOperandsType(cElements);
				}	
			}
			
			this.setType(this.list[0].getType());
			
		};

SFExpressionSum.prototype["cloneOperator"]=function(){
			return new SFExpressionSum();
		};

SFExpressionSum.prototype["evaluate"]=function(values){
			var sum=getExpressionElement(0).evaluate(values);
			for (var i = 1; i < getElementSize(); i++) {
				sum.addMult(1, getExpressionElement(i).evaluate(values));
			}
			return sum;
		};
		