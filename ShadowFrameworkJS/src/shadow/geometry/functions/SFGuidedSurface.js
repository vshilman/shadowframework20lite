
function SFGuidedSurface(a,b,c){
	
		this.A = a;
		this.B = b;
		this.C = c;
		this.maxTA=A.getTMax();
		this.maxTB=B.getTMax();
		
		
		this.startingTransform=new Array();
		this.actualTransform=new Array();
		
		this.evalTransform(startingTransform, 0);
		
		this.getTransformInverse(startingTransform);
};

inherit(SFGuidedSurface,SFUnoptimizedSurfaceFunction);


SFGuidedSurface.prototype["destroy"]=function(){
	
};

SFGuidedSurface.prototype["init"]=function(){
	
};	

SFGuidedSurface.prototype["evalTransform"]=function(transform,t){
	var pos=new SFVertex3f();
		this.B.getVertex(t, pos);
		var CB=new SFVertex3f();
		this.C.getVertex(t, pos);
		CB.subtract(pos);
		var T=new SFVertex3f();
		this.B.getDevDt(t, pos);
		T.normalize3f();
		var B=CB.cross(T);
		B.normalize3f();
		
		transform[0]=T.getX();transform[1]=CB.getX();transform[2]=B.getX();transform[3]=pos.getX();
		transform[4]=T.getY();transform[5]=CB.getY();transform[6]=B.getY();transform[7]=pos.getY();
		transform[8]=T.getZ();transform[9]=CB.getZ();transform[10]=B.getZ();transform[11]=pos.getZ();
		transform[12]=0;transform[13]=0;transform[14]=0;transform[15]=1;
};
	
	
SFGuidedSurface.prototype["getTransformInverse"]=function(transform){
	
		
		var mat=new SFMatrix3f();
		mat.setA(transform[0]);
		mat.setB(transform[1]);
		mat.setC(transform[2]);
		mat.setD(transform[4]);
		mat.setE(transform[5]);
		mat.setF(transform[6]);
		mat.setG(transform[8]);
		mat.setH(transform[9]);
		mat.setI(transform[10]);
		mat=mat.getInverse(mat);
		transform[0]=mat.getA();
		transform[1]=mat.getB();
		transform[2]=mat.getC();
		transform[4]=mat.getD();
		transform[5]=mat.getE();
		transform[6]=mat.getF();
		transform[8]=mat.getG();
		transform[9]=mat.getH();
		transform[10]=mat.getI();
		
		var x=-(transform[0]*transform[3]+transform[1]*transform[7]+transform[2]*transform[11]);
		var y=-(transform[4]*transform[3]+transform[5]*transform[7]+transform[6]*transform[11]);
		var z=-(transform[8]*transform[3]+transform[9]*transform[7]+transform[10]*transform[11]);
		
		transform[3]=x;
		transform[7]=y;
		transform[11]=z;

};	
	
	
SFGuidedSurface.prototype["getX"]=function(u,v){
	
		u*=this.maxTA;
		v*=this.maxTB;
		
		this.evalTransform(this.actualTransform, v);
		var vertex=new SFVertex3f();
		this.A.getVertex(u, vertex);
		var x=vertex.getX();
		var y=vertex.getY();
		var z=vertex.getZ();
		
		this.x=this.startingTransform[0]*x+this.startingTransform[1]*y+this.startingTransform[2]*z+this.startingTransform[3];
		this.y=this.startingTransform[4]*x+this.startingTransform[5]*y+this.startingTransform[6]*z+this.startingTransform[7];
		this.z=this.startingTransform[8]*x+this.startingTransform[9]*y+this.startingTransform[10]*z+this.startingTransform[11];
		
		return this.actualTransform[0]*this.x+this.actualTransform[1]*this.y+
				this.actualTransform[2]*this.z+this.actualTransform[3];
};


SFGuidedSurface.prototype["getY"]=function(u,v){
	
		return this.actualTransform[4]*this.x+this.actualTransform[5]*this.y+
				this.actualTransform[6]*this.z+this.actualTransform[7];
};

SFGuidedSurface.prototype["getZ"]=function(u,v){
	
		return this.actualTransform[8]*this.x+this.actualTransform[9]*this.y+
				this.actualTransform[10]*this.z+this.actualTransform[11];
};

	