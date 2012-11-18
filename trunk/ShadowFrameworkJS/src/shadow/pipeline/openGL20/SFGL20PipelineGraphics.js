
function SFGL20PipelineGraphics(){
	SFInitiator_addInitiable(this);
}

var SFGL20PipelineGraphics_N=12;

var SFGL20PipelineGraphics_LINES_WIDTH=4;
var SFGL20PipelineGraphics_program;
var SFGL20PipelineGraphics_projection=null;
var SFGL20PipelineGraphics_transform=undefined;
var SFGL20PipelineGraphics_baseQuadList=-1;
var SFGL20PipelineGraphics_baseTriangleList=-1;
var SFGL20PipelineGraphics_baseLineList=-1;
var SFGL20PipelineGraphics_baseQuadsList=-1;


function SFGL20PipelineGraphics_setProgram(program) {
	SFGL20PipelineGraphics_program=program;
}

SFGL20PipelineGraphics.prototype["setupTransform"]=function(transform) {
	SFGL20PipelineGraphics_staticSetupTransform(transform);
};

function SFGL20PipelineGraphics_staticSetupTransform(transform) {
	SFGL20PipelineGraphics_transform=transform;
}

SFGL20PipelineGraphics.prototype["drawPrimitives"]=function(primitives,first,count) {

	SFGL20PipelineGraphics_program.load();
	
	SFGL20PipelineGraphics_program.setTransformData(SFGL20PipelineGraphics_transform);
	
	//alert("transform "+SFGL20PipelineGraphics_transform);
	//alert("projection "+SFGL20PipelineGraphics_projection);

	if(SFGL20PipelineGraphics_projection!=null)
		SFGL20PipelineGraphics_program.setupProjection(SFGL20PipelineGraphics_projection);

	var prArray=primitives;

	var primitive=primitives.getPrimitive();
	
	var list=SFGL20PipelineGraphics_baseTriangleList[0];
	
	//TODO
	/*if(primitive.isQuad())
		list=SFGL20PipelineGraphics_baseQuadList;
	else if(primitive.isLine())
		list=SFGL20PipelineGraphics_baseLineList;*/

	for (var i=first; i < count + first; i++) {

		var indices=prArray.getValue(i);
		
		SFGL20PipelineGraphics_program.setIndexedData(indices,prArray.getPrimitiveData(),primitive);

		var gl=SFGL2_getGL();
		
		gl.enableVertexAttribArray(SFGL20PipelineGraphics_program.parametersAttrib);
		gl.bindBuffer(gl.ARRAY_BUFFER, list);
		gl.vertexAttribPointer(SFGL20PipelineGraphics_program.parametersAttrib, 3, 
				gl.FLOAT, false, 0, 0);
		
		gl.drawArrays(gl.TRIANGLES, 0, SFGL20PipelineGraphics_baseTriangleList[1]);
	
	}
};


SFGL20PipelineGraphics.prototype["loadStructureData"]=function(module, array, inProgramIndex,indexOfData) {
		SFGL20PipelineGraphics_program.setDataValues(module,inProgramIndex,array.getValue(indexOfData));
};

SFGL20PipelineGraphics.prototype["loadTexture"]=function(module, texture, indexOfTexture) {
		SFGL20PipelineGraphics_program.setTextureData(module,indexOfTexture,texture);
};		
	
		
SFGL20PipelineGraphics.prototype["setupProjection"]=function(projection) {
			SFGL20PipelineGraphics_projection=projection;
		};

