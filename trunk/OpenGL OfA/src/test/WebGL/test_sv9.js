var gl;

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
var sphereVertexPositionBuffer = new Array();
var sphereVertexNormalBuffer = new Array();
var sphereVertexIndexBuffer = new Array();
var sphereVertexTextureCoordBuffer = new Array();
var textures = new Array();
var pMatrix;
var mvMatrix;
var normalMatrix;
var zoom = -8;
var lastTime2 = 0;
var commands = new Array();
var lastMouseX = -1;
var lastMouseY = -1;
var rotationMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 ]);
var mouseDown = false;

function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	initShaders()
	initBuffers();
	initTexture()

	gl.clearColor(0.0, 0.0, 0.0, 1.0);
	gl.blendFunc(gl.SRC_ALPHA, gl.ONE);
	gl.enable(gl.BLEND);
	tick();

	document.onkeydown = keyPressed;
	document.onkeyup = keyReleased;
	canvas.onmousedown = mousePressed;
	document.onmouseup = mouseReleased;
	document.onmousemove = mouseMoved;
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
	var fragmentShader = getShader(gl, "shaders/shader6.fs", gl.FRAGMENT_SHADER);
	var vertexShader = getShader(gl, "shaders/shader6.vs", gl.VERTEX_SHADER);

	var shaderProgram = gl.createProgram();
	gl.attachShader(shaderProgram, vertexShader);
	gl.attachShader(shaderProgram, fragmentShader);
	gl.linkProgram(shaderProgram);

	if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
		alert("Could not initialise shaders");
	}

	gl.useProgram(shaderProgram);

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
	pointLightingLocationUniform = gl.getUniformLocation(shaderProgram, "uPointLightingLocation");
	pointLightingSpecularColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingSpecularColor");
	pointLightingDiffuseColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingDiffuseColor");
	materialShininessUniform = gl.getUniformLocation(shaderProgram, "uMaterialShininess");
	alphaUniform = gl.getUniformLocation(shaderProgram, "uAlpha");
}

function setMatrixUniforms() {
	gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
	gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
	gl.uniformMatrix3fv(nMatrixUniform, false, normalMatrix);

	gl.uniform3f(ambientColorUniform, 0.2, 0.2, 0.2);

	gl.uniform3f(pointLightingLocationUniform, -1, 0, -5.1);

	gl.uniform3f(pointLightingDiffuseColorUniform, 0.9, 0.9, 0.9);
	gl.uniform3f(pointLightingSpecularColorUniform, 0.7, 0.7, 0.7);

	gl.uniform1f(materialShininessUniform, 50);
	gl.uniform1f(alphaUniform, 0.5);
}

function initBuffers() {

	var latitudeBands = 30;
	var longitudeBands = 30;
	var radius = 2;

	var vertices = new Array();
	var normals = new Array();
	var textureCoords = new Array();
	var sphereVertexIndices = new Array();

	for ( var i = 0; i <= latitudeBands; i++) {
		var theta = i * Math.PI / latitudeBands;
		var sinTheta = Math.sin(theta);
		var cosTheta = Math.cos(theta);
		for ( var j = 0; j <= longitudeBands; j++) {
			var phi = (j * 2 * Math.PI / longitudeBands);
			var sinPhi = Math.sin(phi);
			var cosPhi = Math.cos(phi);

			var x = cosPhi * sinTheta;
			var y = cosTheta;
			var z = sinPhi * sinTheta;
			var u = 1 - j / longitudeBands;
			var v = 1 - i / latitudeBands;

			var index = 3 * (j + i * (longitudeBands + 1));
			vertices[index] = radius * x;
			vertices[index + 1] = radius * y;
			vertices[index + 2] = radius * z;
			normals[index] = x;
			normals[index + 1] = y;
			normals[index + 2] = z;
			index = 2 * (j + i * (longitudeBands + 1));
			textureCoords[index] = u;
			textureCoords[index + 1] = v;
		}
	}

	for ( var i = 0; i < latitudeBands; i++) {
		for ( var j = 0; j < longitudeBands; j++) {
			var first = (i * (longitudeBands + 1)) + j;
			var second = first + longitudeBands + 1;

			var index = 6 * (j + i * longitudeBands);

			sphereVertexIndices[index] = first;
			sphereVertexIndices[index + 1] = second;
			sphereVertexIndices[index + 2] = first + 1;
			sphereVertexIndices[index + 3] = second;
			sphereVertexIndices[index + 4] = second + 1;
			sphereVertexIndices[index + 5] = first + 1;
		}
	}

	sphereVertexPositionBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexPositionBuffer[0]);
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
	sphereVertexPositionBuffer[1] = 3;
	sphereVertexPositionBuffer[2] = vertices.length / 3;

	sphereVertexNormalBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexNormalBuffer[0]);
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(normals), gl.STATIC_DRAW);
	sphereVertexNormalBuffer[1] = 3;
	sphereVertexNormalBuffer[2] = vertices.length / 3;

	sphereVertexTextureCoordBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexTextureCoordBuffer[0]);
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(textureCoords), gl.STATIC_DRAW);
	sphereVertexTextureCoordBuffer[1] = 2;
	sphereVertexTextureCoordBuffer[2] = textureCoords.length / 2;

	sphereVertexIndexBuffer[0] = gl.createBuffer();
	gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
	gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(sphereVertexIndices), gl.STATIC_DRAW);
	sphereVertexIndexBuffer[1] = 1;
	sphereVertexIndexBuffer[2] = sphereVertexIndices.length;
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

	tex.src = "images/bubble.gif";
}

