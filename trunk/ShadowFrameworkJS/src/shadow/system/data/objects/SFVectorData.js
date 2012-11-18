//Java to JS on 15/03/2012

function SFVectorData(){
	this.floatValues=new Array();
}

inherit(SFVectorData,SFPrimitiveType);


SFVectorData.prototype["getFloatValues"]=function(){
			return floatValues;
		};
		
SFVectorData.prototype["readFromStream"]=function(stream){
			var values = stream.readFloats(this.floatValues.length);
			for ( var  i  =  0 ; i   < values.length ; i++ ){
				this.floatValues[i]  = values[i];
			}
		};
		
SFVectorData.prototype["writeOnStream"]=function(stream){
			stream.writeFloats(floatValues);
		};

SFVectorData.prototype["clone"]=function(){
			return new SFVectorData();
		};