SFGL20PipelineGraphics.prototype["drawBaseQuad"]=function() {


			var gl=SFGL2_getGL();
								
			var list=SFGL20PipelineGraphics_baseQuadList[0];
			gl.enableVertexAttribArray(SFGL20PipelineGraphics_program.parametersAttrib);
			gl.bindBuffer(gl.ARRAY_BUFFER, list);
			gl.vertexAttribPointer(SFGL20PipelineGraphics_program.parametersAttrib, 2, 
					gl.FLOAT, false, 0, 0);
			
			gl.drawArrays(gl.TRIANGLE_STRIP, 0, SFGL20PipelineGraphics_baseQuadList[1]);
			
};
		
	
SFGL20PipelineGraphics.prototype["init"]=function() {
			
			var gl=SFGL2_getGL();
			
			
			SFGL20PipelineGraphics_baseTriangleList=new Array();
			SFGL20PipelineGraphics_baseTriangleList[0]=gl.createBuffer();
			gl.bindBuffer(gl.ARRAY_BUFFER, SFGL20PipelineGraphics_baseTriangleList[0]);
			/*baseQuadsList=gl.glGenLists(1);
			baseQuadList=gl.glGenLists(1);
			baseLineList=gl.glGenLists(1);*/
			
			var N=SFGL20PipelineGraphics_N;
			var step=1.0 / N;
			
			//var vertices = [ 0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0 ];
			var vertices = new Array();
			
			//gl.glNewList(baseTriangleList, GL2.GL_COMPILE);
				var verticesIndex=0;
				for (var k=0; k < N; k++) {
					var v1=k * step;
					var v2=v1 + step;
					//gl.glBegin(GL.GL_TRIANGLE_STRIP);
						for (var j=0; j < N - k -1; j++) {
							var u=j * step;
							var u2= u + step;
							
							vertices[verticesIndex]=u;
							vertices[verticesIndex+1]=v1;
							vertices[verticesIndex+2]=1-u-v1;
							verticesIndex+=3;
							
							vertices[verticesIndex]=u;
							vertices[verticesIndex+1]=v2;
							vertices[verticesIndex+2]=1-u-v2;
							verticesIndex+=3;
							
							vertices[verticesIndex]=u2;
							vertices[verticesIndex+1]=v1;
							vertices[verticesIndex+2]=1-u2-v1;
							verticesIndex+=3;
							
							vertices[verticesIndex]=u;
							vertices[verticesIndex+1]=v2;
							vertices[verticesIndex+2]=1-u-v2;
							verticesIndex+=3;
							
							vertices[verticesIndex]=u2;
							vertices[verticesIndex+1]=v1;
							vertices[verticesIndex+2]=1-u2-v1;
							verticesIndex+=3;
							
							vertices[verticesIndex]=u2;
							vertices[verticesIndex+1]=v2;
							vertices[verticesIndex+2]=1-u2-v2;
							
							verticesIndex+=3;
						}
						var u=(N - k -1) * step;
						var u2= u + step;
						
						vertices[verticesIndex]=u;
						vertices[verticesIndex+1]=v1;
						vertices[verticesIndex+2]=1-u-v1;
						verticesIndex+=3;
						vertices[verticesIndex]=u;
						vertices[verticesIndex+1]=v2;
						vertices[verticesIndex+2]=1-u-v2;
						verticesIndex+=3;
						vertices[verticesIndex]=u2;
						vertices[verticesIndex+1]=v1;
						vertices[verticesIndex+2]=1-u2-v1;
						verticesIndex+=3;
						
					//gl.glEnd();
				}
			//gl.glEndList();
			
			SFGL20PipelineGraphics_baseTriangleList[1]=verticesIndex/3;

			gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);	
			
			/*gl.glNewList(baseQuadsList, GL2.GL_COMPILE);
				for (var k=0; k < N; k++) {
					
					var v1=k * step;
					var v2=v1 + step;
					gl.glBegin(GL.GL_TRIANGLE_STRIP);
						for (var j=0; j <= N ; j++) {
							var u=j * step;
							gl.glVertex3f(u,v1,1 - u - v1);
							gl.glVertex3f(u,v2,1 - u - v2);
						}
					gl.glEnd();
				}
			gl.glEndList();*/
			
			SFGL20PipelineGraphics_baseQuadList=new Array();
			SFGL20PipelineGraphics_baseQuadList[0]=gl.createBuffer();
			gl.bindBuffer(gl.ARRAY_BUFFER, SFGL20PipelineGraphics_baseQuadList[0]);
			var quadVertices = [-1.0,-1.0,1.0,-1.0,-1.0,1.0,1.0,1.0];
			SFGL20PipelineGraphics_baseQuadList[1]=4;
			gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(quadVertices), gl.STATIC_DRAW);	

			/*N=N*N;
			step=1.0 / N;
			gl.glNewList(baseLineList, GL2.GL_COMPILE);
				gl.glLineWidth(LINES_WIDTH);
				gl.glBegin(GL.GL_LINE_STRIP);
				for (var k=0; k <= N; k++) {
					var t=k * step;
					gl.glVertex3f(t,0,0);
				}
				gl.glEnd();
				gl.glLineWidth(1);
			gl.glEndList();*/
		};	
		
	
SFGL20PipelineGraphics.prototype["destroy"]=function() {
			
			var gl=SFGL2.getGL();
			gl.glDeleteLists(baseQuadsList, 1);
			gl.glDeleteLists(baseQuadList, 1);
			gl.glDeleteLists(baseTriangleList, 1);
			gl.glDeleteLists(baseLineList, 1);
		};	
		

function SFGL20PipelineGraphics_getStencilFunc(func) {
	throw "Not yet implemented";
}


function SFGL20PipelineGraphics_getStencilOp(operation) {
	throw "Not yet implemented";
}


	
SFGL20PipelineGraphics.prototype["setPipelineState"]=function(state) {
		throw "Not yet implemented";
	};	
		
	
SFGL20PipelineGraphics.prototype["executeAccumulationOperation"]=function(operation,value) {
		throw "Not yet implemented";
	};	
