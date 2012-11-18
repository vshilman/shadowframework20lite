
function SFMatrix3f(a,b,c,d,e,f,g,h,i){
	if(typeof a==="undefined")
		a=1;
	if(typeof b==="undefined")
		b=0;
	if(typeof c==="undefined")
		c=0;
	if(typeof d==="undefined")
		d=0;
	if(typeof e==="undefined")
		e=1;
	if(typeof f==="undefined")
		f=0;
	if(typeof g==="undefined")
		g=0;
	if(typeof h==="undefined")
		h=0;
	if(typeof i==="undefined")
		i=1;
	this.v=new Array();
	this.v[0]=a;
	this.v[1]=b;
	this.v[2]=c;
	this.v[3]=d;
	this.v[4]=e;
	this.v[5]=f;
	this.v[6]=g;
	this.v[7]=h;
	this.v[8]=i;
}

inherit(SFMatrix3f,SFMatrix2f);
	
		
SFMatrix3f.prototype["cloneValue"]=function(){
			var v=this.v;
			return new SFMatrix3f(v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7],v[8]);
		};
		
SFMatrix3f.prototype["getRotationZ"]=function(angle){
			 var  m = new  SFMatrix3f();
			 var cos=(Math.cos(angle));//Warning: Not well Identified 
			 var sin=(Math.sin(angle));//Warning: Not well Identified 
			m.v[0]  = cos;
			m.v[1]  = sin;
			m.v[2]  = 0;
			m.v[3]  = -sin;
			m.v[4]  = cos;
			m.v[5]  = 0;
			m.v[6]  = 0;
			m.v[7]  = 0;
			m.v[8]  = 1;
			return m;
		};
	
SFMatrix3f.prototype["getRotationY"]=function(angle){
			 var  m = new  SFMatrix3f();
			 var cos=(Math.cos(angle));//Warning: Not well Identified 
			 var sin=(Math.sin(angle));//Warning: Not well Identified 
			m.v[0]  = cos;
			m.v[1]  = 0;
			m.v[2]  = sin;
			m.v[3]  = 0;
			m.v[4]  = 1;
			m.v[5]  = 0;
			m.v[6]  = -sin;
			m.v[7]  = 0;
			m.v[8]  = cos;
			return m;
		};
		
SFMatrix3f.prototype["getRotationX"]=function(angle){
			var  m = new  SFMatrix3f();
			var cos=(Math.cos(angle));//Warning: Not well Identified 
			var sin=(Math.sin(angle));//Warning: Not well Identified 
			m.v[0]  = 1;
			m.v[1]  = 0;
			m.v[2]  = 0;
			m.v[3]  = 0;
			m.v[4]  = cos;
			m.v[5]  = sin;
			m.v[6]  = 0;
			m.v[7]  = -sin;
			m.v[8]  = cos;
			return m;
		};
		
SFMatrix3f.prototype["getTrasposed"]=function(m){
			var  n = new  SFMatrix3f();
			n.v[0]  = m.v[0];
			n.v[3]  = m.v[1];
			n.v[6]  = m.v[2];
			n.v[1]  = m.v[3];
			n.v[4]  = m.v[4];
			n.v[7]  = m.v[5];
			n.v[2]  = m.v[6];
			n.v[5]  = m.v[7];
			n.v[8]  = m.v[8];
			return n;
		};
		
SFMatrix3f.prototype["getIdentity"]=function(){
			var  n = new  SFMatrix3f();
			n.v[0]  = 1;
			n.v[3]  = 0;
			n.v[6]  = 0;
			n.v[1]  = 0;
			n.v[4]  = 1;
			n.v[7]  = 0;
			n.v[2]  = 0;
			n.v[5]  = 0;
			n.v[8]  = 1;
			return n;
		};
		
SFMatrix3f.prototype["getAmpl"]=function(AmplX, AmplY, AmplZ){
			var  n = new  SFMatrix3f();
			n.v[0]  = AmplX;
			n.v[3]  = 0;
			n.v[6]  = 0;
			n.v[1]  = 0;
			n.v[4]  = AmplY;
			n.v[7]  = 0;
			n.v[2]  = 0;
			n.v[5]  = 0;
			n.v[8]  = AmplZ;
			return n;
		};
		
SFMatrix3f.prototype["getInverse"]=function(m){
			var  n = new  SFMatrix3f();
			var  delta   = m.v[0] * ( m.v[4]*m.v[8]-m.v[5]*m.v[7] ) - m.v[1] * ( m.v[3]*m.v[8]-m.v[5]*m.v[6] ) + 
				m.v[2] * ( m.v[3]*m.v[7]-m.v[4]*m.v[6] );
			if ( delta!=0 ){
				delta  = 1/delta;
				//A=0 B=1 C=2 
				//D=3 E=4 F=5
				//G=6 H=7 I=8
				n.v[0]  = delta*(m.v[4]*m.v[8]-m.v[5]*m.v[7]);
				n.v[1]  = -delta*(m.v[1]*m.v[8]-m.v[2]*m.v[7]);
				n.v[2]  = delta*(m.v[1]*m.v[5]-m.v[2]*m.v[4]);
				n.v[3]  = -delta*(m.v[3]*m.v[8]-m.v[5]*m.v[6]);
				n.v[4]  = delta*(m.v[0]*m.v[8]-m.v[2]*m.v[6]);
				n.v[5]  = -delta*(m.v[0]*m.v[5]-m.v[2]*m.v[3]);
				n.v[6]  = delta*(m.v[3]*m.v[7]-m.v[4]*m.v[6]);
				n.v[7]  = -delta*(m.v[0]*m.v[7]-m.v[1]*m.v[6]);
				n.v[8]  = delta*(m.v[0]*m.v[4]-m.v[1]*m.v[3]);
			}
			return n;
		};
		
