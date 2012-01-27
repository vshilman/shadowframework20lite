
function SFValue1f(x){
		this.v[0]=x;
}
function SFValue1f(x){
	this.v[0] = (float)x;//Warning: Not well Identified 
}

SFValue1f.prototype = {

	getX:function(){
		return this.v[0];
	},

	setX:function(x){
	v[0]=x;//Warning: Not well Identified 
	},

	mult1f:function(m){
	v[0]*=m;//Warning: Not well Identified 
	},

	add1f:function(dX){
	v[0]+=dX;//Warning: Not well Identified 
	},

	subtract1f:function(dX){
	v[0]+=dX;//Warning: Not well Identified 
	},

	setByIndex:function(index, val){
	if(index==0)			v[0]=val;//Warning: Not well Identified 
	}

};