
//Java to JS on 21/03/2012

function SFExpressionMinus(){
	this.list=new Array();
	this.operatorSymbol = "-";
	this.setElement(this.operatorSymbol);
	this.setType(SFParameteri_GLOBAL_FLOAT);
	this.maxSize = SFExpressionOperator.SIZE_ALL;
}

inherit(SFExpressionMinus,SFExpressionOperator);


SFExpressionMinus.prototype["evaluateType"]=function(){
			this.updateSubExpressions();
				
			var cElements = this.getTypesSeparatorList();
			if(cElements.length>1){
			//Tells if there are only vectors.			
				var onlyVectors = this.hasOnlyVectors(cElements);//Warning: Not well Identified 
				if ( onlyVectors ){
					var  maxElement = this.separateAndWrap(cElements);
					this.setType(maxElement);
					return;
				}else{
					this.throwBadOperandsType(cElements);
				}
			}
			this.setType(this.list[0].getType());
		};

SFExpressionMinus.prototype["cloneOperator"]=function(){
			return new SFExpressionMinus();
		};
		

SFExpressionMinus.prototype["evaluate"]=function(values){
			var sum=getExpressionElement(0).evaluate(values);
			if(this.getElementSize()==1){
				sum.mult(-1);
			}
			for (var i = 1; i < getElementSize(); i++) {
				sum.addMult(-1, getExpressionElement(i).evaluate(values));
			}
			return sum;
		};
