

function SFGLSLSet(){
	this.frShader=0;
	this.vxShader=0;
	this.program=0;
	this.initialized=false;
}

var SFGLSLSet_REPORT_SHADERS=false;
	
	
SFGLSLSet.prototype["getProgram"]=function(){
		return this.program;
	};	
	
SFGLSLSet.prototype["initFragmentShader"]=function(gl){

		this.frShader= gl.createShader(gl.FRAGMENT_SHADER); 
		this.updateFragmentShader(gl);
	};
	
SFGLSLSet.prototype["updateFragmentShader"]=function(gl){

		var shaderSource=this.loadFragmentShaderText();
		
		//shaderSource=loadFile("tempfs");
		
		if(SFGLSLSet_REPORT_SHADERS)
			alert("shaderText[0] " + shaderSource);
		
		gl.shaderSource(this.frShader, shaderSource);
		gl.compileShader(this.frShader);
	
		if (!gl.getShaderParameter(this.frShader, gl.COMPILE_STATUS)) {
			alert(gl.getShaderInfoLog(this.frShader));
		}
	};
	

SFGLSLSet.prototype["initVertexShader"]=function(gl){

		this.vxShader= gl.createShader(gl.VERTEX_SHADER); 
		this.updateVertexShader(gl);
	};
	
SFGLSLSet.prototype["updateVertexShader"]=function(gl){

		var shaderSource=this.loadVertexShaderText();
		
		//shaderSource=loadFile("tempvs");
		
		if(SFGLSLSet_REPORT_SHADERS)
			alert("shaderText[0] " + shaderSource);
		
		gl.shaderSource(this.vxShader, shaderSource);
		gl.compileShader(this.vxShader);
	
		if (!gl.getShaderParameter(this.vxShader, gl.COMPILE_STATUS)) {
			alert(gl.getShaderInfoLog(this.vxShader));
		}
	};
	
SFGLSLSet.prototype["initProgram"]=function(gl){

		this.program=gl.createProgram();
		this.updateProgram(gl);
	};
	

SFGLSLSet.prototype["updateProgram"]=function(gl){
		
		gl.attachShader(this.program, this.vxShader);
		gl.attachShader(this.program, this.frShader);
		gl.linkProgram(this.program);
				
		if (!gl.getProgramParameter(this.program, gl.LINK_STATUS)) {
			alert(gl.getProgramInfoLog(this.program));
		}
		
		gl.useProgram(this.program);
	};
	
	
SFGLSLSet.prototype["apply"]=function(){
		
		var gl=SFGL2_getGL();
		gl.useProgram(this.program);
	};
	
SFGLSLSet.prototype["unapply"]=function(){
		
		var gl=SFGL2_getGL();
		gl.useProgram(0);
	};
	
	
SFGLSLSet.prototype["init"]=function(){
		
		var gl=SFGL2_getGL();
		if(this.initialized===undefined || !this.initialized){
			this.initialized=true;
			this.initVertexShader(gl);
			this.initFragmentShader(gl);
			this.initProgram(gl);
		}
	};
	
SFGLSLSet.prototype["destroy"]=function(){
		
		var gl=SFGL2_getGL();
		gl.deleteProgram(this.program);
		gl.deleteShader(this.frShader);
		gl.deleteShader(this.vxShader);
	};
	
SFGLSLSet.prototype["update"]=function(){
		
		var gl=SFGL2_getGL();
		this.updateVertexShader(gl);
		this.updateFragmentShader(gl);
		this.updateProgram(gl);	
	};
	
	