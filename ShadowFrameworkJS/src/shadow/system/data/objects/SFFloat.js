//Java to JS on 15/03/2012

function SFFloat(floatValue){
	this.floatValue=floatValue;
}

inherit(SFFloat,SFPrimitiveType);

SFFloat.prototype["getFloatValue"]=function(){
			return this.floatValue;
		};

SFFloat.prototype["setFloatValue"]=function(floatValue){
			this.floatValue = floatValue;
		};

SFFloat.prototype["readFromStream"]=function(stream){
			this.floatValue = stream.readFloat();
		};

SFFloat.prototype["writeOnStream"]=function(stream){
			stream.writeFloat(this.floatValue);
		};
		
SFFloat.prototype["clone"]=function(){
			return new SFFloat(this.floatValue);
		};