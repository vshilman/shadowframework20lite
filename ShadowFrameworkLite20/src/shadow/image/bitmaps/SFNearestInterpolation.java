package shadow.image.bitmaps;

import shadow.image.SFBitmap;

public class SFNearestInterpolation implements SFImageInterpolant{

	public float getOctaveValue(float u,float v,int octave,SFBitmap bitmap){
		
		int width=bitmap.getWidth();
		int height=bitmap.getHeight();
		
		float U=u*octave;
		U-=((int)U);
		float V=v*octave;
		V-=((int)V);
		
		/*Conversione (u,v)->(px,py) che sono le coordinate del texel sull'immagine della texture*/
		double px=U*width;
		double py=V*height;

		/*Coordinate Intere più vicine alle coordinate (px,py)*/
		int pxLower=(int)px;
		int pyLower=(int)py;
		
		/*Controllo per coordinate intere che non appartengono ai limiti
		 * dell'immagine*/
		if(pxLower>=width)
			pxLower-=width;
		if(pyLower>=height)
			pyLower-=height;
		
		/*Lettura dei 4 valori della zImm interessati*/
		return bitmap.getGray(pxLower,pyLower);//randomImage[pxLower+width*pyLower];
		
	}
}
