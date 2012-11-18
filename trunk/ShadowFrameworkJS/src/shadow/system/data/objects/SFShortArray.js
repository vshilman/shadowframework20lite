//Java to JS on 15/03/2012

function SFShortArray(shortValues){
	this.shortValues=shortValues;
	//n is ignored
}

inherit(SFShortArray,SFPrimitiveType);

SFShortArray.prototype["getShortValues"]=function(){
			return this.shortValues;
		};

SFShortArray.prototype["readFromStream"]=function(stream){
			var  n = stream.readInt();
			this.shortValues  = stream.readShorts(n);
		};

SFShortArray.prototype["writeOnStream"]=function(stream){
			stream.writeInt(this.shortValues.length);
			stream.writeShorts(this.shortValues);
		};

SFShortArray.prototype["clone"]=function(){
			return new SFShortArray(this.shortValues.length);
		};
