//Java to JS on 15/03/2012

function SFVertex3fData(){
	this.vertex=new SFVertex3f();
	this.floatValues=this.vertex.v;
}

inherit(SFVertex3fData,SFVectorData);


SFVertex3fData.prototype["getVertex3f"]=function(){
			return this.vertex;
		};
		
SFVertex3fData.prototype["clone"]=function(){
			return new SFVertex3fData();
		};
