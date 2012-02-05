
function SFPrimitiveIndices(){
}

SFPrimitiveIndices.prototype = {

	setData:function(indices, registerIndex){
		for ( int  j  =  0 ; j   < primitiveIndices[registerIndex].length ; j++ ){
		primitiveIndices[registerIndex][j]  = indices.primitiveIndices[registerIndex][j];
	}
	},

	set:function(indices){
		for ( int  i  =  0 ; i   < primitiveIndices.length ; i++ ){
		for ( int  j  =  0 ; j   < primitiveIndices[i].length ; j++ ){
		primitiveIndices[i][j]  = indices.primitiveIndices[i][j];
	}
	}
	},

	getPrimitiveIndices:function(){
		return ,primitiveIndices;
	},

	setPrimitiveIndices:function(primitiveIndices){
		this.primitiveIndices    = primitiveIndices;
	},

	clone:function(){
		 SFPrimitiveIndices  indices = new  SFPrimitiveIndices();
		indices.primitiveIndices  = new .int[this.primitiveIndices.length][];
		for ( int  i  =  0 ; i   < indices.primitiveIndices.length ; i++ ){
		indices.primitiveIndices[i]  = new .int[this.primitiveIndices[i].length];
	}
		return ,indices;
	}

};