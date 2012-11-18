
function SFVertex3f(x,y,z){
	this.v=new Array();
	if(x===undefined)
		x=0;
	if(y===undefined)
		y=0;
	if(z===undefined)
		z=0;
	this.v[0]=x;
	this.v[1]=y;
	this.v[2]=z;
}

inherit(SFVertex3f,SFVertex2f);

SFVertex3f.prototype["getZ"]=function(){
			return this.v[2];
		};
		
SFVertex3f.prototype["setZ"]=function(z){
			this.v[2]  = z;
		};
		
SFVertex3f.prototype["set3f"]=function(x, y, z){
			this.v[0]  = x;
			this.v[1]  = y;
			this.v[2]  = z;
		};
		
SFVertex3f.prototype["set3d"]=function(x, y, z){
			this.v[0]  = x;
			this.v[1]  = y;
			this.v[2]  = z;
		};

SFVertex3f.prototype["cloneV"]=function(){
			return new SFVertex3f(this.v[0],this.v[1],this.v[2]);
		};

SFVertex3f.prototype["scale3f"]=function(sx, sy, sz){
			this.v[0] *= sx;
			this.v[1] *= sy;
			this.v[2] *= sz;
		};

SFVertex3f.prototype["scale3d"]=function(sx, sy, sz){
			this.v[0] *= sx;
			this.v[1] *= sy;
			this.v[2] *= sz;
		};
			
SFVertex3f.prototype["set3f"]=function(vx){
			this.v[0]  = vx.v[0];
			this.v[1]  = vx.v[1];
			this.v[2]  = vx.v[2];
		};
		
SFVertex3f.prototype["dot3f"]=function(vx){
			return vx.v[0]*this.v[0]+vx.v[1]*this.v[1]+vx.v[2]*this.v[2];
		};
		
SFVertex3f.prototype["cross"]=function(vx){
			var cross = new SFVertex3f(this.v[1]*vx.v[2]-this.v[2]*vx.v[1],
				this.v[2]*vx.v[0]-this.v[0]*vx.v[2],							
				this.v[0]*vx.v[1]-this.v[1]*vx.v[0]);
				
				return cross;
		};
		
SFVertex3f.prototype["normalize3f"]=function(){
			var lengthRec=1/this.getLength();//Warning: Not well Identified 
			this.v[0] *= lengthRec;
			this.v[1] *= lengthRec;
			this.v[2] *= lengthRec;
		};
		
SFVertex3f.prototype["getLength"]=function(){
			var v=this.v;
			return Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
		};	
		
SFVertex3f.prototype["getDistance"]=function(v1,v2){
			var x=v1.getX()-v2.getX();
			var y=v1.getY()-v2.getY();
			var z=v1.getZ()-v2.getZ();
			return Math.sqrt(x*x+y*y+z*z);
		};
		
SFVertex3f.prototype["mult3f"]=function(a){
			this.v[0] *= a;
			this.v[1] *= a;
			this.v[2] *= a;
		};

SFVertex3f.prototype["storeOn3f"]=function(f){
			storeOn3f(f);
			f[2]=this.v[2];
		};

SFVertex3f.prototype["addMult3f"]=function(a, vx){
			this.v[0] += vx.v[0]*a;
			this.v[1] += vx.v[1]*a;
			this.v[2] += vx.v[2]*a;
		};

SFVertex3f.prototype["addMult3d"]=function(a, vx){
			this.v[0] += vx.v[0]*a;
			this.v[1] += vx.v[1]*a;
			this.v[2] += vx.v[2]*a;
		};
		
SFVertex3f.prototype["add3fv"]=function(vx){
			this.v[0] += vx.v[0];
			this.v[1] += vx.v[1];
			this.v[2] += vx.v[2];
		};

SFVertex3f.prototype["add3f"]=function(vx,vy,vz){
			this.v[0] += vx;
			this.v[1] += vy;
			this.v[2] += vz;
		};

SFVertex3f.prototype["subtract3f"]=function(vx){
			this.v[0] -= vx.v[0];
			this.v[1] -= vx.v[1];
			this.v[2] -= vx.v[2];
		};

SFVertex3f.prototype["toString"]=function(){
			return "x:"+this.v[0]+",y:"+this.v[1]+",z:"+this.v[2];
		};

SFVertex3f.prototype["middle3f"]=function(){
			return new SFVertex3f((A.v[0]+B.v[0])*0.5,(A.v[1]+B.v[1])*0.5,(A.v[2]+B.v[2])*0.5);
		};
		
SFVertex3f.prototype["cloneValue"]=function(){
			return new SFVertex3f(this.v[0],this.v[1],this.v[2]);
		};
