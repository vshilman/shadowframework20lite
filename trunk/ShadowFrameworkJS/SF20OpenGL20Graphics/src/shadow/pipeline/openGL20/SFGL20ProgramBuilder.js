
function SFGL20ProgramBuilder(){
}

SFGL20ProgramBuilder.prototype = {

	generateNewProgram:function(){
	return new SFGL20Program();//Warning: Not well Identified 
	},

	prepareProgram:function(program){
	SFInitiator.addInitiable((SFGL20GenericProgram)program);//Warning: Not well Identified 
	},

	loadProgram:function(program){
	program.load();//Warning: Not well Identified 
	SFGL20PipelineGraphics.setProgram((SFGL20GenericProgram)program);//Warning: Not well Identified 
	},

	generateImageProgram:function(){
	return new SFGL20ImageProgram();//Warning: Not well Identified 
	}

};