//Java to JS on 21/03/2012


var SFPipelineRegister_READ_ON_TESSELLATION=1;
SFPipelineRegister_WRITE_ON_TESSELLATION=2;
SFPipelineRegister_READ_ON_PRIMITIVE=4;
SFPipelineRegister_WRITE_ON_PRIMITIVE=8;
SFPipelineRegister_READ_ON_TRANSFORM=16;
SFPipelineRegister_WRITE_ON_TRANSFORM=32;
SFPipelineRegister_READ_ON_MATERIAL=64;
SFPipelineRegister_WRITE_ON_MATERIAL=128;
SFPipelineRegister_READ_ON_LIGHTING=256;
SFPipelineRegister_WRITE_ON_LIGHTING=512;
SFPipelineRegister_READ_ALL=SFPipelineRegister_READ_ON_LIGHTING+SFPipelineRegister_READ_ON_MATERIAL+
	SFPipelineRegister_READ_ON_PRIMITIVE+SFPipelineRegister_READ_ON_TESSELLATION+SFPipelineRegister_READ_ON_TRANSFORM;
SFPipelineRegister_WROTE_BY_TESSELLATION=SFPipelineRegister_READ_ON_LIGHTING+SFPipelineRegister_READ_ON_MATERIAL+
	SFPipelineRegister_READ_ON_PRIMITIVE+SFPipelineRegister_READ_ON_TRANSFORM+SFPipelineRegister_WRITE_ON_TESSELLATION;
SFPipelineRegister_WROTE_BY_PRIMITIVE=SFPipelineRegister_READ_ON_LIGHTING+SFPipelineRegister_READ_ON_MATERIAL+
	SFPipelineRegister_READ_ON_TRANSFORM+SFPipelineRegister_WRITE_ON_PRIMITIVE;
SFPipelineRegister_WROTE_BY_TRANSFORM=SFPipelineRegister_READ_ON_LIGHTING+SFPipelineRegister_READ_ON_MATERIAL+SFPipelineRegister_WRITE_ON_TRANSFORM;
SFPipelineRegister_WROTE_BY_MATERIAL=SFPipelineRegister_READ_ON_LIGHTING+SFPipelineRegister_WRITE_ON_MATERIAL;


function SFPipelineRegister(type,name,use){
	this.type=type;
	this.use=use;
	this.name=name;
}

inherit(SFPipelineRegister,SFParameter);


var predefinedGlobalV=new Array();

predefinedGlobalV["uv"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "uv",SFPipelineRegister_WRITE_ON_TESSELLATION);
predefinedGlobalV["uvw"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "uvw",SFPipelineRegister_WRITE_ON_TESSELLATION);
predefinedGlobalV["w"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "w",SFPipelineRegister_WRITE_ON_TESSELLATION);
predefinedGlobalV["u"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "u",SFPipelineRegister_WRITE_ON_TESSELLATION);
predefinedGlobalV["v"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "v",SFPipelineRegister_WRITE_ON_TESSELLATION);
predefinedGlobalV["w"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "w",SFPipelineRegister_WRITE_ON_TESSELLATION);
predefinedGlobalV["<>"]=new SFPipelineRegister(SFParameteri_GLOBAL_GENERIC, "<>",0);
predefinedGlobalV["projection"]=new SFPipelineRegister(SFParameteri_GLOBAL_MATRIX4, "projection",SFPipelineRegister_READ_ALL);
predefinedGlobalV["modelview"]=new SFPipelineRegister(SFParameteri_GLOBAL_MATRIX4, "modelview",SFPipelineRegister_READ_ALL);
predefinedGlobalV["vectorsModelview"]=new SFPipelineRegister(SFParameteri_GLOBAL_MATRIX4, "vectorsModelview",SFPipelineRegister_READ_ALL);
predefinedGlobalV["projection"]=new SFPipelineRegister(SFParameteri_GLOBAL_MATRIX4, "projection",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture0"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture0",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture1"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture1",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture2"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture2",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture3"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture3",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture4"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture4",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture5"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture5",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture6"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture6",SFPipelineRegister_READ_ALL);
predefinedGlobalV["texture7"]=new SFPipelineRegister(SFParameteri_GLOBAL_TEXTURE, "texture7",SFPipelineRegister_READ_ALL);
predefinedGlobalV["position"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "position",SFPipelineRegister_WROTE_BY_TRANSFORM);
predefinedGlobalV["Color1"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "Color1",SFPipelineRegister_READ_ALL);
predefinedGlobalV["LightDir1"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "LightDir1",SFPipelineRegister_READ_ALL);
predefinedGlobalV["normal"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "normal",SFPipelineRegister_WROTE_BY_TRANSFORM);
predefinedGlobalV["duVector"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "duVector",SFPipelineRegister_WROTE_BY_TRANSFORM);
predefinedGlobalV["dvVector"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "dvVector",SFPipelineRegister_WROTE_BY_TRANSFORM);
predefinedGlobalV["texCoord0"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT2, "texCoord0",SFPipelineRegister_WROTE_BY_TRANSFORM);
predefinedGlobalV["color"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color1"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color2"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color2",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color3"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color3",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color4"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color4",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color5"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color5",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color6"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color6",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color7"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color7",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["color8"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "color8",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matnormal"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "matnormal",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty1"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty2"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty3"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty4"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty5"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty6"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty7"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["matproperty8"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT, "matproperty1",SFPipelineRegister_WROTE_BY_MATERIAL);
predefinedGlobalV["uvP"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "uvP",SFPipelineRegister_WROTE_BY_TRANSFORM);
predefinedGlobalV["uvw"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "uvw",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["P"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "P",SFPipelineRegister_WROTE_BY_PRIMITIVE);
predefinedGlobalV["N"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "N",SFPipelineRegister_WROTE_BY_PRIMITIVE);
predefinedGlobalV["du"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "du",SFPipelineRegister_WROTE_BY_PRIMITIVE);
predefinedGlobalV["dv"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT3, "dv",SFPipelineRegister_WROTE_BY_PRIMITIVE);
predefinedGlobalV["Tx0"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT2, "Tx0",SFPipelineRegister_WROTE_BY_PRIMITIVE);
predefinedGlobalV["fColor"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor0"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor0",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor1"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor1",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor2"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor2",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor3"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor3",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor4"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor4",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor5"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor5",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor6"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor6",SFPipelineRegister_WROTE_BY_TESSELLATION);
predefinedGlobalV["fColor7"]=new SFPipelineRegister(SFParameteri_GLOBAL_FLOAT4, "fColor7",SFPipelineRegister_WROTE_BY_TESSELLATION);

SFPipelineRegister.prototype["getUse"]=function(){
			return this.use;
		};

function SFPipelineRegister_getFromName(name){
	return predefinedGlobalV[name];
}
		
