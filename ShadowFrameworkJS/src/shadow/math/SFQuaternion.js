



function SFQuaternion(x,y,z,w){
	this.v=new Array();
	this.v[0]=x;
	this.v[1]=y;
	this.v[2]=z;
	this.v[3]=w;
}

inherit(SFQuaternion,SFVertex4f);

SFQuaternion.prototype["multTo"]=function(q){
	var v=this.v;
	var w1=v[3]*q.v[3]-v[0]*q.v[0]-v[1]*q.v[1]-v[2]*q.v[2];
	var x1=v[3]*q.v[0]+v[0]*q.v[3]+v[2]*q.v[1]-v[1]*q.v[2];
	var y1=v[3]*q.v[1]+v[1]*q.v[3]+v[0]*q.v[2]-v[2]*q.v[0];
	var z1=v[3]*q.v[2]+v[2]*q.v[3]+v[1]*q.v[0]-v[0]*q.v[1];
	this.v[0]=x1;
	this.v[1]=y1;
	this.v[2]=z1;
	this.v[3]=w1;
};


SFQuaternion.prototype["applyRotation"]=function(a){
	var b=new SFVertex3f(a.v[0],a.v[1],a.v[2]);
	
	var x1=(float)(v[1]*a.v[2]-v[2]*a.v[1]);
	var y1=(float)(v[2]*a.v[0]-v[0]*a.v[2]);
	var z1=(float)(v[0]*a.v[1]-v[1]*a.v[0]);
	
	var x2=v[1]*z1-v[2]*y1;
	var y2=v[2]*x1-v[0]*z1;
	var z2=v[0]*y1-v[1]*x1;
	
	var wr=1-v[3];
	
	b.v[0]+=x1+wr*x2;
	b.v[1]+=y1+wr*y2;
	b.v[2]+=z1+wr*z2;
	
	return b;
};


SFEulerAngles3f.prototype["getRotationMatrix"]=function(){
	var v=this.v;
	var m=new SFMatrix3f();
	
	m.setA(1 - 2*(v[1]*v[1] + v[2]*v[2]));   //L'ultimo termine.. y( x ay - y ax ) - z*( z ax - x az )  = (-y*y -z*z)ax  +x*y ay + z*x az 
	m.setB(-2*v[2]*v[3]+2*v[0]*v[1]);
	m.setC(2*v[3]*v[1] +2*v[0]*v[2]);

	m.setD(- 2*v[2]*v[3]+2*v[0]*v[1]);
	m.setE(- 1 + 2*(v[0]*v[0] + v[2]*v[2]));
	m.setF(- 2*v[1]*v[2]-2*v[0]*v[3]);

	m.setG(2*v[0]*v[2]-2*v[3]*v[1]);
	m.setH(2*v[1]*v[1]-2*v[3]*v[0]);
	m.setI(1 - 2*(v[0]*v[0] + v[1]*v[1]));
	
	return m;
};
