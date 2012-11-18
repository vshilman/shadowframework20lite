//Java to JS on 15/03/2012

function SFShort(shortValue){
	this.shortValue=shortValue;
}

inherit(SFShort,SFPrimitiveType);

SFShort.prototype["getShortValue"]=function(){
			return this.shortValue;
		};

SFShort.prototype["setShortValue"]=function(shortValue){
			this.shortValue = shortValue;
		};

SFShort.prototype["readFromStream"]=function(stream){
			this.shortValue = stream.readShort();
		};

SFShort.prototype["writeOnStream"]=function(stream){
			stream.writeShort(this.shortValue);
		};
		
SFShort.prototype["clone"]=function(){
			return new SFShort(this.shortValue);
		};