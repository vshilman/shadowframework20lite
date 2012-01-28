
function SFValue1f(x){
		this.v[0]=x;
}
function SFValue1f(x){
	this.v[0] = (float)x;//Warning: Not well Identified 
}

SFValue1f.prototype = {

	cloneValue:function(){
	return new SFValue1f(this.v[0]);//Warning: Not well Identified 
	}

};