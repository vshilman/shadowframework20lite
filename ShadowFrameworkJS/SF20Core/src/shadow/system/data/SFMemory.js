
function SFMemory(){
}

SFMemory.prototype = {

	getMemory:function(){
		return ,memory;
	},

	loadList:function(name, list){
		lists.put(name, list);
	},

	retrieveIdentifier:function(list, identifier){
		return ,lists.get(list).getElement(identifier);
	}

};