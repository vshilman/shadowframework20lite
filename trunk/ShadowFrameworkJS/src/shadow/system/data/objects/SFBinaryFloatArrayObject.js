

function SFBinaryFloatArrayObject(size,dim){
	this.size=size;
	this.dim=dim;
}

inherit(SFBinaryFloatArrayObject,SFPrimitiveType);


SFBinaryFloatArrayObject.prototype["getBytes"]=function(){
			return this.values;			
		};

SFBinaryFloatArrayObject.prototype["clone"]=function(bytes){
			return new SFBinaryFloatArrayObject(this.size,this.dim);		
		};

SFBinaryFloatArrayObject.prototype["getValues"]=function(){
			return this.values;			
		};	

SFBinaryFloatArrayObject.prototype["setValues"]=function(values){
			this.values=values;			
		};	
		

SFBinaryFloatArrayObject.prototype["readFromStream"]=function(stream){
			var length=stream.readShort();
			var values=stream.readBinaryData(length, this.size);
			this.values=new Array;
			var recDim=1.0/this.dim;
			for (var i = 0; i < values.length; i++) {
				this.values[i]=values[i]*recDim;
			}		
		};	

	