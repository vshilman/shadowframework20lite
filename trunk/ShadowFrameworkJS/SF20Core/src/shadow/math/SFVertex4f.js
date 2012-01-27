
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

	getW:function(){
		return this.v[3];
	},

	setW:function(w){
	v[3]=w;//Warning: Not well Identified 
	},

	set4d:function(x, y, z, w){
	super.set3d(x,y,z);//Warning: Not well Identified 
	this.v[3]=(float)w;//Warning: Not well Identified 
	},

	set4f:function(x, y, z, w){
	super.set3d(x,y,z);//Warning: Not well Identified 
		this.v[3]=w;
	},

	scale4f:function(sx, sy, sz, sw){
	super.scale3f(sx,sy,sz);//Warning: Not well Identified 
	this.v[3]*=sw;//Warning: Not well Identified 
	},

	set:function(vx){
	super.set3f(vx);//Warning: Not well Identified 
	v[3]=vx.v[3];//Warning: Not well Identified 
	},

	dot:function(vx){
	return vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2]+vx.v[3]*v[3];//Warning: Not well Identified 
	},

	middle:function(A, B){
	return new SFVertex4f(				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f,(A.v[3]+B.v[3])*0.5f		);//Warning: Not well Identified 
	},

	normalize4f:function(){
	float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]+v[3]*v[3]));//Warning: Not well Identified 
	v[0]*=lengthRec;//Warning: Not well Identified 
	v[1]*=lengthRec;//Warning: Not well Identified 
	v[2]*=lengthRec;//Warning: Not well Identified 
	v[3]*=lengthRec;//Warning: Not well Identified 
	},

	mult:function(a){
	v[0]*=a;//Warning: Not well Identified 
	v[1]*=a;//Warning: Not well Identified 
	v[2]*=a;//Warning: Not well Identified 
	v[3]*=a;//Warning: Not well Identified 
	},

	addMult:function(a, vx){
	addMult3f(a,vx);//Warning: Not well Identified 
	v[3]+=vx.v[3]*a;//Warning: Not well Identified 
	},

	toString:function(){
	return super.toString()+","+v[3]+"f";//Warning: Not well Identified 
	},

	setByIndex:function(index, val){
	if(index==3)			v[3]=val;//Warning: Not well Identified 
	else			super.setByIndex(index,val);//Warning: Not well Identified 
	},

	getByIndex:function(index){
	if(index==3)			return v[3];//Warning: Not well Identified 
	else			return super.getByIndex(index);//Warning: Not well Identified 
	},

	add4f:function(q){
	add3f(q);//Warning: Not well Identified 
	v[3]+=q.v[3];//Warning: Not well Identified 
	},

	subtract4f:function(q){
	subtract3f(q);//Warning: Not well Identified 
	v[3]-=q.v[3];//Warning: Not well Identified 
	},

	applayQuaternionRotation:function(a){
	SFVertex3f b=new SFVertex3f(a.v[0],a.v[1],a.v[2]);//Warning: Not well Identified 
	float x1=(float)(v[1]*a.v[2]-v[2]*a.v[1]);//Warning: Not well Identified 
	float y1=(float)(v[2]*a.v[0]-v[0]*a.v[2]);//Warning: Not well Identified 
	float z1=(float)(v[0]*a.v[1]-v[1]*a.v[0]);//Warning: Not well Identified 
	float x2=v[1]*z1-v[2]*y1;//Warning: Not well Identified 
	float y2=v[2]*x1-v[0]*z1;//Warning: Not well Identified 
	float z2=v[0]*y1-v[1]*x1;//Warning: Not well Identified 
	float wr=1-v[3];//Warning: Not well Identified 
	b.v[0]+=x1+wr*x2;//Warning: Not well Identified 
	b.v[1]+=y1+wr*y2;//Warning: Not well Identified 
	b.v[2]+=z1+wr*z2;//Warning: Not well Identified 
		return this.b;
	}

};