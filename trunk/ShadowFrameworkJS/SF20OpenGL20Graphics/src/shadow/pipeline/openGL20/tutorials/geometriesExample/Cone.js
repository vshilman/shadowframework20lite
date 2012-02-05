
function Cone(){
}

Cone.prototype = {

	generateGeometry:function(primitive){
		 ConeFunction  function = new  ConeFunction();
		 SFConcreteTriangleExtractor  trianglesExtractor = new  SFConcreteTriangleExtractor();
		 SFQuadsSurfaceGeometry  quadsSurfaceGeometry = new  SFQuadsSurfaceGeometry(primitive,				function, new ,TexCoordFunction(), trianglesExtractor, 5, 2);
		quadsSurfaceGeometry.compile();
		return ,quadsSurfaceGeometry;
	}

};