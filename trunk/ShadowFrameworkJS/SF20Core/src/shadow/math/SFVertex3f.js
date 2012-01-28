
function SFVertex3f(vx){
	set3f(vx);//Warning: Not well Identified 
}
function SFVertex3f(x, y, z){
	set3d(x,y,z);//Warning: Not well Identified 
}
function SFVertex3f(){
	this(0,0,0);//Warning: Not well Identified 
}
function SFVertex3f(x, y, z){
	set3d(x,y,z);//Warning: Not well Identified 
}

SFVertex3f.prototype = {

	cloneValue:function(){
	return new SFVertex3f(v[0],v[1],v[2]);//Warning: Not well Identified 
	}

};