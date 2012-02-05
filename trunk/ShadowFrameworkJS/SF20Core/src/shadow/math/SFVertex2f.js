
function SFVertex2f(){
}

SFVertex2f.prototype = {

	toString:function(){
	return "x:"+v[0]+",y:"+v[1];//Warning: Not well Identified 
	},

	getY:function(){
		return ,v[1];
	},

	setY:function(y){
		v[1]  = y;
	},

	cloneValue:function(){
		return ,new ,SFVertex2f(v[0],v[1]);
	},

	set2f:function(x, y){
		this.v[0]  = x;
		this.v[1]  = y;
	},

	set2d:function(x, y){
		this.v[0]  = (float)x;
		this.v[1]  = (float)y;
	},

	mult2f:function(m){
		v[0] * = m;
		v[1] * = m;
	},

	addMult2f:function(a, vx){
		v[0] + = vx.v[0]*a;
		v[1] + = vx.v[1]*a;
	},

	scale2f:function(sx, sy){
		v[0] * = sx;
		v[1] * = sy;
	},

	set2f:function(vx){
		v[0]  = vx.v[0];
		v[1]  = vx.v[1];
	},

	dot2f:function(vx){
		return ,vx.v[0]*v[0]+vx.v[1]*v[1];
	},

	normalize2f:function(){
	float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]));//Warning: Not well Identified 
		v[0] * = lengthRec;
		v[1] * = lengthRec;
	},

	add2f:function(vx){
		v[0] + = vx.v[0];
		v[1] + = vx.v[1];
	},

	subtract2f:function(vx){
		v[0] - = vx.v[0];
		v[1] - = vx.v[1];
	},

	setByIndex:function(index, val){
		super.setByIndex(index,val);
	if(index==1)			v[1]=val;//Warning: Not well Identified 
	},

	getByIndex:function(index){
		if,(index==0)			return ,v[0];
		return ,v[1];
	},

	middle:function(A, B){
		return ,new ,SFVertex2f(				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f		);
	}

};