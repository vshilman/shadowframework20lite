
function SFExpressionTypeWrapper(type,wrappedExpression){
	this.list=new Array();
	this.element="wr"+type;
	this.setType(type);
	
	if(wrappedExpression!=undefined)
		addSubExpression(wrappedExpression);
}

inherit(SFExpressionTypeWrapper,SFExpressionElement);


SFExpressionTypeWrapper.prototype["addSubExpression"]=function(element){
			list.addElement(element);
		};

SFExpressionTypeWrapper.prototype["evaluateType"]=function(){
			
		};

SFExpressionTypeWrapper.prototype["cloneAsIndexed"]=function(toBeIndexed){
	var wrapper=new SFExpressionTypeWrapper(getType());
	for (var i = 0; i < this.list.length; i++) {
		wrapper.list.push(cloneAsIndexed(toBeIndexed));
	}
	return wrapper;	
};	

		
SFExpressionTypeWrapper.prototype["evaluate"]=function(values){
			var subValue=getExpressionElement(0).evaluate(values);
			var dimension=SFParameteri_.getTypeDimension(this.getType());
		
			var result=new SFValuenf(dimension);
			
			if(subValue.get().length==1){
				for (var i = 0; i < result.getSize(); i++) {
					result.get()[i]=subValue.get()[0];
				}
			}else if(dimension>=subValue.getSize()){
				for (var i = 0; i < subValue.getSize(); i++) {
					result.get()[i]=subValue.get()[i];
				}
				if(subValue.getSize()<3){
					result.get()[2]=0;
				}
				if(subValue.getSize()<4){
					result.get()[3]=1;
				}
			}else{//dimension<subValue.getSize()
				for (var i = 0; i <dimension; i++) {
					result.get()[i]=subValue.get()[i];
				}
			}
		
			return result;
		};
