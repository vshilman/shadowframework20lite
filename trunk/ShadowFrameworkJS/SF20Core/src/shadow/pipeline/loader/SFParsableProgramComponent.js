

SFParsableProgramComponent.prototype = {

	finalize:function(){
	SFPipeline.loadShaderComponent(getName(), this);//Warning: Not well Identified 
	},

	getAllCommands:function(){
		return this.allCommands;
	}

};