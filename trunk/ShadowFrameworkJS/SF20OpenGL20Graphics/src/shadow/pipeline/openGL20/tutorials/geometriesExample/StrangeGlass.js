

StrangeGlass.prototype = {

	generateGeometry:function(primitive){
	SFCurvedTubeFunction function;
	function.Cc.add(new SFVertex3f(0,0,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.1f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.025f,0.2f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.3f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.4f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.5f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.6f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.7f,0));//Warning: Not well Identified 
	function.Cc.add(new SFVertex3f(0.0f,0.8f,0));//Warning: Not well Identified 
	//function.Cc.add(new SFVertex3f(0.0f,0.35f,0));//Warning: Not well Identified 
	//function.Rc.add(new SFVertex3f(0.0f,0.40f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.1f,0,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.1f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.2f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.05f,0.4f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.05f,0.4f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.5f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.2f,0.6f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.50f,0.7f,0));//Warning: Not well Identified 
	function.Rc.add(new SFVertex3f(0.40f,0.80f,0));//Warning: Not well Identified 
	SFConcreteTriangleExtractor trianglesExtractor;
	SFQuadsSurfaceGeometry quadsSurfaceGeometry;
	quadsSurfaceGeometry.compile();//Warning: Not well Identified 
		return this.quadsSurfaceGeometry;
	}

};