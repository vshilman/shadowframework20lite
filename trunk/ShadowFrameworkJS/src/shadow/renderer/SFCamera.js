
function SFCamera(focus,dir,left,up,leftL,upL,distance){
	this.F=new SFVertex3f();
	this.Dir=new SFVertex3f();
	this.Up=new SFVertex3f();
	this.Left=new SFVertex3f();
	this.leftL=0.3;
	this.upL=0.3;
	this.distance=0;
	this.delta=1;
	this.isPerspective=false;
	if(!(focus==undefined)){
		this.F.setValue(focus);
	}
	if(!(dir==undefined)){
		this.Dir.setValue(dir);
	}
	if(!(up==undefined)){
		this.Up.setValue(up);
	}
	if(!(left==undefined)){
		this.Left.setValue(left);
	}
	if(!(leftL==undefined)){
		this.leftL=leftL;
	}
	if(!(upL==undefined)){
		this.upL=upL;
	}
	if(!(distance==undefined)){
		this.distance=distance;
	}
	this.update();
}
	

SFCamera.prototype["init"]=function(){
};

SFCamera.prototype["destroy"]=function(){
};
	

SFCamera.prototype["getDir"]=function(){
	return this.Dir;
};

SFCamera.prototype["setDir"]=function(dir){
	this.Dir=dir;
};

SFCamera.prototype["update"]=function(){
	this.getLeft().normalize3f();
	this.getUp().normalize3f();
	this.getLeft().mult(this.getLeftL());
	this.getUp().mult(this.getUpL());
	this.getDir().normalize3f();
};


SFCamera.prototype["isPerspective"]=function(){
	return this.isPerspective;
};

SFCamera.prototype["setPerspective"]=function(isPerspective){
	this.isPerspective=isPerspective;
};

SFCamera.prototype["extractTransform"]=function(){

	var matrix=new Array;
	
	var mat=new SFMatrix3f(
			this.getLeft().getX(),this.getUp().getX(),this.getDir().getX(),
			this.getLeft().getY(),this.getUp().getY(),this.getDir().getY(),
			this.getLeft().getZ(),this.getUp().getZ(),this.getDir().getZ()
	);

	//setDelta((float)Math.sqrt(getDir().dot3f(getDir())));
	mat=mat.getInverse(mat);
	
	matrix[0]=mat.getA();
	matrix[1]=mat.getD();
	matrix[2]=mat.getG()*this.getDelta();
	matrix[3]=0;
	
	matrix[4]=mat.getB();
	matrix[5]=mat.getE();
	matrix[6]=mat.getH()*this.getDelta();
	matrix[7]=0;
	
	matrix[8]=mat.getC();
	matrix[9]=mat.getF();
	matrix[10]=mat.getI()*this.getDelta();
	matrix[11]=0;
	
	matrix[12]=-(matrix[0]*this.getF().getX()+matrix[4]*this.getF().getY()+matrix[8]*this.getF().getZ());
	matrix[13]=-(matrix[1]*this.getF().getX()+matrix[5]*this.getF().getY()+matrix[9]*this.getF().getZ());
	matrix[14]=-(matrix[2]*this.getF().getX()+matrix[6]*this.getF().getY()+matrix[10]*this.getF().getZ());
	matrix[15]=1;
	
	if(this.isPerspective){
		
		var al=(this.getDistance()+this.getDelta())/(this.getDistance()-this.getDelta());
		var bl=(-2*this.getDistance()*this.getDelta())/(this.getDistance()-this.getDelta());
	
		matrix[0]=this.getDelta()*matrix[0];
		
		matrix[1]=this.getDelta()*matrix[1];
		matrix[3]=matrix[2];
		matrix[2]=al*matrix[2];
		
		matrix[4]=this.getDelta()*matrix[4];
		matrix[5]=this.getDelta()*matrix[5];
		matrix[7]=matrix[6];
		matrix[6]=al*matrix[6];

		matrix[8]=this.getDelta()*matrix[8];
		matrix[9]=this.getDelta()*matrix[9];
		matrix[11]=matrix[10];
		matrix[10]=al*matrix[10];

		matrix[12]=this.getDelta()*matrix[12];
		matrix[13]=this.getDelta()*matrix[13];
		matrix[15]=matrix[14];
		matrix[14]=al*matrix[14]+bl;

	}
	return matrix;
};


SFCamera.prototype["getWorldPosition"]=function(cameraPosition){

	var position=new SFVertex3f(this.getF());
	position.addMult(this.getDistance()+cameraPosition.getZ(),this.getDir());
	position.addMult((this.getLeftL()*cameraPosition.getX()),this.getLeft());
	position.addMult((this.getUpL()*cameraPosition.getY()),this.getUp());
	
	return position;
};

SFCamera.prototype["getWorldRotation"]=function(matrixOrientation){

	var cameraMatrix=new SFMatrix3f(
			this.getLeft().getX(),this.getUp().getX(),this.getDir().getX(),
			this.getLeft().getY(),this.getUp().getY(),this.getDir().getY(),
			this.getLeft().getZ(),this.getUp().getZ(),this.getDir().getZ()
		);

	return SFMatrix3f_getTransposed(cameraMatrix).Mult(matrixOrientation.Mult(cameraMatrix));
};


SFCamera.prototype["getF"]=function(){
	return this.F;
};

SFCamera.prototype["setF"]=function(F){
	this.F=F;
};

SFCamera.prototype["getUp"]=function(){
	return this.Up;
};

SFCamera.prototype["setUp"]=function(Up){
	this.Up=Up;
};


SFCamera.prototype["getLeft"]=function(){
	return this.Left;
};

SFCamera.prototype["setLeft"]=function(Left){
	this.Left=Left;
};

SFCamera.prototype["getLeftL"]=function(){
	return this.leftL;
};

SFCamera.prototype["setLeftL"]=function(leftL){
	this.leftL=leftL;
};

SFCamera.prototype["getUpL"]=function(){
	return this.upL;
};

SFCamera.prototype["setUpL"]=function(upL){
	this.upL=upL;
};

SFCamera.prototype["getDistance"]=function(){
	return this.distance;
};

SFCamera.prototype["setDistance"]=function(distance){
	this.distance=distance;
};


SFCamera.prototype["getDelta"]=function(){
	return this.delta;
};

SFCamera.prototype["setDelta"]=function(delta){
	this.delta=delta;
};


SFCamera.prototype["setupDimensions"]=function(leftL,upL){
	this.setLeftL(leftL);
	this.setUpL(upL);
	this.update();
};


