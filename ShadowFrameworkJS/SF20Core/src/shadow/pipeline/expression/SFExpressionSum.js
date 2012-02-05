
function SFExpressionSum(){
}

SFExpressionSum.prototype = {

	evaluateType:function(){
		updateSubExpressions();
	//Get a list of all Elements which have a different type from previous one		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();//Warning: Not well Identified 
	//if(cElements.size()>1);//Warning: Not well Identified 
	//Tells if there are only vectors.			boolean onlyVectors = hasOnlyVectors(cElements);//Warning: Not well Identified 
		 if ( onlyVectors ){
		 short  maxElement = separateAndWrap(cElements);
		this.setType(maxElement);
		return;
	}
		else{
		throwBadOperandsType(cElements);
	}
	//}
		this.setType(list.getFirst().getType());
	},

	cloneOperator:function(){
		return ,new ,SFExpressionSum();
	}

};