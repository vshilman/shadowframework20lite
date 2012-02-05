
function LateralTube(){
}

LateralTube.prototype = {

	generateGeometry:function(primitive){
		 SFCurvedTubeFunction  function = new  SFCurvedTubeFunction();
	//Primo Pezzo		function.Cc.add(new SFVertex3f(0,0,0));//Warning: Not well Identified 
		function.Cc.add(new SFVertex3f(0.0f,0.1f,0));
		function.Cc.add(new SFVertex3f(0.0f,0.2f,0));
		function.Cc.add(new SFVertex3f(0.0f,0.3f,0));
		function.Cc.add(new SFVertex3f(0.0f,0.4f,0));
		function.Rc.add(new SFVertex3f(0.1f,0,0));
		function.Rc.add(new SFVertex3f(0.2f,0.1f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.2f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.3f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.4f,0));
	//Secondo Pezzo		function.Cc.add(new SFVertex3f(0.0f,0.5f,0));//Warning: Not well Identified 
		function.Cc.add(new SFVertex3f(0.1f,0.5f,0));
		function.Cc.add(new SFVertex3f(0.2f,0.5f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.4f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.4f,0));
		function.Rc.add(new SFVertex3f(0.2f,0.4f,0));
	//Terzo Pezzo		function.Cc.add(new SFVertex3f(0.3f,0.5f,0));//Warning: Not well Identified 
		function.Rc.add(new SFVertex3f(0.2f,0.4f,0));
		 SFConcreteTriangleExtractor  trianglesExtractor = new  SFConcreteTriangleExtractor();
		 SFQuadsSurfaceGeometry  quadsSurfaceGeometry = new  SFQuadsSurfaceGeometry(primitive,				function, new ,TexCoordFunction(), trianglesExtractor, 6, 8);
		quadsSurfaceGeometry.compile();
		return ,quadsSurfaceGeometry;
	}

};