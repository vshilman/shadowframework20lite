var vertexPositionAttribute;
var vertexNormalAttribute;
var pMatrixUniform;
var mvMatrixUniform;
var nMatrixUniform;
var ambientColorUniform;
var pointLightingLocationUniform;
var pointLightingColorUniform;
var shaderUniform;
var shaderChoice = 0;
var sphereVertexPositionBuffer = new Array();
var sphereVertexNormalBuffer = new Array();
var sphereVertexIndexBuffer = new Array();
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

function Test_sv7Drawer() {
}

Test_sv7Drawer.prototype = {

	initShaders : function(gl) {
		var shaderProgram = getShaderProgram(gl, "shaders/shader4.fs", "shaders/shader4.vs");

		vertexPositionAttribute = gl.getAttribLocation(shaderProgram, "aVertexPosition");
		gl.enableVertexAttribArray(vertexPositionAttribute);

		vertexNormalAttribute = gl.getAttribLocation(shaderProgram, "aVertexNormal");
		gl.enableVertexAttribArray(vertexNormalAttribute);

		pMatrixUniform = gl.getUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.getUniformLocation(shaderProgram, "uMVMatrix");
		nMatrixUniform = gl.getUniformLocation(shaderProgram, "uNMatrix");
		ambientColorUniform = gl.getUniformLocation(shaderProgram, "uAmbientColor");
		pointLightingLocationUniform = gl.getUniformLocation(shaderProgram, "uPointLightingLocation");
		pointLightingColorUniform = gl.getUniformLocation(shaderProgram, "uPointLightingColor");
		shaderUniform = gl.getUniformLocation(shaderProgram, "uShader");
	},

	setMatrixUniforms : function(gl) {
		gl.uniformMatrix4fv(pMatrixUniform, false, pMatrix);
		gl.uniformMatrix4fv(mvMatrixUniform, false, mvMatrix);
		gl.uniformMatrix3fv(nMatrixUniform, false, normalMatrix);

		gl.uniform3f(ambientColorUniform, 0.1, 0.1, 0.1);

		gl.uniform3f(pointLightingLocationUniform, 0, 0, -5.1);

		gl.uniform3f(pointLightingColorUniform, 0.7, 0.7, 0.7);
	},

	initBuffers : function(gl) {

		var latitudeBands = 15;
		var longitudeBands = 15;
		var radius = 2;

		var vertices = new Array();
		var normals = new Array();
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

				var index = 3 * (j + i * (longitudeBands + 1));
				vertices[index] = radius * x;
				vertices[index + 1] = radius * y;
				vertices[index + 2] = radius * z;
				normals[index] = x;
				normals[index + 1] = y;
				normals[index + 2] = z;
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
		sphereVertexNormalBuffer[2] = normals.length / 3;

		sphereVertexIndexBuffer[0] = gl.createBuffer();
		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
		gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array(sphereVertexIndices), gl.STATIC_DRAW);
		sphereVertexIndexBuffer[1] = 1;
		sphereVertexIndexBuffer[2] = sphereVertexIndices.length;
	},

	drawScene : function(gl) {
		gl.clearColor(0.0, 0.0, 0.0, 1.0);
		gl.enable(gl.DEPTH_TEST);
		gl.cullFace(gl.BACK);
		gl.enable(gl.CULL_FACE);

		this.handleKeys();
		gl.viewport(0, 0, 500, 500);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

		gl.uniform1i(shaderUniform, shaderChoice);

		var cx = Math.cos(rAnglex);
		var sx = Math.sin(rAnglex);
		var cy = Math.cos(rAngley);
		var sy = Math.sin(rAngley);

		pMatrix = new Float32Array([ 2.4142136573791504, 0, 0, 0, 0, 2.4142136573791504, 0, 0, 0, 0, -1.0020020008087158, -1, 0, 0, -0.20020020008087158, 0 ]);

		var mvMatrixv = new Float32Array([ cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, 0, zoom, 1 ]);
		mvMatrix = mvMatrixv;
		
		var normalMatrixv = mat3Traspose(mat4ToInverseMat3(mvMatrixv));
		normalMatrix = normalMatrixv;

		gl.frontFace(gl.CW);
		gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexPositionBuffer[0]);
		gl.vertexAttribPointer(vertexPositionAttribute, sphereVertexPositionBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ARRAY_BUFFER, sphereVertexNormalBuffer[0]);
		gl.vertexAttribPointer(vertexNormalAttribute, sphereVertexNormalBuffer[1], gl.FLOAT, false, 0, 0);

		gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
		this.setMatrixUniforms(gl);
		gl.drawElements(gl.TRIANGLES, sphereVertexIndexBuffer[2], gl.UNSIGNED_SHORT, 0);

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
		if (e.keyCode == 83) {
			shaderChoice = shaderChoice == 1 ? 0 : (shaderChoice + 1);
		}
		this.setCommands(e, true);
	},

	keyReleased : function(e) {
		this.setCommands(e, false);
	}
}