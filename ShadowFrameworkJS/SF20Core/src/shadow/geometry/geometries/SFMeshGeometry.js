
function SFMeshGeometry(){
}

SFMeshGeometry.prototype = {

	getFirstElement:function(){
		return ,firstElement;
	},

	getElementsCount:function(){
		return ,lastElement-firstElement;
	},

	setFirstElement:function(firstElement){
		this.firstElement  = firstElement;
	},

	getLastElement:function(){
		return ,lastElement;
	},

	setLastElement:function(lastElement){
		this.lastElement  = lastElement;
	},

	getArray:function(){
		return ,array;
	},

	drawGeometry:function(lod){
	//lod is still ignored				//this is much ok...		SFPipeline.getSfPipelineGraphics().drawPrimitives(array,firstElement,lastElement-firstElement);//Warning: Not well Identified 
	},

	generateNewDatasetInstance:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	getCode:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	getGeometricRegisters:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	getPrimitive:function(){
		return ,primitive;
	},

	getTessellator:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	readFromStream:function(stream){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	},

	writeOnStream:function(stream){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	},

	allocateBuffers:function(){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	},

	deallocateBuffers:function(){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	}

};