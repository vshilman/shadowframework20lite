var gl;

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

function loadFile(src){
	var testo="Errore lettura file!!";
	var request = new XMLHttpRequest();
	request.open("GET", src,false);
	request.onreadystatechange = function () {
		if (request.readyState == 4) {
			testo=request.responseText;
		}
	}
	request.send();
	return testo;
}


function getShader(gl, src, type) {
	
	var str=loadFile(src);
	
	shader = gl.createShader(type);
	
	gl.shaderSource(shader, str);
	gl.compileShader(shader);

	if (!gl.getShaderParameter(shader, gl.COMPILE_STATUS)) {
		alert(gl.getShaderInfoLog(shader));
		return null;
	}

	return shader;
}


var shaderProgram;

function initShaders() {
	var fragmentShader = getShader(gl,"shaders/shader1.fs", gl.FRAGMENT_SHADER);
    var vertexShader = getShader(gl,"shaders/shader1.vs", gl.VERTEX_SHADER);

	shaderProgram = gl.createProgram();
	gl.attachShader(shaderProgram, vertexShader);
	gl.attachShader(shaderProgram, fragmentShader);
	gl.linkProgram(shaderProgram);

	if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
		alert("Could not initialise shaders");
	}

	gl.useProgram(shaderProgram);

	shaderProgram.vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
	gl.enableVertexAttribArray(shaderProgram.vertexPositionAttribute);

	shaderProgram.vertexColorAttribute = gl.getAttribLocation(shaderProgram, "aVertexColor");
	gl.enableVertexAttribArray(shaderProgram.vertexColorAttribute);

	shaderProgram.pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
	shaderProgram.mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
}


var mvMatrix;
var pMatrix;

function setMatrixUniforms() {
	gl.uniformMatrix4fv(shaderProgram.pMatrixUniform, false, pMatrix);
	gl.uniformMatrix4fv(shaderProgram.mvMatrixUniform, false, mvMatrix);
}


var triangleVertexPositionBuffer;
var triangleVertexColorBuffer;
var squareVertexPositionBuffer;
var squareVertexColorBuffer;

function initBuffers() {
	triangleVertexPositionBuffer = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer);
	var vertices = [
		 0.0,  1.0,  0.0,
		-1.0, -1.0,  0.0,
		 1.0, -1.0,  0.0
	];
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
	triangleVertexPositionBuffer.itemSize = 3;
	triangleVertexPositionBuffer.numItems = 3;

	triangleVertexColorBuffer = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexColorBuffer);
	var colors = [
		1.0, 0.0, 0.0, 1.0,
		0.0, 1.0, 0.0, 1.0,
		0.0, 0.0, 1.0, 1.0
	];
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors), gl.STATIC_DRAW);
	triangleVertexColorBuffer.itemSize = 4;
	triangleVertexColorBuffer.numItems = 3;


	squareVertexPositionBuffer = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer);
	vertices = [
		 1.0,  1.0,  0.0,
		-1.0,  1.0,  0.0,
		 1.0, -1.0,  0.0,
		-1.0, -1.0,  0.0
	];
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);
	squareVertexPositionBuffer.itemSize = 3;
	squareVertexPositionBuffer.numItems = 4;

	squareVertexColorBuffer = gl.createBuffer();
	gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexColorBuffer);
	colors = []
	for (var i=0; i < 4; i++) {
		colors = colors.concat([1, 0.75, 0.8, 1.0]);
	}
	gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(colors), gl.STATIC_DRAW);
	squareVertexColorBuffer.itemSize = 4;
	squareVertexColorBuffer.numItems = 4;
}



function drawScene() {
	gl.viewport(0, 0, gl.viewportWidth, gl.viewportHeight);
	gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

	pMatrix=new Float32Array([ 
		1,0,0,0, 
		0,1,0,0, 
		0,0,1,0, 
		0,0,0,4
	]);
	
	mvMatrix=new Float32Array([ 
		1,0,0,0, 
		0,1,0,0, 
		0,0,1,0,
		-1.5,0,0,1
	]);

	gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexPositionBuffer);
	gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, triangleVertexPositionBuffer.itemSize, gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, triangleVertexColorBuffer);
	gl.vertexAttribPointer(shaderProgram.vertexColorAttribute, triangleVertexColorBuffer.itemSize, gl.FLOAT, false, 0, 0);

	setMatrixUniforms();
	gl.drawArrays(gl.TRIANGLES, 0, triangleVertexPositionBuffer.numItems);

	mvMatrix=new Float32Array([ 
		1,0,0,0, 
		0,1,0,0, 
		0,0,1,0,
		1.5,0,0,1
	]);
	
	gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexPositionBuffer);
	gl.vertexAttribPointer(shaderProgram.vertexPositionAttribute, squareVertexPositionBuffer.itemSize, gl.FLOAT, false, 0, 0);

	gl.bindBuffer(gl.ARRAY_BUFFER, squareVertexColorBuffer);
	gl.vertexAttribPointer(shaderProgram.vertexColorAttribute, squareVertexColorBuffer.itemSize, gl.FLOAT, false, 0, 0);

	setMatrixUniforms();
	gl.drawArrays(gl.TRIANGLE_STRIP, 0, squareVertexPositionBuffer.numItems);
}



function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	initShaders()
	initBuffers();

	gl.clearColor(0.0, 0.0, 0.0, 1.0);
	gl.enable(gl.DEPTH_TEST);

	setInterval(drawScene, 15);
}