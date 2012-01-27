

SFNode.prototype = {

	addNode:function(node){
	nodes.add(node);//Warning: Not well Identified 
	},

	getSonsNodes:function(){
		return this.nodes;
	},

	getMaterials:function(){
		return this.material;
	},

	addMaterial:function(material){
	this.material.add(material);//Warning: Not well Identified 
	},

	getRootGeometry:function(){
		return this.rootGeometry;
	},

	setRootGeometry:function(rootGeometry){
		this.rootGeometry=rootGeometry;
	},

	getProgram:function(index, step){
	String[] materialsName=new String[material.size()];//Warning: Not well Identified 
	//TODO: its missing its Primitive.		SFProgram program=SFPipeline.getStaticProgram(rootGeometry.getPrimitive()				,materialsName,step.getProgramName());//Warning: Not well Identified 
	programs.add(program);//Warning: Not well Identified 
		return this.program;
	},

	cleanPrograms:function(){
	programs.clear();//Warning: Not well Identified 
	}

};