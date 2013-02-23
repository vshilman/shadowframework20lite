

function SFGenericFixedFloat(multiplier,backmultiplier){
	this.multiplier = multiplier;
	this.backmultiplier = backmultiplier;
}

inherit(SFGenericFixedFloat,SFBinaryValue);


SFGenericFixedFloat.prototype["getFloat"]=function(){
			var COUNT=(1<<size);
			var MAX_POSITIVE=(1<<(size-1))-1;
			var value=this.value>=32768?(this.value-65536):this.value;
			return value*this.multiplier;
		};

SFGenericFixedFloat.prototype["setFloat"]=function(f){
			var data=Math.floor(f*this.backmultiplier);
			this.value=(data<0?data+256*256:data);
		};
