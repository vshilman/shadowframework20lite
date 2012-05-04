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

import java.nio.ByteBuffer;

import shadow.image.SFBitmap;
import shadow.image.SFImageFormat;

public class SFSimplePerlinNoise extends SFBitmap{

	static int BASIC_SIZE=8;
	static int randomImage[]={
		250, 62, 46, 111, 242, 125, 54, 23, 
		65, 31, 181, 229, 63, 48, 215, 0, 
		118, 100, 7, 240, 248, 191, 168, 225, 
		150, 180, 49, 146, 106, 111, 152, 57, 
		231, 6, 168, 64, 235, 164, 9, 151, 
		64, 95, 148, 116, 34, 112, 157, 223, 
		146, 112, 238, 251, 86, 17, 41, 18, 
		202, 46, 140, 164, 239, 103, 249, 72
	};
	private float[] weights;
	
	public SFSimplePerlinNoise(){
	}
	
	
	public SFSimplePerlinNoise(int width,int height,float[] weights,boolean rgb){
		
		super();
		
		setWidth((short)width);
		setHeight((short)height);
		
		setup(weights, rgb);
	}

	private void setup(float[] weights, boolean rgb) {
		int width=getWidth();
		int height=getHeight();

		if(rgb){
			this.setFormat(SFImageFormat.RGB8);
		}else{
			this.setFormat(SFImageFormat.GRAY8);
		}
		this.weights=weights;
		
		int size=1;
		if(rgb){
			size=3;
		}
		ByteBuffer buffer=ByteBuffer.allocateDirect(width*height*size);
		
		float stepH=1.0f/height;
		float stepW=1.0f/width;
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				
				float u=stepW*j;
				float v=stepH*i;
				
				float color=0;
				int oct=0;
				for(int k=0;k<weights.length;k++){
					color+=getOctaveValue(u, v, oct)*weights[k];
					if(k==0){
						oct=1;
					}else{
						oct*=2;
					}
				}
				if(color>255)
					color=255;
				if(color<0)
					color=0;
				
				buffer.put((byte)color); 
				if(rgb){
					buffer.put((byte)color);
					buffer.put((byte)color);
				}
			}
		}
		this.setWidth(width);
		this.setHeight(height);
		this.setData(buffer);
		buffer.rewind();
	}
	
	public static void setFunction(byte[] data){
		for(int i=0;i<data.length && i<randomImage.length;i++){
			randomImage[i]=(data[i]>=0?data[i]:data[i]+255);
		}
	}
	
	public static void randomizeFunction(){

		//randomize all values
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				float f=((float)Math.random());
				int a=(int)(256*f);
				randomImage[j+i*8]=a;
			}
		}
	}
	
	private float getOctaveValue(float u,float v,int octave){
		
		float U=u*octave;
		U-=((int)U);
		float V=v*octave;
		V-=((int)V);
		
		/*Conversione (u,v)->(px,py) che sono le coordinate del texel sull'immagine della texture*/
		double px=U*BASIC_SIZE;
		double py=V*BASIC_SIZE;

		/*Coordinate Intere più vicine alle coordinate (px,py)*/
		int pxLower=(int)px;
		int pyLower=(int)py;
		int pxUpper=pxLower+1;
		int pyUpper=pyLower+1;

		/*Controllo per coordinate intere che non appartengono ai limiti
		 * dell'immagine*/
		if(pxLower>=BASIC_SIZE)
			pxLower=0;
		if(pyLower>=BASIC_SIZE)
			pyLower=0;
		if(pxUpper>=BASIC_SIZE)
			pxUpper=0;
		if(pyUpper>=BASIC_SIZE)
			pyUpper=0;
		
		/*Calcolo dei parametri dell'interpolazione bi-lineare. Le coordinate (s,t) 
		 * hanno valore in [0,1]x[0,1], servono per fare l'interpolazione bilineare e 
		 * non vanno confuse con le coordinate (u,v)*/
		double s=px-pxLower;
		double t=py-pyLower;

		/*Lettura dei 4 valori della zImm interessati*/
		int zImm00=randomImage[pxLower+BASIC_SIZE*pyLower];
		int zImm01=randomImage[pxLower+BASIC_SIZE*pyUpper];
		int zImm10=randomImage[pxUpper+BASIC_SIZE*pyLower];
		int zImm11=randomImage[pxUpper+BASIC_SIZE*pyUpper];

		/*Interpolazione bilineare dei dati, moltiplicati rec255*maxZ (dove rec255=1/255):
		 * I valori di zij sono in [0,255]
		 * I valori ritornati sono in [0,maxZ] 
		 * */
		return (float)(zImm00*(1-s)*(1-t)+zImm01*t*(1-s)+zImm10*s*(1-t)+s*t*zImm11);
	}
	
}
