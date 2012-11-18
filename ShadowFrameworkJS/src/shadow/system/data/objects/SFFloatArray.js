//Java to JS on 15/03/2012

function SFFloatArray(n){
	this.floatValues=new Array();
	//n is ignored
}

inherit(SFFloatArray,SFPrimitiveType);

SFFloatArray.prototype["getFloatValues"]=function(){
			return this.floatValues;
		};

SFFloatArray.prototype["readFromStream"]=function(stream){
			var  n = stream.readInt();
			this.floatValues  = stream.readFloats(n);
		};

SFFloatArray.prototype["writeOnStream"]=function(stream){
			stream.writeInt(this.floatValues.length);
			stream.writeFloats(this.floatValues);
		};

SFFloatArray.prototype["clone"]=function(){
			return new SFFloatArray(floatValues.length);
		};
