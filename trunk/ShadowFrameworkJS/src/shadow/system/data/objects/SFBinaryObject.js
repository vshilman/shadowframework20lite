


function SFBinaryObject(binaryValue){
	this.binaryValue = binaryValue;
}

inherit(SFBinaryObject,SFPrimitiveType);

SFBinaryObject.prototype["getBinaryValue"]=function(){
	return this.binaryValue;
};

SFBinaryObject.prototype["clone"]=function(){
	return new SFBinaryObject<SFBinaryValue>(this.binaryValue.clone());
};


SFBinaryObject.prototype["readFromStream"]=function(stream){
	this.binaryValue.value=stream.readBinaryDataValue(this.binaryValue.getBitSize());
	if(this.binaryValue.value!=0){
		var max=1<<(this.binaryValue.getBitSize()-1);
		if(this.binaryValue.value>max){
			this.binaryValue.value = this.binaryValue.value-(1<<(this.binaryValue.getBitSize())); 
		}	
	}
};