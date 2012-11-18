
//Java to JS on 21/03/2012

function SFExpressionMult(){
	this.list=new Array();
	this.operatorSymbol = "*";
	this.setElement(this.operatorSymbol);
	this.setType(SFParameteri_GLOBAL_FLOAT);
	this.maxSize = SFExpressionOperator_SIZE_ALL;
}

inherit(SFExpressionMult,SFExpressionOperator);

var SFExpressionMult_consecutives=new Array();
{
	var consecutive1=[SFParameteri_GLOBAL_MATRIX4,SFParameteri_GLOBAL_FLOAT4];
	SFExpressionMult_consecutives.push(consecutive1);
	var consecutive2=[SFParameteri_GLOBAL_FLOAT4,SFParameteri_GLOBAL_FLOAT4];
	SFExpressionMult_consecutives.push(consecutive2);
	var consecutive3=[SFParameteri_GLOBAL_FLOAT3,SFParameteri_GLOBAL_FLOAT3];
	SFExpressionMult_consecutives.push(consecutive3);
	var consecutive4=[SFParameteri_GLOBAL_FLOAT2,SFParameteri_GLOBAL_FLOAT2];
	SFExpressionMult_consecutives.push(consecutive4);
	var consecutive5=[SFParameteri_GLOBAL_FLOAT,SFParameteri_GLOBAL_FLOAT];
	SFExpressionMult_consecutives.push(consecutive5);
	var consecutive6=[SFParameteri_GLOBAL_FLOAT,SFParameteri_GLOBAL_FLOAT2];
	SFExpressionMult_consecutives.push(consecutive6);
	var consecutive7=[SFParameteri_GLOBAL_FLOAT,SFParameteri_GLOBAL_FLOAT3];
	SFExpressionMult_consecutives.push(consecutive7);
	var consecutive8=[SFParameteri_GLOBAL_FLOAT,SFParameteri_GLOBAL_FLOAT4];
	SFExpressionMult_consecutives.push(consecutive8);
	var consecutive9=[SFParameteri_GLOBAL_FLOAT2,SFParameteri_GLOBAL_FLOAT];
	SFExpressionMult_consecutives.push(consecutive9);
	var consecutive10=[SFParameteri_GLOBAL_FLOAT3,SFParameteri_GLOBAL_FLOAT];
	SFExpressionMult_consecutives.push(consecutive10);
	var consecutive11=[SFParameteri_GLOBAL_FLOAT4,SFParameteri_GLOBAL_FLOAT];
	SFExpressionMult_consecutives.push(consecutive11);
	var consecutive12=[SFParameteri_GLOBAL_MATRIX4,SFParameteri_GLOBAL_FLOAT3];
	SFExpressionMult_consecutives.push(consecutive12);
	var consecutive13=[SFParameteri_GLOBAL_FLOAT4,SFParameteri_GLOBAL_FLOAT3];
	SFExpressionMult_consecutives.push(consecutive13);
	var consecutive14=[SFParameteri_GLOBAL_FLOAT3,SFParameteri_GLOBAL_FLOAT4];
	SFExpressionMult_consecutives.push(consecutive14);
	var consecutive15=[SFParameteri_GLOBAL_GENERIC,SFParameteri_GLOBAL_FLOAT];
	SFExpressionMult_consecutives.push(consecutive15);
	var consecutive16=[SFParameteri_GLOBAL_FLOAT,SFParameteri_GLOBAL_GENERIC];
	SFExpressionMult_consecutives.push(consecutive16);
	var consecutive17=[SFParameteri_GLOBAL_GENERIC,SFParameteri_GLOBAL_GENERIC];
	SFExpressionMult_consecutives.push(consecutive17);
	var consecutive18=[SFParameteri_GLOBAL_FLOAT3,SFParameteri_GLOBAL_MATRIX4];
	SFExpressionMult_consecutives.push(consecutive18);
}


SFExpressionMult.prototype["evaluateType"]=function(){
			this.updateSubExpressions();
				
			var cElements = this.getTypesSeparatorList();
			if(cElements.length>1){
				this.checkConsecutives(cElements,SFExpressionMult_consecutives);
				var  maxElement = this.separateAndWrap(cElements);
				this.setType(maxElement);
				return;
			}
			this.setType(this.list[0].getType());
		};

SFExpressionMult.prototype["cloneOperator"]=function(){
			return new SFExpressionMult();
		};


SFExpressionMult.prototype["evaluate"]=function(values){
			var mult=getExpressionElement(0).evaluate(values);
			for (var i = 1; i < getElementSize(); i++) {
				mult.mult( getExpressionElement(i).evaluate(values));
			}
			return mult;
		};
	