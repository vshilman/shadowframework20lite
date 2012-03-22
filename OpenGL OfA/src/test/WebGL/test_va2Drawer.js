var vertexPositionAttribute;
var vertexNormalAttribute;
var textureCoordAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var nMatrixUniform;
var samplerUniform;
var ambientColorUniform;
var pointLightingLocationUniform;
var pointLightingSpecularColorUniform;
var pointLightingDiffuseColorUniform;
var materialShininessUniform;
var alphaUniform;
var doorVertexPositionBuffer = new Array();
var doorVertexNormalBuffer = new Array();
var doorVertexTextureCoordBuffer = new Array();
var greenVertexPositionBuffer = new Array();
var greenVertexNormalBuffer = new Array();
var greenVertexTextureCoordBuffer = new Array();
var pyramidVertexPositionBuffer = new Array();
var pyramidVertexNormalBuffer = new Array();
var pyramidVertexIndexBuffer = new Array();
var pyramidVertexTextureCoordBuffer = new Array();
var cubeVertexPositionBuffer = new Array();
var cubeVertexNormalBuffer = new Array();
var cubeVertexIndexBuffer = new Array();
var cubeVertexTextureCoordBuffer = new Array();
var tractorVertexPositionBuffer = new Array();
var tractorVertexNormalBuffer = new Array();
var tractorVertexTextureCoordBuffer = new Array();
var tractorVertexIndexBuffer = new Array();
var seagulVertexPositionBuffer = new Array();
var seagulVertexNormalBuffer = new Array();
var seagulVertexTextureCoordBuffer = new Array();
var seagulVertexIndexBuffer = new Array();
var textures = new Array();
var pMatrix;
var mvMatrix;
var normalMatrix;
var lastTime = 0;
var commands = new Array();
var seagulAngle = 0;
var tractorx = 0;
var pitch = 0;
var yaw = 0;
var xPos = 0;
var yStart = -1.5;
var yPos = yStart;
var zPos = 10;
var speed = 0;
var lspeed = 0;
var pitchRate = 0;
var yawRate = 0;
var joggingAngle = 0;

function Test_va2Drawer() {
}

