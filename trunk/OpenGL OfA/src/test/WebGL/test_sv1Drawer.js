var vertexPositionAttribute;
var vertexColorAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var triangleVertexPositionBuffer = new Array();
var triangleVertexColorBuffer = new Array();
var squareVertexPositionBuffer = new Array();
var squareVertexColorBuffer = new Array();
var pMatrix;
var mvMatrix;

function Test_sv1Drawer() {
}

Test_sv1Drawer.prototype = {
	initShaders : function(gl) {
		var shaderProgram = getShaderProgram(gl, "shaders/shader1.fs", "shaders/shader1.vs");

		vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);

		vertexColorAttribute = gl.getAttribLocation(shaderProgram, "aVertexColor");
		gl.enableVertexAttribArray(vertexColorAttribute);

		pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
	},

	setMatrixUniforms : function(gl) {
		gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
		gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
	},

	initBuffers : function(gl) {
		triangleVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer[0]);
		var vertices = [ 0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
		triangleVertexPositionBuffer[1] = 3;
		triangleVertexPositionBuffer[2] = 3;

		triangleVertexColorBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexColorBuffer[0]);
		var colors = [ 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors), gl.STATIC_DRAW);
		triangleVertexColorBuffer[1] = 4;
		triangleVertexColorBuffer[2] = 3;

		squareVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer[0]);
		var vertices2 = [ 1.0, 1.0, 0.0, -1.0, 1.0, 0.0, 1.0, -1.0, 0.0, -1.0, -1.0, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices2), gl.STATIC_DRAW);
		squareVertexPositionBuffer[1] = 3;
		squareVertexPositionBuffer[2] = 4;

		squareVertexColorBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexColorBuffer[0]);
		var colors2 = new Array();
		for ( var i = 0; i < 4; i++) {
			var c = i << 2;
			colors2[c] = 1;
			colors2[c + 1] = 0.75;
			colors2[c + 2] = 0.8;
			colors2[c + 3] = 1;
		}
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors2), gl.STATIC_DRAW);
		squareVertexColorBuffer[1] = 4;
		squareVertexColorBuffer[2] = 4;
	},

	drawScene : function(gl) {
		gl.clearColor(0.0, 0.0, 0.0, 1.0);
		gl.enable(gl.DEPTH_TEST);

		gl.viewport(0, 0, 500, 500);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

		pMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4 ]);

		mvMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, -1.5, 0, 0, 1 ]);

		gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, triangleVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexColorBuffer[0]);
		gl.vertexAttribPointer(vertexColorAttribute, triangleVertexColorBuffer[1], gl.FLOAT, false, 0, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLES, 0, triangleVertexPositionBuffer[2]);

		mvMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1.5, 0, 0, 1 ]);

		gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, squareVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexColorBuffer[0]);
		gl.vertexAttribPointer(vertexColorAttribute, squareVertexColorBuffer[1], gl.FLOAT, false, 0, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_STRIP, 0, squareVertexPositionBuffer[2]);
	}
};