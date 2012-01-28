
function SFBasicExpressionGenerator(){
}

SFBasicExpressionGenerator.prototype = {

	getOperator:function(operatorSymbol){
	return new SFExpressionSum();//Warning: Not well Identified 
	},

	getExpressionElement:function(value, set){
	return new SFExpressionVariable(value,set);//Warning: Not well Identified 
	},

	getWrapper:function(type){
	return new SFExpressionTypeWrapper(type);//Warning: Not well Identified 
	}

};