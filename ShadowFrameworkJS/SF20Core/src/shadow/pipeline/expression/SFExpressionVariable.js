
function SFExpressionVariable(element, parameters){
	this.parameters.addAll(parameters);//Warning: Not well Identified 
}

SFExpressionVariable.prototype = {

	getParameter:function(set, name){
		return this.null;
	}

};