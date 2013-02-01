function MyCharModel(){
	
	
	if (MyCharModel.instance)
	    return MyCharModel.instance;

    MyCharModel.instance = this;
    this.myID;
	this.myPosition = new SFVertex3f();

	
}


MyCharModel.prototype.setMyID = function(id){
		
	this.myID = id;
	
};
MyCharModel.prototype.setMyPosition = function(pos){
	
		
	this.myPosition = pos;
	
};

MyCharModel.prototype.getMyID = function(){
	
	return this.myID;
	
	
};

MyCharModel.prototype.getMyPosition = function(){
	
		
	return this.myPosition;
	
};