
function SFBinaryArrayObject(size){
	this.size=size;
}

inherit(SFBinaryArrayObject,SFPrimitiveType);


SFBinaryArrayObject.prototype["getBytes"]=function(){
			return this.values;			
		};

SFBinaryArrayObject.prototype["setBytes"]=function(bytes){
			this.values=bytes;			
		};

SFBinaryArrayObject.prototype["clone"]=function(bytes){
			return new SFBinaryArrayObject(this.size);		
		};

SFBinaryArrayObject.prototype["getValues"]=function(){
			return this.values;			
		};			

SFBinaryArrayObject.prototype["readFromStream"]=function(stream){
			var length=stream.readShort();
			this.values=stream.readBinaryData(length, this.size);		
		};	