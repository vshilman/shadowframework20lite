
function SFGL20ProgramBuilder(){

}


SFGL20ProgramBuilder.prototype["generateNewProgram"]=function(){
		return new SFGL20Program();
	};

SFGL20ProgramBuilder.prototype["loadProgram"]=function(program){
		program.load();
		SFGL20PipelineGraphics_setProgram(program);
	};

SFGL20ProgramBuilder.prototype["generateImageProgram"]=function(){
		return new SFGL20ImageProgram();
	};