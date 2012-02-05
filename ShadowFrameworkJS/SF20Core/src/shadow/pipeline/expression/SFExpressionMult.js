
function SFExpressionMult(){
}

SFExpressionMult.prototype = {

	evaluateType:function(){
		updateSubExpressions();
	//Get a list of all Elements which have a different type from previous one		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();//Warning: Not well Identified 
	//if(cElements.size()>1);//Warning: Not well Identified 
		checkConsecutives(cElements,consecutives);
		 short  maxElement = separateAndWrap(cElements);
		this.setType(maxElement);
		return;
	//}
		this.setType(list.getFirst().getType());
	},

	cloneOperator:function(){
		return ,new ,SFExpressionMult();
	}

};