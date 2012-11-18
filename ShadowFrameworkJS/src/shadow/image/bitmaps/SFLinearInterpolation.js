

function SFLinearInterpolation(){
	
}


SFLinearInterpolation.prototype["getOctaveValue"]=function(u,v,octave,bitmap){
		
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
		var t=px-pxLower;
		var s=py-pyLower;

		/*Lettura dei 4 valori della zImm interessati*/
		var zImm00=bitmap.getGray(pxLower, pyLower);
		var zImm01=bitmap.getGray(pxLower, pyUpper);
		var zImm10=bitmap.getGray(pxUpper, pyLower);
		var zImm11=bitmap.getGray(pxUpper, pyUpper);
		
		
		var sm=1.0-s;
		var tm=1.0-t;
		return  sm*( zImm00*tm+zImm10*t )+
				s*( zImm01*tm+zImm11*t );
};


