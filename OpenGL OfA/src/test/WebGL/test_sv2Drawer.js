var vertexPositionAttribute;
var vertexColorAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var pyramidVertexPositionBuffer = new Array();
var pyramidVertexColorBuffer = new Array();
var baseVertexPositionBuffer = new Array();
var baseVertexColorBuffer = new Array();
var pMatrix;
var mvMatrix;
var rAngle = 0;
var lastTime = 0;

function Test_sv2Drawer() {
}

Test_sv2Drawer.prototype = {

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
		pyramidVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		var vertices = [ 0.0, 1.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
		pyramidVertexPositionBuffer[1] = 3;
		pyramidVertexPositionBuffer[2] = 6;

		pyramidVertexColorBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexColorBuffer[0]);
		var colors = [ 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors), gl.STATIC_DRAW);
		pyramidVertexColorBuffer[1] = 4;
		pyramidVertexColorBuffer[2] = 6;

		baseVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, baseVertexPositionBuffer[0]);
		var vertices2 = [ -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices2), gl.STATIC_DRAW);
		baseVertexPositionBuffer[1] = 3;
		baseVertexPositionBuffer[2] = 4;

		baseVertexColorBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, baseVertexColorBuffer[0]);
		var colors2 = [ 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors2), gl.STATIC_DRAW);
		baseVertexColorBuffer[1] = 4;
		baseVertexColorBuffer[2] = 4;
	},

	drawScene : function(gl) {
		gl.clearColor(0.0, 0.0, 0.0, 1.0);
		gl.enable(gl.DEPTH_TEST);
		gl.cullFace(gl.BACK);
		gl.enable(gl.CULL_FACE);

		gl.viewport(0, 0, 500, 500);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

		var cx = Math.cos(rAngle);
		var sx = Math.sin(rAngle);

		pMatrix = new Float32Array([ 2.4142136573791504, 0, 0, 0, 0, 2.4142136573791504, 0, 0, 0, 0, -1.0020020008087158, -1, 0, 0, -0.20020020008087158, 0 ]);

		mvMatrix = new Float32Array([ cx, sx * sx, -cx * sx, 0, 0, cx, sx, 0, sx, -cx * sx, cx * cx, 0, 0, 0, -8, 1 ]);

		gl.frontFace(gl.CCW);
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, pyramidVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexColorBuffer[0]);
		gl.vertexAttribPointer(vertexColorAttribute, pyramidVertexColorBuffer[1], gl.FLOAT, false, 0, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_FAN, 0, pyramidVertexPositionBuffer[2]);

		gl.frontFace(gl.CW);
		gl.bindBuffer(gl.ARRAY_BUFFER, baseVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, baseVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, baseVertexColorBuffer[0]);
		gl.vertexAttribPointer(vertexColorAttribute, baseVertexColorBuffer[1], gl.FLOAT, false, 0, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_STRIP, 0, baseVertexPositionBuffer[2]);

		this.animate();
	},

	animate : function() {
		var timeNow = new Date().getTime();
		if (lastTime != 0) {
			var elapsed = timeNow - lastTime;
			rAngle += 1.57 * elapsed * 0.001;
		}
		lastTime = timeNow;
	}
}