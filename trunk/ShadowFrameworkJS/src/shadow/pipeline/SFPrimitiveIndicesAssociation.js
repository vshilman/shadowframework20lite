//Java to JS on 21/03/2012

function SFPrimitiveIndicesAssociation(register,indices){
	this.register=register;
	this.indices=indices;
}

SFPrimitiveIndicesAssociation.prototype["getRegister"]=function(){
			return this.register;
		};

SFPrimitiveIndicesAssociation.prototype["getIndices"]=function(){
			return this.indices;
		};
