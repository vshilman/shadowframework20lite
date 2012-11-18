//Java to JS on 15/03/2012

function SFVoidData(){
}

inherit(SFVoidData,SFPrimitiveType);

var static_SFVoidData=new SFVoidData();

SFVoidData.prototype["clone"]=function(label){
			return static_SFVoidData;
		};
		