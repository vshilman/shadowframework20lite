
function SFRendering_Test006v03(){
	setSize(400, 400);//Warning: Not well Identified 
	setTitle("Frame");//Warning: Not well Identified 
	GLCanvas canvas;
	canvas.addGLEventListener(new EventListener001());//Warning: Not well Identified 
	FPSAnimator animator;
	animator.start();//Warning: Not well Identified 
	getContentPane().add(canvas);//Warning: Not well Identified 
}

SFRendering_Test006v03.prototype = {

	main:function(args){
	SFRendering_Test006v03 universe=new SFRendering_Test006v03();//Warning: Not well Identified 
	universe.setDefaultCloseOperation(EXIT_ON_CLOSE);//Warning: Not well Identified 
	universe.setVisible(true);//Warning: Not well Identified 
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
	},

	render:function(){
	//load program		SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	//load material data		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load light data		SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());//Warning: Not well Identified 
	f+=0.01f;//Warning: Not well Identified 
	SFPipeline.getSfPipelineGraphics().rotateModel(0.25f+f,0,0);//Warning: Not well Identified 
	//load primitives		//SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
	geometry.drawGeometry(1);//Warning: Not well Identified 
	}

};