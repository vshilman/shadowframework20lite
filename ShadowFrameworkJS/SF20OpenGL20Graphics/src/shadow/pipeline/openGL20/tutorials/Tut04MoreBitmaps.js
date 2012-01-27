

Tut04MoreBitmaps.prototype = {

	main:function(args){
	SFGL20Pipeline.setup();//Warning: Not well Identified 
	SFProgram program;
	Tut04MoreBitmaps tut03Bitmap;
	tut03Bitmap.program=program;//Warning: Not well Identified 
	tut03Bitmap.prepareFrame("Curved Tube Function", 600, 600);//Warning: Not well Identified 
	},

	getAllBitmaps:function(){
		return this.bitmap;
	}

};