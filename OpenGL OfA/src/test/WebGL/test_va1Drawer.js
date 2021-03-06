var vertexPositionAttribute;
var textureCoordAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var samplerUniform;
var doorVertexPositionBuffer = new Array();
var doorVertexTextureCoordBuffer = new Array();
var greenVertexPositionBuffer = new Array();
var greenVertexTextureCoordBuffer = new Array();
var pyramidVertexPositionBuffer = new Array();
var pyramidVertexTextureCoordBuffer = new Array();
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

function Test_va1Drawer() {
}

Test_va1Drawer.prototype = {

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

		pyramidVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		var vertices1 = [ 0.0, 1.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices1), gl.STATIC_DRAW);
		pyramidVertexPositionBuffer[1] = 3;
		pyramidVertexPositionBuffer[2] = 6;

		pyramidVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		var textureCoords1 = [ 3.0, 0.0, 0.0, 6.0, 6.0, 6.0, 0.0, 6.0, 6.0, 6.0, 0.0, 6.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords1), gl.STATIC_DRAW);
		pyramidVertexTextureCoordBuffer[1] = 2;
		pyramidVertexTextureCoordBuffer[2] = 6;

		cubeVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		var vertices2 = [ -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices2), gl.STATIC_DRAW);
		cubeVertexPositionBuffer[1] = 3;
		cubeVertexPositionBuffer[2] = 16;

		cubeVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		var textureCoords2 = [ 0.0, 0.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0, 0.0, 0.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0, 0.0, 0.0, 0.0, 0.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords2), gl.STATIC_DRAW);
		cubeVertexTextureCoordBuffer[1] = 2;
		cubeVertexTextureCoordBuffer[2] = 16;

		cubeVertexIndexBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		var cubeVertexIndices = [ 0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15 ];
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(cubeVertexIndices), gl.STATIC_DRAW);
		cubeVertexIndexBuffer[1] = 1;
		cubeVertexIndexBuffer[2] = 24;

		greenVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		var vertices3 = [ -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices3), gl.STATIC_DRAW);
		greenVertexPositionBuffer[1] = 3;
		greenVertexPositionBuffer[2] = 4;

		greenVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		var textureCoords3 = [ 0.0, 10.0, 0.0, 0.0, 10.0, 0.0, 10.0, 10.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords3), gl.STATIC_DRAW);
		greenVertexTextureCoordBuffer[1] = 2;
		greenVertexTextureCoordBuffer[2] = 4;

		doorVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexPositionBuffer[0]);
		var vertices4 = [ -0.2, -1.0, 1.001, 0.2, -1.0, 1.001, -0.2, 0.0, 1.001, 0.2, 0.0, 1.001 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices4), gl.STATIC_DRAW);
		doorVertexPositionBuffer[1] = 3;
		doorVertexPositionBuffer[2] = 4;

		doorVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		var textureCoords4 = [ 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords4), gl.STATIC_DRAW);
		doorVertexTextureCoordBuffer[1] = 2;
		doorVertexTextureCoordBuffer[2] = 4;

	},

	initTexture : function(gl) {
		for ( var i = 0; i < 4; i++) {
			textures[i] = gl.createTexture();
		}
		var tex1 = new Image();
		tex1.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[0]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex1);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);
		}
		tex1.src = "images/muro.gif";

		var tex2 = new Image();
		tex2.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[1]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex2);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);
		}
		tex2.src = "images/roof.gif";

		var tex3 = new Image();
		tex3.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[2]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex3);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);
		}
		tex3.src = "images/grass.gif";

		var tex4 = new Image();
		tex4.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[3]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex4);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.generateMipmap(gl.TEXTURE_2D);
			gl.bindTexture(gl.TEXTURE_2D, 0);
		}
		tex4.src = "images/door.gif";
	},

	drawScene : function(gl) {
		gl.clearColor(0.0, 0.71, 0.91, 1.0);
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

		var mvMatrixv = new Float32Array([ cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, -cx, zoom - sx, 1 ]);
		mvMatrix = mvMatrixv;
		
		gl.frontFace(gl.CCW);
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, cubeVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, cubeVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.activeTexture(gl.TEXTURE0);
		gl.bindTexture(gl.TEXTURE_2D, textures[0]);
		gl.uniform1i(samplerUniform, 0);

		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		this.setMatrixUniforms(gl);
		gl.drawElements(gl.TRIANGLES, cubeVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, doorVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, doorVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindTexture(gl.TEXTURE_2D, textures[3]);
		gl.uniform1i(samplerUniform, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_STRIP, 0, doorVertexPositionBuffer[2]);

		gl.disable(gl.CULL_FACE);

		var mvMatrixv2 = new Float32Array(mvMatrixv);
		mvMatrixv[0] *= 10;
		mvMatrixv[1] *= 10;
		mvMatrixv[2] *= 10;
		mvMatrixv[8] *= 10;
		mvMatrixv[9] *= 10;
		mvMatrixv[10] *= 10;
		mvMatrix = mvMatrixv;

		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, greenVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, greenVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindTexture(gl.TEXTURE_2D, textures[2]);
		gl.uniform1i(samplerUniform, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_STRIP, 0, greenVertexPositionBuffer[2]);
		mvMatrixv = mvMatrixv2;

		gl.enable(gl.CULL_FACE);

		mvMatrixv[13] += 2 * cx;
		mvMatrixv[14] += 2 * sx;
		mvMatrix = mvMatrixv;

		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, pyramidVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, pyramidVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindTexture(gl.TEXTURE_2D, textures[1]);
		gl.uniform1i(samplerUniform, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_FAN, 0, pyramidVertexPositionBuffer[2]);

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
		this.setCommands(e, true);
	},

	keyReleased : function(e) {
		this.setCommands(e, false);
	}
}