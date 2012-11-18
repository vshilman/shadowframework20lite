//Java to JS on 20/03/2012

function SFBinaryIntValue(bits){
	setValue(bits);
}

inherit(SFBinaryIntValue,SFBinaryValue);

SFCompositeDataArray.prototype["clone"]=function(object){
			return new SFBinaryValue(getValue());
		};
		
SFCompositeDataArray.prototype["getBitSize"]=function(object){
			return 0;
		};
