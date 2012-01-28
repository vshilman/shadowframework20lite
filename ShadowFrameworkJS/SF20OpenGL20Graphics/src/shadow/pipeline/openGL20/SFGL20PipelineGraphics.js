

SFGL20PipelineGraphics.prototype = {

	translateModel:function(modelX, modelY, modelZ){
	SFGL20PipelineGraphics.modelX=modelX;//Warning: Not well Identified 
	SFGL20PipelineGraphics.modelY=modelY;//Warning: Not well Identified 
	SFGL20PipelineGraphics.modelZ=modelZ;//Warning: Not well Identified 
	},

	rotateModel:function(rotX, rotY, rotZ){
	SFGL20PipelineGraphics.rotX=rotX;//Warning: Not well Identified 
	SFGL20PipelineGraphics.rotY=rotY;//Warning: Not well Identified 
	SFGL20PipelineGraphics.rotZ=rotZ;//Warning: Not well Identified 
	},

	drawPrimitives:function(primitives, first, count){
	((SFGL20Program)program).setTransformData(modelX,modelY,modelZ,rotX,rotY,rotZ);//Warning: Not well Identified 
	SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray) primitives;//Warning: Not well Identified 
	SFPipelineRegister registers[];
	Integer[][] uniforms;
	},

	loadStructureData:function(array, indexOfData){
	SFGL20StructureArray strArray=(SFGL20StructureArray) array;//Warning: Not well Identified 
	SFPipelineStructureInstance structure;
	program.setData(structure,strArray.getValue(indexOfData));//Warning: Not well Identified 
	},

	drawBaseQuad:function(){
	GL2 gl;
	gl.glBegin(GL.GL_TRIANGLE_STRIP);//Warning: Not well Identified 
	gl.glVertex2f(-1,-1);//Warning: Not well Identified 
	gl.glVertex2f(1,-1);//Warning: Not well Identified 
	gl.glVertex2f(-1,1);//Warning: Not well Identified 
	gl.glVertex2f(1,1);//Warning: Not well Identified 
	gl.glEnd();//Warning: Not well Identified 
	}

};