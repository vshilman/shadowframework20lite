


function SFShortByteField(shortValue){
	this.setShortValue(shortValue);
}

inherit(SFShortByteField,SFShort);

SFShortByteField.prototype["getByte"]=function(byteIndex){
	var value = this.shortValue >> (byteIndex * 8);
	return value & 0xff;
};
		

SFShortByteField.prototype["setByte"]=function(byteIndex,value){
	value = value & 0xff;
	value = value << (byteIndex * 8);
	var mask = 0xff << (byteIndex * 8);
	mask = (-1) - mask;
	this.shortValue = (this.shortValue & mask);
	this.shortValue += value; 
};
	
