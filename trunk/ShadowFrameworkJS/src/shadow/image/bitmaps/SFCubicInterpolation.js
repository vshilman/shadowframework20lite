

function SFCubicInterpolation(){
	
}


SFCubicInterpolation.prototype["getOctaveValue"]=function(u,v,octave,bitmap){
		
		var width=bitmap.getWidth();
		var height=bitmap.getHeight();
		
		var U=u*octave;
		U-=(Math.floor(U));
		var V=v*octave;
		V-=(Math.floor(V));
		
		/*Conversione (u,v)->(px,py) che sono le coordinate del texel sull'immagine della texture*/
		var px=U*width;
		var py=V*height;

		/*Coordinate Intere pi˘ vicine alle coordinate (px,py)*/
		var pxLower=Math.floor(px);
		var pyLower=Math.floor(py);
		var pxUpper=pxLower+1;
		var pyUpper=pyLower+1;

		var pxLowerLower=pxLower-1;
		var pyLowerLower=pyLower-1;
		var pxUpperUpper=pxUpper+1;
		var pyUpperUpper=pyUpper+1;
		
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

		if(pxUpperUpper>=width)
			pxUpperUpper-=width;
		if(pyUpperUpper>=height)
			pyUpperUpper-=height;

		if(pxLowerLower<0)
			pxLowerLower+=width;
		if(pyLowerLower<0)
			pyLowerLower+=height;
		
		/*Calcolo dei parametri dell'interpolazione bi-lineare. Le coordinate (s,t) 
		 * hanno valore in [0,1]x[0,1], servono per fare l'interpolazione bilineare e 
		 * non vanno confuse con le coordinate (u,v)*/
		var t=px-pxLower;
		var s=py-pyLower;

		/*Lettura dei 4 valori della zImm interessati*/
		var zImm00=bitmap.getGray(pxLower, pyLower);
		var zImm01=bitmap.getGray(pxLower, pyUpper);
		var zImm10=bitmap.getGray(pxUpper, pyLower);
		var zImm11=bitmap.getGray(pxUpper, pyUpper);
		
		var Imm00du=(zImm10-bitmap.getGray(pxLowerLower, pyLower))>>1;
		var Imm00dv=(zImm01-bitmap.getGray(pxLower, pyLowerLower))>>1;
		
		var Imm10du=(bitmap.getGray(pxUpperUpper, pyLower)-zImm00)>>1;
		var Imm10dv=(zImm11-bitmap.getGray(pxUpper, pyLowerLower))>>1;
		
		var Imm01du=(zImm11- bitmap.getGray(pxLowerLower, pyUpper))>>1;
		var Imm01dv=(bitmap.getGray(pxLower, pyUpperUpper)-zImm00)>>1;
		
		var Imm11du=(bitmap.getGray(pxUpperUpper, pyUpper)-zImm01)>>1;
		var Imm11dv=(bitmap.getGray(pxUpper, pyUpperUpper)-zImm10)>>1;
		
		var b00=zImm00;
		var b01=zImm00+(Imm00du/3);
		var b02=zImm10-(Imm10du/3);
		var b03=zImm10;

		var b10=zImm00+(Imm00dv/3);
		var b11=zImm00+(Imm00dv/3)+(Imm00du/3);
		var b12=zImm10+(Imm10dv/3)-(Imm10du/3);
		var b13=zImm10+(Imm10dv/3);
		
		var b20=zImm01-(Imm01dv/3);
		var b21=zImm01-(Imm01dv/3)+(Imm01du/3);
		var b22=zImm11-(Imm11dv/3)-(Imm11du/3);
		var b23=zImm11-(Imm11dv/3);
		
		var b30=zImm01;
		var b31=zImm01+(Imm01du/3);
		var b32=zImm11-(Imm11du/3);
		var b33=zImm11;
		
		/*Interpolazione bilineare dei dati, moltiplicati rec255*maxZ (dove rec255=1/255):
		 * I valori di zij sono in [0,255]
		 * I valori ritornati sono in [0,maxZ] 
		 * */
		var sm=1.0-s;
		var tm=1.0-t;
		
		var ret=sm*sm*sm*( b00*tm*tm*tm+b01*3*t*tm*tm+b02*3*t*t*tm+b03*t*t*t )+
				3*s*sm*sm*( b10*tm*tm*tm+b11*3*t*tm*tm+b12*3*t*t*tm+b13*t*t*t )+
				3*s*s*sm*( b20*tm*tm*tm+b21*3*t*tm*tm+b22*3*t*t*tm+b23*t*t*t )+
				s*s*s*( b30*tm*tm*tm+b31*3*t*tm*tm+b32*3*t*t*tm+b33*t*t*t );
		
		return ret;
};


