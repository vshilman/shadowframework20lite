
function SFMatrix3f(){
	A=1;//Warning: Not well Identified 
	E=1;//Warning: Not well Identified 
	I=1;//Warning: Not well Identified 
}
function SFMatrix3f(a, b, c, d, e, f, g, h, i){
	A = a;//Warning: Not well Identified 
	B = b;//Warning: Not well Identified 
	C = c;//Warning: Not well Identified 
	D = d;//Warning: Not well Identified 
	E = e;//Warning: Not well Identified 
	F = f;//Warning: Not well Identified 
	G = g;//Warning: Not well Identified 
	H = h;//Warning: Not well Identified 
	I = i;//Warning: Not well Identified 
}

SFMatrix3f.prototype = {

	getRotationZ:function(angle){
	SFMatrix3f m;
	float cos;
	float sin;
	m.A=cos;//Warning: Not well Identified 
	m.B=sin;//Warning: Not well Identified 
	m.C=0;//Warning: Not well Identified 
	m.D=-sin;//Warning: Not well Identified 
	m.E=cos;//Warning: Not well Identified 
	m.F=0;//Warning: Not well Identified 
	m.G=0;//Warning: Not well Identified 
	m.H=0;//Warning: Not well Identified 
	m.I=1;//Warning: Not well Identified 
		return this.m;
	},

	getRotationY:function(angle){
	SFMatrix3f m;
	float cos;
	float sin;
	m.A=cos;//Warning: Not well Identified 
	m.B=0;//Warning: Not well Identified 
	m.C=sin;//Warning: Not well Identified 
	m.D=0;//Warning: Not well Identified 
	m.E=1;//Warning: Not well Identified 
	m.F=0;//Warning: Not well Identified 
	m.G=-sin;//Warning: Not well Identified 
	m.H=0;//Warning: Not well Identified 
	m.I=cos;//Warning: Not well Identified 
		return this.m;
	},

	getRotationX:function(angle){
	SFMatrix3f m;
	float cos;
	float sin;
	m.A=1;//Warning: Not well Identified 
	m.B=0;//Warning: Not well Identified 
	m.C=0;//Warning: Not well Identified 
	m.D=0;//Warning: Not well Identified 
	m.E=cos;//Warning: Not well Identified 
	m.F=sin;//Warning: Not well Identified 
	m.G=0;//Warning: Not well Identified 
	m.H=-sin;//Warning: Not well Identified 
	m.I=cos;//Warning: Not well Identified 
		return this.m;
	},

	getTrasposed:function(m){
	SFMatrix3f n;
	n.A=m.A;//Warning: Not well Identified 
	n.D=m.B;//Warning: Not well Identified 
	n.G=m.C;//Warning: Not well Identified 
	n.B=m.D;//Warning: Not well Identified 
	n.E=m.E;//Warning: Not well Identified 
	n.H=m.F;//Warning: Not well Identified 
	n.C=m.G;//Warning: Not well Identified 
	n.F=m.H;//Warning: Not well Identified 
	n.I=m.I;//Warning: Not well Identified 
		return this.n;
	},

	getIdentity:function(){
	SFMatrix3f n;
	n.A=1;//Warning: Not well Identified 
	n.D=0;//Warning: Not well Identified 
	n.G=0;//Warning: Not well Identified 
	n.B=0;//Warning: Not well Identified 
	n.E=1;//Warning: Not well Identified 
	n.H=0;//Warning: Not well Identified 
	n.C=0;//Warning: Not well Identified 
	n.F=0;//Warning: Not well Identified 
	n.I=1;//Warning: Not well Identified 
		return this.n;
	},

	getAmpl:function(AmplX, AmplY, AmplZ){
	SFMatrix3f n;
	n.A=AmplX;//Warning: Not well Identified 
	n.D=0;//Warning: Not well Identified 
	n.G=0;//Warning: Not well Identified 
	n.B=0;//Warning: Not well Identified 
	n.E=AmplY;//Warning: Not well Identified 
	n.H=0;//Warning: Not well Identified 
	n.C=0;//Warning: Not well Identified 
	n.F=0;//Warning: Not well Identified 
	n.I=AmplZ;//Warning: Not well Identified 
		return this.n;
	},

	getInversa:function(m){
	SFMatrix3f n;
	float delta;
		return this.n;
	},

	getRotationMatrix:function(q){
	SFMatrix3f m;
	m.setA(1 - 2*(q.getY()*q.getY() + q.getZ()*q.getZ()));//Warning: Not well Identified 
	m.setB(-2*q.getZ()*q.getW()+2*q.getX()*q.getY());//Warning: Not well Identified 
	m.setC(2*q.getW()*q.getY() +2*q.getX()*q.getZ());//Warning: Not well Identified 
	m.setD(2*q.getZ()*q.getW()+2*q.getX()*q.getY());//Warning: Not well Identified 
	m.setE(1 - 2*(q.getX()*q.getX() + q.getZ()*q.getZ()));//Warning: Not well Identified 
	m.setF(2*q.getY()*q.getZ()-2*q.getX()*q.getW());//Warning: Not well Identified 
	m.setG(2*q.getX()*q.getZ()-2*q.getW()*q.getY());//Warning: Not well Identified 
	m.setH(2*q.getY()*q.getY()-2*q.getW()*q.getX());//Warning: Not well Identified 
	m.setI(1 - 2*(q.getX()*q.getX() + q.getY()*q.getY()));//Warning: Not well Identified 
		return this.m;
	},

	set:function(m){
	A=m.A;//Warning: Not well Identified 
	B=m.B;//Warning: Not well Identified 
	C=m.C;//Warning: Not well Identified 
	D=m.D;//Warning: Not well Identified 
	E=m.E;//Warning: Not well Identified 
	F=m.F;//Warning: Not well Identified 
	G=m.G;//Warning: Not well Identified 
	H=m.H;//Warning: Not well Identified 
	I=m.I;//Warning: Not well Identified 
	},

	toString:function(){
	return "GLMatrix3f \n " + A+" "+ B+" "+ C+" \n"+								D+" "+ E+" "+ F+" \n"+								G+" "+ H+" "+ I+" \n";//Warning: Not well Identified 
	},

	Mult:function(m){
	SFMatrix3f n;
	n.A= A*m.A + B*m.D + C*m.G;//Warning: Not well Identified 
	n.B= A*m.B + B*m.E + C*m.H;//Warning: Not well Identified 
	n.C= A*m.C + B*m.F + C*m.I;//Warning: Not well Identified 
	n.D= D*m.A + E*m.D + F*m.G;//Warning: Not well Identified 
	n.E= D*m.B + E*m.E + F*m.H;//Warning: Not well Identified 
	n.F= D*m.C + E*m.F + F*m.I;//Warning: Not well Identified 
	n.G= G*m.A + H*m.D + I*m.G;//Warning: Not well Identified 
	n.H= G*m.B + H*m.E + I*m.H;//Warning: Not well Identified 
	n.I= G*m.C + H*m.F + I*m.I;//Warning: Not well Identified 
		return this.n;
	},

	Mult:function(p){
	SFVertex3f n;
	n.setX( A*p.getX() + B*p.getY() + C*p.getZ() );//Warning: Not well Identified 
	n.setY( D*p.getX() + E*p.getY() + F*p.getZ() );//Warning: Not well Identified 
	n.setZ( G*p.getX() + H*p.getY() + I*p.getZ() );//Warning: Not well Identified 
		return this.n;
	},

	transform:function(p){
	float x=(float)p.getX(),y=(float)p.getY(),z=(float)p.getZ();//Warning: Not well Identified 
	p.setX( A*x + B*y + C*z);//Warning: Not well Identified 
	p.setY( D*x + E*y + F*z);//Warning: Not well Identified 
	p.setZ( G*x + H*y + I*z);//Warning: Not well Identified 
	},

	setByIndex:function(index, val){
	if(index==0)			A=val;//Warning: Not well Identified 
	else if(index==1)			B=val;//Warning: Not well Identified 
	else if(index==2)			C=val;//Warning: Not well Identified 
	else if(index==3)			D=val;//Warning: Not well Identified 
	else if(index==4)			E=val;//Warning: Not well Identified 
	else if(index==5)			F=val;//Warning: Not well Identified 
	else if(index==6)			G=val;//Warning: Not well Identified 
	else if(index==7)			H=val;//Warning: Not well Identified 
	else if(index==8)			I=val;//Warning: Not well Identified 
	},

	getByIndex:function(index){
	if(index==0)			return A;//Warning: Not well Identified 
	else if(index==1)			return B;//Warning: Not well Identified 
	else if(index==2)			return C;//Warning: Not well Identified 
	else if(index==3)			return D;//Warning: Not well Identified 
	else if(index==4)			return E;//Warning: Not well Identified 
	else if(index==5)			return F;//Warning: Not well Identified 
	else if(index==6)			return G;//Warning: Not well Identified 
	else if(index==7)			return H;//Warning: Not well Identified 
		return this.I;
	}

};