
function SFExpressionVariable(element,type){
	this.list=new Array();
	this.element=element;
	this.setType(type);
}

inherit(SFExpressionVariable,SFExpressionElement);


SFExpressionVariable.prototype["cloneAsIndexed"]=function(toBeIndexed){
	for (var i = 0; i < toBeIndexed.length; i++) {
		if(toBeIndexed[i].getName()===(this.getElement())){
			return new SFExpressionIndex(i);
		}
	}
	return this;
};
		
SFExpressionVariable.prototype["addSubExpression"]=function(element){
			//Nothing to do;
		};
	
SFExpressionVariable.prototype["evaluateType"]=function(){
			//Nothing to do;
		};
		
SFExpressionVariable.prototype["evaluate"]=function(values){

			var value = values.generateValue();
			var d = getElement();
			for (var i = 0; i < value.get().length; i++) {
				value.get()[i] = d;
			}
			return value;
			
		};