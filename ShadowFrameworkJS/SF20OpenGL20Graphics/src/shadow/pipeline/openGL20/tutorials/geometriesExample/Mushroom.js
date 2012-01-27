

Mushroom.prototype = {

	generateGeometry:function(primitive){
	SFCurvedTubeFunction function;
	function.Cc.add(new SFVertex3f(0,0,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.1f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.025f,0.2f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.3f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.4f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.5f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.1f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.2f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.05f,0.3f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.05f,0.4f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.45f,0.5f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.05f,0.5f,0));//Warning: Not well Identified 
	SFConcreteTriangleExtractor trianglesExtractor;
	SFQuadsSurfaceGeometry quadsSurfaceGeometry;
	quadsSurfaceGeometry.compile();//Warning: Not well Identified 
		return this.quadsSurfaceGeometry;
	}

};