var vertexPositionAttribute;
var textureCoordAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var samplerUniform;
var cubeVertexPositionBuffer = new Array();
var cubeVertexIndexBuffer = new Array();
var cubeVertexTextureCoordBuffer = new Array();
var textures = new Array();
var pMatrix;
var mvMatrix;
var rAnglex = 0;
var speedx = 0;
var rAngley = 0;
var speedy = 0;
var zoom = -8;
var lastTime = 0;
var lastTime2 = 0;
var commands = new Array();
var wrapper = 0;

function Test_sv5Drawer() {
}

Test_sv5Drawer.prototype = {

	initShaders : function(gl) {
		var shaderProgram = getShaderProgram(gl, "shaders/shader2.fs", "shaders/shader2.vs");

		vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);

		shaderProgram.textureCoordAttribute = gl.getAttribLocation(shaderProgram, "aTextureCoord");
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
		var textureCoords = [ -1.0, -1.0, 2.0, -1.0, 2.0, 2.0, -1.0, 2.0, 2.0, -1.0, 2.0, 2.0, -1.0, 2.0, -1.0, -1.0, -1.0, 2.0, -1.0, -1.0, 2.0, -1.0, 2.0, 2.0, 2.0, 2.0, -1.0, 2.0, -1.0, -1.0, 2.0, -1.0, 2.0, -1.0, 2.0, 2.0, -1.0, 2.0, -1.0, -1.0, -1.0, -1.0, 2.0, -1.0, 2.0, 2.0, -1.0, 2.0 ];
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
		for ( var i = 0; i < 3; i++) {
			textures[i] = gl.createTexture();
		}
		var tex = new Image();
		tex.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[0]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);

			gl.bindTexture(gl.TEXTURE_2D, textures[1]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.MIRRORED_REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.MIRRORED_REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);

			gl.bindTexture(gl.TEXTURE_2D, textures[2]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.CLAMP_TO_EDGE);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.CLAMP_TO_EDGE);
			gl.generateMipmap(gl.TEXTURE_2D);

			gl.bindTexture(gl.TEXTURE_2D, null);
		}

		tex.src = "images/roof.gif";
	},

	drawScene : function(gl) {
		gl.clearColor(0.0, 0.0, 0.0, 1.0);
		gl.enable(gl.DEPTH_TEST);
		gl.cullFace(gl.BACK);
		gl.enable(gl.CULL_FACE);

		this.handleKeys();
		gl.viewport(0, 0, 500, 500);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

		var cx = Math.cos(rAnglex);
		var sx = Math.sin(rAnglex);
		var cy = Math.cos(rAngley);
		var sy = Math.sin(rAngley);

		pMatrix = new Float32Array([ 2.4142136573791504, 0, 0, 0, 0, 2.4142136573791504, 0, 0, 0, 0, -1.0020020008087158, -1, 0, 0, -0.20020020008087158, 0 ]);

		mvMatrix = new Float32Array([ cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, 0, zoom, 1 ]);

		gl.frontFace(gl.CCW);
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, cubeVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, cubeVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.activeTexture(gl.TEXTURE0);
		gl.bindTexture(gl.TEXTURE_2D, textures[wrapper]);
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
			rAnglex += speedx * elapsed * 0.001;
			rAngley += speedy * elapsed * 0.001;
		}
		lastTime = timeNow;
	},

	handleKeys : function() {
		var timeNow = new Date().getTime();
		if (lastTime2 != 0) {
			var elapsed = timeNow - lastTime2;
			if (commands[0]) {
				zoom += 5 * elapsed * 0.001;
			}
			if (commands[1]) {
				zoom -= 5 * elapsed * 0.001;
			}
			if (commands[2]) {
				speedx -= 2 * elapsed * 0.001;
			}
			if (commands[3]) {
				speedx += 2 * elapsed * 0.001;
			}
			if (commands[4]) {
				speedy -= 2 * elapsed * 0.001;
			}
			if (commands[5]) {
				speedy += 2 * elapsed * 0.001;
			}
		}
		lastTime2 = timeNow;
	},

	setCommands : function(e, value) {
		if (e.keyCode == 65) {
			commands[0] = value;
		}
		if (e.keyCode == 90) {
			commands[1] = value;
		}
		if (e.keyCode == 38) {
			commands[2] = value;
		}
		if (e.keyCode == 40) {
			commands[3] = value;
		}
		if (e.keyCode == 37) {
			commands[4] = value;
		}
		if (e.keyCode == 39) {
			commands[5] = value;
		}
	},

	keyPressed : function(e) {
		if (e.keyCode == 87) {
			wrapper = wrapper == 2 ? 0 : (wrapper + 1);
		}
		this.setCommands(e, true);
	},

	keyReleased : function(e) {
		this.setCommands(e, false);
	}
}