

function SFEulerAngles3f(x,y,z){
	this.v=new Array();
	this.v[0]=x;
	this.v[1]=y;
	this.v[2]=z;
}

inherit(SFEulerAngles3f,SFVertex3f);

SFEulerAngles3f.prototype["getMatrix"]=function(matrix){

	var v=this.v;
	var c1=(Math.cos(v[0]));
	var s1=(Math.sin(v[0]));
	var c2=(Math.cos(v[1]));
	var s2=(Math.sin(v[1]));
	var c3=(Math.cos(v[2]));
	var s3=(Math.sin(v[2]));

	matrix.setA(c1*c3-s1*s2*s3);
	matrix.setB(-c2*s1);
	matrix.setC(c1*s3+c3*s1*s2);
	matrix.setD(c3*s1+c1*s2*s3);
	matrix.setE(c1*c2);
	matrix.setF(s1*s3-c1*c3*s2);
	matrix.setG(-c2*s3);
	matrix.setH(s2);
	matrix.setI(c2*c3); 
};
	

SFEulerAngles3f.prototype["setMatrix"]=function(matrix){

	var v=this.v;
	//Retrieve s2 from H and evaluate c2
	var s2=matrix.getH();
	var c2=(Math.sqrt(1-s2*s2));
	var c1=0,s1=0,c3=0,s3=0;

	v[1]=(Math.atan2(s2, c2));//SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(c2, s2);
	
	if(c2==0){
		c3=1;
		s3=0;
		c1=matrix.getA();
		s1=matrix.getD();
	}else{
		var recC2=1.0/c2;
		//get (c3,s3) from G,I
		c3=matrix.getI()*recC2;
		s3=-matrix.getG()*recC2;
		//get (c1,s1) from E,B
		c1=matrix.getE()*recC2;
		s1=-matrix.getB()*recC2;
	}
	
	v[0]=(Math.atan2(s1, c1));
	v[2]=(Math.atan2(s3, c3));	
};
		
		
