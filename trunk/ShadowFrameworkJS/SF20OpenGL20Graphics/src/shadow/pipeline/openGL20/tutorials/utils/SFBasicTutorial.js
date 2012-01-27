

SFBasicTutorial.prototype = {

	setGeometry:function(geometry){
	this.primitiveData = geometry.getArray();//Warning: Not well Identified 
	this.elementIndex = geometry.getFirstElement();//Warning: Not well Identified 
	this.elementSize = geometry.getElementsCount();//Warning: Not well Identified 
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
	},

	render:function(){
	SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	//load light data		if(lightData!=null && lightReference!=null)			SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());//Warning: Not well Identified 
	//load material data		if(materialData!=null && materialReference!=null)			SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, elementSize);//Warning: Not well Identified 
	}

};