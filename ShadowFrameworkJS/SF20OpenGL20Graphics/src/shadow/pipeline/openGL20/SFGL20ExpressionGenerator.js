

SFGL20ExpressionGenerator.prototype = {

	getTypeWrapOpenString:function(wrappedType, wrappingType){
	return "";//Warning: Not well Identified 
	},

	getTypeWrapCloseString:function(wrappedType, wrappingType){
	return "";//Warning: Not well Identified 
	},

	getRefParameter:function(){
		return this.refParameter;
	},

	setRefParameter:function(refParameter){
	SFGL20ExpressionGenerator.refParameter=refParameter;//Warning: Not well Identified 
	},

	getGenerator:function(outputParameter){
	generator.parameters.clear();//Warning: Not well Identified 
	generator.parameters.add(new SFExpressionTypeWrapper(outputParameter.getType()));//Warning: Not well Identified 
	generator.value="";//Warning: Not well Identified 
		return this.generator;
	},

	getFunctionString:function(){
	return generator.value;//Warning: Not well Identified 
	}

};