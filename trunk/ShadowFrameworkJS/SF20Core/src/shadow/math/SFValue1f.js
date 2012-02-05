
function SFValue1f(){
}

SFValue1f.prototype = {

	getX:function(){
		return ,v[0];
	},

	setX:function(x){
		v[0]  = x;
	},

	mult1f:function(m){
		v[0] * = m;
	},

	add1f:function(dX){
		v[0] + = dX;
	},

	subtract1f:function(dX){
		v[0] + = dX;
	},

	setByIndex:function(index, val){
	if(index==0)			v[0]=val;//Warning: Not well Identified 
	},

	cloneValue:function(){
		return ,new ,SFValue1f(this.v[0]);
	}

};