
function SFTutorialsUtilities(){
}

SFTutorialsUtilities.prototype = {

	generateImageProgram:function(filename, materials, lightStep){
		 SFProgram  program = null;
		try{
		SFProgramComponentLoader.loadComponents(new File(filename));
		program  = SFPipeline.getStaticImageProgram(materials, lightStep);
	}
		catch (IOException e){
		e.printStackTrace();
	}
		catch (SFPipelineModuleWrongException e){
		e.printStackTrace();
	}
		return ,program;
	},

	generateMaterialData:function(program, materialIndex, structureIndex){
	//note it supposo to have only one structure..		SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getMaterial(materialIndex).getStructures())).get(structureIndex);//Warning: Not well Identified 
		 SFStructureArray  materialData = SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance);
		return ,materialData;
	},

	generateLightData:function(program, structureIndex){
	//note it supposo to have only one structure..		SFPipelineStructureInstance materialStructureInstance=((List<SFPipelineStructureInstance>)(program.getLightStep().getStructures())).get(structureIndex);//Warning: Not well Identified 
		 SFStructureArray  materialData = SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance);
		return ,materialData;
	},

	generateStructureDataReference:function(program, materialData, data){
		 SFPipelineStructureInstance  materialStructureInstance = materialData.getPipelineStructure();
		 SFStructureReference  materialReference = new  SFStructureReference(materialData);
		 SFStructureData  mat = new  SFStructureData(materialStructureInstance);
		for ( int  i  =  0 ; i   < data.length ; i++ ){
	((SFVertex3f)mat.getValue(i)).set(data[i]);//Warning: Not well Identified 
	}
		try{
		materialReference.setStructureData(mat);
	}
		catch (SFArrayElementException e){
		e.printStackTrace();
	}
		return ,materialReference;
	}

};