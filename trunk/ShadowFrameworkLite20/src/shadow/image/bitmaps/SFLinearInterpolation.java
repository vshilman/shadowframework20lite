package shadow.image.bitmaps;

import shadow.image.SFBitmap;

public class SFLinearInterpolation implements SFImageInterpolant{

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
		int pxUpper=pxLower+1;
		int pyUpper=pyLower+1;
		
		/*Controllo per coordinate intere che non appartengono ai limiti
		 * dell'immagine*/
		if(pxLower>=width)
			pxLower-=width;
		if(pyLower>=height)
			pyLower-=height;
		if(pxUpper>=width)
			pxUpper-=width;
		if(pyUpper>=height)
			pyUpper-=height;
		
		/*Calcolo dei parametri dell'interpolazione bi-lineare. Le coordinate (s,t) 
		 * hanno valore in [0,1]x[0,1], servono per fare l'interpolazione bilineare e 
		 * non vanno confuse con le coordinate (u,v)*/
		double t=px-pxLower;
		double s=py-pyLower;

		/*Lettura dei 4 valori della zImm interessati*/
		int zImm00=bitmap.getGray(pxLower, pyLower);
		int zImm01=bitmap.getGray(pxLower, pyUpper);
		int zImm10=bitmap.getGray(pxUpper, pyLower);
		int zImm11=bitmap.getGray(pxUpper, pyUpper);
		/*Interpolazione bilineare dei dati, moltiplicati rec255*maxZ (dove rec255=1/255):
		 * I valori di zij sono in [0,255]
		 * I valori ritornati sono in [0,maxZ] 
		 * */
		double sm=1.0f-s;
		double tm=1.0f-t;
		return (float)(
				sm*( zImm00*tm+zImm10*t )+
				s*( zImm01*tm+zImm11*t )
			);
		
		
	}
}
