


function SFMatrix3fData(matrix){
	this.matrix=new SFMatrix3f();
	this.matrix.setArray(this.getFloatValues());
	if(!(matrix===undefined)){
		this.matrix.set(matrix);
	}else{
		this.matrix.set(SFMatrix3f_getIdentity());
	}
}

inherit(SFMatrix3fData,SFVectorData);

SFMatrix3fData.prototype["getMatrix3f"]=function(){
	return matrix;
};

SFMatrix3fData.prototype["clone"]=function(){
	return new SFMatrix3fData();
};
