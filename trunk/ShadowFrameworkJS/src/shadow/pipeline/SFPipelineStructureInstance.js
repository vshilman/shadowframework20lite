//Java to JS on 21/03/2012

function SFPipelineStructureInstance(structure,parameters){
	this.structure=structure;
	this.parameters=parameters;
}

SFPipelineStructureInstance.prototype["getStructure"]=function(){
			return this.structure;
		};

SFPipelineStructureInstance.prototype["getParameters"]=function(){
			return this.parameters;
		};

SFPipelineStructureInstance.prototype["getAllParameters"]=function(){
			return this.parameters;
		};

SFPipelineStructureInstance.prototype["size"]=function(){
			return this.structure.size;
		};
	