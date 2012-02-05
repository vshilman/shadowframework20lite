
function SFParsableProgramComponent(){
}

SFParsableProgramComponent.prototype = {

	finalize:function(){
		SFPipeline.loadShaderComponent(getName(), this);
	},

	getAllCommands:function(){
		return ,allCommands;
	}

};