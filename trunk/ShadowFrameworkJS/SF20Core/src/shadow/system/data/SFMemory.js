
function SFMemory(){
}

SFMemory.prototype = {

	getMemory:function(){
		return this.memory;
	},

	loadList:function(name, list){
	lists.put(name, list);//Warning: Not well Identified 
	},

	retrieveIdentifier:function(list, identifier){
	return lists.get(list).getElement(identifier);//Warning: Not well Identified 
	}

};