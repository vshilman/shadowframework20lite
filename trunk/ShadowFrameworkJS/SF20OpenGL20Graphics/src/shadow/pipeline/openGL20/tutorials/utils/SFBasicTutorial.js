
function SFBasicTutorial(){
}

SFBasicTutorial.prototype = {

	setGeometry:function(geometry){
		this.primitiveData    = geometry.getArray();
		this.elementIndex    = geometry.getFirstElement();
		this.elementSize    = geometry.getElementsCount();
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
	},

	render:function(){
		SFPipeline.getSfProgramBuilder().loadProgram(program);
	//load light data		if(lightData!=null && lightReference!=null)			SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());//Warning: Not well Identified 
	//load material data		if(materialData!=null && materialReference!=null)			SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, elementSize);//Warning: Not well Identified 
	}

};