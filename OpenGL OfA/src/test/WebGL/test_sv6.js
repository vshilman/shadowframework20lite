var gl;

var vertexPositionAttribute;
var vertexNormalAttribute;
var textureCoordAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var nMatrixUniform;
var samplerUniform;
var ambientColorUniform;
var lightingDirectionUniform;
var directionalColorUniform;
var cubeVertexPositionBuffer = new Array();
var cubeVertexNormalBuffer = new Array();
var cubeVertexIndexBuffer = new Array();
var cubeVertexTextureCoordBuffer = new Array();
var textures = new Array();
var pMatrix;
var mvMatrix;
var normalMatrix;
var rAnglex = 0;
var speedx = 0;
var rAngley = 0;
var speedy = 0;
var zoom = -8;
var lastTime = 0;
var lastTime2 = 0;
var commands = new Array();

function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	initShaders()
	initBuffers();
	initTexture();

	gl.clearColor(0.0, 0.0, 0.0, 1.0);
	gl.enable(gl.DEPTH_TEST);
	gl.cullFace(gl.BACK);
	gl.enable(gl.CULL_FACE);
	tick();

	document.onkeydown = keyPressed;
	document.onkeyup = keyReleased;
}

function tick() {
	requestAnimFrame(tick);
	drawScene();
}

function initGL(canvas) {
	try {
		gl = canvas.getContext("experimental-webgl");
		gl.viewportWidth = canvas.width;
		gl.viewportHeight = canvas.height;
	} catch (e) {
	}
	if (!gl) {
		alert("Could not initialise WebGL, sorry :-(");
	}
}

function initShaders() {
	var shaderProgram = getShaderProgram(gl, "shaders/shader3.fs", "shaders/shader3.vs");

	vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
	gl.enableVertexAttribArray(vertexPositionAttribute);

	vertexNormalAttribute = gl.getAttribLocation(shaderProgram, "aVertexNormal");
	gl.enableVertexAttribArray(vertexNormalAttribute);

	textureCoordAttribute = gl.getAttribLocation(shaderProgram, "aTextureCoord");
	gl.enableVertexAttribArray(textureCoordAttribute);

	pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
	mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
	nMatrixUniform = gl.getUniformLocation(shaderProgram, "uNMatrix");
	samplerUniform = gl.getUniformLocation(shaderProgram, "uSampler");
	ambientColorUniform = gl.getUniformLocation(shaderProgram, "uAmbientColor");
	lightingDirectionUniform = gl.getUniformLocation(shaderProgram, "uLightingDirection");
	directionalColorUniform = gl.getUniformLocation(shaderProgram, "uDirectionalColor");
}

function setMatrixUniforms() {
	gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
	gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
	gl.uniformMatrix3fv(nMatrixUniform, false, normalMatrix);

	gl.uniform3f(ambientColorUniform, 0.1, 0.1, 0.1);

	var lightingDirection = [ 0.57735026918962576450914878050196, 0.57735026918962576450914878050196, 0.57735026918962576450914878050196 ];
	gl.uniform3fv(lightingDirectionUniform, lightingDirection);

	gl.uniform3f(directionalColorUniform, 0.7, 0.7, 0.7);
}

function initBuffers() {

	cubeVertexPositionBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
	var vertices = [ -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0 ];
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
	cubeVertexPositionBuffer[1] = 3;
	cubeVertexPositionBuffer[2] = 24;

	cubeVertexNormalBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, cubeVertexNormalBuffer[0]);
	var normals = [ 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0 ];
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(normals), gl.STATIC_DRAW);
	cubeVertexNormalBuffer[1] = 3;
	cubeVertexNormalBuffer[2] = 24;

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
}

function initTexture() {
	textures[0] = gl.createTexture();
	var tex = new Image();
	tex.onload = function() {
		gl.bindTexture(gl.TEXTURE_2D, textures[0]);
		gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGB, gl.RGB, gl.UNSIGNED_BYTE, tex);
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.LINEAR);
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR_MIPMAP_LINEAR);
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.REPEAT);
		gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.REPEAT);
		gl.generateMipmap(gl.TEXTURE_2D);

		gl.bindTexture(gl.TEXTURE_2D, null);
	}

	tex.src = "images/muro.gif";
}

function drawScene() {
	handleKeys();
	gl.viewport(0, 0, gl.viewportWidth, gl.viewportHeight);
	gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

	var cx = Math.cos(rAnglex);
	var sx = Math.sin(rAnglex);
	var cy = Math.cos(rAngley);
	var sy = Math.sin(rAngley);

	pMatrix = new Float32Array([ 2.4142136573791504, 0, 0, 0, 0, 2.4142136573791504, 0, 0, 0, 0, -1.0020020008087158, -1, 0, 0, -0.20020020008087158, 0 ]);

	mvMatrix = new Float32Array([ cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, 0, zoom, 1 ]);
	
	normalMatrix = new Float32Array(9);
	for (var i = 0; i < 3; i++) {
		for (var j = 0; j < 3; j++) {
			normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
		}
	}

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
	setMatrixUniforms();
	gl.drawElements(gl.TRIANGLES, cubeVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

	animate();
}

function animate() {
	var timeNow = new Date().getTime();
	if (lastTime != 0) {
		var elapsed = timeNow - lastTime;
		rAnglex += speedx * elapsed * 0.001;
		rAngley += speedy * elapsed * 0.001;
	}
	lastTime = timeNow;
}

function handleKeys() {
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
}

function setCommands(e, value) {
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
}

function keyPressed(e) {
	setCommands(e, true);
}

function keyReleased(e) {
	setCommands(e, false);
}