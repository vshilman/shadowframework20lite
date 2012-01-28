

Tut03Bitmap.prototype = {

	init:function(){
	SFSpecialPerlinNoise.randomizeFunction();//Warning: Not well Identified 
	SFBitmap bitmap;
	texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR_MIPMAP,				WrapMode.REPEAT, WrapMode.REPEAT);//Warning: Not well Identified 
	},

	render:function(){
	texture.apply(0);//Warning: Not well Identified 
	SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	SFPipeline.getSfPipelineGraphics().drawBaseQuad();//Warning: Not well Identified 
	}

};