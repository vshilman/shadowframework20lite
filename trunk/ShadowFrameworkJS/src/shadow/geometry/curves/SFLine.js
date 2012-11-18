
function SFLine(a,b){
	if(a==undefined)
		a=new SFVertex3f();
	if(b==undefined)
		b=new SFVertex3f();
	this.vertices=new Array();
	this.vertices[0]=a;
	this.vertices[1]=b;
}

inherit(SFLine,SFStandardAbstractCurve);

SFLine.prototype["getDev2Dt"]=function(t,read){
	var data = read.get();
	for (var i = 0; i < data.length; i++) {
		data[i]=0;
	}
}

SFLine.prototype["getDevDt"]=function(t,read){
	read.set(this.vertices[1]);
	read.addMult(-1,this.vertices[0]);
}	

SFLine.prototype["getVertex"]=function(t,read){
	read.set(this.vertices[0]);
	read.mult(1-t);
	read.addMult(t,this.vertices[1]);
}	
	
SFLine.prototype["clone"]=function(){
	return new SFLine(this.vertices[0], this.vertices[1]);
}	
	
	
