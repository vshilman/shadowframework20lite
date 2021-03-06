var vertexPositionAttribute;
var textureCoordAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var samplerUniform;
var cubeVertexPositionBuffer = new Array();
var cubeVertexIndexBuffer = new Array();
var cubeVertexTextureCoordBuffer = new Array();
var texture1 = new Array();
var pMatrix;
var mvMatrix;
var rAngle = 0;
var lastTime = 0;

function Test_sv3Drawer() {
}

Test_sv3Drawer.prototype = {

	initShaders : function(gl) {
		var shaderProgram = getShaderProgram(gl, "shaders/shader2.fs", "shaders/shader2.vs");

		vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);

		textureCoordAttribute = gl.getAttribLocation(shaderProgram, "aTextureCoord");
		gl.enableVertexAttribArray(textureCoordAttribute);

		pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
		samplerUniform = gl.getUniformLocation(shaderProgram, "uSampler");
	},

	setMatrixUniforms : function(gl) {
		gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
		gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
	},

	initBuffers : function(gl) {

		cubeVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		var vertices = [ -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
		cubeVertexPositionBuffer[1] = 3;
		cubeVertexPositionBuffer[2] = 24;

		cubeVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		var textureCoords = [ 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords), gl.STATIC_DRAW);
		cubeVertexTextureCoordBuffer[1] = 2;
		cubeVertexTextureCoordBuffer[2] = 24;

		cubeVertexIndexBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		var cubeVertexIndices = [ 0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15, 16, 17, 18, 16, 18, 19, 20, 21, 22, 20, 22, 23 ];
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(cubeVertexIndices), gl.STATIC_DRAW);
		cubeVertexIndexBuffer[1] = 1;
		cubeVertexIndexBuffer[2] = 36;
	},

	initTexture : function(gl) {
		texture1[0] = gl.createTexture();
		var tex = new Image();
		tex.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, texture1[0]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.NEAREST);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.NEAREST);
			gl.bindTexture(gl.TEXTURE_2D, 0);
		}

		tex.src = "images/crate.gif";
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

		mvMatrix = new Float32Array([ cx * cx, cx * (sx * sx + sx), sx * sx - cx * cx * sx, 0, -cx * sx, cx * cx - sx * sx * sx, cx * (sx * sx + sx), 0, sx, -cx * sx, cx * cx, 0, 0, 0, -8, 1 ]);

		gl.frontFace(gl.CCW);
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, cubeVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, cubeVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.activeTexture(gl.TEXTURE0);
		gl.bindTexture(gl.TEXTURE_2D, texture1[0]);
		gl.uniform1i(samplerUniform, 0);

		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		this.setMatrixUniforms(gl);
		gl.drawElements(gl.TRIANGLES, cubeVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

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