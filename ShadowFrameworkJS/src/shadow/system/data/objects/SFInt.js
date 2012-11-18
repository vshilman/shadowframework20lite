//Java to JS on 15/03/2012

function SFInt(intValue){
	this.intValue=intValue;
}

inherit(SFInt,SFPrimitiveType);

SFInt.prototype["getIntValue"]=function(){
			return this.intValue;
		};

SFInt.prototype["setIntValue"]=function(intValue){
			this.intValue = intValue;
		};

SFInt.prototype["readFromStream"]=function(stream){
			this.intValue = stream.readInt();
		};

SFInt.prototype["writeOnStream"]=function(stream){
			stream.writeInt(this.intValue);
		};
		
SFInt.prototype["clone"]=function(){
			return new SFInt(this.intValue);
		};
		