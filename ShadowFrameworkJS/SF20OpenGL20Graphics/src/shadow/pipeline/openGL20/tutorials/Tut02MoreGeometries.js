

Tut02MoreGeometries.prototype = {

	main:function(args){
	SFGL20Pipeline.setup();//Warning: Not well Identified 
	SFPrimitive primitive;
	SFProgram program;
	geometries=generateGeometries(primitive);//Warning: Not well Identified 
	SFStructureArray materialArray;
	SFStructureReference materialReference;
	SFStructureArray lightArray;
	SFStructureReference lightReference;
	Tut02MoreGeometries tut01CurvedTubeFunction=new Tut02MoreGeometries(program, 				lightArray, lightReference, 				materialArray, materialReference,				geometries[0].getArray(), geometries[0].getFirstElement(),geometries[0].getElementsCount());//Warning: Not well Identified 
	tut01CurvedTubeFunction.prepareFrame("Curved Tube Function", 600, 600);//Warning: Not well Identified 
	},

	generateGeometries:function(primitive){
		return this.geometries;
	}

};