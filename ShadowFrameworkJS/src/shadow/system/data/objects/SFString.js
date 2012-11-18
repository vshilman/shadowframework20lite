//Java to JS on 15/03/2012

function SFString(string){
	this.string=string;
}

inherit(SFString,SFPrimitiveType);

SFString.prototype["getString"]=function(){
			return this.string;
		};

SFString.prototype["setString"]=function(string){
			this.string = string;
		};

SFString.prototype["readFromStream"]=function(stream){
			this.string  = stream.readString();
		};

SFString.prototype["writeOnStream"]=function(stream){
			stream.writeString(this.string);
		};

SFString.prototype["clone"]=function(stream){
			return new SFString(this.string);
		};
		