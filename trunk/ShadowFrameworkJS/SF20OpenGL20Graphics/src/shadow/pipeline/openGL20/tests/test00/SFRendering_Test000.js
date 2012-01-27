
function SFRendering_Test000(){
	setSize(400, 400);//Warning: Not well Identified 
	setTitle("Frame");//Warning: Not well Identified 
	GLCanvas canvas;
	canvas.addGLEventListener(new EventListener001());//Warning: Not well Identified 
	FPSAnimator animator;
	animator.start();//Warning: Not well Identified 
	getContentPane().add(canvas);//Warning: Not well Identified 
}

SFRendering_Test000.prototype = {

	main:function(args){
	SFRendering_Test000 universe=new SFRendering_Test000();//Warning: Not well Identified 
	universe.setDefaultCloseOperation(EXIT_ON_CLOSE);//Warning: Not well Identified 
	universe.setVisible(true);//Warning: Not well Identified 
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