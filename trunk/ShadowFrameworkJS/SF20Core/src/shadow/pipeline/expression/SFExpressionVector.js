
function SFExpressionVector(){
	super(",", SIZE_ALL);//Warning: Not well Identified 
}

SFExpressionVector.prototype = {

	cloneOperator:function(){
	return new SFExpressionVector();//Warning: Not well Identified 
	}

};