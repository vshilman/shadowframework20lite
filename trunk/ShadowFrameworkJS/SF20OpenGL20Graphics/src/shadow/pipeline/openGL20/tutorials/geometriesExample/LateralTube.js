

LateralTube.prototype = {

	generateGeometry:function(primitive){
	SFCurvedTubeFunction function;
	//Primo Pezzo		function.Cc.add(new SFVertex3f(0,0,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.1f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.2f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.3f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.4f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.1f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0.2f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0.3f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0.4f,0));//Warning: Not well Identified 
	//Secondo Pezzo		function.Cc.add(new SFVertex3f(0.0f,0.5f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.1f,0.5f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.2f,0.5f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0.4f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0.4f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.4f,0));//Warning: Not well Identified 
	//Terzo Pezzo		function.Cc.add(new SFVertex3f(0.3f,0.5f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.4f,0));//Warning: Not well Identified 
	SFConcreteTriangleExtractor trianglesExtractor;
	SFQuadsSurfaceGeometry quadsSurfaceGeometry;
	quadsSurfaceGeometry.compile();//Warning: Not well Identified 
		return this.quadsSurfaceGeometry;
	}

};