

SFGL20ExpressionGenerator.prototype = {

	closeElement:function(element){
	},

	refreshElement:function(element){
	String function;
	},

	startElement:function(element){
	//System.out.println("Start "+element.getElement());//Warning: Not well Identified 
	//System.out.println("starting element "+element.getElement()+" last "+parameters.getLast().getElement()+" "+element.getType()+" "+parameters.getLast().getType()+" ");//Warning: Not well Identified 
	},

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