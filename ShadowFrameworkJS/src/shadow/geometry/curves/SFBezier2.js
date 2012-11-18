

function SFBezier2(a,b,c){
	this.vertices=new Array();
	if(a===undefined)
		a=new SFVertex3f();
	if(b===undefined)
		b=new SFVertex3f();
	if(c===undefined)
		c=new SFVertex3f();
	this.vertices[0]=a;
	this.vertices[1]=b;
	this.vertices[2]=c;
}

inherit(SFBezier2,SFStandardAbstractCurve);


SFBezier2.prototype["getDev2Dt"]=function(t,read){
		read.set(this.vertices[0]);
		read.mult(2);
		read.addMult(-4,this.vertices[1]);
		read.addMult(2,this.vertices[2]);
};


SFBezier2.prototype["getDevDt"]=function(t,read){
		read.set(this.vertices[0]);
		read.mult(-2*(1-t));
		read.addMult(2-4*t,this.vertices[1]);
		read.addMult(2*t,this.vertices[2]);
};	
	
SFBezier2.prototype["getVertex"]=function(t,read){
		var tm = 1-t;
		read.set(this.vertices[0]);
		read.mult(tm*tm);
		read.addMult(2*t*tm,this.vertices[1]);
		read.addMult(t*t,this.vertices[2]);
};
	

SFBezier2.prototype["clone"]=function(){
		return new SFBezier2<T>(this.vertices[0], this.vertices[1], this.vertices[2]);
};	
