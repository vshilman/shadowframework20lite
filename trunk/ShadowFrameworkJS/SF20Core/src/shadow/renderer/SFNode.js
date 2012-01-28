

SFNode.prototype = {

	getCode:function(){
	throw new RuntimeException("not implemented");//Warning: Not well Identified 
	},

	readFromStream:function(stream){
	throw new RuntimeException("not implemented");//Warning: Not well Identified 
	},

	writeOnStream:function(stream){
	throw new RuntimeException("not implemented");//Warning: Not well Identified 
	},

	generateNewDatasetInstance:function(){
	return new SFNode();//Warning: Not well Identified 
	}

};