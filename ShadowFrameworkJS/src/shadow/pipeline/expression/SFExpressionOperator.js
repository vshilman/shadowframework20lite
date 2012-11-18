
var SFExpressionOperator_SIZE_ALL=-1; 

function SFExpressionOperator(operatorSymbol,maxSize){
	this.list=new Array();
	this.setElement(operatorSymbol);
	this.operatorSymbol = operatorSymbol;
	this.maxSize = maxSize;
}

inherit(SFExpressionOperator,SFExpressionElement);

SFExpressionOperator.prototype["getOperatorSymbol"]=function(){
			return this.operatorSymbol;
		};

SFExpressionOperator.prototype["cloneAsIndexed"]=function(toBeIndexed){
	var operator=this.cloneOperator();
	for (var i = 0; i < this.list.length; i++) {
		operator.list.push(this.list[i].cloneAsIndexed(toBeIndexed));
	}
	return operator;
};


SFExpressionOperator.prototype["addSubExpression"]=function(element){
			if(!(this.maxSize==SFExpressionOperator_SIZE_ALL) && getElementSize()==maxSize)
				throw "Too much elements in SFExpressionOperator "+operatorSymbol;
			addElement(element);
		};
	
SFExpressionOperator.prototype["updateSubExpressions"]=function(){
			for (var index in this.list) {
				var expression=this.list[index];
				expression.evaluateType();
			}
		};			
	
SFExpressionOperator.prototype["throwBadOperandsType"]=function(cElements){
			var s="";
			s+=cElements[0].getElement();
			for(var i=1;i<cElements.length;i++){
				s+=";"+cElements[i];
			}
			throw "Cannot apply "+this.getElement()+" with the following operands: "+s;
		};	
		
SFExpressionOperator.prototype["separateAndWrap"]=function(cElements){
			
			var typeElements=new Array();
			typeElements[0]=new Array();
			
			if(this.list.length>1){
				var index=0;
				typeElements[0].push(this.list[0]);
				for(var i=1;i<this.list.length;i++){
					var element=this.list[i];
					if(element.getType()!=typeElements[index][typeElements[index].length-1].getType()){
						index++;
						typeElements[index]=new Array();
						typeElements[index].push(element);
					}else{ 
						typeElements[index].push(element);
					}
				}
			}

			var maxElement=0;
			
			this.list=[];
			
			for (var i = 0; i < cElements.length; i++) {
				
				var elements=typeElements[i];
				var type=elements[0].getType();
				if(type>maxElement){
					maxElement=type;
				}
				if(elements.length>1){
					var wrapper=SFExpressionGeneratorKeeper_getKeeper().getGenerator().getWrapper(type);
					var clone=this.cloneOperator();
					clone.setType(type);
					if(type>maxElement){
						maxElement=type;
					}
					addAll(clone.list,elements);
					wrapper.list.push(clone);
					this.list.push(wrapper);
				}else{
					this.list.push(elements[0]);
				}
			}
			
			return maxElement;
		};				
		
SFExpressionOperator.prototype["getTypesSeparatorList"]=function(){
			var cElements=new Array();
			if(this.list.length>0){
				cElements.push(this.list[0]);
				for(var i=1;i<this.list.length;i++){
					var tmp=this.list[i];
					if(tmp.getType()!=getLast(cElements).getType()){
						cElements.push(tmp);
					}
				}
			}
			return cElements;
			
		};			
	
SFExpressionOperator.prototype["checkConsecutives"]=function(cElements,consecutives){
			/*if(cElements.length>1){
				//var tmp=null;
				var tmpPrev=cElements[0];
				for(var i=1;i<cElements.length;i++){
					var tmp=cElements[i];
					var foundConsecutive=false;
					var i=0;
					while(i<consecutives.length && !foundConsecutive){
						var tps=consecutives[i];
						if(tmpPrev.getType()==tps[0] & tmp.getType()==tps[1]){
							foundConsecutive=true;
						}
						i++;
					}
					if(!foundConsecutive){
						throw "Uncompatible operands: "+tmpPrev.getElement()+"("+tmpPrev.getType()+")"+
							getElement()+tmp.getElement()+"("+tmp.getType()+")";
					}
				}
			}*/
		};
	
SFExpressionOperator.prototype["hasOnlyVectors"]=function(cElements){
			var onlyVectors=true;
			for(var index in cElements){
				var element=cElements[index];	
				var type=element.getType();
				if(type<SFParameteri_GLOBAL_FLOAT || type>SFParameteri_GLOBAL_FLOAT4){
					onlyVectors=false;
				}
			}
			return onlyVectors;
		};
		