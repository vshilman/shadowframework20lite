
function SFExpressionBuilderData(){
	this.expressions=new Array();
}

SFExpressionBuilderData.prototype["setupLastSymbol"]=function(sfExpressionBuilder){
		if(!(this.getlSymbol()==undefined))
			this.getlSymbol().addElement(this.getLastValue());
	};

SFExpressionBuilderData.prototype["getExpressions"]=function(){
		return this.expressions;
	};

SFExpressionBuilderData.prototype["setExpressions"]=function(expressions){
		this.expressions = expressions;
	};

SFExpressionBuilderData.prototype["getlSymbol"]=function(){
		return this.lSymbol;
	};

SFExpressionBuilderData.prototype["setlSymbol"]=function(lSymbol){
		this.lSymbol = lSymbol;
	};

SFExpressionBuilderData.prototype["getLastValue"]=function(){
		return this.lastValue;
	};

SFExpressionBuilderData.prototype["setLastValue"]=function(lastValue){
		this.lastValue = lastValue;
	};

SFExpressionBuilderData.prototype["getIndexOfLastOperation"]=function(){
		return this.indexOfLastOperation;
	};

SFExpressionBuilderData.prototype["setIndexOfLastOperation"]=function(indexOfLastOperation){
		this.indexOfLastOperation = indexOfLastOperation;
	};