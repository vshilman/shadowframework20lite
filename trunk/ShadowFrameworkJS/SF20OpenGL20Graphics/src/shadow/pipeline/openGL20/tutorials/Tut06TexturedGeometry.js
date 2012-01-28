
function Tut06TexturedGeometry(){
}

Tut06TexturedGeometry.prototype = {

	init:function(){
	SFBitmap bitmap;
	texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR,				WrapMode.REPEAT, WrapMode.REPEAT);//Warning: Not well Identified 
	texture1 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFFormat.RGB8,  Filter.LINEAR,				WrapMode.REPEAT, WrapMode.REPEAT);//Warning: Not well Identified 
	texture2 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(200, 200, SFFormat.RGB8,  Filter.LINEAR,				WrapMode.REPEAT, WrapMode.REPEAT);//Warning: Not well Identified 
	SFRenderedTexture renderedTexture;
	renderedTexture.addColorData(texture1);//Warning: Not well Identified 
	renderedTexture.addColorData(texture2);//Warning: Not well Identified 
	SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);//Warning: Not well Identified 
	texture.apply(0);//Warning: Not well Identified 
	SFPipeline.getSfProgramBuilder().loadProgram(programGenerate);//Warning: Not well Identified 
	SFPipeline.getSfPipelineGraphics().drawBaseQuad();//Warning: Not well Identified 
	SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);//Warning: Not well Identified 
	},

	render:function(){
	if(shownTexture==0)			texture1.apply(0);//Warning: Not well Identified 
	else if(shownTexture==1)			texture2.apply(0);//Warning: Not well Identified 
	else			texture.apply(0);//Warning: Not well Identified 
	SFPipeline.getSfProgramBuilder().loadProgram(programShow);//Warning: Not well Identified 
	SFPipeline.getSfPipelineGraphics().drawPrimitives(geometry.getArray(), geometry.getFirstElement(), geometry.getElementsCount());//Warning: Not well Identified 
	},

	keyPressed:function(e){
	super.keyPressed(e);//Warning: Not well Identified 
	}

};