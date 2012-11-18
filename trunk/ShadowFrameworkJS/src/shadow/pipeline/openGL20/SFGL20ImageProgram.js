//Java to JS on 22/03/2012

function SFGL20ImageProgram(){
	this.frShader=0;
	this.vxShader=0;
	this.program=0;
	this.initialized=false;
	this.fragmentShader=new Array();
	this.light=new SFProgramComponent();
	this.registeredUniforms=false;
	this.data=new SFGL20UniformData();
}

inherit(SFGL20ImageProgram,SFGL20AbstractProgram);

SFGL20ImageProgram.prototype["clearVertexShader"]=function(){
		//do nothing
	};

SFGL20ImageProgram.prototype["addToVertexShader"]=function(component,register){
		//do nothing
	};
	
SFGL20ImageProgram_vShader= "precision mediump float;" +
				"attribute vec3 tessellationParameters;"+
				"varying vec2 texCoord0;\n" +
				"varying vec3 normal;\n" +
				"void main(void){\n" +
				"\t gl_Position=vec4(tessellationParameters,1);\n" +
				"\t texCoord0=vec2(0.5,0.5)+tessellationParameters.xy*vec2(0.5,0.5);\n" +
				"\t normal=vec3(0,0,-1);\n" +
				"}\n";
	
	
	
	
SFGL20ImageProgram.prototype["loadVertexShaderText"]=function(){		
		return SFGL20ImageProgram_vShader;
	};

SFGL20ImageProgram.prototype["setPrimitive"]=function(primitive){
		//do nothing
	};
	
SFGL20ImageProgram.prototype["getPrimitive"]=function(){
		//do nothing
	};

SFGL20ImageProgram.prototype["setTransform"]=function(transform){
		//do nothing
	};

	