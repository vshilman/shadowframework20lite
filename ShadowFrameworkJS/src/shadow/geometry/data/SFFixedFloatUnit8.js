
var SFFixedFloatUnit8_BIT_SIZE=8;
var SFFixedFloatUnit8_MULTIPLIER=0.004;
var SFFixedFloatUnit8_BACKMULTIPLIER=250;

function SFFixedFloatUnit8(f){
	this.multiplier=SFFixedFloatUnit8_MULTIPLIER;
	this.backmultiplier=SFFixedFloatUnit8_BACKMULTIPLIER;
	if(!(f==undefined)){
		this.setFloat(f);
	}
}

inherit(SFFixedFloatUnit8,SFGenericFixedFloat);


SFFixedFloatUnit8.prototype["getFloat"]=function(){
			return this.value*this.multiplier;
		};

SFFixedFloatUnit8.prototype["clone"]=function(){
	return new SFFixedFloatUnit8();
};

SFFixedFloatUnit8.prototype["getBitSize"]=function(){
	return SFFixedFloatUnit8_BIT_SIZE;
};

