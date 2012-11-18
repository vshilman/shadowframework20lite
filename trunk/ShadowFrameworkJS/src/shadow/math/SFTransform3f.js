
function SFTransform3f(){
	this.v=new Array();
	for(var i=0;i<12;i++)
		this.v[i]=0;
	this.v[0]=1;
	this.v[4]=1;
	this.v[8]=1;
}

inherit(SFTransform3f,SFValuenf);

SFTransform3f.prototype["cloneValue"]=function(){
	var transform = new SFTransform3f();
	transform.set(this);
	return transform;
};


		
SFTransform3f.prototype["setPosition"]=function(x,y,z){
	this.v[9] = x;
	this.v[10] = y;
	this.v[11] = z;
};	


SFTransform3f.prototype["setMatrix"]=function(a,b,c,d,e,f,g,h,i){
	this.v[0] = a;
	this.v[1] = b;
	this.v[2] = c;
	this.v[3] = d;
	this.v[4] = e;
	this.v[5] = f;
	this.v[6] = g;
	this.v[7] = h;
	this.v[8] = i;
};



SFTransform3f.prototype["setMatrix"]=function(matrix){
	for(var i=0;i<matrix.v.length;i++){
		this.v[i] = matrix.v[i];
	}
};

SFTransform3f.prototype["setPosition"]=function(position){
	for(var i=0;i<position.v.length;i++){
		this.v[i + 9] = position.v[i];
	}
};

SFTransform3f.prototype["getMatrix"]=function(matrix){
	for (var i = 0; i < matrix.v.length; i++) {
		matrix.v[i] = this.v[i];
	}
};

SFTransform3f.prototype["getPosition"]=function(position){
	for (var i = 0; i < position.v.length; i++) {
		position.v[i] = this.v[i+9];
	}
};


SFTransform3f.prototype["transform"]=function(position){
	var v=this.v;
	var x=position.get()[0];
	var y=position.get()[1];
	var z=position.get()[2];
	position.get()[0]=x*v[0]+y*v[1]+z*v[2]+v[9];
	position.get()[1]=x*v[3]+y*v[4]+z*v[5]+v[10];
	position.get()[2]=x*v[6]+y*v[7]+z*v[8]+v[11];
};


SFTransform3f.prototype["transformDir"]=function(dir){
	var v=this.v;
	var x=dir.get()[0];
	var y=dir.get()[1];
	var z=dir.get()[2];
	dir.get()[0]=x*v[0]+y*v[1]+z*v[2];
	dir.get()[1]=x*v[3]+y*v[4]+z*v[5];
	dir.get()[2]=x*v[6]+y*v[7]+z*v[8];
};



var SFTransform3f_multTmpVal=new Array();

SFTransform3f.prototype["mult"]=function(transform){
	var val=transform.v;
	var v=this.v;
		
	SFTransform3f_multTmpVal[0]=v[0]*val[0]+v[1]*val[3]+v[2]*val[6];
	SFTransform3f_multTmpVal[1]=v[0]*val[1]+v[1]*val[4]+v[2]*val[7];
	SFTransform3f_multTmpVal[2]=v[0]*val[2]+v[1]*val[5]+v[2]*val[8];
	
	SFTransform3f_multTmpVal[3]=v[3]*val[0]+v[4]*val[3]+v[5]*val[6];
	SFTransform3f_multTmpVal[4]=v[3]*val[1]+v[4]*val[4]+v[5]*val[7];
	SFTransform3f_multTmpVal[5]=v[3]*val[2]+v[4]*val[5]+v[5]*val[8];
	
	SFTransform3f_multTmpVal[6]=v[6]*val[0]+v[7]*val[3]+v[8]*val[6];
	SFTransform3f_multTmpVal[7]=v[6]*val[1]+v[7]*val[4]+v[8]*val[7];
	SFTransform3f_multTmpVal[8]=v[6]*val[2]+v[7]*val[5]+v[8]*val[8];
	
	SFTransform3f_multTmpVal[9]=v[0]*val[9]+v[1]*val[10]+v[2]*val[11]+v[9];
	SFTransform3f_multTmpVal[10]=v[3]*val[9]+v[4]*val[10]+v[5]*val[11]+v[10];
	SFTransform3f_multTmpVal[11]=v[6]*val[9]+v[7]*val[10]+v[8]*val[11]+v[11];
	
	for (var i = 0; i < SFTransform3f_multTmpVal.length; i++) {
		this.v[i]=SFTransform3f_multTmpVal[i];
	}
};


SFTransform3f.prototype["init"]=function(){
	
};


SFTransform3f.prototype["destroy"]=function(){
	
};
