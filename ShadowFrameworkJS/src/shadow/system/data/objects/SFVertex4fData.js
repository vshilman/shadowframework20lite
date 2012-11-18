//Java to JS on 15/03/2012

function SFVertex4fData(){
	this.vertex=new SFVertex4f();
	this.floatValues=this.vertex.v;
}

inherit(SFVertex4fData,SFVectorData);


SFVertex4fData.prototype["getVertex4f"]=function(){
			return vertex;
		};
		
SFVertex4fData.prototype["clone"]=function(){
			return new SFVertex4fData();
		};
