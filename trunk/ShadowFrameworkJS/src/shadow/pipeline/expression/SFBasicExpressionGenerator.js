//Java to JS on 21/03/2012

function SFBasicExpressionGenerator(){
}

SFBasicExpressionGenerator.prototype["getOperator"]=function(operatorSymbol){
			if(operatorSymbol=="+"){
				return new SFExpressionSum();
			}
			if(operatorSymbol=="*"){
				return new SFExpressionMult();
			}
			if(operatorSymbol=="/"){
				return new SFExpressionDivide();
			}
			if(operatorSymbol=="-"){
				return new SFExpressionMinus();
			}
			if(operatorSymbol==","){
				return new SFExpressionVector();
			}
			return new SFExpressionSum();
		};

SFBasicExpressionGenerator.prototype["getExpressionElement"]=function(value,type){
			return new SFExpressionVariable(value,type);
		};		
		
SFBasicExpressionGenerator.prototype["getWrapper"]=function(type){
			return new SFExpressionTypeWrapper(type);
		};	
		
SFBasicExpressionGenerator.prototype["getFunction"]=function(functionSymbol){
			return new SFFunctionOperator(functionSymbol);
		};	
		
