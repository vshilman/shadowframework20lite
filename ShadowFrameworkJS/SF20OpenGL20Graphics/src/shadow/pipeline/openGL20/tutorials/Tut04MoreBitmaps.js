
function Tut04MoreBitmaps(){
}

Tut04MoreBitmaps.prototype = {

	main:function(args){
		SFGL20Pipeline.setup();
		 String[] materials={
		"TexturedMat";
	}
		 SFProgram  program = SFTutorialsUtilities.generateImageProgram("data/pipeline/primitive", materials, "BasicColor");
		 Tut04MoreBitmaps  tut03Bitmap = new  Tut04MoreBitmaps();
		tut03Bitmap.program  = program;
		tut03Bitmap.prepareFrame("Curved Tube Function", 600, 600);
	},

	init:function(){
		 SFBitmap[]  bitmap   = getAllBitmaps();
		texture  = new .SFTextureData[bitmap.length];
		for ( int  i  =  0 ; i   < bitmap.length ; i++ ){
		texture[i]  = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap[i], Filter.LINEAR,					WrapMode.REPEAT, WrapMode.REPEAT);
	}
	},

	getAllBitmaps:function(){
		 SFBitmap[] bitmap  ={
		PerlinNoise1.generateBitmap(),				PerlinNoise2.generateBitmap(),				PerlinNoise3.generateBitmap();
	}
		return ,bitmap;
	},

	render:function(){
		texture[selectedTexture].apply(0);
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	},

	keyPressed:function(e){
		super.keyPressed(e);
	//if(e.getKeyCode()==KeyEvent.VK_PLUS);//Warning: Not well Identified 
		selectedTexture++;
		 if ( selectedTexture>=texture.length ){
		selectedTexture  = 0;
	}
	//}
	//if(e.getKeyCode()==KeyEvent.VK_MINUS);//Warning: Not well Identified 
		selectedTexture--;
	//if(selectedTexture<0);//Warning: Not well Identified 
		selectedTexture  = texture.length-1;
	//}
	//}
	}

};