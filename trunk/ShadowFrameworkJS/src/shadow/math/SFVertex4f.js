
function SFVertex4f(x,y,z,w){
	this.v=new Array();
	if(w==undefined)
		w=1;
	this.v=new Array();
	this.v[0]  = x;
	this.v[1]  = y;
	this.v[2]  = z;
	this.v[3]  = w;
}

inherit(SFVertex4f,SFVertex3f);

SFVertex4f.prototype["getW"]=function(){
			return this.v[3];
		};
		
SFVertex4f.prototype["setW"]=function(w){
			this.v[3]  = w;
		};
		
SFVertex4f.prototype["cloneValue"]=function(w){
			var v=this.v;
			return new SFVertex4f(v[0],v[1],v[2],v[3]);
		};
		
SFVertex4f.prototype["set4f"]=function(x, y, z, w){
			this.v[0]  = x;
			this.v[1]  = y;
			this.v[2]  = z;
			this.v[3]  = w;
		};
		
SFVertex4f.prototype["scale4f"]=function(sx, sy, sz, sw){
			this.v[0] *= sx;
			this.v[1] *= sy;
			this.v[2] *= sz;
			this.v[3] *= sw;
		};
		
SFVertex4f.prototype["set"]=function(vx){
			this.v[0]  = vx.v[0];
			this.v[1]  = vx.v[1];
			this.v[2]  = vx.v[2];
			this.v[3]  = vx.v[3];
		};
		
SFVertex4f.prototype["dot"]=function(vx){
			var v=this.v;
			return vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2]+vx.v[3]*v[3];
		};
		
SFVertex4f.prototype["middle4f"]=function(A, B){
			return new SFVertex4f((A.v[0]+B.v[0])*0.5,(A.v[1]+B.v[1])*0.5,(A.v[2]+B.v[2])*0.5,(A.v[3]+B.v[3])*0.5);
		};
		
SFVertex4f.prototype["normalize4f"]=function(){
			var v=this.v;
			var lengthRec=1/(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]+v[3]*v[3]));//Warning: Not well Identified 
			this.v[0] *= lengthRec;
			this.v[1] *= lengthRec;
			this.v[2] *= lengthRec;
			this.v[3] *= lengthRec;
		};
		
SFVertex4f.prototype["mult"]=function(a){
			this.v[0] *= a;
			this.v[1] *= a;
			this.v[2] *= a;
			this.v[3] *= a;
		};
		
SFVertex4f.prototype["storeOn"]=function(f){
			f[0] = this.v[0];
			f[1] = this.v[1];
			f[2] = this.v[2];
			f[3] = this.v[3];
		};
		
SFVertex4f.prototype["mult4f"]=function(a,vx){
			this.v[0] *= a+vx.v[0];
			this.v[1] *= a+vx.v[1];
			this.v[2] *= a+vx.v[2];
			this.v[3] *= a+vx.v[3];
		};
				
SFVertex4f.prototype["toString_"]=function(){
			var v=this.v;
			return "x:"+v[0]+",y:"+v[1]+",z:"+v[2]+",w:"+v[3]+" ";
		};
		
SFVertex4f.prototype["add4f"]=function(q){
			this.v[0] += q.v[0];
			this.v[1] += q.v[1];
			this.v[2] += q.v[2];
			this.v[3] += q.v[3];
		};

SFVertex4f.prototype["setByIndex"]=function(index,val){
			this.v[index]=val;
		};
		
SFVertex4f.prototype["getByIndex"]=function(index){
			return this.v[index];
		};
		
		
SFVertex4f.prototype["add4f"]=function(x,y,z,w){
			this.v[0] += x;
			this.v[1] += y;
			this.v[2] += z;
			this.v[3] += w;
		};

SFVertex4f.prototype["subtract4f"]=function(x,y,z,w){
			this.v[0] -= x;
			this.v[1] -= y;
			this.v[2] -= z;
			this.v[3] -= w;
		};
		
SFVertex4f.prototype["multQuaternions"]=function(q){
			var  w1 = v[3]*q.v[3]-v[0]*q.v[0]-v[1]*q.v[1]-v[2]*q.v[2];
			var  x1 = v[3]*q.v[0]+v[0]*q.v[3]+v[2]*q.v[1]-v[1]*q.v[2];
			var  y1 = v[3]*q.v[1]+v[1]*q.v[3]+v[0]*q.v[2]-v[2]*q.v[0];
			var  z1 = v[3]*q.v[2]+v[2]*q.v[3]+v[1]*q.v[0]-v[0]*q.v[1];
			this.v[0]  = x1;
			this.v[1]  = y1;
			this.v[2]  = z1;
			this.v[3]  = w1;
		};
		
SFVertex4f.prototype["applayQuaternionRotation"]=function(a){
			var  b = new  SFVertex3f(a.v[0],a.v[1],a.v[2]);
			var x1=(v[1]*a.v[2]-v[2]*a.v[1]);//Warning: Not well Identified 
			var y1=(v[2]*a.v[0]-v[0]*a.v[2]);//Warning: Not well Identified 
			var z1=(v[0]*a.v[1]-v[1]*a.v[0]);//Warning: Not well Identified 
			var  x2 = v[1]*z1-v[2]*y1;
			var  y2 = v[2]*x1-v[0]*z1;
			var  z2 = v[0]*y1-v[1]*x1;
			var  wr = 1-v[3];
			b.v[0] += x1+wr*x2;
			b.v[1] += y1+wr*y2;
			b.v[2] += z1+wr*z2;
			return b;
		};
