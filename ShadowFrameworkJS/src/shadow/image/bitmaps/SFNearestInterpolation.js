

function SFNearestInterpolation(){
	
}


SFNearestInterpolation.prototype["getOctaveValue"]=function(u,v,octave,bitmap){
		
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

		/*Controllo per coordinate intere che non appartengono ai limiti
		 * dell'immagine*/
		if(pxLower>=width)
			pxLower-=width;
		if(pyLower>=height)
			pyLower-=height;

		/*Lettura dei 4 valori della zImm interessati*/
		return bitmap.getGray(pxLower,pyLower);//randomImage[pxLower+width*pyLower];
};


