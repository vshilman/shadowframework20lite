
function SFMatrix2f(){
}
function SFMatrix2f(a, b, c, d){
	v[0]=a;//Warning: Not well Identified 
	v[1]=b;//Warning: Not well Identified 
	v[2]=c;//Warning: Not well Identified 
	v[3]=d;//Warning: Not well Identified 
}

SFMatrix2f.prototype = {

	cloneValue:function(){
	return new SFMatrix2f(v[0],v[1],v[2],v[3]);//Warning: Not well Identified 
	},

	addMult:function(scalar, value){
	v[0]+=scalar*value.v[0];//Warning: Not well Identified 
	}

};