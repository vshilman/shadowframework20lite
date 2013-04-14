
function SFGL20PipelineMemory(){

}

SFGL20PipelineMemory.prototype["generatePrimitiveArray"]=function(primitive){
		return new SFGL20PrimitiveArray(primitive);
	};

SFGL20PipelineMemory.prototype["generateStructureData"]=function(structure){
		return new SFGL20StructureArray(structure);
	};

SFGL20PipelineMemory.prototype["generateRigidTransformsArray"]=function(){
		return new SFGL20RigidTransforms3fArray();
	};

SFGL20PipelineMemory.prototype["generatePrimitiveArrayView"]=function(array,primitive){
		return array.getView(primitive);
	};

SFGL20PipelineMemory.prototype["getMixArray"]=function(arrays,mixPrimitive){
		return SFGL20PrimitiveArray_mixArrays(arrays,mixPrimitive);
	};
	
SFGL20PipelineMemory.prototype["destroyPrimitiveArray"]=function(array){
		//Do nothing, this is js, GC will do that
	};
	
	
SFGL20PipelineMemory.prototype["destroyRigidTransformsArray"]=function(array){
		//Do nothing, this is js, GC will do that
	};
	
SFGL20PipelineMemory.prototype["destroyStructureData"]=function(array){
		//Do nothing, this is js, GC will do that
	};
	