
var SFFixedFloat16_BIT_SIZE=16;
var SFFixedFloat16_MULTIPLIER=0.001;
var SFFixedFloat16_BACKMULTIPLIER=1000;

function SFFixedFloat16(f){
	this.multiplier=SFFixedFloat16_MULTIPLIER;
	this.backmultiplier=SFFixedFloat16_BACKMULTIPLIER;
	if(!(f==undefined)){
		this.setFloat(f);
	}
}

inherit(SFFixedFloat16,SFGenericFixedFloat);


SFFixedFloat16.prototype["getFloat"]=function(){
			var value=this.value>=32768?(this.value-65536):this.value;
			return value*this.multiplier;
		};

SFFixedFloat16.prototype["clone"]=function(){
	return new SFFixedFloat16();
};

SFFixedFloat16.prototype["getBitSize"]=function(){
	return SFFixedFloat16_BIT_SIZE;
};
