


function SFIntShortField(intValue){
	this.intValue=intValue;
}

inherit(SFIntShortField,SFInt);

SFIntShortField.prototype["getShort"]=function(byteIndex){
	var value = this.intValue >> (byteIndex * 16);
	return value & 0xff;
};
	

SFIntShortField.prototype["setShort"]=function(shortIndex,value){
	value = value & 0xffff;
	value = value << (byteIndex * 16);
	var mask = 0xffff << (byteIndex * 16);
	mask = (-1) - mask;
	this.intValue = intValue & mask;
	this.intValue += value;
};	

