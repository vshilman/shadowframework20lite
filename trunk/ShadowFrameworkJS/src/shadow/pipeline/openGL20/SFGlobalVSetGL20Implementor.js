

var SFGlobalVSetGL20Implementor_declarations=new Array();
SFGlobalVSetGL20Implementor_declarations[SFParameteri_GLOBAL_FLOAT]="float";
SFGlobalVSetGL20Implementor_declarations[SFParameteri_GLOBAL_FLOAT2]="vec2";
SFGlobalVSetGL20Implementor_declarations[SFParameteri_GLOBAL_FLOAT3]="vec3";
SFGlobalVSetGL20Implementor_declarations[SFParameteri_GLOBAL_FLOAT4]="vec4";
SFGlobalVSetGL20Implementor_declarations[SFParameteri_GLOBAL_MATRIX4]="mat4";
SFGlobalVSetGL20Implementor_declarations[SFParameteri_GLOBAL_TEXTURE]="sampler2D";


function SFGlobalVSetGL20Implementor_generateShaderParameters(set){
	var parameters="";
	for(var i=0;i<set.length;i++){
		var pr=set[i];
		if(SFGL20GlobalV_requiresDeclaration(pr)){
			var name=pr.getName();
			if(pr instanceof SFPipelineRegister)
				name=SFGL20GlobalV_getRegisterName(pr);
			var declaration=SFGL20GlobalV_getModifiers(pr)+" "+SFGlobalVSetGL20Implementor_getDeclarationString(pr.getType())+" "+
				name+";\n";
			
			parameters+=declaration;
		}
	}
	return parameters;
}


function SFGlobalVSetGL20Implementor_getDeclarationString(param_){
	return SFGlobalVSetGL20Implementor_declarations[param_];
}


function SFGlobalVSetGL20Implementor_generateInstancedStructures(instance,functionParameter,suffix,parameters){
	
	var res="\n";
	var sfParameters=instance.getParameters();
	
	var index=0;
	for (var j in sfParameters) {
		var sfParameteri=sfParameters[j];
		var found=false;
		for (var j in parameters) {
			if(parameters[j].getName()===sfParameteri.getName()){
				found=true;
			}
		}
		if(!found){
			var param_=sfParameteri.getType();
			res+="uniform "+SFGlobalVSetGL20Implementor_getDeclarationString(param_)+" "+suffix+instance.getParameters()[index].getName()+";\n";
			parameters.push(sfParameteri);
			index++;
		}
		
	}
	
	return res;
}




function SFGlobalVSetGL20Implementor_generateInstancedGrids(instance,type,suffix){
	
	var res="\n";
		
	for (var parameterIndex in instance.getParameters()) {
		var parameter=instance.getParameters()[parameterIndex];
		res+="uniform "+SFGlobalVSetGL20Implementor_getDeclarationString(type)+" "+suffix+parameter.getName()+";\n";
	}
	
	return res;
}