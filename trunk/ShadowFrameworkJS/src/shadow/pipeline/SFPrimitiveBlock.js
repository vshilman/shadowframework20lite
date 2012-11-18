

//TODO: verify
function PrimitiveBlock(register,index){
	this.register=register;
	this.index=index;
}


PrimitiveBlock.prototype["getType"]=function(){
			return this.register.getType();
		};

PrimitiveBlock.prototype["getRegister"]=function(){
			return this.register;
		};

PrimitiveBlock.prototype["getIndex"]=function(){
			return this.index;
		};

//unnecessary
//public static PrimitiveBlock getBlock(SFPipelineRegister register){

var SFPrimitiveBlock_TESSELLATOR=new PrimitiveBlock(SFPipelineRegister_getFromName("P"),0);
var SFPrimitiveBlock_POSITION=new PrimitiveBlock(SFPipelineRegister_getFromName("P"),1);
var SFPrimitiveBlock_NORMAL=new PrimitiveBlock(SFPipelineRegister_getFromName("N"),2);
var SFPrimitiveBlock_DU=new PrimitiveBlock(SFPipelineRegister_getFromName("du"),3);
var SFPrimitiveBlock_DV=new PrimitiveBlock(SFPipelineRegister_getFromName("dv"),4);
var SFPrimitiveBlock_TXO=new PrimitiveBlock(SFPipelineRegister_getFromName("Tx0"),5);
var SFPrimitiveBlock_UV=new PrimitiveBlock(SFPipelineRegister_getFromName("uv"),6);
var SFPrimitiveBlock_UVP=new PrimitiveBlock(SFPipelineRegister_getFromName("uvP"),7);

var SFPrimitiveBlock_PrimitiveBlock=
	[
	 SFPrimitiveBlock_TESSELLATOR,
	 SFPrimitiveBlock_POSITION,
	 SFPrimitiveBlock_NORMAL,
	 SFPrimitiveBlock_DU,
	 SFPrimitiveBlock_DV,
	 SFPrimitiveBlock_TXO,
	 SFPrimitiveBlock_UV,
	 SFPrimitiveBlock_UVP
	];

function SFPrimitiveBlock_getBlock(register){
	for(var i=0;i<PrimitiveBlock_PrimitiveBlock.length;i++){
		if(PrimitiveBlock_PrimitiveBlock[i]==register){
			return i;
		}
	}
	return 0;
}


var PrimitiveBlock_BlockByName=new Array();
PrimitiveBlock_BlockByName["POSITION"]=SFPrimitiveBlock_POSITION;
PrimitiveBlock_BlockByName["NORMAL"]=SFPrimitiveBlock_NORMAL;
PrimitiveBlock_BlockByName["DU"]=SFPrimitiveBlock_DU;
PrimitiveBlock_BlockByName["DV"]=SFPrimitiveBlock_DV;
PrimitiveBlock_BlockByName["TXO"]=SFPrimitiveBlock_TXO;
PrimitiveBlock_BlockByName["UV"]=SFPrimitiveBlock_UV;
PrimitiveBlock_BlockByName["UVP"]=SFPrimitiveBlock_UVP;


function SFPrimitiveBlock_valueOf(value){
	
	return PrimitiveBlock_BlockByName[value];
}
