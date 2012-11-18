//Java to JS on 22/03/2012

function SFGL20Function(globalV,func,set){
	this.parameter = globalV;
	
	var builder=new SFExpressionBuilder();
	SFExpressionParser_getParser().parseString(func,set,builder);

	this.setFunction(builder.getBuiltExpression());

}

inherit(SFGL20Function,SFFunction);