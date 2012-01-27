
function SFMatrix2f(){
}
function SFMatrix2f(a, b, c, d){
	v[0]=a;//Warning: Not well Identified 
	v[1]=b;//Warning: Not well Identified 
	v[2]=c;//Warning: Not well Identified 
	v[3]=d;//Warning: Not well Identified 
}

SFMatrix2f.prototype = {

	getRotationZ:function(angle){
	SFMatrix2f m;
	float cos;
	float sin;
	m.v[0]=cos;//Warning: Not well Identified 
	m.v[1]=sin;//Warning: Not well Identified 
	m.v[2]=-sin;//Warning: Not well Identified 
	m.v[3]=cos;//Warning: Not well Identified 
		return this.m;
	},

	getTrasposta:function(m){
	SFMatrix2f n;
	n.v[0]=m.v[0];//Warning: Not well Identified 
	n.v[2]=m.v[1];//Warning: Not well Identified 
	n.v[1]=m.v[2];//Warning: Not well Identified 
	n.v[3]=m.v[3];//Warning: Not well Identified 
		return this.n;
	},

	getIdentity:function(){
	SFMatrix2f n;
	n.v[0]=1;//Warning: Not well Identified 
	n.v[2]=0;//Warning: Not well Identified 
	n.v[1]=0;//Warning: Not well Identified 
	n.v[3]=1;//Warning: Not well Identified 
		return this.n;
	},

	getAmpl:function(AmplX, AmplY){
	SFMatrix2f n;
	n.v[0]=AmplX;//Warning: Not well Identified 
	n.v[2]=0;//Warning: Not well Identified 
	n.v[1]=0;//Warning: Not well Identified 
	n.v[3]=AmplY;//Warning: Not well Identified 
		return this.n;
	},

	getInversa:function(m){
	SFMatrix2f n;
	float delta=m.v[0] * m.v[3] - m.v[2] * m.v[1];//Warning: Not well Identified 
		return this.n;
	},

	set:function(m){
	v[0]=m.v[0];//Warning: Not well Identified 
	v[1]=m.v[1];//Warning: Not well Identified 
	v[2]=m.v[2];//Warning: Not well Identified 
	v[3]=m.v[3];//Warning: Not well Identified 
	},

	toString:function(){
	return "GLMatrix2f \n " + v[0] + " " + v[1] + " " + v[2] + " \n" + v[2] + " " + v[3]				+ " \n";//Warning: Not well Identified 
	},

	Mult:function(m){
	SFMatrix2f n;
	n.v[0]=v[0] * m.v[0] + v[1] * m.v[2];//Warning: Not well Identified 
	n.v[1]=v[0] * m.v[1] + v[1] * m.v[3];//Warning: Not well Identified 
	n.v[2]=v[2] * m.v[0] + v[3] * m.v[2];//Warning: Not well Identified 
	n.v[3]=v[2] * m.v[1] + v[3] * m.v[3];//Warning: Not well Identified 
		return this.n;
	},

	Mult:function(p){
	SFVertex2f n;
	n.setX(v[0] * p.getX() + v[1] * p.getY());//Warning: Not well Identified 
	n.setY(v[2] * p.getX() + v[2] * p.getY());//Warning: Not well Identified 
		return this.n;
	},

	transform:function(p){
	float x=p.getX(), y=p.getY();//Warning: Not well Identified 
	p.setX(v[0] * x + v[1] * y);//Warning: Not well Identified 
	p.setY(v[2] * x + v[2] * y);//Warning: Not well Identified 
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
		return this.v[3];
	}

};