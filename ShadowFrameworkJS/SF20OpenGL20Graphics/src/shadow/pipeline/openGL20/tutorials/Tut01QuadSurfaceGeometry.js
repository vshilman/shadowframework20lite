

Tut01QuadSurfaceGeometry.prototype = {

	main:function(args){
	//Setup the SFPipeline to use an implementation based on OpenGL 2.0 		SFGL20Pipeline.setup();//Warning: Not well Identified 
	//Generate a Program attaching Program Components defined in the file ""data/pipeline/primitive""		SFPrimitive primitive=new SFPrimitive();//Warning: Not well Identified 
	SFProgram program;
	//Generate an SFMeshGeometry, which is a Mesh of Curved Primitives (the one defined as Triangle2 aka 2nd Order Bezier Triangle)		SFMeshGeometry quadsSurfaceGeometry = (new StrangeCylinder()).generateGeometry(primitive);//Warning: Not well Identified 
	//Generate a Materials Table, and 1 Material on it, assigning materials Data		SFStructureArray materialArray=SFTutorialsUtilities.generateMaterialData(program, 0, 0);//Warning: Not well Identified 
	SFStructureReference materialReference;
	//Generate a Lights Data Table, and 1 Light on it, assigning lights Data		SFStructureArray lightArray=SFTutorialsUtilities.generateLightData(program, 0);//Warning: Not well Identified 
	SFStructureReference lightReference;
	Tut01QuadSurfaceGeometry tut01CurvedTubeFunction;
	tut01CurvedTubeFunction.prepareFrame("Curved Tube Function", 600, 600);//Warning: Not well Identified 
	}

};