
function SFVertex4f(x, y, z, w){
	this.set4f(x,y,z,w);//Warning: Not well Identified 
}
function SFVertex4f(x, y, z, w){
	this.set4d(x,y,z,w);//Warning: Not well Identified 
}
function SFVertex4f(q){
	set4f(q.v[0],q.v[1],q.v[2],q.v[3]);//Warning: Not well Identified 
}
function SFVertex4f(){
}

SFVertex4f.prototype = {

	cloneValue:function(){
	return new SFVertex4f(v[0],v[1],v[2],v[3]);//Warning: Not well Identified 
	}

};