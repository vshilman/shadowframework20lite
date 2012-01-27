

SFParsableGrid.prototype = {

	loadName:function(vertex){
	if(verticesLoading.get(vertex)==null)			verticesLoading.put(vertex,(short)verticesLoading.size());//Warning: Not well Identified 
	return verticesLoading.get(vertex);//Warning: Not well Identified 
	},

	loadVertex:function(vertex){
	cornersLoading.add(loadName(vertex));//Warning: Not well Identified 
	},

	loadEdge:function(vertex){
	edgesLoading.add(getPath(vertex));//Warning: Not well Identified 
	},

	loadPath:function(vertex){
	pathsLoading.add(getPath(vertex));//Warning: Not well Identified 
	},

	loadInternal:function(vertex){
	},

	getPath:function(vertex){
	ArrayList<Short> path;
		return this.path;
	}

};