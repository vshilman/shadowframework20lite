

function SFGL20Program(){
	this.frShader=0;
	this.vxShader=0;
	this.program=0;
	this.initialized=false;
	this.fragmentShader=new Array();
	this.materials=new Array();
	this.light=new SFProgramComponent();
	this.registeredUniforms=false;
	this.data=new SFGL20UniformData();
	this.vertexShader=new Array();
	this.primitive=0;
}

inherit(SFGL20Program,SFGL20AbstractProgram);

SFGL20Program.prototype["clearVertexShader"]=function(){
		this.vertexShader.length;
	};

//SFGL20Program.prototype["addToVertexShader"]=function(component,register){
//		this.vertexShader.push(new SFPrimitiveProgramAssociation(register,component));
//	};
	
	
SFGL20Program.prototype["loadVertexShaderText"]=function(){
		return this.getShaderText(this.vertexShader,true);
	};
	
SFGL20Program.prototype["write"]=function(){
		alert("Vertex Program");
		for (var i=0; i < this.vertexShader.length; i++) {
			alert("\t\tvertexShader.get(i) " + this.vertexShader.get(i));
		}
	};
	
SFGL20Program.prototype["setPrimitive"]=function(primitive){
		
		this.primitive=primitive;

		for (var i=0; i<primitive.getComponents().length; i++) {
			var pr=primitive.getComponents()[i];
			var outputRegister=primitive.getBlocks()[i].getRegister();
			
			//In-line substitution
			//this.addToVertexShader(pr,outputRegister);
			this.vertexShader.push(new SFPrimitiveProgramAssociation(outputRegister,pr));
		}
	};

SFGL20Program.prototype["addToVertexShader"]=function(module){
		for (var i = 0; i < module.getComponents().length; i++) {
			this.vertexShader.push(new SFPrimitiveProgramAssociation(null,module.getComponents()[i]));
		}
	};
	
SFGL20Program.prototype["setTransform"]=function(transform){
		this.addToVertexShader(transform);
		this.transform=transform;
	};

SFGL20Program.prototype["getPrimitive"]=function(){
		return this.primitive;
	};	

SFGL20Program.prototype["getTransforms"]=function(){
		return this.transforms;
	};	
	
	