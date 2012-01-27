
function SFGL20PrimitiveArray(primitive){
		this.primitive=primitive;
	List<Entry<SFPipelineRegister, SFProgramComponent>> wroteRegisters=primitive.getPrimitiveMap();//Warning: Not well Identified 
	primitiveData=new SFGL20ListData[wroteRegisters.size()];//Warning: Not well Identified 
	registers=new SFPipelineRegister[wroteRegisters.size()];//Warning: Not well Identified 
	int index=0;//Warning: Not well Identified 
}

SFGL20PrimitiveArray.prototype = {

	getPrimitiveData:function(){
		return this.primitiveData;
	}

};