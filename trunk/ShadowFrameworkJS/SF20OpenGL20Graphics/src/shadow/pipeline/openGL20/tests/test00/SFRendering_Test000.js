
function SFRendering_Test000(){
}

SFRendering_Test000.prototype = {

	main:function(args){
		 SFRendering_Test000  universe = new  SFRendering_Test000();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
	},

	render:function(){
	//long t1=System.nanoTime();//Warning: Not well Identified 
	//load program		SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	//load material data		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, 4);//Warning: Not well Identified 
	//long t2=System.nanoTime();//Warning: Not well Identified 
	//ystem.out.println("t2-t2 "+(t2-t1));//Warning: Not well Identified 
	}

};