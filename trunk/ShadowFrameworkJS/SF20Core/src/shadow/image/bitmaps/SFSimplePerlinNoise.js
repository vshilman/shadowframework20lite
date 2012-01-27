
function SFSimplePerlinNoise(){
}
function SFSimplePerlinNoise(width, height, weights, rgb){
	setWidth((short)width);//Warning: Not well Identified 
	setHeight((short)height);//Warning: Not well Identified 
	setup(weights, rgb);//Warning: Not well Identified 
}

SFSimplePerlinNoise.prototype = {

	setup:function(weights, rgb){
	int width;
	int height;
		this.weights=weights;
	int size=1;//Warning: Not well Identified 
	ByteBuffer buffer;
	float stepH=1.0f/height;//Warning: Not well Identified 
	float stepW=1.0f/width;//Warning: Not well Identified 
	this.setWidth(width);//Warning: Not well Identified 
	this.setHeight(height);//Warning: Not well Identified 
	this.setData(buffer);//Warning: Not well Identified 
	buffer.rewind();//Warning: Not well Identified 
	},

	setFunction:function(data){
	},

	randomizeFunction:function(){
	//randomize all values		for(int i=0;//Warning: Not well Identified 
	i<8;//Warning: Not well Identified 
	},

	getOctaveValue:function(u, v, octave){
	float U=u*octave;//Warning: Not well Identified 
	U-=((int)U);//Warning: Not well Identified 
	float V=v*octave;//Warning: Not well Identified 
	V-=((int)V);//Warning: Not well Identified 
	/*Conversione (u,v)->(px,py) che sono le coordinate del texel sull'immagine della texture*/		double px=U*BASIC_SIZE;//Warning: Not well Identified 
	double py=V*BASIC_SIZE;//Warning: Not well Identified 
	/*Coordinate Intere più vicine alle coordinate (px,py)*/		int pxLower=(int)px;//Warning: Not well Identified 
	int pyLower=(int)py;//Warning: Not well Identified 
	int pxUpper=pxLower+1;//Warning: Not well Identified 
	int pyUpper=pyLower+1;//Warning: Not well Identified 
	/*Controllo per coordinate intere che non appartengono ai limiti		 * dell'immagine*/		if(pxLower>=BASIC_SIZE)			pxLower=0;//Warning: Not well Identified 
	if(pyLower>=BASIC_SIZE)			pyLower=0;//Warning: Not well Identified 
	if(pxUpper>=BASIC_SIZE)			pxUpper=0;//Warning: Not well Identified 
	if(pyUpper>=BASIC_SIZE)			pyUpper=0;//Warning: Not well Identified 
	/*Calcolo dei parametri dell'interpolazione bi-lineare. Le coordinate (s,t) 		 * hanno valore in [0,1]x[0,1], servono per fare l'interpolazione bilineare e 		 * non vanno confuse con le coordinate (u,v)*/		double s=px-pxLower;//Warning: Not well Identified 
	double t=py-pyLower;//Warning: Not well Identified 
	/*Lettura dei 4 valori della zImm interessati*/		int zImm00=randomImage[pxLower+BASIC_SIZE*pyLower];//Warning: Not well Identified 
	int zImm01=randomImage[pxLower+BASIC_SIZE*pyUpper];//Warning: Not well Identified 
	int zImm10=randomImage[pxUpper+BASIC_SIZE*pyLower];//Warning: Not well Identified 
	int zImm11=randomImage[pxUpper+BASIC_SIZE*pyUpper];//Warning: Not well Identified 
	/*Interpolazione bilineare dei dati, moltiplicati rec255*maxZ (dove rec255=1/255):		 * I valori di zij sono in [0,255]		 * I valori ritornati sono in [0,maxZ] 		 * */		return (float)(zImm00*(1-s)*(1-t)+zImm01*t*(1-s)+zImm10*s*(1-t)+s*t*zImm11);//Warning: Not well Identified 
	}

};