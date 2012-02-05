
function SFMatrix3f(){
}

SFMatrix3f.prototype = {

	cloneValue:function(){
		return ,new ,SFMatrix3f(v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7],v[8]);
	},

	main:function(args[]){
		 SFMatrix3f  m1 = SFMatrix3f.getRotationZ(3);
		System.out.println(m1);
		System.out.println(SFMatrix3f.getInversa(m1));
		System.out.println(SFMatrix3f.getTrasposed(m1));
		System.out.println(m1.Mult(SFMatrix3f.getInversa(m1)));
	},

	getRotationZ:function(angle){
		 SFMatrix3f  m = new  SFMatrix3f();
	float cos=(float)(Math.cos(angle));//Warning: Not well Identified 
	float sin=(float)(Math.sin(angle));//Warning: Not well Identified 
		m.A  = cos;
		m.B  = sin;
		m.C  = 0;
		m.D  = -sin;
		m.E  = cos;
		m.F  = 0;
		m.G  = 0;
		m.H  = 0;
		m.I  = 1;
		return ,m;
	},

	getRotationY:function(angle){
		 SFMatrix3f  m = new  SFMatrix3f();
	float cos=(float)(Math.cos(angle));//Warning: Not well Identified 
	float sin=(float)(Math.sin(angle));//Warning: Not well Identified 
		m.A  = cos;
		m.B  = 0;
		m.C  = sin;
		m.D  = 0;
		m.E  = 1;
		m.F  = 0;
		m.G  = -sin;
		m.H  = 0;
		m.I  = cos;
		return ,m;
	},

	getRotationX:function(angle){
		 SFMatrix3f  m = new  SFMatrix3f();
	float cos=(float)(Math.cos(angle));//Warning: Not well Identified 
	float sin=(float)(Math.sin(angle));//Warning: Not well Identified 
		m.A  = 1;
		m.B  = 0;
		m.C  = 0;
		m.D  = 0;
		m.E  = cos;
		m.F  = sin;
		m.G  = 0;
		m.H  = -sin;
		m.I  = cos;
		return ,m;
	},

	getTrasposed:function(m){
		 SFMatrix3f  n = new  SFMatrix3f();
		n.A  = m.A;
		n.D  = m.B;
		n.G  = m.C;
		n.B  = m.D;
		n.E  = m.E;
		n.H  = m.F;
		n.C  = m.G;
		n.F  = m.H;
		n.I  = m.I;
		return ,n;
	},

	getIdentity:function(){
		 SFMatrix3f  n = new  SFMatrix3f();
		n.A  = 1;
		n.D  = 0;
		n.G  = 0;
		n.B  = 0;
		n.E  = 1;
		n.H  = 0;
		n.C  = 0;
		n.F  = 0;
		n.I  = 1;
		return ,n;
	},

	getAmpl:function(AmplX, AmplY, AmplZ){
		 SFMatrix3f  n = new  SFMatrix3f();
		n.A  = AmplX;
		n.D  = 0;
		n.G  = 0;
		n.B  = 0;
		n.E  = AmplY;
		n.H  = 0;
		n.C  = 0;
		n.F  = 0;
		n.I  = AmplZ;
		return ,n;
	},

	getInversa:function(m){
		 SFMatrix3f  n = new  SFMatrix3f();
		 float  delta   = m.A * ( m.E*m.I-m.F*m.H ) - m.B * ( m.D*m.I-m.F*m.G ) + m.C * ( m.D*m.H-m.E*m.G );
		 if ( delta!=0 ){
		delta  = 1/delta;
		n.A  = delta*(m.E*m.I-m.F*m.H);
		n.B  = -delta*(m.B*m.I-m.C*m.H);
		n.C  = delta*(m.B*m.F-m.C*m.E);
		n.D  = -delta*(m.D*m.I-m.F*m.G);
		n.E  = delta*(m.A*m.I-m.C*m.G);
		n.F  = -delta*(m.A*m.F-m.C*m.D);
		n.G  = delta*(m.D*m.H-m.E*m.G);
		n.H  = -delta*(m.A*m.H-m.B*m.G);
		n.I  = delta*(m.A*m.E-m.B*m.D);
	}
		return ,n;
	},

	getRotationMatrix:function(q){
		 SFMatrix3f  m = new  SFMatrix3f();
		m.setA(1 - 2*(q.getY()*q.getY() + q.getZ()*q.getZ()));
		m.setB(-2*q.getZ()*q.getW()+2*q.getX()*q.getY());
		m.setC(2*q.getW()*q.getY() +2*q.getX()*q.getZ());
		m.setD(2*q.getZ()*q.getW()+2*q.getX()*q.getY());
		m.setE(1 - 2*(q.getX()*q.getX() + q.getZ()*q.getZ()));
		m.setF(2*q.getY()*q.getZ()-2*q.getX()*q.getW());
		m.setG(2*q.getX()*q.getZ()-2*q.getW()*q.getY());
		m.setH(2*q.getY()*q.getY()-2*q.getW()*q.getX());
		m.setI(1 - 2*(q.getX()*q.getX() + q.getY()*q.getY()));
		return ,m;
	},

	set:function(m){
		A  = m.A;
		B  = m.B;
		C  = m.C;
		D  = m.D;
		E  = m.E;
		F  = m.F;
		G  = m.G;
		H  = m.H;
		I  = m.I;
	},

	toString:function(){
	return "GLMatrix3f \n " + A+" "+ B+" "+ C+" \n"+								D+" "+ E+" "+ F+" \n"+								G+" "+ H+" "+ I+" \n";//Warning: Not well Identified 
	},

	Mult:function(m){
		 SFMatrix3f  n = new  SFMatrix3f();
		n.A  = A*m.A + B*m.D + C*m.G;
		n.B  = A*m.B + B*m.E + C*m.H;
		n.C  = A*m.C + B*m.F + C*m.I;
		n.D  = D*m.A + E*m.D + F*m.G;
		n.E  = D*m.B + E*m.E + F*m.H;
		n.F  = D*m.C + E*m.F + F*m.I;
		n.G  = G*m.A + H*m.D + I*m.G;
		n.H  = G*m.B + H*m.E + I*m.H;
		n.I  = G*m.C + H*m.F + I*m.I;
		return ,n;
	},

	Mult:function(p){
		 SFVertex3f  n = new  SFVertex3f(3);
		n.setX( A*p.getX() + B*p.getY() + C*p.getZ() );
		n.setY( D*p.getX() + E*p.getY() + F*p.getZ() );
		n.setZ( G*p.getX() + H*p.getY() + I*p.getZ() );
		return ,n;
	},

	transform:function(p){
	float x=(float)p.getX(),y=(float)p.getY(),z=(float)p.getZ();//Warning: Not well Identified 
		p.setX( A*x + B*y + C*z);
		p.setY( D*x + E*y + F*z);
		p.setZ( G*x + H*y + I*z);
	},

	getA:function(){
		return ,A;
	},

	setA:function(a){
		A    = a;
	},

	getB:function(){
		return ,B;
	},

	setB:function(b){
		B    = b;
	},

	getC:function(){
		return ,C;
	},

	setC:function(c){
		C    = c;
	},

	getD:function(){
		return ,D;
	},

	setD:function(d){
		D    = d;
	},

	getE:function(){
		return ,E;
	},

	setE:function(e){
		E    = e;
	},

	getF:function(){
		return ,F;
	},

	setF:function(f){
		F    = f;
	},

	getG:function(){
		return ,G;
	},

	setG:function(g){
		G    = g;
	},

	getH:function(){
		return ,H;
	},

	setH:function(h){
		H    = h;
	},

	getI:function(){
		return ,I;
	},

	setI:function(i){
		I    = i;
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
		if,(index==0)			return ,A;
		else ,if,(index==1)			return ,B;
		else ,if,(index==2)			return ,C;
		else ,if,(index==3)			return ,D;
		else ,if,(index==4)			return ,E;
		else ,if,(index==5)			return ,F;
		else ,if,(index==6)			return ,G;
		else ,if,(index==7)			return ,H;
		return ,I;
	}

};