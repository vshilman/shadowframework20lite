//Java to JS on 06/02/2012

function SFVertex2f(x,y){
	this.v=new Array();
	this.v[0]=x;
	this.v[1]=y;
}

inherit(SFVertex2f,SFValue1f);

SFVertex2f.prototype["toString"]=function(){
			return "x:"+this.v[0]+",y:"+this.v[1]; 
		};
		
SFVertex2f.prototype["getY"]=function(){
			return this.v[1];
		};
		
SFVertex2f.prototype["setY"]=function(y){
			this.v[1]  = y;
		};
		
SFVertex2f.prototype["cloneValue"]=function(){
			return new SFVertex2f(this.v[0],this.v[1]);
		};
		
SFVertex2f.prototype["set2f"]=function(x,y){
			this.v[0]  = x;
			this.v[1]  = y;
		};

SFVertex2f.prototype["set2d"]=function(x,y){
			this.v[0]  = x;
			this.v[1]  = y;
		};
		
SFVertex2f.prototype["mult2f"]=function(m){
			this.v[0] *= m;
			this.v[1] *= m;
		};

SFVertex2f.prototype["storeOn2f"]=function(f){
	storeOn1f(f);
	f[1]=this.v[1];
};
		
SFVertex2f.prototype["addMult2f"]=function(a, vx){
			this.v[0] += vx.v[0]*a;
			this.v[1] += vx.v[1]*a;
		};
		
SFVertex2f.prototype["scale2f"]=function(sx, sy){
			this.v[0] *= sx;
			this.v[1] *= sy;
		};
		
SFVertex2f.prototype["set2f"]=function(vx){
			this.v[0]  = vx.v[0];
			this.v[1]  = vx.v[1];
		};
		
SFVertex2f.prototype["dot2f"]=function(vx){
			return vx.v[0]*v[0]+vx.v[1]*v[1];
		};
		
SFVertex2f.prototype["normalize2f"]=function(){
			var lengthRec=1/this.getLength();//Warning: Not well Identified 
			this.v[0] *= lengthRec;
			this.v[1] *= lengthRec;
		};
		
SFVertex2f.prototype["getLength"]=function(){
			var v=this.v;
			return Math.sqrt(v[0]*v[0]+v[1]*v[1]);
		};	
		
SFVertex2f.prototype["getDistance"]=function(v1,v2){
			var x=v1.getX()-v2.getX();
			var y=v1.getY()-v2.getY();
			return Math.sqrt(x*x+y*y);
		};		
		
SFVertex2f.prototype["add2f"]=function(vx){
			this.v[0] += vx.v[0];
			this.v[1] += vx.v[1];
		};
		
SFVertex2f.prototype["add2f"]=function(vx,vy){
			this.v[0] += vx;
			this.v[1] += vy;
		};
		
SFVertex2f.prototype["subtract2f"]=function(vx,vy){
			this.v[0] -= vx.v[0];
			this.v[1] -= vx.v[1];
		};
		
SFVertex2f.prototype["getByIndex"]=function(index){
			if(index==0)
				return this.v[0];
			return this.v[1];
		};

SFVertex2f.prototype["middle"]=function(A, B){
			return new SFVertex2f((A.v[0]+B.v[0])*0.5,(A.v[1]+B.v[1])*0.5);
		};
		