var gl;

var vertexPositionAttribute;
var vertexNormalAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var nMatrixUniform;
var ambientColorUniform;
var pointLightingLocationUniform;
var pointLightingSpecularColorUniform;
var pointLightingDiffuseColorUniform;
var materialShininessUniform;
var teapotVertexPositionBuffer = new Array();
var teapotVertexNormalBuffer = new Array();
var teapotVertexIndexBuffer = new Array();
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
	var shaderProgram = getShaderProgram(gl, "shaders/shader5.fs", "shaders/shader5.vs");

	vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
	gl.enableVertexAttribArray(vertexPositionAttribute);

	vertexNormalAttribute = gl.getAttribLocation(shaderProgram, "aVertexNormal");
	gl.enableVertexAttribArray(vertexNormalAttribute);

	pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
	mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
	nMatrixUniform = gl.getUniformLocation(shaderProgram, "uNMatrix");
	ambientColorUniform = gl.getUniformLocation(shaderProgram, "uAmbientColor");
	pointLightingLocationUniform = gl.getUniformLocation(shaderProgram, "uPointLightingLocation");
	pointLightingSpecularColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingSpecularColor");
	pointLightingDiffuseColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingDiffuseColor");
	materialShininessUniform = gl.getUniformLocation(shaderProgram, "uMaterialShininess");
}

function setMatrixUniforms() {
	gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
	gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
	gl.uniformMatrix3fv(nMatrixUniform, false, normalMatrix);

	gl.uniform3f(ambientColorUniform, 0.1, 0.1, 0.1);

	gl.uniform3f(pointLightingLocationUniform, -1, 0, -5.1);

	gl.uniform3f(pointLightingDiffuseColorUniform, 0.7, 0.7, 0.7);
	gl.uniform3f(pointLightingSpecularColorUniform, 0.7, 0.7, 0.7);

	gl.uniform1f(materialShininessUniform, 10);
}

function initBuffers() {

	var teapot = loadObj("models/teapot.obj");

	teapotVertexPositionBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexPositionBuffer[0]);
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(teapot.vertexPositions), gl.STATIC_DRAW);
	teapotVertexPositionBuffer[1] = 3;
	teapotVertexPositionBuffer[2] = teapot.vertexPositions.length / 3;

	teapotVertexNormalBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexNormalBuffer[0]);
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(teapot.vertexNormals), gl.STATIC_DRAW);
	teapotVertexNormalBuffer[1] = 3;
	teapotVertexNormalBuffer[2] = teapot.vertexNormals.length / 3;

	teapotVertexIndexBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, teapotVertexIndexBuffer[0]);
	gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(teapot.indices), gl.STATIC_DRAW);
	teapotVertexIndexBuffer[1] = 1;
	teapotVertexIndexBuffer[2] = teapot.indices.length;
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
	for ( var i = 0; i < 3; i++) {
		for ( var j = 0; j < 3; j++) {
			normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
		}
	}

	gl.frontFace(gl.CCW);
	gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexPositionBuffer[0]);
	gl.vertexAttribPointer(vertexPositionAttribute, teapotVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, teapotVertexNormalBuffer[0]);
	gl.vertexAttribPointer(vertexNormalAttribute, teapotVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, teapotVertexIndexBuffer[0]);
	setMatrixUniforms();
	gl.drawElements(gl.TRIANGLES, teapotVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

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