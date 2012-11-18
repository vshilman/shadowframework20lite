//Java to JS on 06/02/2012

function SFMatrix2f(a,b,c,d){
	if(typeof a==="undefined")
		a=1;
	if(typeof b==="undefined")
		b=0;
	if(typeof c==="undefined")
		c=0;
	if(typeof d==="undefined")
		d=1;
	this.v=new Array();
	this.v[0]=a;
	this.v[1]=b;
	this.v[2]=c;
	this.v[3]=d;
}

inherit(SFMatrix2f,SFValuenf);
	
		
SFMatrix2f.prototype["cloneValue"]=function(){
			var v=this.v;
			return new SFMatrix2f(v[0],v[1],v[2],v[3]);
		};
		
SFMatrix2f.prototype["getRotationZ"]=function(angle){
			 var  m = new  SFMatrix2f();
			 var cos=(Math.cos(angle));//Warning: Not well Identified 
			 var	 sin= (Math.sin(angle));//Warning: Not well Identified 
			m.v[0]  = cos;
			m.v[1]  = sin;
			m.v[2]  = -sin;
			m.v[3]  = cos;
			return m;
		};

SFMatrix2f.prototype["getTransposed"]=function(m){
			 var  n = new  SFMatrix2f();
			n.v[0]  = m.v[0];
			n.v[2]  = m.v[1];
			n.v[1]  = m.v[2];
			n.v[3]  = m.v[3];
			return n;
		};
		
SFMatrix2f.prototype["getIdentity"]=function(){
			 var  n = new  SFMatrix2f();
			n.v[0]  = 1;
			n.v[2]  = 0;
			n.v[1]  = 0;
			n.v[3]  = 1;
			return n;
		};
		
SFMatrix2f.prototype["getAmpl"]=function(AmplX, AmplY){
			 var  n = new  SFMatrix2f();
			n.v[0]  = AmplX;
			n.v[2]  = 0;
			n.v[1]  = 0;
			n.v[3]  = AmplY;
			return n;
		};
	
SFMatrix2f.prototype["getInversa"]=function(m){
			 var  n = new  SFMatrix2f();
			 var  delta = m.v[0] * m.v[3] - m.v[2] * m.v[1];
			 if ( delta != 0 ){
				 delta  = 1 / delta;
				 n.v[0]  = delta * (m.v[3]);
				 n.v[1]  = -delta * (m.v[1]);
				 n.v[2]=delta * (-m.v[2]);//Warning: Not well Identified 
				 n.v[3]  = delta * (m.v[0]);
			 }
			return n;
		};	
	
SFMatrix2f.prototype["set"]=function(m){
			var v=this.v;
			v[0]  = m.v[0];
			v[1]  = m.v[1];
			v[2]  = m.v[2];
			v[3]  = m.v[3];
		};		
		
SFMatrix2f.prototype["toString"]=function(){
			var v=this.v;
			return "GLMatrix2f \n " + v[0] + " " + v[1] + " " + v[2] + " " + v[3]	+ " ";//Warning: Not well Identified 
		};	
		
SFMatrix2f.prototype["MultMatrix"]=function(m){
			var n = new  SFMatrix2f();
			var v = this.v;
			n.v[0]  = v[0] * m.v[0] + v[1] * m.v[2];
			n.v[1]  = v[0] * m.v[1] + v[1] * m.v[3];
			n.v[2]  = v[2] * m.v[0] + v[3] * m.v[2];
			n.v[3]  = v[2] * m.v[1] + v[3] * m.v[3];
			return n;
		};	
		
SFMatrix2f.prototype["Mult"]=function(p){
			var v=this.v;
			 var  n = new  SFVertex2f(2);
			n.setX(v[0] * p.getX() + v[1] * p.getY());
			n.setY(v[2] * p.getX() + v[2] * p.getY());
			return n;
		};
		
SFMatrix2f.prototype["transform"]=function(p){
			var v=this.v;
			var x=p.getX();
			var y=p.getY();//Warning: Not well Identified 
			p.setX(v[0] * x + v[1] * y);
			p.setY(v[2] * x + v[2] * y);
		};
		
SFMatrix2f.prototype["getA"]=function(){
			return this.v[0];
		};
SFMatrix2f.prototype["setA"]=function(a){
	this.v[0] = a;
		};
		
SFMatrix2f.prototype["getB"]=function(){
			return this.v[1];
		};
SFMatrix2f.prototype["setB"]=function(b){
	this.v[1] = b;
		};
		
SFMatrix2f.prototype["getC"]=function(){
			return this.v[2];
		};
SFMatrix2f.prototype["setC"]=function(c){
	this.v[2] = c;
		};
		
		
SFMatrix2f.prototype["getD"]=function(){
			return this.v[3];
		};
SFMatrix2f.prototype["setD"]=function(d){
	this.v[3] = d;
		};
		