//Java to JS on 20/03/2012

function SFBinaryValue(){
	this.value = getBitSize();
}

SFBinaryValue.prototype["getValue"]=function(object){
			return this.value;
		};
		
SFBinaryValue.prototype["setValue"]=function(value){
			this.value = value;
			//alert("setValue END "+this.value+" ");
	};