
//Java to JS on 21/03/2012

function SFExpressionDivide(){
	this.list=new Array();
	this.operatorSymbol = "/";
	this.setElement(this.operatorSymbol);
	this.setType(SFParameteri_GLOBAL_FLOAT);
	this.maxSize = 2;
}

inherit(SFExpressionDivide,SFExpressionOperator);

var SFExpressionDivide_consecutives=new Array();
{
	var consecutive2=[SFParameteri_GLOBAL_FLOAT4,SFParameteri_GLOBAL_FLOAT];
	SFExpressionDivide_consecutives.push(consecutive2);
	var consecutive3=[SFParameteri_GLOBAL_FLOAT3,SFParameteri_GLOBAL_FLOAT];
	SFExpressionDivide_consecutives.push(consecutive3);
	var consecutive4=[SFParameteri_GLOBAL_FLOAT2,SFParameteri_GLOBAL_FLOAT];
	SFExpressionDivide_consecutives.push(consecutive4);
	var consecutive5=[SFParameteri_GLOBAL_FLOAT,SFParameteri_GLOBAL_FLOAT];
	SFExpressionDivide_consecutives.push(consecutive5);
}


SFExpressionDivide.prototype["evaluateType"]=function(){
			this.updateSubExpressions();
			var cElements = this.getTypesSeparatorList();
			this.checkConsecutives(cElements,SFExpressionDivide_consecutives);
			this.setType(cElements[0].getType());
		};

SFExpressionDivide.prototype["cloneOperator"]=function(){
			return new SFExpressionDivide();
		};
		

SFExpressionDivide.prototype["evaluate"]=function(values){
			var value1=getExpressionElement(0).evaluate(values);
			var value2=getExpressionElement(1).evaluate(values);
			return new SFValue1f(value1.get()[0]/value2.get()[1]);
		};
		