

function SFExpressionsBuilderStack(sfExpressionBuilderData){
	this.storedExpressiond=new Array();
	this.storedlSymbol=new Array();
	this.storedlastIndexOf=new Array();
	this.sfExpressionBuilderData=sfExpressionBuilderData;
}


SFExpressionsBuilderStack.prototype["pushExpressions"]=function(){
	this.storedExpressiond.push(this.sfExpressionBuilderData.getExpressions());
	this.storedlSymbol.push(this.sfExpressionBuilderData.getlSymbol());
	this.storedlastIndexOf.push(this.sfExpressionBuilderData.getIndexOfLastOperation());
		
		this.sfExpressionBuilderData.setExpressions(new Array());
		this.sfExpressionBuilderData.setlSymbol(null);
		this.sfExpressionBuilderData.setLastValue(null);
		this.sfExpressionBuilderData.setIndexOfLastOperation(-1);
};

SFExpressionsBuilderStack.prototype["popExpressions"]=function(){
		if(this.sfExpressionBuilderData.getExpressions().length>0)
			this.sfExpressionBuilderData.setLastValue(this.sfExpressionBuilderData.getExpressions()[0]);
		var position=this.storedExpressiond.length-1;
		this.sfExpressionBuilderData.setExpressions(remove(this.storedExpressiond,position));
		this.sfExpressionBuilderData.setlSymbol(remove(this.storedlSymbol,position));
		this.sfExpressionBuilderData.setIndexOfLastOperation(remove(this.storedlastIndexOf,position));
};