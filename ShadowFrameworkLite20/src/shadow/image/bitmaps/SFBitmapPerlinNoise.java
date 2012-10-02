/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.image.bitmaps;

import shadow.image.SFBitmap;

public class SFBitmapPerlinNoise extends SFPerlinNoise{

//	static int BASIC_SIZEX=7;
//	static int BASIC_SIZEY=8;
//	static int randomImage[]={
//		250, 62, 46, 111, 242, 125, 54,
//		65, 31, 181, 229, 63, 48, 215, 
//		118, 100, 7, 240, 248, 191, 168, 
//		150, 180, 49, 146, 106, 111, 152,  
//		231, 6, 168, 64, 235, 164, 9, 
//		64, 95, 148, 116, 34, 112, 157, 
//		146, 112, 238, 251, 86, 17, 41, 
//		202, 46, 140, 164, 239, 103, 249,
//	};
	
//	static int BASIC_SIZEX=2;
//	static int BASIC_SIZEY=2;
//	static int randomImage[]={
//		255,0,
//		0,255
//	};
	protected SFImageInterpolant interpolant = new SFLinearInterpolation();
	private SFBitmap bitmap=new SFFunction2DBitmap(10,10,false,SFBasicBitmapFunctions.CIRCLE);
	
	public SFBitmapPerlinNoise(){
	}
	
	public SFBitmap getBitmap() {
		return bitmap;
	}

	public SFImageInterpolant getInterpolant() {
		return interpolant;
	}

	public void setInterpolant(SFImageInterpolant interpolant) {
		this.interpolant = interpolant;
	}
	
	public void setBitmap(SFBitmap bitmap) {
		this.bitmap = bitmap;
	}

	public SFBitmapPerlinNoise(int width,int height,float[] weights,boolean rgb){
		
		super();
		
		setWidth((short)width);
		setHeight((short)height);
		
		this.weights=weights;
		this.rgb=rgb;
	}
	
	protected float getOctaveValue(float u, float v, int octave){
		return interpolant.getOctaveValue(u, v, octave,bitmap);
	}

//	public static void setFunction(byte[] data){
//		for(int i=0;i<data.length && i<randomImage.length;i++){
//			randomImage[i]=(data[i]>=0?data[i]:data[i]+255);
//		}
//	}
//	
//	public static void randomizeFunction(){
//
//		//randomize all values
//		for(int i=0;i<8;i++){
//			for(int j=0;j<8;j++){
//				float f=((float)Math.random());
//				int a=(int)(256*f);
//				randomImage[j+i*8]=a;
//			}
//		}
//	}
	
}
