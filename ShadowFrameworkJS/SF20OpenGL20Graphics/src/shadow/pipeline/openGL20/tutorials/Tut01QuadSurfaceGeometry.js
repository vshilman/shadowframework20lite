
function Tut01QuadSurfaceGeometry(){
}

Tut01QuadSurfaceGeometry.prototype = {

	main:function(args){
	//Setup the SFPipeline to use an implementation based on OpenGL 2.0 		SFGL20Pipeline.setup();//Warning: Not well Identified 
	//Generate a Program attaching Program Components defined in the file ""data/pipeline/primitive""		SFPrimitive primitive=new SFPrimitive();//Warning: Not well Identified 
		 String[] materials={
		"BasicMat";
	}
		 SFProgram  program = SFTutorialsUtilities.generateProgram("data/pipeline/primitive", materials, 				"BasicLSPN", primitive, "Triangle2", "Triangle2", "BasicTess");
	//Generate an SFMeshGeometry, which is a Mesh of Curved Primitives (the one defined as Triangle2 aka 2nd Order Bezier Triangle)		SFMeshGeometry quadsSurfaceGeometry = (new StrangeCylinder()).generateGeometry(primitive);//Warning: Not well Identified 
	//Generate a Materials Table, and 1 Material on it, assigning materials Data		SFStructureArray materialArray=SFTutorialsUtilities.generateMaterialData(program, 0, 0);//Warning: Not well Identified 
		 SFVertex3f[] materialData={
		new ,SFVertex3f(1,0,0),new ,SFVertex3f(0.1f,0.1f,0.1f);
	}
		 SFStructureReference  materialReference = SFTutorialsUtilities.generateStructureDataReference(program, materialArray, materialData);
	//Generate a Lights Data Table, and 1 Light on it, assigning lights Data		SFStructureArray lightArray=SFTutorialsUtilities.generateLightData(program, 0);//Warning: Not well Identified 
		 SFVertex3f[] lightData={
		new ,SFVertex3f(1, 0, 1),new ,SFVertex3f(1, 1, 1);
	}
		 SFStructureReference  lightReference = SFTutorialsUtilities.generateStructureDataReference(program, lightArray, lightData);
		 Tut01QuadSurfaceGeometry  tut01CurvedTubeFunction = new  Tut01QuadSurfaceGeometry(program, 				lightArray, lightReference, 				materialArray, materialReference,				quadsSurfaceGeometry.getArray(), quadsSurfaceGeometry.getFirstElement(), quadsSurfaceGeometry.getElementsCount());
		tut01CurvedTubeFunction.prepareFrame("Curved Tube Function", 600, 600);
	}

};