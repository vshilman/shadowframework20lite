//Java to JS on 15/03/2012

function SFVertex2fData(){
	this.vertex=new SFVertex2f();
	this.floatValues=this.vertex.v;
}

inherit(SFVertex2fData,SFVectorData);


SFVertex2fData.prototype["getVertex2f"]=function(){
			return vertex;
		};
		
SFVertex2fData.prototype["clone"]=function(){
			return new SFVertex2fData();
		};
