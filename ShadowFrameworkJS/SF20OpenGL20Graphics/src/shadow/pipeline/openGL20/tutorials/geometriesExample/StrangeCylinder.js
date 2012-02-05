
function StrangeCylinder(){
}

StrangeCylinder.prototype = {

	generateGeometry:function(primitive){
		 CylinderFunction  function = new  CylinderFunction();
		 SFConcreteTriangleExtractor  trianglesExtractor = new  SFConcreteTriangleExtractor();
		 SFQuadsSurfaceGeometry  quadsSurfaceGeometry = new  SFQuadsSurfaceGeometry(primitive,				function, new ,TexCoordFunction(), trianglesExtractor, 3, 2);
		quadsSurfaceGeometry.compile();
		return ,quadsSurfaceGeometry;
	}

};