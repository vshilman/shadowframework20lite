package shadow.image.bitmaps;

public class SFBitmapFunctionPerlinNoise extends SFPerlinNoise{

	private SFBitmapFunction bitmap;
	
	public SFBitmapFunctionPerlinNoise(){
	}
	
	public SFBitmapFunctionPerlinNoise(int width,int height,float[] weights,boolean rgb){
		
		super();
		
		setWidth((short)width);
		setHeight((short)height);
		
		this.weights=weights;
		this.rgb=rgb;
	}
	
	
	public SFBitmapFunction getBitmap() {
		return bitmap;
	}

	public void setBitmap(SFBitmapFunction bitmap) {
		this.bitmap = bitmap;
	}

	
	@Override
	protected float getOctaveValue(float u, float v, int octave) {
		
		float U=u*octave;
		U-=((int)U);
		float V=v*octave;
		V-=((int)V);
		
		float value=bitmap.getValue(U, V);
		return value*256;
	}
	

}