SFMatrix3f.prototype["getRotationMatrix"]=function(q){
			var  m = new  SFMatrix3f();
			m.setA(1 - 2*(q.getY()*q.getY() + q.getZ()*q.getZ()));
			m.setB(-2*q.getZ()*q.getW()+2*q.getX()*q.getY());
			m.setC(2*q.getW()*q.getY() +2*q.getX()*q.getZ());
			m.setD(2*q.getZ()*q.getW()+2*q.getX()*q.getY());
			m.setE(1 - 2*(q.getX()*q.getX() + q.getZ()*q.getZ()));
			m.setF(2*q.getY()*q.getZ()-2*q.getX()*q.getW());
			m.setG(2*q.getX()*q.getZ()-2*q.getW()*q.getY());
			m.setH(2*q.getY()*q.getY()-2*q.getW()*q.getX());
			m.setI(1 - 2*(q.getX()*q.getX() + q.getY()*q.getY()));
			return m;
		};
		
SFMatrix3f.prototype["set"]=function(m){
			var v=this.v;
			for(var i=0;i<9;i++){
				v[i]=m.v[i];
			}
		};
		
SFMatrix3f.prototype["toString"]=function(m){
			var v =this.v;
			return "GLMatrix3f \n " + v[0]+" "+ v[1]+" "+ v[2]+" \n"+v[3]+" "+ v[4]+" "+ v[5]+" \n"+v[6]+" "+ v[7]+" "+ v[8]+" \n";//Warning: Not well Identified 
		};
		
SFMatrix3f.prototype["MultMatrix"]=function(m){
			var  n = new  SFMatrix3f();
			n.v[0]  = this.v[0]*m.v[0] + this.v[1]*m.v[3] + this.v[2]*m.v[6];
			n.v[1]  = this.v[0]*m.v[1] + this.v[1]*m.v[4] + this.v[2]*m.v[7];
			n.v[2]  = this.v[0]*m.v[2] + this.v[1]*m.v[5] + this.v[2]*m.v[8];
			n.v[3]  = this.v[3]*m.v[0] + this.v[4]*m.v[3] + this.v[5]*m.v[6];
			n.v[4]  = this.v[3]*m.v[1] + this.v[4]*m.v[4] + this.v[5]*m.v[7];
			n.v[5]  = this.v[3]*m.v[2] + this.v[4]*m.v[5] + this.v[5]*m.v[8];
			n.v[6]  = this.v[6]*m.v[0] + this.v[7]*m.v[3] + this.v[8]*m.v[6];
			n.v[7]  = this.v[6]*m.v[1] + this.v[7]*m.v[4] + this.v[8]*m.v[7];
			n.v[8]  = this.v[6]*m.v[2] + this.v[7]*m.v[5] + this.v[8]*m.v[8];
			return n;
		};
		
SFMatrix3f.prototype["Mult"]=function(p){
			 var  n = new  SFVertex3f(3);
			n.setX( this.v[0]*p.getX() + this.v[1]*p.getY() + this.v[2]*p.getZ() );
			n.setY( this.v[3]*p.getX() + this.v[4]*p.getY() + this.v[5]*p.getZ() );
			n.setZ( this.v[6]*p.getX() + this.v[7]*p.getY() + this.v[8]*p.getZ() );
			return n;
		};
		
SFMatrix3f.prototype["transform"]=function(p){
			var x=p.getX(),y=p.getY(),z=p.getZ();//Warning: Not well Identified 
			n.setX( this.v[0]*x + this.v[1]*x + this.v[2]*x );
			n.setY( this.v[3]*y + this.v[4]*y + this.v[5]*y );
			n.setZ( this.v[6]*z + this.v[7]*z + this.v[8]*z );
			return n;
		};
		
		
SFMatrix3f.prototype["getE"]=function(){
	return this.v[4];
};

SFMatrix3f.prototype["setE"]=function(e){
	this.v[4] = e;
};

SFMatrix3f.prototype["getF"]=function(){
	return this.v[5];
};

SFMatrix3f.prototype["setF"]=function(f){
	this.v[5] = f;
};

SFMatrix3f.prototype["getG"]=function(){
	return this.v[6];
};

SFMatrix3f.prototype["setG"]=function(g){
	this.v[6] = g;
};


SFMatrix3f.prototype["getH"]=function(){
	return this.v[7];
};

SFMatrix3f.prototype["setH"]=function(h){
	this.v[7] = h;
};

SFMatrix3f.prototype["getI"]=function(){
	return this.v[8];
};

SFMatrix3f.prototype["setI"]=function(i){
	this.v[8] = i;
};

var testMatrix=new SFMatrix3f();
var testD=testMatrix.cloneValue();

		