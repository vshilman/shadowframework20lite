
function SFParsableStructure(){
}

SFParsableStructure.prototype = {

	addParameter:function(parameter){
		loadingParameters.add(parameter);
	},

	finalize:function(){
		addParameters(loadingParameters);
		loadingParameters.clear();
		SFPipeline.loadStructure(getName(), this);
	},

	getAllCommands:function(){
		return ,allCommands;
	}

};