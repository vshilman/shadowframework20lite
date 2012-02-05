
function SFMatrix2f(){
}

SFMatrix2f.prototype = {

	cloneValue:function(){
		return ,new ,SFMatrix2f(v[0],v[1],v[2],v[3]);
	},

	main:function(args[]){
		 SFMatrix2f  m1 = SFMatrix2f.getRotationZ(3);
		System.out.println(m1);
		System.out.println(SFMatrix2f.getInversa(m1));
		System.out.println(SFMatrix2f.getTrasposta(m1));
		System.out.println(m1.Mult(SFMatrix2f.getInversa(m1)));
	},

	getRotationZ:function(angle){
		 SFMatrix2f  m = new  SFMatrix2f();
	float cos=(float) (Math.cos(angle));//Warning: Not well Identified 
	float sin=(float) (Math.sin(angle));//Warning: Not well Identified 
		m.v[0]  = cos;
		m.v[1]  = sin;
		m.v[2]  = -sin;
		m.v[3]  = cos;
		return ,m;
	},

	getTrasposta:function(m){
		 SFMatrix2f  n = new  SFMatrix2f();
		n.v[0]  = m.v[0];
		n.v[2]  = m.v[1];
		n.v[1]  = m.v[2];
		n.v[3]  = m.v[3];
		return ,n;
	},

	getIdentity:function(){
		 SFMatrix2f  n = new  SFMatrix2f();
		n.v[0]  = 1;
		n.v[2]  = 0;
		n.v[1]  = 0;
		n.v[3]  = 1;
		return ,n;
	},

	getAmpl:function(AmplX, AmplY){
		 SFMatrix2f  n = new  SFMatrix2f();
		n.v[0]  = AmplX;
		n.v[2]  = 0;
		n.v[1]  = 0;
		n.v[3]  = AmplY;
		return ,n;
	},

	getInversa:function(m){
		 SFMatrix2f  n = new  SFMatrix2f();
		 float  delta = m.v[0] * m.v[3] - m.v[2] * m.v[1];
		 if ( delta != 0 ){
		delta  = 1 / delta;
		n.v[0]  = delta * (m.v[3]);
		n.v[1]  = -delta * (m.v[1]);
	n.v[2]=delta * (-m.v[2]);//Warning: Not well Identified 
		n.v[3]  = delta * (m.v[0]);
	}
		return ,n;
	},

	set:function(m){
		v[0]  = m.v[0];
		v[1]  = m.v[1];
		v[2]  = m.v[2];
		v[3]  = m.v[3];
	},

	toString:function(){
	return "GLMatrix2f \n " + v[0] + " " + v[1] + " " + v[2] + " \n" + v[2] + " " + v[3]				+ " \n";//Warning: Not well Identified 
	},

	Mult:function(m){
		 SFMatrix2f  n = new  SFMatrix2f();
		n.v[0]  = v[0] * m.v[0] + v[1] * m.v[2];
		n.v[1]  = v[0] * m.v[1] + v[1] * m.v[3];
		n.v[2]  = v[2] * m.v[0] + v[3] * m.v[2];
		n.v[3]  = v[2] * m.v[1] + v[3] * m.v[3];
		return ,n;
	},

	Mult:function(p){
		 SFVertex2f  n = new  SFVertex2f(2);
		n.setX(v[0] * p.getX() + v[1] * p.getY());
		n.setY(v[2] * p.getX() + v[2] * p.getY());
		return ,n;
	},

	transform:function(p){
	float x=p.getX(), y=p.getY();//Warning: Not well Identified 
		p.setX(v[0] * x + v[1] * y);
		p.setY(v[2] * x + v[2] * y);
	},

	getA:function(){
		return ,v[0];
	},

	setA:function(a){
		v[0]  = a;
	},

	getB:function(){
		return ,v[1];
	},

	setB:function(b){
		v[1]  = b;
	},

	getC:function(){
		return ,v[2];
	},

	setC:function(c){
		v[2]  = c;
	},

	getD:function(){
		return ,v[3];
	},

	setD:function(d){
		v[3]  = d;
	},

	setByIndex:function(index, val){
	if (index == 0)			v[0]=val;//Warning: Not well Identified 
	else if (index == 1)			v[1]=val;//Warning: Not well Identified 
	else if (index == 2)			v[2]=val;//Warning: Not well Identified 
	else if (index == 3)			v[3]=val;//Warning: Not well Identified 
	},

	getByIndex:function(index){
	if (index == 0)			return v[0];//Warning: Not well Identified 
	else if (index == 1)			return v[1];//Warning: Not well Identified 
	else if (index == 2)			return v[2];//Warning: Not well Identified 
		return ,v[3];
	},

	addMult:function(scalar, value){
		v[0] + = scalar*value.v[0];
	}

};