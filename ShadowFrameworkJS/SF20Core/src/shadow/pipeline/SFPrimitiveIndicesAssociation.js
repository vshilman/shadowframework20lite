
function SFPrimitiveIndicesAssociation(register, indices){
		this.register=register;
		this.indices=indices;
}

SFPrimitiveIndicesAssociation.prototype = {

	getRegister:function(){
		return this.register;
	},

	getIndices:function(){
		return this.indices;
	}

};