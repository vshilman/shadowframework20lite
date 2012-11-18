//Java to JS on 06/02/2012

var SFParameteri_GLOBAL_GENERIC=100;
var SFParameteri_GLOBAL_FLOAT=0;
var SFParameteri_GLOBAL_FLOAT2=1;
var SFParameteri_GLOBAL_FLOAT3=2;
var SFParameteri_GLOBAL_FLOAT4=3;
var SFParameteri_GLOBAL_MATRIX2=4;
var SFParameteri_GLOBAL_MATRIX3=5;
var SFParameteri_GLOBAL_MATRIX4=6;
var SFParameteri_GLOBAL_TEXTURE=9;


function SFParameteri_getTypeDimension(type){

	if(typeof type == 'string')
		type=parseInt(type);
	
	if(type>=0 && type<=3)
		return type+1;
	if(type==4)
		return 4;
	if(type==5)
		return 9;
	if(type==6)
		return 16;
	if(type==9)
		return 1;
	if(type==100)
		return 0;
	
}

function SFParameteri_getExpressionDimension(type){

	if(typeof type == 'string')
		type=parseInt(type);
	
	if(type>=0 && type<=3)
		return type+1;
	if(type>=4 && type<=6)
		return type-2;
	if(type==9)
		return 1;
	if(type==100)
		return 0;
	
}


function SFParameteri_generateValue(parameter){
	
	var type=parameter.getType();

	if(typeof type == 'string')
		type=parseInt(type);
	
	if(type===undefined){
		type=parameter;
	}
	if(parameter.type==SFParameteri_GLOBAL_FLOAT)
		return new SFValue1f(0); 
	else if(parameter.type==SFParameteri_GLOBAL_FLOAT2)
		return new SFVertex2f(0,0);
	else if(parameter.type==SFParameteri_GLOBAL_FLOAT3)
		return new SFVertex3f(0,0,0);
	else if(parameter.type==SFParameteri_GLOBAL_FLOAT4)
		return new SFVertex4f(0,0,0);
	else if(parameter.type==SFParameteri_GLOBAL_MATRIX2)
		return new SFMatrix2f();
	else if(parameter.type==SFParameteri_GLOBAL_MATRIX3)
		return new SFMatrix3f();
	else if(parameter.type==SFParameteri_GLOBAL_MATRIX4)
		return new SFMatrix4f();
	else if(parameter.type==SFParameteri_GLOBAL_TEXTURE)
		return new SFValue1f(0);
	
	return null;
}

