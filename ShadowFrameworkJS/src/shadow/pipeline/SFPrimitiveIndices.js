//Java to JS on 21/03/2012
//Java to JS Update on 12/06/2012

function SFPrimitiveIndices(primitive){
	//it primitive is undefined, it is the default Constructor without parameters
	this.primitiveIndices=new Array();
}

		
SFPrimitiveIndices.prototype["set"]=function(indices){
			for ( var  i  =  0 ; i   < indices.primitiveIndices.length ; i++ ){
				this.primitiveIndices[i]=indices.primitiveIndices[i];
			}
		};
		
SFPrimitiveIndices.prototype["getPrimitiveIndices"]=function(){
			return this.primitiveIndices;
		};
	
SFPrimitiveIndices.prototype["setPrimitiveIndices"]=function(primitiveIndices){
			this.primitiveIndices=primitiveIndices;
		};

SFPrimitiveIndices.prototype["setData"]=function(indices,firstIndex,size){
			for ( var  j  =  firstIndex ; j   < firstIndex+size ; j++ ){
				this.primitiveIndices[j]=indices.primitiveIndices[j];
			}
		};

SFPrimitiveIndices.prototype["clone"]=function(){
			var  indices = new  SFPrimitiveIndices();
			indices.primitiveIndices  = new Array();
			return indices;
		};
