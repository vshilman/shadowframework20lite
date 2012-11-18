



function SFRigidTransform2f(x,y,z){
	this.v=new Array();
	for(var i=0;i<6;i++){
		this.v[i]=0;
	}
}

inherit(SFRigidTransform2f,SFValuenf);

SFRigidTransform2f.prototype["cloneValue"]=function(){
	var transform=new SFRigidTransform2f();
	transform.set(this);
	return transform;
};

SFRigidTransform2f.prototype["setPosition"]=function(x,y){
	this.v[4]=x;
	this.v[5]=y;
};

SFRigidTransform2f.prototype["setMatrix"]=function(a,b,c,d){
	this.v[0]=a;
	this.v[1]=b;
	this.v[2]=c;
	this.v[3]=d;
};	

SFRigidTransform2f.prototype["setMatrix"]=function(matrix){
	for (var i = 0; i < matrix.length; i++) {
		this.v[i]=matrix.v[i];
	}
};
	
SFRigidTransform2f.prototype["setPosition"]=function(position){
	for (var i = 0; i < position.v.length; i++) {
		this.v[i+4]=position.v[i];
	}
};	

SFRigidTransform2f.prototype["getMatrix"]=function(matrix){
	for (var i = 0; i < matrix.length; i++) {
		matrix.v[i]=this.v[i];
	}
};	

SFRigidTransform2f.prototype["getPosition"]=function(position){
	for (var i = 0; i < position.v.length; i++) {
		position.v[i]=this.v[i+4];
	}
};	
