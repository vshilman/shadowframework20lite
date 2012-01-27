
function SFInstancedParameter(parameter, name){
		this.parameter=parameter;
		this.name=name;
}

SFInstancedParameter.prototype = {

	getName:function(){
		return this.name;
	},

	getParameter:function(){
		return this.parameter;
	},

	setParameter:function(parameter){
		this.parameter=parameter;
	},

	setName:function(name){
		this.name=name;
	}

};