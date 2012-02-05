
function SFExpressionVector(){
}

SFExpressionVector.prototype = {

	evaluateType:function(){
		updateSubExpressions();
	//Get a list of all Elements which have a different type from previous one		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();//Warning: Not well Identified 
	//if(cElements.size()>1);//Warning: Not well Identified 
		checkConsecutives(cElements,consecutives);
		 short  maxElement = separateAndWrap(cElements);
		this.setType(maxElement);
		return;
	//}
		switch (list.size()){
	case 1: this.setType(SFParameteri.GLOBAL_FLOAT);//Warning: Not well Identified 
		break;
	case 2: this.setType(SFParameteri.GLOBAL_FLOAT2);//Warning: Not well Identified 
		break;
	case 3: this.setType(SFParameteri.GLOBAL_FLOAT3);//Warning: Not well Identified 
		break;
	default: this.setType(SFParameteri.GLOBAL_FLOAT4);//Warning: Not well Identified 
		break;
	}
		this.setType(list.getFirst().getType());
	},

	cloneOperator:function(){
		return ,new ,SFExpressionVector();
	}

};