
function PerlinNoise2(){
}

PerlinNoise2.prototype = {

	generateBitmap:function(){
		 float[] weights={
		0.6f,0.2f,0.1f,0.05f,0.05f,0.05f,0.05f;
	}
		SFSimplePerlinNoise.randomizeFunction();
		 SFBitmap  bitmap = new  SFSimplePerlinNoise(100, 100, weights, false);
		return ,bitmap;
	}

};