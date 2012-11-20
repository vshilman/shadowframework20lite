package com.shadow.android.opengles20;

import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGL20GlobalV {
	
	public static String getRegisterName(SFPipelineRegister register){		
		if(register.getName().equalsIgnoreCase("vertex"))
			return "gl_Vertex";
		
		return register.getName();
	}
	
	public static boolean requiresDeclaration(SFParameteri global){
		
		if(global.getType()==SFParameteri.GLOBAL_GENERIC){
			return false;
		}
		if(!(global instanceof SFPipelineRegister)){
			return false;
		}else{
			SFPipelineRegister gl=(SFPipelineRegister)global;
			if(gl.getUse()==SFPipelineRegister.WRITE_ON_TESSELLATION)
				return false;
			if(gl.getUse()==SFPipelineRegister.READ_ALL)
				return true;
			if(gl.getUse()==SFPipelineRegister.WROTE_BY_TRANSFORM){
				return true;
			}
			if(gl.getUse()==SFPipelineRegister.READ_ON_TESSELLATION)
				return false;
			if(gl.getUse()==SFPipelineRegister.WROTE_BY_TESSELLATION)
				return false;
			if(gl.getUse()==SFPipelineRegister.WROTE_BY_PRIMITIVE)
				return false;
			if(gl.getUse()==SFPipelineRegister.WROTE_BY_MATERIAL)
				return false;
		} 
		return true;
	}
	
	public static boolean declaredOnWrite(SFParameteri global){
		return !requiresDeclaration(global);
	}
	
	public static String getModifiers(SFParameteri global){
		
		if(!(global instanceof SFPipelineRegister)){
			return "uniform";
		}else{ 
			SFPipelineRegister gl=(SFPipelineRegister)global;
			if(gl.getUse()==SFPipelineRegister.WROTE_BY_TRANSFORM)
				return "varying";
		}
		return "uniform";
	}
	
}
