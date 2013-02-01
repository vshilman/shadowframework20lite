function CharHandler(){
	
	if ( CharHandler.instance )
	    return CharHandler.instance;

CharHandler.instance = this;
	this.charList = [];
	
}

CharHandler.prototype.setPositionAt = function(id, position){

var charfound = false;	
	
for(var i = 0; i < this.charList.length; i++){
		
		if(this.charList[i].id == id){
			
			this.charList[i].setPosition(position);
			charfound = true;
			break;
		}
		
	}

	if(charfound == false){
		
		var obj = new Character();
		obj.setID(id);
		obj.setPosition(position);
		obj.setNode(generateModelAtPosition("Mushroom", position));
		this.charList.push(obj);
		
		
	}
	
};



CharHandler.prototype.removeChar = function(id){
	
	for(var i = 0; i < this.charList.length; i++){
		
		if(this.charList[i].id == id){
			
			this.charList.splice(i,1);
			break;
		}
		
	}
	
};


