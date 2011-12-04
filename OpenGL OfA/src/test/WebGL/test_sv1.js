var gl;

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

function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	initShaders()
	initBuffers();

	gl.clearColor(0.0, 0.0, 0.0, 1.0);
	gl.enable(gl.DEPTH_TEST);

	tick();
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
	var fragmentShader = getShader(gl, "shaders/shader1.fs", gl.FRAGMENT_SHADER);
	var vertexShader = getShader(gl, "shaders/shader1.vs", gl.VERTEX_SHADER);

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

	vertexColorAttribute = gl.getAttribLocation(shaderProgram, "aVertexColor");
	gl.enableVertexAttribArray(vertexColorAttribute);

	pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
	mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
}

function setMatrixUniforms() {
	gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
	gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
}

function initBuffers() {
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
	var colors2 = []
	for ( var i = 0; i < 4; i++) {
		colors2 = colors2.concat([ 1, 0.75, 0.8, 1.0 ]);
	}
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors2), gl.STATIC_DRAW);
	squareVertexColorBuffer[1] = 4;
	squareVertexColorBuffer[2] = 4;
}

function drawScene() {
	gl.viewport(0, 0, gl.viewportWidth, gl.viewportHeight);
	gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

	pMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4 ]);

	mvMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, -1.5, 0, 0, 1 ]);

	gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer[0]);
	gl.vertexAttribPointer(vertexPositionAttribute, triangleVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexColorBuffer[0]);
	gl.vertexAttribPointer(vertexColorAttribute, triangleVertexColorBuffer[1], gl.FLOAT, false, 0, 0);

	setMatrixUniforms();
	gl.drawArrays(gl.TRIANGLES, 0, triangleVertexPositionBuffer[2]);

	mvMatrix = new Float32Array([ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1.5, 0, 0, 1 ]);

	gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer[0]);
	gl.vertexAttribPointer(vertexPositionAttribute, squareVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexColorBuffer[0]);
	gl.vertexAttribPointer(vertexColorAttribute, squareVertexColorBuffer[1], gl.FLOAT, false, 0, 0);

	setMatrixUniforms();
	gl.drawArrays(gl.TRIANGLE_STRIP, 0, squareVertexPositionBuffer[2]);
}