function drawScene() {
	handleKeys();
	gl.viewport(0, 0, gl.viewportWidth, gl.viewportHeight);
	gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

	pMatrix = new Float32Array([ 2.4142136573791504, 0, 0, 0, 0, 2.4142136573791504, 0, 0, 0, 0, -1.0020020008087158, -1, 0, 0, -0.20020020008087158, 0 ]);

	mvMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, zoom, 1 ]);
	mvMatrix = multiplyMatrix(rotationMatrix, mvMatrix);

	normalMatrix = new Float32Array(9);
	for ( var i = 0; i < 3; i++) {
		for ( var j = 0; j < 3; j++) {
			normalMatrix[i + 3 * j] = mvMatrix[i + 4 * j];
		}
	}

	gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexPositionBuffer[0]);
	gl.vertexAttribPointer(vertexPositionAttribute, sphereVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexNormalBuffer[0]);
	gl.vertexAttribPointer(vertexNormalAttribute, sphereVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexTextureCoordBuffer[0]);
	gl.vertexAttribPointer(textureCoordAttribute, sphereVertexTextureCoordBuffer[1], gl.FLOAT, false, 0, 0);

	gl.activeTexture(gl.TEXTURE0);
	gl.bindTexture(gl.TEXTURE_2D, textures[0]);
	gl.uniform1i(samplerUniform, 0);

	gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
	setMatrixUniforms();
	gl.drawElements(gl.TRIANGLES, sphereVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

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
}

function keyPressed(e) {
	setCommands(e, true);
}

function keyReleased(e) {
	setCommands(e, false);
}

function mousePressed(e) {
	mouseDown = true;
	lastMouseX = e.clientX;
	lastMouseY = e.clientY;
}

function mouseReleased(e) {
	mouseDown = false;
}

function mouseMoved(e) {
	if (!mouseDown) {
		return;
	}
	var deltaX = e.clientX - lastMouseX;
	var deltaY = e.clientY - lastMouseY;

	var rAnglex = deltaY * 0.01;
	var rAngley = deltaX * 0.01;

	var cx = Math.cos(rAnglex);
	var sx = Math.sin(rAnglex);
	var cy = Math.cos(rAngley);
	var sy = Math.sin(rAngley);

	var newRotationMatrix = new Float32Array([ cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, 0, 0, 1 ]);

	rotationMatrix = multiplyMatrix(rotationMatrix, newRotationMatrix);

	lastMouseX = e.clientX;
	lastMouseY = e.clientY;
}

function multiplyMatrix(a, b) {
	var c = new Float32Array(16);
	c[0] = a[0] * b[0] + a[1] * b[4] + a[2] * b[8] + a[3] * b[12];
	c[4] = a[4] * b[0] + a[5] * b[4] + a[6] * b[8] + a[7] * b[12];
	c[8] = a[8] * b[0] + a[9] * b[4] + a[10] * b[8] + a[11] * b[12];
	c[12] = a[12] * b[0] + a[13] * b[4] + a[14] * b[8] + a[15] * b[12];
	c[1] = a[0] * b[1] + a[1] * b[5] + a[2] * b[9] + a[3] * b[13];
	c[5] = a[4] * b[1] + a[5] * b[5] + a[6] * b[9] + a[7] * b[13];
	c[9] = a[8] * b[1] + a[9] * b[5] + a[10] * b[9] + a[11] * b[13];
	c[13] = a[12] * b[1] + a[13] * b[5] + a[14] * b[9] + a[15] * b[13];
	c[2] = a[0] * b[2] + a[1] * b[6] + a[2] * b[10] + a[3] * b[14];
	c[6] = a[4] * b[2] + a[5] * b[6] + a[6] * b[10] + a[7] * b[14];
	c[10] = a[8] * b[2] + a[9] * b[6] + a[10] * b[10] + a[11] * b[14];
	c[14] = a[12] * b[2] + a[13] * b[6] + a[14] * b[10] + a[15] * b[14];
	c[3] = a[0] * b[3] + a[1] * b[7] + a[2] * b[11] + a[3] * b[15];
	c[7] = a[4] * b[3] + a[5] * b[7] + a[6] * b[11] + a[7] * b[15];
	c[11] = a[8] * b[3] + a[9] * b[7] + a[10] * b[11] + a[11] * b[15];
	c[15] = a[12] * b[3] + a[13] * b[7] + a[14] * b[11] + a[15] * b[15];
	return c;
}