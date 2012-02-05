
function Tut03Bitmap(){
}

Tut03Bitmap.prototype = {

	main:function(args){
		SFGL20Pipeline.setup();
		 String[] materials={
		"TexturedMat";
	}
		 SFProgram  program = SFTutorialsUtilities.generateImageProgram("data/pipeline/primitive", materials, "BasicYellowColor");
		 Tut03Bitmap  tut03Bitmap = new  Tut03Bitmap();
		tut03Bitmap.program  = program;
		tut03Bitmap.prepareFrame("Curved Tube Function", 600, 600);
	},

	init:function(){
		 float[] weights={
		0.1f,0.4f,0.3f;
	}
		 float[] colors={
	1,1,0,						1,0,0,						1,0,1;//Warning: Not well Identified 
	}
		SFSpecialPerlinNoise.randomizeFunction();
		 SFBitmap  bitmap = new  SFSpecialPerlinNoise(200, 200, weights, colors);
		texture  = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR_MIPMAP,				WrapMode.REPEAT, WrapMode.REPEAT);
	},

	render:function(){
		texture.apply(0);
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	}

};