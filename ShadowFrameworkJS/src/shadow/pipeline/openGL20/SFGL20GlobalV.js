//Java to JS on 22/03/2012

//SFGL20GlobalV is a static class

function SFGL20GlobalV_getRegisterName(register){
	if(register.getName()=="vertex")
		return "gl_Vertex";
	
	return register.getName();
}
	
function SFGL20GlobalV_requiresDeclaration(global){
	
	if(global.getType()==SFParameteri_GLOBAL_GENERIC){
		return false;
	}
	if(!(global instanceof SFPipelineRegister)){
		return false;
	}else{
		var gl=global;
		if(gl.getUse()==SFPipelineRegister_WRITE_ON_TESSELLATION)
			return false;
		if(gl.getUse()==SFPipelineRegister_READ_ALL)
			return true;
		if(gl.getUse()==SFPipelineRegister_WROTE_BY_TRANSFORM){
			return true;
		}
		if(gl.getUse()==SFPipelineRegister_READ_ON_TESSELLATION)
			return false;
		if(gl.getUse()==SFPipelineRegister_WROTE_BY_TESSELLATION)
			return false;
		if(gl.getUse()==SFPipelineRegister_WROTE_BY_PRIMITIVE)
			return false;
		if(gl.getUse()==SFPipelineRegister_WROTE_BY_MATERIAL)
			return false;
	} 
	return true;
}


function SFGL20GlobalV_declaredOnWrite(global){
	return !SFGL20GlobalV_requiresDeclaration(global);
}


function SFGL20GlobalV_getModifiers(global){
	
	if(!(global instanceof SFPipelineRegister)){
		return "uniform";
	}else{ 
		var gl=global;
		if(gl.getUse()==SFPipelineRegister_WROTE_BY_TRANSFORM)
			return "varying";
	}
	return "uniform";
}

function SFGL20GlobalV_getType(type){
	return definitions.get(type);
}

