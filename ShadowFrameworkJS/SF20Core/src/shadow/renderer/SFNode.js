
function SFNode(){
}

SFNode.prototype = {

	addNode:function(node){
		nodes.add(node);
	},

	getSonsNodes:function(){
		return ,nodes;
	},

	getCode:function(){
		throw ,new ,RuntimeException("not implemented");
	},

	readFromStream:function(stream){
		throw ,new ,RuntimeException("not implemented");
	},

	writeOnStream:function(stream){
		throw ,new ,RuntimeException("not implemented");
	},

	getMaterials:function(){
		return ,material;
	},

	addMaterial:function(material){
		this.material.add(material);
	},

	getRootGeometry:function(){
		return ,rootGeometry;
	},

	setRootGeometry:function(rootGeometry){
		this.rootGeometry    = rootGeometry;
	},

	generateNewDatasetInstance:function(){
		return ,new ,SFNode();
	},

	getProgram:function(index, step){
	//if(programs.size()>index);//Warning: Not well Identified 
		return ,programs.get(index);
	//}
	String[] materialsName=new String[material.size()];//Warning: Not well Identified 
		for ( int  i=0 ; i   < materialsName.length ; i++ ){
		materialsName[i]  = material.get(i).getStructure().getName();
	}
	//TODO: its missing its Primitive.		SFProgram program=SFPipeline.getStaticProgram(rootGeometry.getPrimitive()				,materialsName,step.getProgramName());//Warning: Not well Identified 
		programs.add(program);
		return ,program;
	},

	cleanPrograms:function(){
		programs.clear();
		//for (Iterator<SFNode> iterator=nodes.iterator();iterator.hasNext(););//Warning: Not well Identified 
			 SFNode  node = (SFNode) iterator.next();
			node.cleanPrograms();
		//}
	}

};