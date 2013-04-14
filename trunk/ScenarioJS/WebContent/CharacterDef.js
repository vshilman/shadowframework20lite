function CharacterDef(){
	
	this.id;
	this.position = new SFVertex3f();
	this.direction = new SFMatrix3f();
	this.node = new SFObjectModel();	
	
}


CharacterDef.prototype.setID = function(id){
	
	this.id = id;
	
	
	
	
};


CharacterDef.prototype.getID = function(){
	
	return this.id;
	
};


CharacterDef.prototype.setState = function(position, direction){
	
	this.position = position;
	this.direction = direction;
	if(this.node !== null){
		
		this.node.getTransform().setPosition(position);
		this.node.getTransform().setOrientation(direction);
	}
};

CharacterDef.prototype.getPosition = function(){
	
	return this.position;
	
};

CharacterDef.prototype.getDirection = function(){
	
	return this.direction;
	
};

CharacterDef.prototype.setNode = function(node){
	
	this.node = node;
	
};

CharacterDef.prototype.getNode = function(node){
	
	return this.node;
	
};


