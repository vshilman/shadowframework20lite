
function SFExpressionMult(){
	super("*", SIZE_ALL);//Warning: Not well Identified 
}

SFExpressionMult.prototype = {

	cloneOperator:function(){
	return new SFExpressionMult();//Warning: Not well Identified 
	}

};