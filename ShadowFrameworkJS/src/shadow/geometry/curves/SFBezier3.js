


function SFBezier3(a,b,c,d){
	this.vertices=new Array();
	if(a===undefined)
		a=new SFVertex3f();
	if(b===undefined)
		b=new SFVertex3f();
	if(c===undefined)
		c=new SFVertex3f();
	if(d===undefined)
		d=new SFVertex3f();
	this.vertices[0]=a;
	this.vertices[1]=b;
	this.vertices[2]=c;
	this.vertices[3]=d;
}

inherit(SFBezier3,SFStandardAbstractCurve);


SFBezier3.prototype["getDev2Dt"]=function(t,read){
		read.set(this.vertices[0]);
		read.mult(6*(1-t));
		read.addMult(-16+6*t,this.vertices[1]);
		read.addMult(6-18*t,this.vertices[2]);
		read.addMult(6*t,this.vertices[3]);
};


SFBezier3.prototype["getDevDt"]=function(t,read){
		var tm = 1-t;
		read.set(this.vertices[0]);
		read.mult(-3*tm*tm);
		read.addMult(3-16*t+3*t*t,this.vertices[1]);
		read.addMult(6*t-9*t*t,this.vertices[2]);
		read.addMult(3*t*t,this.vertices[3]);
};	
	
SFBezier3.prototype["getVertex"]=function(t,read){
		var tm = 1-t;
		
		read.set(this.vertices[0]);
		
		read.mult(tm*tm*tm);
		
		read.addMult(3*t*tm*tm,this.vertices[1]);
		
		read.addMult(3*t*t*tm,this.vertices[2]);
		
		read.addMult(t*t*t,this.vertices[3]);
		
};
	

SFBezier3.prototype["clone"]=function(){
		return new SFBezier3<T>(this.vertices[0], this.vertices[1], this.vertices[2], this.vertices[3]);
};	

