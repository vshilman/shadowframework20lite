

function SFPipelineInstructionObjects(){
	this.dataObject = new Array();
	
	this.command=new SFString("");
	this.parametersList=new SFDataList(new SFString(""));
	this.dataList=new SFDataList(new SFString(""));
	this.addDataObject(this.command);
	this.addDataObject(this.parametersList);
	this.addDataObject(this.dataList);
}


inherit(SFPipelineInstructionObjects,SFCompositeDataArray);


SFPipelineInstructionObjects.prototype["clone"]=function(){
		return new SFPipelineInstructionObjects();
	};
	
SFPipelineInstructionObjects.prototype["commandName"]=function(){
		return this.command.getString();
	};
	
SFPipelineInstructionObjects.prototype["getParameters"]=function(){

		var parameters=new Array();
		for(var i=0;i<this.parametersList.size();i++){
			parameters.push(this.parametersList.getDataObject()[i].getString());
		}

		return parameters;
	};
	
SFPipelineInstructionObjects.prototype["getData"]=function(index){
		return this.dataList.getDataObject()[index].getString();
	};
	