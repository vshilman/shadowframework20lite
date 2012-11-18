//Java to JS on 06/02/2012

function SFInputStreamJs(data){
	this.v=data.split("#");
	this.counter=0;
}

SFInputStreamJs.prototype["readString"]=function(){
			var string=this.v[this.counter];
			this.counter++;
			return string;
		};

SFInputStreamJs.prototype["readShort"]=function(){
			var string=this.readString();
			return string;
		};

SFInputStreamJs.prototype["readInt"]=function(){
			return this.readString();
		};

SFInputStreamJs.prototype["readFloat"]=function(){
			return this.readString();
		};


SFInputStreamJs.prototype["readShorts"]=function(n){
			var array=new Array();
			for(var i=0;i<n;i++){
				array[i]=this.readString();
			}
			return array;
		};
		
SFInputStreamJs.prototype["readInts"]=function(n){
			return this.readShorts(n);
		};
		
SFInputStreamJs.prototype["readBytes"]=function(n){
			return this.readShorts(n);
		};
				
SFInputStreamJs.prototype["readFloats"]=function(n){
			var array=new Array();
			for(var i=0;v<n;i++){
				array[i]=parsetFlaot(readString());
			}
			return array;
		};
		
SFInputStreamJs.prototype["readBinaryData"]=function(n,bitSize){
			return this.readShorts(n);
		};

		