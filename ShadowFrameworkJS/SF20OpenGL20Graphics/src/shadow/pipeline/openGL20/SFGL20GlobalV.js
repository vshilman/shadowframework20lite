

SFGL20GlobalV.prototype = {

	getRegisterName:function(register){
	if(register.getName().equalsIgnoreCase("vertex"))			return "gl_Vertex";//Warning: Not well Identified 
	return register.getName();//Warning: Not well Identified 
	},

	requiresDeclaration:function(global){
		return this.true;
	},

	declaredOnWrite:function(global){
	return !requiresDeclaration(global);//Warning: Not well Identified 
	},

	getModifiers:function(global){
	return "uniform";//Warning: Not well Identified 
	},

	getType:function(type){
	return definitions.get(type);//Warning: Not well Identified 
	}

};