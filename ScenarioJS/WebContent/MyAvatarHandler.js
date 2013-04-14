function MyAvatarHandler(){
	
	
	if (MyAvatarHandler.instance)
	    return MyAvatarHandler.instance;

    MyAvatarHandler.instance = this;
    this.myID;
	this.myPosition = new SFVertex3f();
	this.myDirection = new SFMatrix3f();
	this.node = new SFObjectModel();	


	
}
MyAvatarHandler.prototype.setMyState = function(position, direction){
	
	this.myPosition = position;
	this.myDirection = direction;
if(this.node !== null){
		
		this.node.getTransform().setPosition(position);
		this.node.getTransform().setOrientation(direction);
	}
	
};

MyAvatarHandler.prototype.setMyNode = function(node){
	
	this.node = node;
	
};

MyAvatarHandler.prototype.setMyID = function(id){
		
	this.myID = id;
	
};
MyAvatarHandler.prototype.setMyPosition = function(pos){
	
		
	this.myPosition = pos;
	this.node.getTransform().setPosition(pos);

	
};
MyAvatarHandler.prototype.setMyDirection = function(dir){
	
	
	this.myDirection = dir;
	this.node.getTransform().setOrientation(dir);

};

MyAvatarHandler.prototype.getMyID = function(){
	
	return this.myID;
	
	
};



MyAvatarHandler.prototype.getMyPosition = function(){
	
		
	return this.myPosition;
	
};


MyAvatarHandler.prototype.getMyDirection = function(){
	
	
	return this.myDirection;
	
};
