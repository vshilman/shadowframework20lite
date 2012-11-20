package com.shadow.android.opengles20;

import java.util.HashMap;
import java.util.List;

import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGlobalVSetGL20Implementor{
	
	private static HashMap<Short, String> declarations=new HashMap<Short, String>();
	static{
		declarations.put(SFParameteri.GLOBAL_FLOAT, "float");
		declarations.put(SFParameteri.GLOBAL_FLOAT2, "vec2");
		declarations.put(SFParameteri.GLOBAL_FLOAT3, "vec3");
		declarations.put(SFParameteri.GLOBAL_FLOAT4, "vec4");
		declarations.put(SFParameteri.GLOBAL_MATRIX4, "mat4");
		declarations.put(SFParameteri.GLOBAL_TEXTURE, "sampler2D");
	}
	
	public static String generateShaderParameters(List<SFParameteri> set) {
		String parameters="";
		for (SFParameteri pr : set) {
			if(SFGL20GlobalV.requiresDeclaration(pr)){
				String name=pr.getName();
				if(pr instanceof SFPipelineRegister)
					name=SFGL20GlobalV.getRegisterName((SFPipelineRegister)pr);
				String declaration=SFGL20GlobalV.getModifiers(pr)+" "+SFGlobalVSetGL20Implementor.getDeclarationString(pr.getType())+" "+
				name+";\n";
				
				parameters+=declaration;
			}
		}
		return parameters;
	}

	public static String getDeclarationString(short param_) {
		return declarations.get(param_);
	}	
	
	public static String generateInstancedStructures(SFPipelineStructureInstance instance,
			SFParameteri functionParameter,String suffix,List<SFParameteri> parameters){
		
		String res="\n";
		List<SFParameteri> sfParameters=instance.getParameters();
		
		for (SFParameteri sfParameteri : sfParameters) {
			boolean found=false;
			for (SFParameteri parameter : parameters) {
				if(parameter.getName().equalsIgnoreCase(sfParameteri.getName())){
					found=true;
				}
			}
			if(!found){
				short param_=sfParameteri.getType();
				res+="uniform "+getDeclarationString(param_)+" "+suffix+sfParameteri.getName()+";\n";
				parameters.add(sfParameteri);
			}
			
		}
		
		return res;
	}

	public static String generateInstancedGrids(SFPipelineGrid instance,short type,String suffix){
		
		String res="\n";
		
		for (SFParameteri parameter : instance.getParameters()) {
			res+="uniform "+getDeclarationString(type)+" "+suffix+parameter.getName()+";\n";
		}
		
		return res;
	}

}