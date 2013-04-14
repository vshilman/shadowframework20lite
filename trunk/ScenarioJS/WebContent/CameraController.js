function CameraController(camera){
	
	this.camera = camera;
	this.myAvatar = new MyAvatarHandler();
	
	
}

CameraController.prototype.Update = function(event){
	
	var myAvatar = new MyAvatarHandler();
	
	
	switch(event.keyCode){
	
	
	case 38:    // up
		var position = new SFVertex3f();
		this.camera.getF().addMult(0.1, this.camera.getDir());	
		this.camera.update();
		console.log(this.camera.extractTransform());
		position.setX(this.camera.getF().getX());
		position.setY(this.camera.getF().getY());
		position.setZ(this.camera.getF().getZ());
		myAvatar.setMyPosition(position);
		
	break;
	
	case 40:   // down
		var position = new SFVertex3f();
		this.camera.getF().addMult(-0.1, this.camera.getDir());	
		this.camera.update();
		position.setX(this.camera.getF().getX());
		position.setY(this.camera.getF().getY());
		position.setZ(this.camera.getF().getZ());
		myAvatar.setMyPosition(position);
	     break;
	
	case 39:
		
		var matrix = new SFMatrix3f;
		matrix = matrix.getRotationY(Math.PI*0.01);
		myAvatar.setMyDirection(matrix);
	    this.camera.setDir( matrix.Mult(this.camera.getDir()) ); 
		this.camera.setLeft(matrix.Mult(this.camera.getLeft()));
	    this.camera.update();
	    break;
	
	case 37:
		
		var matrix = new SFMatrix3f;
		matrix = matrix.getRotationY(-Math.PI*0.01);
		myAvatar.setMyDirection(matrix);

	    this.camera.setDir( matrix.Mult(this.camera.getDir()) ); 
		this.camera.setLeft(matrix.Mult(this.camera.getLeft()));
	    this.camera.update();	
	
	
	}
	
	
		
	
	
};


CameraController.prototype.initCamera = function(){
	
	

	this.camera.setPerspective(true);
   	this.camera.setLeft(new SFVertex3f(1,0,0));
   	this.camera.setDir(new SFVertex3f(0.0,0.0,1.0));
	this.camera.setUp(new SFVertex3f(0,1,0));
    this.camera.setLeftL(0.3);
	this.camera.setUpL(0.3);
	
	var initPos = this.myAvatar.getMyPosition();
	var x = initPos.getX();
	var y = initPos.getY();
	var z = initPos.getZ();
	this.camera.setF(new SFVertex3f(x, 1, z));
	this.camera.setDelta(1);
	this.camera.setDistance(200);
    this.camera.update();
	
};