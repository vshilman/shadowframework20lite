



function SFUnitVector3f(x,y,z){
	this.v=new Array();
	this.v[0]=0;
	this.v[1]=0;
}

inherit(SFEulerAngles3f,SFVertex2f);

SFUnitVector3f.prototype["getVertex3f"]=function(write){
	var cosa=(float)(Math.cos(v[0]));
	var sina=(float)(Math.sin(v[0]));
	var cosb=(float)(Math.cos(v[1]));
	var sinb=(float)(Math.sin(v[1]));
	write.set3f(cosa,sina*cosb,sina*sinb);
};


SFUnitVector3f.prototype["setVertex3f"]=function(read){
	var v=this.v;
	read.normalize3f();
	var cosa=read.getX();
	var sina=(Math.sqrt(1-cosa*cosa));
	
	v[0]=(Math.atan2(sina, cosa));//SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(cosa, sina);
	var sinaRec=1.0/sina;
	var cosb=read.getY()*sinaRec;
	var sinb=read.getZ()*sinaRec;
	v[1]=(Math.atan2(sinb, cosb));
};


