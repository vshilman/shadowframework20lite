
function Tut02MoreGeometries(){
}

Tut02MoreGeometries.prototype = {

	main:function(args){
		SFGL20Pipeline.setup();
		 SFPrimitive  primitive = new  SFPrimitive();
		 String[] materials={
		"BasicMat";
	}
		 SFProgram  program = SFTutorialsUtilities.generateProgram("data/pipeline/primitive", materials, 				"BasicLSPN", primitive, "Triangle2", "Triangle2", "BasicTess");
		geometries  = generateGeometries(primitive);
		 SFStructureArray  materialArray = SFTutorialsUtilities.generateMaterialData(program, 0, 0);
		 SFVertex3f[] materialData={
		new ,SFVertex3f(1,1,0),new ,SFVertex3f(0.1f,0.1f,0.1f);
	}
		 SFStructureReference  materialReference = SFTutorialsUtilities.generateStructureDataReference(program, materialArray, materialData);
		 SFStructureArray  lightArray = SFTutorialsUtilities.generateLightData(program, 0);
		 SFVertex3f[] lightData={
		new ,SFVertex3f(2, 1, 1),new ,SFVertex3f(1, 1, -1);
	}
		 SFStructureReference  lightReference = SFTutorialsUtilities.generateStructureDataReference(program, lightArray, lightData);
		 Tut02MoreGeometries  tut01CurvedTubeFunction = new  Tut02MoreGeometries(program, 				lightArray, lightReference, 				materialArray, materialReference,				geometries[0].getArray(), geometries[0].getFirstElement(),geometries[0].getElementsCount());
		tut01CurvedTubeFunction.prepareFrame("Curved Tube Function", 600, 600);
	},

	generateGeometries:function(primitive){
		 SFMeshGeometry[] geometries={
	(new StrangeCylinder()).generateGeometry(primitive),				(new Cone()).generateGeometry(primitive),				(new LateralTube()).generateGeometry(primitive),				(new StrangeGlass()).generateGeometry(primitive),				(new Mushroom()).generateGeometry(primitive);//Warning: Not well Identified 
	}
		return ,geometries;
	},

	keyPressed:function(e){
		super.keyPressed(e);
	//if(e.getKeyCode()==KeyEvent.VK_PLUS);//Warning: Not well Identified 
		geometriesIndex++;
		 if ( geometriesIndex>=geometries.length ){
		geometriesIndex  = 0;
	}
		setGeometry(geometries[geometriesIndex]);
	//}
	//if(e.getKeyCode()==KeyEvent.VK_MINUS);//Warning: Not well Identified 
		geometriesIndex--;
	//if(geometriesIndex<0);//Warning: Not well Identified 
		geometriesIndex  = geometries.length-1;
	//}
		setGeometry(geometries[geometriesIndex]);
	//}
	}

};