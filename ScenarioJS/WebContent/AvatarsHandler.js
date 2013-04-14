function AvatarsHandler(){
	
	if ( AvatarsHandler.instance )
	    return AvatarsHandler.instance;

AvatarsHandler.instance = this;
	this.charList = [];
	
}




AvatarsHandler.prototype.setStateAt = function(id, position, direction){

var charfound = false;	
	
for(var i = 0; i < this.charList.length; i++){
		
		if(this.charList[i].id == id){
			
			this.charList[i].setState(position, direction);
			charfound = true;
			break;
		}
		
	}

	if(charfound == false){
		
		var obj = new CharacterDef();
		obj.setID(id);
		obj.setState(position, direction);
		obj.setNode(generateModelAtPosition("Dummy01", position, direction));
		this.charList.push(obj);
		
		
	}
	
};



AvatarsHandler.prototype.removeChar = function(id){
	
	for(var i = 0; i < this.charList.length; i++){
		
		if(this.charList[i].id == id){
			
			this.charList.splice(i,1);
			break;
		}
		
	}
	
};


