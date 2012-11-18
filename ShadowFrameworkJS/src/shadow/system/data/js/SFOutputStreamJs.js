//Java to JS on 06/02/2012

function SFOutputStreamJs(){
	this.v="";
	this.counter=0;
}

SFOutputStreamJs.prototype["writeString"]=function(s){
			if(this.counter>0)
				this.v+="##";
			this.counter++;
			this.v+=s;
		};

SFOutputStreamJs.prototype["writeShort"]=function(value){
			this.writeString(value);
		};

SFOutputStreamJs.prototype["writeInt"]=function(value){
			this.writeString(value);
		};

SFOutputStreamJs.prototype["writeFloat"]=function(value){
			this.writeString(value);
		};


SFOutputStreamJs.prototype["writeShorts"]=function(values){
			for(var i=0;i<values.length;i++){
				this.writeString(values[i]);
			}
		};
		
SFOutputStreamJs.prototype["writeInts"]=function(values){
			this.writeShorts(values);
		};
		
SFOutputStreamJs.prototype["writeBytes"]=function(values){
			this.writeShorts(values);
		};
				
SFOutputStreamJs.prototype["writeFloats"]=function(values){
			this.writeShorts(values);
		};
		
SFOutputStreamJs.prototype["writeBinaryData"]=function(values,bitSize){
			this.writeShorts(values);
		};

SFOutputStreamJs.prototype["getData"]=function(){
			return this.v;
		};



