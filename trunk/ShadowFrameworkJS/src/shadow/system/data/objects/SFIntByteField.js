

function SFIntByteField(intValue){
	this.intValue=intValue;
}

inherit(SFIntByteField,SFInt);

SFIntByteField.prototype["getByte"]=function(byteIndex){
	var value = this.intValue >> (byteIndex * 8);
	return value & 0xff;
};
	

SFIntByteField.prototype["setByte"]=function(byteIndex,value){
	value = value & 0xff;
	value = value << (byteIndex * 8);
	var mask = 0xff << (byteIndex * 8);
	mask = (-1) - mask;
	this.intValue = intValue & mask;
	this.intValue += value;
};	

