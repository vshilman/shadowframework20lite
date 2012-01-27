
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

	getZ:function(){
		return this.v[2];
	},

	setZ:function(z){
	v[2]=z;//Warning: Not well Identified 
	},

	cloneV:function(){
	return new SFVertex3f(v[0],v[1],v[2]);//Warning: Not well Identified 
	},

	set3f:function(x, y, z){
	v[0]=x;//Warning: Not well Identified 
	v[1]=y;//Warning: Not well Identified 
	v[2]=z;//Warning: Not well Identified 
	},

	set3d:function(x, y, z){
	v[0]=(float)x;//Warning: Not well Identified 
	v[1]=(float)y;//Warning: Not well Identified 
	v[2]=(float)z;//Warning: Not well Identified 
	},

	scale3f:function(sx, sy, sz){
	v[0]*=sx;//Warning: Not well Identified 
	v[1]*=sy;//Warning: Not well Identified 
	v[2]*=sz;//Warning: Not well Identified 
	},

	scale3d:function(sx, sy, sz){
	v[0]*=(float)sx;//Warning: Not well Identified 
	v[1]*=(float)sy;//Warning: Not well Identified 
	v[2]*=(float)sz;//Warning: Not well Identified 
	},

	set3f:function(vx){
	set2f(vx);//Warning: Not well Identified 
	v[2]=vx.v[2];//Warning: Not well Identified 
	},

	dot3f:function(vx){
	return vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2];//Warning: Not well Identified 
	},

	cross:function(vx){
	return new SFVertex3f(v[1]*vx.v[2]-v[2]*vx.v[1],							v[2]*vx.v[0]-v[0]*vx.v[2],							v[0]*vx.v[1]-v[1]*vx.v[0]);//Warning: Not well Identified 
	},

	normalize3f:function(){
	float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]));//Warning: Not well Identified 
	v[0]*=lengthRec;//Warning: Not well Identified 
	v[1]*=lengthRec;//Warning: Not well Identified 
	v[2]*=lengthRec;//Warning: Not well Identified 
	},

	mult:function(a){
	mult2f(a);//Warning: Not well Identified 
	v[2]*=a;//Warning: Not well Identified 
	},

	addMult3f:function(a, vx){
	addMult2f(a,vx);//Warning: Not well Identified 
	v[2]+=vx.v[2]*a;//Warning: Not well Identified 
	},

	addMult3d:function(a, vx){
	addMult2f((float)a,vx);//Warning: Not well Identified 
	v[2]+=vx.v[2]*a;//Warning: Not well Identified 
	},

	add3f:function(vx){
	add2f(vx);//Warning: Not well Identified 
	v[2]+=vx.v[2];//Warning: Not well Identified 
	},

	subtract3f:function(vx){
	subtract2f(vx);//Warning: Not well Identified 
	v[2]-=vx.v[2];//Warning: Not well Identified 
	},

	toString:function(){
	return super.toString()+",z:"+v[2];//Warning: Not well Identified 
	},

	setByIndex:function(index, val){
	super.setByIndex(index,val);//Warning: Not well Identified 
	if(index==2)			v[2]=val;//Warning: Not well Identified 
	},

	getByIndex:function(index){
	return super.getByIndex(index);//Warning: Not well Identified 
	},

	middle:function(A, B){
	return new SFVertex3f(				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f		);//Warning: Not well Identified 
	}

};