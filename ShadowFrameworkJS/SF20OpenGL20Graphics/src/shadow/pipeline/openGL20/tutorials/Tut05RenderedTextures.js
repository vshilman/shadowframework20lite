
function Tut05RenderedTextures(){
}

Tut05RenderedTextures.prototype = {

	main:function(args){
		SFGL20Pipeline.setup();
		 Tut05RenderedTextures  tut03Bitmap = new  Tut05RenderedTextures();
		 String[] materials={
		"TexturedMat";
	}
		try{
		SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
		tut03Bitmap.programGenerate  = SFPipeline.getStaticImageProgram(materials, "BasicGrayAndBright");
		tut03Bitmap.programShow  = SFPipeline.getStaticImageProgram(materials, "BasicColor");
	}
		catch (IOException e){
		e.printStackTrace();
	}
		catch (SFPipelineModuleWrongException e){
		e.printStackTrace();
	}
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	},

	init:function(){
		 SFBitmap  bitmap = PerlinNoise3.generateBitmap();
		texture  = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR,				WrapMode.REPEAT, WrapMode.REPEAT);
		texture1    = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFFormat.RGB8,  Filter.LINEAR,				WrapMode.REPEAT, WrapMode.REPEAT);
		texture2    = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFFormat.RGB8,  Filter.LINEAR,				WrapMode.REPEAT, WrapMode.REPEAT);
		 SFRenderedTexture  renderedTexture = new  SFRenderedTexture();
		renderedTexture.addColorData(texture1);
		renderedTexture.addColorData(texture2);
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);
		texture.apply(0);
		SFPipeline.getSfProgramBuilder().loadProgram(programGenerate);
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	},

	render:function(){
		if,(shownTexture==0)			texture1.apply(0);
		else ,if,(shownTexture==1)			texture2.apply(0);
		else			,texture.apply(0);
		SFPipeline.getSfProgramBuilder().loadProgram(programShow);
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
	},

	keyPressed:function(e){
		super.keyPressed(e);
	//if(e.getKeyCode()==KeyEvent.VK_A);//Warning: Not well Identified 
		shownTexture  = 0;
	//}
	//if(e.getKeyCode()==KeyEvent.VK_B);//Warning: Not well Identified 
		shownTexture  = 1;
	//}
	//if(e.getKeyCode()==KeyEvent.VK_C);//Warning: Not well Identified 
		shownTexture  = 2;
	//}
	}

};