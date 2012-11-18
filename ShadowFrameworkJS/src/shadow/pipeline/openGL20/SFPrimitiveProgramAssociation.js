//Java to JS on 21/03/2012

function SFPrimitiveProgramAssociation(register,program){
	this.register=register;
	this.program=program;
}

SFPrimitiveProgramAssociation.prototype["getRegister"]=function(){
			return this.register;
		};

SFPrimitiveProgramAssociation.prototype["getProgram"]=function(){
			return this.program;
		};
