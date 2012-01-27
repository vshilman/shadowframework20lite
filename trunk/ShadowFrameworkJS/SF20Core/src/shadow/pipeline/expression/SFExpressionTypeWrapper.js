
function SFExpressionTypeWrapper(type){
	super("wr"+type);//Warning: Not well Identified 
	setType(type);//Warning: Not well Identified 
}
function SFExpressionTypeWrapper(type, wrappedExpression){
	super("wr"+type);//Warning: Not well Identified 
	setType(type);//Warning: Not well Identified 
	addSubExpression(wrappedExpression);//Warning: Not well Identified 
}

SFExpressionTypeWrapper.prototype = {
};