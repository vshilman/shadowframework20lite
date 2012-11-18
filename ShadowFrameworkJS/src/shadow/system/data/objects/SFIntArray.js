//Java to JS on 15/03/2012

function SFIntArray(n){
	this.intValues=new Array();
	//n is ignored
}

inherit(SFIntArray,SFPrimitiveType);

SFIntArray.prototype["getIntValues"]=function(){
			return this.intValues;
		};

SFIntArray.prototype["readFromStream"]=function(stream){
			var  n = stream.readInt();
			this.intValues  = stream.readInts(n);
		};

SFIntArray.prototype["writeOnStream"]=function(stream){
			stream.writeInt(this.intValues.length);
			stream.writeInts(this.intValues);
		};

SFIntArray.prototype["clone"]=function(){
			return new SFIntArray(intValues.length);
		};
