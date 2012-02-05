
function SFBasicExpressionGenerator(){
}

SFBasicExpressionGenerator.prototype = {

	getOperator:function(operatorSymbol){
		if(operatorSymbol.equalsIgnoreCase("+")){
		return ,new ,SFExpressionSum();
	}
		if(operatorSymbol.equalsIgnoreCase("*")){
		return ,new ,SFExpressionMult();
	}
		if(operatorSymbol.equalsIgnoreCase("/")){
		return ,new ,SFExpressionDivide();
	}
		if(operatorSymbol.equalsIgnoreCase("-")){
		return ,new ,SFExpressionMinus();
	}
	//if(operatorSymbol.equalsIgnoreCase(":"));//Warning: Not well Identified 
		return ,new ,SFExpressionClamp();
	//}
	//if(operatorSymbol.equalsIgnoreCase("°"));//Warning: Not well Identified 
		return ,new ,SFExpressionDot();
	//}
		if(operatorSymbol.equalsIgnoreCase(",")){
		return ,new ,SFExpressionVector();
	}
		return ,new ,SFExpressionSum();
	},

	getExpressionElement:function(value, set){
		return ,new ,SFExpressionVariable(value,set);
	},

	getWrapper:function(type){
		return ,new ,SFExpressionTypeWrapper(type);
	}

};