function Character(){
	
	this.id;
	this.position = new SFVertex3f();
	this.node = new SFObjectModel();	
	
}


Character.prototype.setID = function(id){
	
	this.id = id;
	
	
	
	
};


Character.prototype.getID = function(){
	
	return this.id;
	
};


Character.prototype.setPosition = function(position){
	
	this.position = position;
	if(this.node !== null){
		
		this.node.getTransform().setPosition(position);
		
	}
};

Character.prototype.getPosition = function(){
	
	return this.position;
	
};

Character.prototype.setNode = function(node){
	
	this.node = node;
	
};

Character.prototype.getNode = function(node){
	
	return this.node;
	
};


