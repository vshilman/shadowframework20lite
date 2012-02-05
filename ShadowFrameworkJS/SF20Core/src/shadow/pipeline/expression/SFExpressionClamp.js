
function SFExpressionClamp(){
}

SFExpressionClamp.prototype = {

	evaluateType:function(){
		updateSubExpressions();
	//Get a list of all Elements which have a different type from previous one		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();//Warning: Not well Identified 
		checkConsecutives(cElements,consecutives);
	//This must not be separated and wrapped		//short maxElement=separateAndWrap(cElements);//Warning: Not well Identified 
		this.setType(cElements.get(0).getType());
	},

	cloneOperator:function(){
		return ,new ,SFExpressionClamp();
	}

};