

SFTutorialsUtilities.prototype = {

	generateMaterialData:function(program, materialIndex, structureIndex){
	//note it supposo to have only one structure..		SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getMaterial(materialIndex).getStructures())).get(structureIndex);//Warning: Not well Identified 
	SFStructureArray materialData;
		return this.materialData;
	},

	generateLightData:function(program, structureIndex){
	//note it supposo to have only one structure..		SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getLightStep().getStructures())).get(structureIndex);//Warning: Not well Identified 
	SFStructureArray materialData;
		return this.materialData;
	},

	generateStructureDataReference:function(program, materialData, data){
	SFPipelineStructureInstance materialStructureInstance;
	SFStructureReference materialReference;
	SFStructureData mat;
		return this.materialReference;
	}

};