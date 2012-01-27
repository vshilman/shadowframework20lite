
function SFPrimitiveProgramAssociation(register, program){
		this.register=register;
		this.program=program;
}

SFPrimitiveProgramAssociation.prototype = {

	getRegister:function(){
		return this.register;
	},

	getProgram:function(){
		return this.program;
	}

};