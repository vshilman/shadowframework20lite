
function SFStructuresTable(){
}

SFStructuresTable.prototype = {

	allocateBuffers:function(){
		array  = SFPipeline.getSfPipelineMemory().generateStructureData(structure);
	},

	getDataArray:function(){
		return ,array;
	},

	deallocateBuffers:function(){
	// TODO Deallocation is still not supported;//Warning: Not well Identified 
	},

	readFromStream:function(stream){
	//TODO		//structure=stream.readString();//Warning: Not well Identified 
	},

	writeOnStream:function(stream){
	//TODO		//stream.writeString(structure);//Warning: Not well Identified 
	},

	generateNewDatasetInstance:function(){
		return ,new ,SFStructuresTable();
	},

	getCode:function(){
		return ,getClass().getSimpleName();
	}

};