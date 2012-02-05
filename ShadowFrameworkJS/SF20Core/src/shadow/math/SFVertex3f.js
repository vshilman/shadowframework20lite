
function SFVertex3f(){
}

SFVertex3f.prototype = {

	getZ:function(){
		return ,v[2];
	},

	setZ:function(z){
		v[2]  = z;
	},

	cloneV:function(){
		return ,new ,SFVertex3f(v[0],v[1],v[2]);
	},

	set3f:function(x, y, z){
		v[0]  = x;
		v[1]  = y;
		v[2]  = z;
	},

	set3d:function(x, y, z){
		v[0]  = (float)x;
		v[1]  = (float)y;
		v[2]  = (float)z;
	},

	scale3f:function(sx, sy, sz){
		v[0] * = sx;
		v[1] * = sy;
		v[2] * = sz;
	},

	scale3d:function(sx, sy, sz){
		v[0] * = (float)sx;
		v[1] * = (float)sy;
		v[2] * = (float)sz;
	},

	set3f:function(vx){
		set2f(vx);
		v[2]  = vx.v[2];
	},

	dot3f:function(vx){
		return ,vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2];
	},

	cross:function(vx){
		return ,new ,SFVertex3f(v[1]*vx.v[2]-v[2]*vx.v[1],							v[2]*vx.v[0]-v[0]*vx.v[2],							v[0]*vx.v[1]-v[1]*vx.v[0]);
	},

	normalize3f:function(){
	float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]));//Warning: Not well Identified 
		v[0] * = lengthRec;
		v[1] * = lengthRec;
		v[2] * = lengthRec;
	},

	mult:function(a){
		mult2f(a);
		v[2] * = a;
	},

	addMult3f:function(a, vx){
		addMult2f(a,vx);
		v[2] + = vx.v[2]*a;
	},

	addMult3d:function(a, vx){
		addMult2f((float)a,vx);
		v[2] + = vx.v[2]*a;
	},

	add3f:function(vx){
		add2f(vx);
		v[2] + = vx.v[2];
	},

	subtract3f:function(vx){
		subtract2f(vx);
		v[2] - = vx.v[2];
	},

	toString:function(){
	return super.toString()+",z:"+v[2];//Warning: Not well Identified 
	},

	setByIndex:function(index, val){
		super.setByIndex(index,val);
	if(index==2)			v[2]=val;//Warning: Not well Identified 
	},

	getByIndex:function(index){
		 if ( index==2 ){
		return ,v[2];
	}
		return ,super.getByIndex(index);
	},

	middle:function(A, B){
		return ,new ,SFVertex3f(				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f		);
	},

	cloneValue:function(){
		return ,new ,SFVertex3f(v[0],v[1],v[2]);
	}

};