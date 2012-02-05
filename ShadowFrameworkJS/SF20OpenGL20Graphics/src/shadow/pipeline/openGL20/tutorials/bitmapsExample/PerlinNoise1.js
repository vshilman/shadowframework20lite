
function PerlinNoise1(){
}

PerlinNoise1.prototype = {

	generateBitmap:function(){
		 float[] weights={
		0.1f,0.4f,0.3f,0.05f,0.05f,0.05f,0.05f;
	}
		 float[] colors={
	1,1,0,						1,0,0,						1,0,1,						1,0,1,						1,0,1,						0,1,1,						4,4,0;//Warning: Not well Identified 
	}
		SFSpecialPerlinNoise.randomizeFunction();
		 SFBitmap  bitmap = new  SFSpecialPerlinNoise(100, 100, weights, colors);
		return ,bitmap;
	}

};