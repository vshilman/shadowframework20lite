
function SFVertex2f(x, y){
	v[0]=x;//Warning: Not well Identified 
	v[1]=y;//Warning: Not well Identified 
}
function SFVertex2f(x, y){
	v[0]=(float)x;//Warning: Not well Identified 
	v[1]=(float)y;//Warning: Not well Identified 
}

SFVertex2f.prototype = {

	toString:function(){
	return "x:"+v[0]+",y:"+v[1];//Warning: Not well Identified 
	},

	getY:function(){
		return this.v[1];
	},

	setY:function(y){
	v[1]=y;//Warning: Not well Identified 
	},

	set2f:function(x, y){
		this.v[0]=x;
		this.v[1]=y;
	},

	set2d:function(x, y){
	this.v[0]=(float)x;//Warning: Not well Identified 
	this.v[1]=(float)y;//Warning: Not well Identified 
	},

	mult2f:function(m){
	v[0]*=m;//Warning: Not well Identified 
	v[1]*=m;//Warning: Not well Identified 
	},

	addMult2f:function(a, vx){
	v[0]+=vx.v[0]*a;//Warning: Not well Identified 
	v[1]+=vx.v[1]*a;//Warning: Not well Identified 
	},

	scale2f:function(sx, sy){
	v[0]*=sx;//Warning: Not well Identified 
	v[1]*=sy;//Warning: Not well Identified 
	},

	set2f:function(vx){
	v[0]=vx.v[0];//Warning: Not well Identified 
	v[1]=vx.v[1];//Warning: Not well Identified 
	},

	dot2f:function(vx){
	return vx.v[0]*v[0]+vx.v[1]*v[1];//Warning: Not well Identified 
	},

	normalize2f:function(){
	float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]));//Warning: Not well Identified 
	v[0]*=lengthRec;//Warning: Not well Identified 
	v[1]*=lengthRec;//Warning: Not well Identified 
	},

	add2f:function(vx){
	v[0]+=vx.v[0];//Warning: Not well Identified 
	v[1]+=vx.v[1];//Warning: Not well Identified 
	},

	subtract2f:function(vx){
	v[0]-=vx.v[0];//Warning: Not well Identified 
	v[1]-=vx.v[1];//Warning: Not well Identified 
	},

	setByIndex:function(index, val){
	super.setByIndex(index,val);//Warning: Not well Identified 
	if(index==1)			v[1]=val;//Warning: Not well Identified 
	},

	getByIndex:function(index){
	if(index==0)			return v[0];//Warning: Not well Identified 
		return this.v[1];
	},

	middle:function(A, B){
	return new SFVertex2f(				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f		);//Warning: Not well Identified 
	}

};