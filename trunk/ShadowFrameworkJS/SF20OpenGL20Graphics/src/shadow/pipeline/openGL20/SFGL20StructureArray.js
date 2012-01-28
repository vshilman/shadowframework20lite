
function SFGL20StructureArray(structure){
		this.structure=structure;
}

SFGL20StructureArray.prototype = {

	generateGenericElement:function(){
	return new SFStructureData(structure);//Warning: Not well Identified 
	},

	getPipelineStructure:function(){
		return this.structure;
	}

};