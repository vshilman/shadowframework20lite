

function SFProgramModuleStructures(program){
	this.data=new Array();
	this.textures=new Array();
	this.program=program;
}


SFProgramModuleStructures.prototype["clone"]=function(){
	var element=new SFProgramModuleStructures(this.program);
	for(var i=0;i<this.data.length;i++){
		element.data.push(this.data[i]);
	}
	for(var i=0;i<this.textures.length;i++){
		element.textures.push(this.textures[i]);
	}
	return element;
};


SFProgramModuleStructures.prototype["getData"]=function(){
	return this.data;
};


SFProgramModuleStructures.prototype["addData"]=function(reference){
	this.data.push(reference);
};

SFProgramModuleStructures.prototype["getProgram"]=function(){
	return this.program;
};

SFProgramModuleStructures.prototype["setProgram"]=function(program){
	this.program = program;
};

SFProgramModuleStructures.prototype["getTextures"]=function(program){
	return this.textures;
};

SFProgramModuleStructures.prototype["destroy"]=function(){
	//DO nothing
};


SFProgramModuleStructures.prototype["init"]=function(){
	//DO nothing
};
	