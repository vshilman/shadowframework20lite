
function SFBasicBitmapFunctions(getValueFunction){
	this.getValueFunction=getValueFunction;
}


SFBasicBitmapFunctions.prototype["init"]=function(){
	//Nothing To Do	
};

SFBasicBitmapFunctions.prototype["destroy"]=function(){
	//Nothing To Do
};

SFBasicBitmapFunctions.prototype["getValue"]=function(u,v){
	return this.getValueFunction(u,v);
};


var SFBasicBitmapFunctions_MATRIX_getValue=function(u,v){
	var U=4*u;
	var V=v;
	var row= Math.floor(V);
	V=V-(row);
	U=(U+0.8*row+0.3*V);
	U=U-Math.floor(U);
	return (1-U)*U*(1-V)*V*(1-U)*U*(1-V)*V*512;
};

var SFBasicBitmapFunctions_MATRIX = new SFBasicBitmapFunctions(SFBasicBitmapFunctions_MATRIX_getValue);


SFBasicBitmapFunctions_CIRCLE_getValue=function(u,v){
	u-=0.5;
	v-=0.5;
	return 4*(u*u+v*v);
};

var SFBasicBitmapFunctions_CIRCLE = new SFBasicBitmapFunctions(SFBasicBitmapFunctions_CIRCLE_getValue);

SFBasicBitmapFunctions_SIN_getValue=function(u,v){
	return (0.5*Math.sin(2*Math.PI*u)+0.5)*(0.5*Math.sin(2*Math.PI*v)+0.5);
};


var SFBasicBitmapFunctions_SIN = new SFBasicBitmapFunctions(SFBasicBitmapFunctions_SIN_getValue);


SFBasicBitmapFunctions_SUM_getValue=function(u,v){
	return (u+v);
};

var SFBasicBitmapFunctions_SUM = new SFBasicBitmapFunctions(SFBasicBitmapFunctions_SUM_getValue);



//SHOULD NOT BE NECESSARY

var SFBasicBitmapFunctions_values_=[
	SFBasicBitmapFunctions_MATRIX,SFBasicBitmapFunctions_CIRCLE,SFBasicBitmapFunctions_SIN,SFBasicBitmapFunctions_SUM
];

function SFBasicBitmapFunctions_values(){
	return SFBasicBitmapFunctions_values_;
}