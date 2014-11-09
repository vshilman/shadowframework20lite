//
//  SFPipelineRegister.cpp
//  
//
//  Created by Alessandro Martinelli on 14/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFPipelineRegister.h"


namespace sf{

map<string,SFPipelineRegister*> SFPipelineRegister::predefinedGlobalV;

    
	void SFPipelineRegister::prepare(){
		predefinedGlobalV["uv"]=new SFPipelineRegister(GLOBAL_FLOAT, "uv",WRITE_ON_TESSELLATION);
		predefinedGlobalV["uvw"]=new SFPipelineRegister(GLOBAL_FLOAT, "uvw",WRITE_ON_TESSELLATION);
		predefinedGlobalV["w"]=new SFPipelineRegister(GLOBAL_FLOAT, "w",WRITE_ON_TESSELLATION);
		predefinedGlobalV["u"]=new SFPipelineRegister(GLOBAL_FLOAT, "u",WRITE_ON_TESSELLATION);
		predefinedGlobalV["v"]=new SFPipelineRegister(GLOBAL_FLOAT, "v",WRITE_ON_TESSELLATION);
		predefinedGlobalV["w"]=new SFPipelineRegister(GLOBAL_FLOAT, "w",WRITE_ON_TESSELLATION);
		predefinedGlobalV["<>"]=new SFPipelineRegister(GLOBAL_GENERIC, "<>",0);
		//predefinedGlobalV["uvw"]=new SFPipelineRegister(GLOBAL_FLOAT4, "uvw",READ_ON_PRIMITIVE);
		predefinedGlobalV["projection"]=new SFPipelineRegister(GLOBAL_MATRIX4, "projection",READ_ALL);
		predefinedGlobalV["modelview"]=new SFPipelineRegister(GLOBAL_MATRIX4, "modelview",READ_ALL);
		predefinedGlobalV["vectorsModelview"]=new SFPipelineRegister(GLOBAL_MATRIX4, "vectorsModelview",READ_ALL);
		predefinedGlobalV["projection"]=new SFPipelineRegister(GLOBAL_MATRIX4, "projection",READ_ALL);
		predefinedGlobalV["texture0"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture0",READ_ALL);
		predefinedGlobalV["texture1"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture1",READ_ALL);
		predefinedGlobalV["texture2"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture2",READ_ALL);
		predefinedGlobalV["texture3"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture3",READ_ALL);
		predefinedGlobalV["texture4"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture4",READ_ALL);
		predefinedGlobalV["texture5"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture5",READ_ALL);
		predefinedGlobalV["texture6"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture6",READ_ALL);
		predefinedGlobalV["texture7"]=new SFPipelineRegister(GLOBAL_TEXTURE, "texture7",READ_ALL);
		predefinedGlobalV["position"]=new SFPipelineRegister(GLOBAL_FLOAT3, "position",WROTE_BY_TRANSFORM);
		predefinedGlobalV["Color1"]=new SFPipelineRegister(GLOBAL_FLOAT4, "Color1",READ_ALL);
		predefinedGlobalV["LightDir1"]=new SFPipelineRegister(GLOBAL_FLOAT4, "LightDir1",READ_ALL);
		predefinedGlobalV["normal"]=new SFPipelineRegister(GLOBAL_FLOAT3, "normal",WROTE_BY_TRANSFORM);
		predefinedGlobalV["duVector"]=new SFPipelineRegister(GLOBAL_FLOAT3, "duVector",WROTE_BY_TRANSFORM);
		predefinedGlobalV["dvVector"]=new SFPipelineRegister(GLOBAL_FLOAT3, "dvVector",WROTE_BY_TRANSFORM);
		predefinedGlobalV["texCoord0"]=new SFPipelineRegister(GLOBAL_FLOAT2, "texCoord0",WROTE_BY_TRANSFORM);
		predefinedGlobalV["color"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color",WROTE_BY_MATERIAL);
		predefinedGlobalV["color1"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color1",WROTE_BY_MATERIAL);
		predefinedGlobalV["color2"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color2",WROTE_BY_MATERIAL);
		predefinedGlobalV["color3"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color3",WROTE_BY_MATERIAL);
		predefinedGlobalV["color4"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color4",WROTE_BY_MATERIAL);
		predefinedGlobalV["color5"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color5",WROTE_BY_MATERIAL);
		predefinedGlobalV["color6"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color6",WROTE_BY_MATERIAL);
		predefinedGlobalV["color7"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color7",WROTE_BY_MATERIAL);
		predefinedGlobalV["color8"]=new SFPipelineRegister(GLOBAL_FLOAT4, "color8",WROTE_BY_MATERIAL);
		predefinedGlobalV["matnormal"]=new SFPipelineRegister(GLOBAL_FLOAT3, "matnormal",WROTE_BY_MATERIAL);
		predefinedGlobalV["uvP"]=new SFPipelineRegister(GLOBAL_FLOAT2, "uvP",WROTE_BY_TRANSFORM);
		predefinedGlobalV["uvw"]=new SFPipelineRegister(GLOBAL_FLOAT3, "uvw",WROTE_BY_TESSELLATION);
		predefinedGlobalV["uv"]=new SFPipelineRegister(GLOBAL_FLOAT2, "uv",WROTE_BY_TESSELLATION);
		predefinedGlobalV["P"]=new SFPipelineRegister(GLOBAL_FLOAT3, "P",WROTE_BY_PRIMITIVE);
		predefinedGlobalV["N"]=new SFPipelineRegister(GLOBAL_FLOAT3, "N",WROTE_BY_PRIMITIVE);
		predefinedGlobalV["du"]=new SFPipelineRegister(GLOBAL_FLOAT3, "du",WROTE_BY_PRIMITIVE);
		predefinedGlobalV["dv"]=new SFPipelineRegister(GLOBAL_FLOAT3, "dv",WROTE_BY_PRIMITIVE);
		predefinedGlobalV["Tx0"]=new SFPipelineRegister(GLOBAL_FLOAT2, "Tx0",WROTE_BY_PRIMITIVE);
		predefinedGlobalV["fColor"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor0"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor0",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor1"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor1",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor2"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor2",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor3"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor3",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor4"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor4",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor5"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor5",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor6"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor6",WROTE_BY_TESSELLATION);
		predefinedGlobalV["fColor7"]=new SFPipelineRegister(GLOBAL_FLOAT4, "fColor7",WROTE_BY_TESSELLATION);
	};
	
    SFPipelineRegister::SFPipelineRegister(short type, string name,int use):SFParameter(name,type) {
		this->use=use;
	}
	
	int SFPipelineRegister::getUse() {
		return use;
	}
	
	SFPipelineRegister* SFPipelineRegister::getFromName(string name){
        if(predefinedGlobalV.size()<4){
            SFPipelineRegister::prepare();
        }
		SFPipelineRegister* globalV=predefinedGlobalV[name];
        return globalV;
	}

}
