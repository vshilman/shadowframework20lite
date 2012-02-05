
function SFRendering_Test006v06(){
}

SFRendering_Test006v06.prototype = {

	main:function(args){
		 SFRendering_Test006v06  universe = new  SFRendering_Test006v06();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
		System.err.println(origins.size());
		for ( int  i=0 ; i < 10 ; i++ ){
	float x=-1+2*(float)(Math.random());//Warning: Not well Identified 
	float y=-1+2*(float)(Math.random());//Warning: Not well Identified 
	float rot=(float)(2*Math.PI*Math.random());//Warning: Not well Identified 
		origins.add(new SFVertex3f(x,y,rot));
	}
	},

	render:function(){
	//load program		SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	//load material data		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load light data		SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());//Warning: Not well Identified 
		for ( int  i=0 ; i < origins.size() ; i++ ){
		SFPipeline.getSfPipelineGraphics().rotateModel(0,0,0);
		SFPipeline.getSfPipelineGraphics().translateModel(origins.get(i).getX(),0,origins.get(i).getY());
	//load primitives			//SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		geometry.drawGeometry(1);
	}
	}

};