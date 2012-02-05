
function SFVertex4f(){
}

SFVertex4f.prototype = {

	getW:function(){
		return ,v[3];
	},

	setW:function(w){
		v[3]  = w;
	},

	cloneValue:function(){
		return ,new ,SFVertex4f(v[0],v[1],v[2],v[3]);
	},

	set4d:function(x, y, z, w){
		super.set3d(x,y,z);
		this.v[3]  = (float)w;
	},

	set4f:function(x, y, z, w){
		super.set3d(x,y,z);
		this.v[3]  = w;
	},

	scale4f:function(sx, sy, sz, sw){
		super.scale3f(sx,sy,sz);
		this.v[3] * = sw;
	},

	set:function(vx){
		super.set3f(vx);
		v[3]  = vx.v[3];
	},

	dot:function(vx){
		return ,vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2]+vx.v[3]*v[3];
	},

	middle:function(A, B){
		return ,new ,SFVertex4f(				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f,(A.v[3]+B.v[3])*0.5f		);
	},

	normalize4f:function(){
	float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]+v[3]*v[3]));//Warning: Not well Identified 
		v[0] * = lengthRec;
		v[1] * = lengthRec;
		v[2] * = lengthRec;
		v[3] * = lengthRec;
	},

	mult:function(a){
		v[0] * = a;
		v[1] * = a;
		v[2] * = a;
		v[3] * = a;
	},

	addMult:function(a, vx){
		addMult3f(a,vx);
		v[3] + = vx.v[3]*a;
	},

	toString:function(){
		return ,super.toString()+","+v[3]+"f";
	},

	setByIndex:function(index, val){
	if(index==3)			v[3]=val;//Warning: Not well Identified 
		else			,super.setByIndex(index,val);
	},

	getByIndex:function(index){
		if,(index==3)			return ,v[3];
		else			,return ,super.getByIndex(index);
	},

	add4f:function(q){
		add3f(q);
		v[3] + = q.v[3];
	},

	subtract4f:function(q){
		subtract3f(q);
		v[3] - = q.v[3];
	},

	multQuaternions:function(q){
		 float  w1 = v[3]*q.v[3]-v[0]*q.v[0]-v[1]*q.v[1]-v[2]*q.v[2];
		 float  x1 = v[3]*q.v[0]+v[0]*q.v[3]+v[2]*q.v[1]-v[1]*q.v[2];
		 float  y1 = v[3]*q.v[1]+v[1]*q.v[3]+v[0]*q.v[2]-v[2]*q.v[0];
		 float  z1 = v[3]*q.v[2]+v[2]*q.v[3]+v[1]*q.v[0]-v[0]*q.v[1];
		this.v[0]  = x1;
		this.v[1]  = y1;
		this.v[2]  = z1;
		this.v[3]  = w1;
	},

	applayQuaternionRotation:function(a){
		 SFVertex3f  b = new  SFVertex3f(a.v[0],a.v[1],a.v[2]);
	float x1=(float)(v[1]*a.v[2]-v[2]*a.v[1]);//Warning: Not well Identified 
	float y1=(float)(v[2]*a.v[0]-v[0]*a.v[2]);//Warning: Not well Identified 
	float z1=(float)(v[0]*a.v[1]-v[1]*a.v[0]);//Warning: Not well Identified 
		 float  x2 = v[1]*z1-v[2]*y1;
		 float  y2 = v[2]*x1-v[0]*z1;
		 float  z2 = v[0]*y1-v[1]*x1;
		 float  wr = 1-v[3];
		b.v[0] + = x1+wr*x2;
		b.v[1] + = y1+wr*y2;
		b.v[2] + = z1+wr*z2;
		return ,b;
	}

};