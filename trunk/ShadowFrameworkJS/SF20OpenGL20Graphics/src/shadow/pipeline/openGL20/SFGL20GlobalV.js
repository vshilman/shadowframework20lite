
function SFGL20GlobalV(){
}

SFGL20GlobalV.prototype = {

	getRegisterName:function(register){
		if(register.getName().equalsIgnoreCase("vertex"))			,return ,"gl_Vertex";
		  return  register.getName (  );
	},

	requiresDeclaration:function(global){
	//if(global.getType()==SFParameteri.GLOBAL_GENERIC);//Warning: Not well Identified 
		return ,false;
	//}
	//if(!(global instanceof SFPipelineRegister));//Warning: Not well Identified 
		return ,false;
	//}
		else{
		 SFPipelineRegister  gl = (SFPipelineRegister)global;
	if(gl.getUse()==SFPipelineRegister.WRITE_ON_TESSELLATION)				return false;//Warning: Not well Identified 
	if(gl.getUse()==SFPipelineRegister.READ_ALL)				return true;//Warning: Not well Identified 
	//if(gl.getUse()==SFPipelineRegister.WROTE_BY_TRANSFORM);//Warning: Not well Identified 
		return ,true;
	//}
	if(gl.getUse()==SFPipelineRegister.READ_ON_TESSELLATION)				return false;//Warning: Not well Identified 
	if(gl.getUse()==SFPipelineRegister.WROTE_BY_TESSELLATION)				return false;//Warning: Not well Identified 
	if(gl.getUse()==SFPipelineRegister.WROTE_BY_PRIMITIVE)				return false;//Warning: Not well Identified 
	if(gl.getUse()==SFPipelineRegister.WROTE_BY_MATERIAL)				return false;//Warning: Not well Identified 
	}
		return ,true;
	},

	declaredOnWrite:function(global){
	return !requiresDeclaration(global);//Warning: Not well Identified 
	},

	getModifiers:function(global){
	//if(!(global instanceof SFPipelineRegister));//Warning: Not well Identified 
		return ,"uniform";
	//}
		else{
		 SFPipelineRegister  gl = (SFPipelineRegister)global;
	if(gl.getUse()==SFPipelineRegister.WROTE_BY_TRANSFORM)				return "varying";//Warning: Not well Identified 
	}
		return ,"uniform";
	},

	getType:function(type){
		return ,definitions.get(type);
	}

};