

function SFGenericFixedFloat(multiplier,backmultiplier){
	this.multiplier = multiplier;
	this.backmultiplier = backmultiplier;
}

inherit(SFGenericFixedFloat,SFBinaryValue);


SFGenericFixedFloat.prototype["getFloat"]=function(){
			var size=this.getBitSize();
			var COUNT=(1<<size);
			var MAX_POSITIVE=(1<<(size-1))-1;
			var value=this.value>MAX_POSITIVE?(this.value-COUNT):this.value;
			return value*this.multiplier;
		};

SFGenericFixedFloat.prototype["setFloat"]=function(f){
			var data=Math.floor(f*this.backmultiplier);
			this.value=(data<0?data+256*256:data);
		};
