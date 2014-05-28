#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/image/SFBitmap.h"

class SFCubicInterpolation implements SFImageInterpolant {

//	/* (non-Javadoc)
//	 * @see shadow.image.bitmaps.SFIamgeInterpolant#getOctaveValue(float, float, int, int, int, int[])
//	 */
	
//	float getOctaveValue(float u,float v,int octave,SFBitmap bitmap){

//		int width=bitmap.getWidth();
//		int height=bitmap.getHeight();

//		float U=u*octave;
//		U-=((int)U);
//		float V=v*octave;
//		V-=((int)V);

//		/*Conversione (u,v)->(px,py) che sono le coordinate del texel sull'immagine della texture*/
//		double px=U*width;
//		double py=V*height;

//		/*Coordinate Intere più vicine alle coordinate (px,py)*/
//		int pxLower=(int)px;
//		int pyLower=(int)py;
//		int pxUpper=pxLower+1;
//		int pyUpper=pyLower+1;

//		int pxLowerLower=pxLower-1;
//		int pyLowerLower=pyLower-1;
//		int pxUpperUpper=pxUpper+1;
//		int pyUpperUpper=pyUpper+1;

//		/*Controllo per coordinate intere che non appartengono ai limiti
//		 * dell'immagine*/
//		if(pxLower>=width)
//			pxLower-=width;
//		if(pyLower>=height)
//			pyLower-=height;
//		if(pxUpper>=width)
//			pxUpper-=width;
//		if(pyUpper>=height)
//			pyUpper-=height;

//		if(pxUpperUpper>=width)
//			pxUpperUpper-=width;
//		if(pyUpperUpper>=height)
//			pyUpperUpper-=height;

//		if(pxLowerLower<0)
//			pxLowerLower+=width;
//		if(pyLowerLower<0)
//			pyLowerLower+=height;

//		/*Calcolo dei parametri dell'interpolazione bi-lineare. Le coordinate (s,t) 
//		 * hanno valore in [0,1]x[0,1], servono per fare l'interpolazione bilineare e 
//		 * non vanno confuse con le coordinate (u,v)*/
//		double t=px-pxLower;
//		double s=py-pyLower;

//		/*Lettura dei 4 valori della zImm interessati*/
//		int zImm00=bitmap.getGray(pxLower, pyLower);
//		int zImm01=bitmap.getGray(pxLower, pyUpper);
//		int zImm10=bitmap.getGray(pxUpper, pyLower);
//		int zImm11=bitmap.getGray(pxUpper, pyUpper);

//					int Imm00du=(zImm10-bitmap.getGray(pxLowerLower, pyLower))>>1;
//		int Imm00dv=(zImm01-bitmap.getGray(pxLower, pyLowerLower))>>1;

//		int Imm10du=(bitmap.getGray(pxUpperUpper, pyLower)-zImm00)>>1;
//		int Imm10dv=(zImm11-bitmap.getGray(pxUpper, pyLowerLower))>>1;

//					int Imm01du=(zImm11- bitmap.getGray(pxLowerLower, pyUpper))>>1;
//		int Imm01dv=(bitmap.getGray(pxLower, pyUpperUpper)-zImm00)>>1;

//		int Imm11du=(bitmap.getGray(pxUpperUpper, pyUpper)-zImm01)>>1;
//		int Imm11dv=(bitmap.getGray(pxUpper, pyUpperUpper)-zImm10)>>1;

//		int b00=zImm00;
//								int b01=zImm00+(Imm00du/3);
//		int b02=zImm10-(Imm10du/3);
//		int b03=zImm10;

//		int b10=zImm00+(Imm00dv/3);
//		int b11=zImm00+(Imm00dv/3)+(Imm00du/3);
//		int b12=zImm10+(Imm10dv/3)-(Imm10du/3);
//		int b13=zImm10+(Imm10dv/3);

//		int b20=zImm01-(Imm01dv/3);
//		int b21=zImm01-(Imm01dv/3)+(Imm01du/3);
//		int b22=zImm11-(Imm11dv/3)-(Imm11du/3);
//		int b23=zImm11-(Imm11dv/3);

//		int b30=zImm01;
//								int b31=zImm01+(Imm01du/3);
//		int b32=zImm11-(Imm11du/3);
//		int b33=zImm11;

//		/*Interpolazione bilineare dei dati, moltiplicati rec255*maxZ (dove rec255=1/255):
//		 * I valori di zij sono in [0,255]
//		 * I valori ritornati sono in [0,maxZ] 
//		 * */
//		double sm=1.0f-s;
//		double tm=1.0f-t;
//		return (float)(
//				sm*sm*sm*( b00*tm*tm*tm+b01*3*t*tm*tm+b02*3*t*t*tm+b03*t*t*t )+
//				3*s*sm*sm*( b10*tm*tm*tm+b11*3*t*tm*tm+b12*3*t*t*tm+b13*t*t*t )+
//				3*s*s*sm*( b20*tm*tm*tm+b21*3*t*tm*tm+b22*3*t*t*tm+b23*t*t*t )+
//				s*s*s*( b30*tm*tm*tm+b31*3*t*tm*tm+b32*3*t*t*tm+b33*t*t*t )
//			);

	}
}
;
}
#endif