Test_va2Drawer.prototype = {

	initShaders : function(gl) {
		var shaderProgram = getShaderProgram(gl, "shaders/shader6.fs", "shaders/shader6.vs");

		vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);

		vertexNormalAttribute = gl.getAttribLocation(shaderProgram, "aVertexNormal");
		gl.enableVertexAttribArray(vertexNormalAttribute);

		shaderProgram.textureCoordAttribute = gl.getAttribLocation(shaderProgram, "aTextureCoord");
		gl.enableVertexAttribArray(textureCoordAttribute);

		pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
		nMatrixUniform = gl.getUniformLocation(shaderProgram, "uNMatrix");
		samplerUniform = gl.getUniformLocation(shaderProgram, "uSampler");
		ambientColorUniform = gl.getUniformLocation(shaderProgram, "uAmbientColor");
		pointLightingLocationUniform = gl.getUniformLocation(shaderProgram, "uPointLightingLocation");
		pointLightingSpecularColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingSpecularColor");
		pointLightingDiffuseColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingDiffuseColor");
		materialShininessUniform = gl.getUniformLocation(shaderProgram, "uMaterialShininess");
		alphaUniform = gl.getUniformLocation(shaderProgram, "uAlpha");
	},

	setMatrixUniforms : function(gl) {
		gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
		gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
		gl.uniformMatrix3fv(nMatrixUniform, false, normalMatrix);
	},

	initBuffers : function(gl) {

		pyramidVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		var vertices1 = [ 0.0, 1.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, 0.0, 1.0, 0.0, -1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 0.0, 1.0, 0.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices1), gl.STATIC_DRAW);
		pyramidVertexPositionBuffer[1] = 3;
		pyramidVertexPositionBuffer[2] = 12;

		pyramidVertexNormalBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexNormalBuffer[0]);
		var normals1 = [ -0.89442719099991587856366946749251, 0.44721359549995793928183473374626, 0.0, -0.89442719099991587856366946749251, 0.44721359549995793928183473374626, 0.0, -0.89442719099991587856366946749251, 0.44721359549995793928183473374626, 0.0, 0.0, 0.44721359549995793928183473374626, -0.89442719099991587856366946749251, 0.0, 0.44721359549995793928183473374626, -0.89442719099991587856366946749251, 0.0, 0.44721359549995793928183473374626, -0.89442719099991587856366946749251, 0.0, 0.44721359549995793928183473374626, 0.89442719099991587856366946749251, 0.0, 0.44721359549995793928183473374626, 0.89442719099991587856366946749251, 0.0, 0.44721359549995793928183473374626, 0.89442719099991587856366946749251, 0.89442719099991587856366946749251, 0.44721359549995793928183473374626, 0.0, 0.89442719099991587856366946749251, 0.44721359549995793928183473374626, 0.0, 0.89442719099991587856366946749251, 0.44721359549995793928183473374626, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(normals1), gl.STATIC_DRAW);
		pyramidVertexNormalBuffer[1] = 3;
		pyramidVertexNormalBuffer[2] = 12;

		pyramidVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		var textureCoords1 = [ 3.0, 0.0, 0.0, 6.0, 6.0, 6.0, 3.0, 0.0, 0.0, 6.0, 6.0, 6.0, 3.0, 0.0, 6.0, 6.0, 0.0, 6.0, 3.0, 0.0, 0.0, 6.0, 6.0, 6.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords1), gl.STATIC_DRAW);
		pyramidVertexTextureCoordBuffer[1] = 2;
		pyramidVertexTextureCoordBuffer[2] = 12;

		pyramidVertexIndexBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, pyramidVertexIndexBuffer[0]);
		var cubeVertexIndices = [ 0, 1, 2, 3, 5, 4, 6, 7, 8, 9, 10, 11 ];
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(cubeVertexIndices), gl.STATIC_DRAW);
		pyramidVertexIndexBuffer[1] = 1;
		pyramidVertexIndexBuffer[2] = 12;

		cubeVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		var vertices2 = [ -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices2), gl.STATIC_DRAW);
		cubeVertexPositionBuffer[1] = 3;
		cubeVertexPositionBuffer[2] = 16;

		cubeVertexNormalBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexNormalBuffer[0]);
		var normals2 = [ 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(normals2), gl.STATIC_DRAW);
		cubeVertexNormalBuffer[1] = 3;
		cubeVertexNormalBuffer[2] = 16;

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

		greenVertexNormalBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexNormalBuffer[0]);
		var normals3 = [ 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(normals3), gl.STATIC_DRAW);
		greenVertexNormalBuffer[1] = 3;
		greenVertexNormalBuffer[2] = 4;

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

		doorVertexNormalBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexNormalBuffer[0]);
		var normals4 = [ 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(normals4), gl.STATIC_DRAW);
		doorVertexNormalBuffer[1] = 3;
		doorVertexNormalBuffer[2] = 4;

		doorVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		var textureCoords4 = [ 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0 ];
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords4), gl.STATIC_DRAW);
		doorVertexTextureCoordBuffer[1] = 2;
		doorVertexTextureCoordBuffer[2] = 4;

		var tractor = loadObj("models/tractor.obj");

		tractorVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, tractorVertexPositionBuffer[0]);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(tractor.vertexPositions), gl.STATIC_DRAW);
		tractorVertexPositionBuffer[1] = 3;
		tractorVertexPositionBuffer[2] = tractor.vertexPositions.length / 3;

		tractorVertexNormalBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, tractorVertexNormalBuffer[0]);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(tractor.vertexNormals), gl.STATIC_DRAW);
		tractorVertexNormalBuffer[1] = 3;
		tractorVertexNormalBuffer[2] = tractor.vertexNormals.length / 3;

		tractorVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, tractorVertexTextureCoordBuffer[0]);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(tractor.vertexTextureCoords), gl.STATIC_DRAW);
		tractorVertexTextureCoordBuffer[1] = 2;
		tractorVertexTextureCoordBuffer[2] = tractor.vertexTextureCoords.length / 2;

		tractorVertexIndexBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, tractorVertexIndexBuffer[0]);
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(tractor.indices), gl.STATIC_DRAW);
		tractorVertexIndexBuffer[1] = 1;
		tractorVertexIndexBuffer[2] = tractor.indices.length;

		var seagul = loadObj("models/seagul.obj");

		seagulVertexPositionBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, seagulVertexPositionBuffer[0]);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(seagul.vertexPositions), gl.STATIC_DRAW);
		seagulVertexPositionBuffer[1] = 3;
		seagulVertexPositionBuffer[2] = seagul.vertexPositions.length / 3;

		seagulVertexNormalBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, seagulVertexNormalBuffer[0]);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(seagul.vertexNormals), gl.STATIC_DRAW);
		seagulVertexNormalBuffer[1] = 3;
		seagulVertexNormalBuffer[2] = seagul.vertexNormals.length / 3;

		seagulVertexTextureCoordBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ARRAY_BUFFER, seagulVertexTextureCoordBuffer[0]);
		gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(seagul.vertexTextureCoords), gl.STATIC_DRAW);
		seagulVertexTextureCoordBuffer[1] = 2;
		seagulVertexTextureCoordBuffer[2] = seagul.vertexTextureCoords.length / 2;

		seagulVertexIndexBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, seagulVertexIndexBuffer[0]);
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(seagul.indices), gl.STATIC_DRAW);
		seagulVertexIndexBuffer[1] = 1;
		seagulVertexIndexBuffer[2] = seagul.indices.length;

	},

	initTexture : function(gl) {
		for ( var i = 0; i < 6; i++) {
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
		}
		tex4.src = "images/door.gif";

		var tex5 = new Image();
		tex5.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[4]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex5);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.MIRRORED_REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.MIRRORED_REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);
		}
		tex5.src = "images/tractor.gif";

		var tex6 = new Image();
		tex6.onload = function() {
			gl.bindTexture(gl.TEXTURE_2D, textures[5]);
			gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex6);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
			gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
			gl.generateMipmap(gl.TEXTURE_2D);
			gl.bindTexture(gl.TEXTURE_2D, null);
		}
		tex6.src = "images/seagul.gif";
	},

	drawScene : function(gl) {
		gl.clearColor(0.0, 0.71, 0.91, 1.0);
		gl.enable(gl.DEPTH_TEST);
		gl.cullFace(gl.BACK);
		gl.enable(gl.CULL_FACE);

		this.handleKeys();
		gl.viewport(0, 0, 500, 500);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

		var cx = Math.cos(-pitch);
		var sx = Math.sin(-pitch);
		var cy = Math.cos(-yaw);
		var sy = Math.sin(-yaw);

		pMatrix = new Float32Array([ 2.4142136573791504, 0, 0, 0, 0, 2.4142136573791504, 0, 0, 0, 0, -1.0020020008087158, -1, 0, 0, -0.20020020008087158, 0 ]);

		mvMatrix = new Float32Array([ cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, -cy * xPos - sy * zPos, -cx - sx * sy * xPos - cx * yPos + cy * sx * zPos, -sx + cx * sy * xPos - sx * yPos - cx * cy * zPos, 1 ]);

		normalMatrix = new Float32Array(9);
		for ( var i = 0; i < 3; i++) {
			for ( var j = 0; j < 3; j++) {
				normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
			}
		}

		gl.uniform3f(ambientColorUniform, 0.1, 0.1, 0.1);

		var lightLoc = new Float32Array([ 0, 4, 2, 1 ]);
		var tlightLoc = multiplyMatrixVector(pMatrix, multiplyMatrixVector(mvMatrix, lightLoc));
		gl.uniform3f(pointLightingLocationUniform, tlightLoc[0], tlightLoc[1], tlightLoc[2]);

		gl.uniform3f(pointLightingDiffuseColorUniform, 0.5, 0.5, 0.5);
		gl.uniform3f(pointLightingSpecularColorUniform, 0.5, 0.5, 0.5);

		gl.uniform1f(materialShininessUniform, 50);
		gl.uniform1f(alphaUniform, 1);

		gl.frontFace(gl.CCW);
		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, cubeVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, cubeVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

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

		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, doorVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, doorVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.activeTexture(gl.TEXTURE0);
		gl.bindTexture(gl.TEXTURE_2D, textures[3]);
		gl.uniform1i(samplerUniform, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_STRIP, 0, doorVertexPositionBuffer[2]);

		gl.disable(gl.CULL_FACE);

		mvMatrix2 = new Float32Array(mvMatrix);
		mvMatrix[0] *= 10;
		mvMatrix[1] *= 10;
		mvMatrix[2] *= 10;
		mvMatrix[8] *= 10;
		mvMatrix[9] *= 10;
		mvMatrix[10] *= 10;

		normalMatrix = new Float32Array(9);
		for ( var i = 0; i < 3; i++) {
			for ( var j = 0; j < 3; j++) {
				normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
			}
		}

		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, greenVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, greenVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, greenVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.activeTexture(gl.TEXTURE0);
		gl.bindTexture(gl.TEXTURE_2D, textures[2]);
		gl.uniform1i(samplerUniform, 0);

		this.setMatrixUniforms(gl);
		gl.drawArrays(gl.TRIANGLE_STRIP, 0, greenVertexPositionBuffer[2]);

		mvMatrix = new Float32Array(mvMatrix2);

		gl.enable(gl.CULL_FACE);

		mvMatrix[13] += 2 * cx;
		mvMatrix[14] += 2 * sx;

		normalMatrix = new Float32Array(9);
		for ( var i = 0; i < 3; i++) {
			for ( var j = 0; j < 3; j++) {
				normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
			}
		}

		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, pyramidVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, pyramidVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, pyramidVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.activeTexture(gl.TEXTURE0);
		gl.bindTexture(gl.TEXTURE_2D, textures[1]);
		gl.uniform1i(samplerUniform, 0);

		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, pyramidVertexIndexBuffer[0]);
		this.setMatrixUniforms(gl);
		gl.drawElements(gl.TRIANGLES, pyramidVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

		mvMatrix = new Float32Array(mvMatrix2);
		for ( var i = 0; i < 12; i++) {
			mvMatrix[i] *= 0.01;
		}
		var xt = -11 + tractorx;
		var yt = -1;
		var zt = 4;
		mvMatrix[12] += cy * xt + sy * zt;
		mvMatrix[13] += sx * sy * xt + cx * yt - cy * sx * zt;
		mvMatrix[14] += -cx * sy * xt + sx * yt + cx * cy * zt;

		normalMatrix = new Float32Array(9);
		for ( var i = 0; i < 3; i++) {
			for ( var j = 0; j < 3; j++) {
				normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
			}
		}

		gl.bindBuffer(gl.ARRAY_BUFFER, tractorVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, tractorVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, tractorVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, tractorVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, tractorVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, tractorVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindTexture(gl.TEXTURE_2D, textures[4]);
		gl.uniform1i(samplerUniform, 0);

		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, tractorVertexIndexBuffer[0]);
		this.setMatrixUniforms(gl);
		gl.drawElements(gl.TRIANGLES, tractorVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

		mvMatrix = mvMatrix2;
		var cs = Math.cos(seagulAngle);
		var ss = Math.sin(seagulAngle);
		xt = -3 * cs - xPos;
		yt = 2 - yPos;
		zt = 4 * ss - zPos;
		var s = 0.3;
		mvMatrix[0] = cs * cy * s - s * ss * sy;
		mvMatrix[1] = cy * s * ss * sx + cs * s * sx * sy;
		mvMatrix[2] = -cx * cy * s * ss - cs * cx * s * sy;
		mvMatrix[3] = 0;
		mvMatrix[4] = 0;
		mvMatrix[5] = cx * s;
		mvMatrix[6] = s * sx;
		mvMatrix[7] = 0;
		mvMatrix[8] = cy * s * ss + cs * s * sy;
		mvMatrix[9] = -cs * cy * s * sx + s * ss * sx * sy;
		mvMatrix[10] = cs * cx * cy * s - cx * s * ss * sy;
		mvMatrix[11] = 0;
		mvMatrix[12] = cy * xt + sy * zt;
		mvMatrix[13] = -cx + sx * sy * xt + cx * yt - cy * sx * zt;
		mvMatrix[14] = -sx - cx * sy * xt + sx * yt + cx * cy * zt;
		mvMatrix[15] = 1;

		normalMatrix = new Float32Array(9);
		for ( var i = 0; i < 3; i++) {
			for ( var j = 0; j < 3; j++) {
				normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
			}
		}

		gl.bindBuffer(gl.ARRAY_BUFFER, seagulVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, seagulVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, seagulVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, seagulVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, seagulVertexTextureCoordBuffer[0]);
		gl.vertexAttribPointer(textureCoordAttribute, seagulVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindTexture(gl.TEXTURE_2D, textures[5]);
		gl.uniform1i(samplerUniform, 0);

		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, seagulVertexIndexBuffer[0]);
		this.setMatrixUniforms(gl);
		gl.drawElements(gl.TRIANGLES, seagulVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

		this.animate();
	},

	animate : function() {
		var timeNow = new Date().getTime();
		if (lastTime != 0) {
			var elapsed = timeNow - lastTime;
			var move = false;
			if (speed != 0) {
				xPos -= Math.sin(yaw) * speed * elapsed;
				zPos -= Math.cos(yaw) * speed * elapsed;
				move = true;
			}
			if (lspeed != 0) {
				xPos += Math.cos(yaw) * lspeed * elapsed;
				zPos -= Math.sin(yaw) * lspeed * elapsed;
				move = true;
			}

			if (move) {
				joggingAngle += elapsed * 0.0104;
				yPos = Math.sin(joggingAngle) / 20 + yStart;
			}

			yaw += yawRate * elapsed;
			pitch += pitchRate * elapsed;

			seagulAngle += 0.0008 * elapsed;
			tractorx += 0.0008 * elapsed;
			if (tractorx > 22) {
				tractorx = 0;
			}
		}
		lastTime = timeNow;
	},

	handleKeys : function() {
		if (commands[0]) {
			speed = 0.003;
		} else if (commands[1]) {
			speed = -0.003;
		} else {
			speed = 0;
		}

		if (commands[2]) {
			pitchRate = 0.00174;
		} else if (commands[3]) {
			pitchRate = -0.00174;
		} else {
			pitchRate = 0;
		}

		if (commands[4]) {
			yawRate = 0.00174;
		} else if (commands[5]) {
			yawRate = -0.00174;
		} else {
			yawRate = 0;
		}

		if (commands[6]) {
			lspeed = 0.003;
		} else if (commands[7]) {
			lspeed = -0.003;
		} else {
			lspeed = 0;
		}
	},

	setCommands : function(e, value) {
		if (e.keyCode == 87) {
			commands[0] = value;
		}
		if (e.keyCode == 83) {
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
		if (e.keyCode == 68) {
			commands[6] = value;
		}
		if (e.keyCode == 65) {
			commands[7] = value;
		}
	},

	keyPressed : function(e) {
		this.setCommands(e, true);
	},

	keyReleased : function(e) {
		this.setCommands(e, false);
	}
}