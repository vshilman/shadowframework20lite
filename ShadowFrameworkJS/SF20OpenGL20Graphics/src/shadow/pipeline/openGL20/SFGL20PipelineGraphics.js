
function SFGL20PipelineGraphics(){
}

SFGL20PipelineGraphics.prototype = {

	setProgram:function(program){
		SFGL20PipelineGraphics.program  = program;
	},

	translateModel:function(modelX, modelY, modelZ){
		SFGL20PipelineGraphics.modelX  = modelX;
		SFGL20PipelineGraphics.modelY  = modelY;
		SFGL20PipelineGraphics.modelZ  = modelZ;
	},

	rotateModel:function(rotX, rotY, rotZ){
		SFGL20PipelineGraphics.rotX  = rotX;
		SFGL20PipelineGraphics.rotY  = rotY;
		SFGL20PipelineGraphics.rotZ  = rotZ;
	},

	drawPrimitives:function(primitives, first, count){
	((SFGL20Program)program).setTransformData(modelX,modelY,modelZ,rotX,rotY,rotZ);//Warning: Not well Identified 
		 SFGL20PrimitiveArray  prArray = (SFGL20PrimitiveArray) primitives;
		 SFPipelineRegister  registers[] = primitives.getRegisters();
	Integer[][] uniforms=((SFGL20Program)program).getData().getAllPrimitiveUniforms(				registers);//Warning: Not well Identified 
		for ( int  i=first ; i   < count + first ; i++ ){
		 SFPrimitiveIndices  indices = prArray.getValue(i);
	((SFGL20Program)program).setIndexedData(indices,prArray.getPrimitiveData(),uniforms,					registers);//Warning: Not well Identified 
		 GL2  gl = SFGL2.getGL();
		 int  N = 6;
		 float  step = 1.0f / N;
		for ( int  k=0 ; k   < N ; k++ ){
		 float  v1 = k * step;
		 float  v2 = v1 + step;
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		for ( int  j=0 ; j   < N - k ; j++ ){
		 float  u = j * step;
		gl.glVertex3f(u,v1,1 - u - v1);
		gl.glVertex3f(u,v2,1 - u - v2);
	}
		 float  u = (N - k) * step;
		gl.glVertex3f(u,v1,1 - u - v1);
		gl.glEnd();
	}
	}
	},

	loadStructureData:function(array, indexOfData){
		 SFGL20StructureArray  strArray = (SFGL20StructureArray) array;
		 SFPipelineStructureInstance  structure = strArray.getPipelineStructure();
		program.setData(structure,strArray.getValue(indexOfData));
	},

	drawBaseQuad:function(){
		 GL2  gl = SFGL2.getGL();
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
		gl.glVertex2f(-1,-1);
		gl.glVertex2f(1,-1);
		gl.glVertex2f(-1,1);
		gl.glVertex2f(1,1);
		gl.glEnd();
	}

};