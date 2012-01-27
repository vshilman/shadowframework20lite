

SFPipelineGrid.prototype = {

	getNames:function(){
		return this.names;
	},

	setNames:function(names){
		this.names=names;
	},

	getCorners:function(){
		return this.corners;
	},

	setCorners:function(corners){
		this.corners=corners;
	},

	getEdges:function(){
		return this.edges;
	},

	setEdges:function(edges){
		this.edges=edges;
	},

	getPaths:function(){
		return this.paths;
	},

	setPaths:function(paths){
		this.paths=paths;
	},

	size:function(){
	return names.length;//Warning: Not well Identified 
	}

};