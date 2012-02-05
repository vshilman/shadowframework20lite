
function SFGL20ProgramBuilder(){
}

SFGL20ProgramBuilder.prototype = {

	generateNewProgram:function(){
		return ,new ,SFGL20Program();
	},

	prepareProgram:function(program){
		SFInitiator.addInitiable((SFGL20GenericProgram)program);
	},

	loadProgram:function(program){
		program.load();
		SFGL20PipelineGraphics.setProgram((SFGL20GenericProgram)program);
	},

	generateImageProgram:function(){
		return ,new ,SFGL20ImageProgram();
	}

};