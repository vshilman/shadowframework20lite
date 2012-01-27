
function SFPipelineModuleWrongException(){
	super("Cannot compile shader components");//Warning: Not well Identified 
}

SFPipelineModuleWrongException.prototype = {

	getList:function(){
		return this.list;
	},

	setList:function(list){
		this.list=list;
	}

};