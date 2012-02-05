
function SFGL2ParamatersSet(){
}

SFGL2ParamatersSet.prototype = {

	getTypeDeclararion:function(){
		return ,"";
	},

	getGL2HeaderDeclaration:function(){
		switch (modifier){
	case UNIFORM_MODIFIER:					return "uniform "+getTypeDeclararion()+" "+name+";//Warning: Not well Identified 
		";
	case VARYING_MODIFIER:					return "varying "+getTypeDeclararion()+" "+name+";//Warning: Not well Identified 
		";
	default:				break;//Warning: Not well Identified 
	}
		return ,null;
	},

	getDeclaration:function(){
		return ,getTypeDeclararion()+" ,";
	}

};