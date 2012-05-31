function loadFile(src) {
	var testo = "Errore lettura file!!";
	var request = new XMLHttpRequest();
	request.open("GET", src, false);
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			testo = request.responseText;
		}
	}
	request.send();
	return testo;
}

function getShader(gl, src, type) {

	var str = loadFile(src);

	shader = gl.createShader(type);

	gl.shaderSource(shader, str);
	gl.compileShader(shader);

	if (!gl.getShaderParameter(shader, gl.COMPILE_STATUS)) {
		alert(gl.getShaderInfoLog(shader));
		return null;
	}

	return shader;
}

function getShaderProgram(gl, fragmentShaderFile, vertexShaderFile) {
	var fragmentShader = getShader(gl, fragmentShaderFile, gl.FRAGMENT_SHADER);
	var vertexShader = getShader(gl, vertexShaderFile, gl.VERTEX_SHADER);

	var shaderProgram = gl.createProgram();
	gl.attachShader(shaderProgram, vertexShader);
	gl.attachShader(shaderProgram, fragmentShader);
	gl.linkProgram(shaderProgram);

	if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
		alert("Could not initialise shaders");
	}

	gl.useProgram(shaderProgram);
	return shaderProgram;
}

function loadObj(src) {

	var text = loadFile(src);

	vertexArray = [];
	normalArray = [];
	textureArray = [];
	indexArray = [];

	var vertex = [];
	var normal = [];
	var texture = [];
	var facemap = {};
	var index = 0;

	var lines = text.split("\n");
	for ( var lineIndex in lines) {
		var line = lines[lineIndex].replace(/[ \t]+/g, " ").replace(/\s\s*$/, "");

		if (line[0] == "#")
			continue;

		var array = line.split(" ");
		if (array[0] == "v") {
			vertex.push(parseFloat(array[1]));
			vertex.push(parseFloat(array[2]));
			vertex.push(parseFloat(array[3]));
		} else if (array[0] == "vt") {
			texture.push(parseFloat(array[1]));
			texture.push(parseFloat(array[2]));
		} else if (array[0] == "vn") {
			normal.push(parseFloat(array[1]));
			normal.push(parseFloat(array[2]));
			normal.push(parseFloat(array[3]));
		} else if (array[0] == "f") {
			if (array.length != 4) {
				continue;
			}

			for ( var i = 1; i < 4; ++i) {
				if (!(array[i] in facemap)) {
					var f = array[i].split("/");
					var vtx, nor, tex;

					if (f.length == 1) {
						vtx = parseInt(f[0]) - 1;
						nor = vtx;
						tex = vtx;
					} else if (f.length = 3) {
						vtx = parseInt(f[0]) - 1;
						tex = parseInt(f[1]) - 1;
						nor = parseInt(f[2]) - 1;
					} else {
						return null;
					}

					var x = 0;
					var y = 0;
					var z = 0;
					if (vtx * 3 + 2 < vertex.length) {
						x = vertex[vtx * 3];
						y = vertex[vtx * 3 + 1];
						z = vertex[vtx * 3 + 2];
					}
					vertexArray.push(x);
					vertexArray.push(y);
					vertexArray.push(z);

					x = 0;
					y = 0;
					if (tex * 2 + 1 < texture.length) {
						x = texture[tex * 2];
						y = texture[tex * 2 + 1];
					}
					textureArray.push(x);
					textureArray.push(y);

					x = 0;
					y = 0;
					z = 1;
					if (nor * 3 + 2 < normal.length) {
						x = normal[nor * 3];
						y = normal[nor * 3 + 1];
						z = normal[nor * 3 + 2];
					}
					normalArray.push(x);
					normalArray.push(y);
					normalArray.push(z);

					facemap[array[i]] = index++;
				}

				indexArray.push(facemap[array[i]]);
			}
		}
	}

	var result = new Object();
	result.vertexPositions = vertexArray;
	result.vertexNormals = normalArray;
	result.vertexTextureCoords = textureArray;
	result.indices = indexArray;

	return result;
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

function multiplyMatrixVector(a, b) {
	var c = new Float32Array(4);
	c[0] = a[0] * b[0] + a[1] * b[1] + a[2] * b[2] + a[3] * b[3];
	c[1] = a[4] * b[0] + a[5] * b[1] + a[6] * b[2] + a[7] * b[3];
	c[2] = a[8] * b[0] + a[9] * b[1] + a[10] * b[2] + a[11] * b[3];
	c[3] = a[12] * b[0] + a[13] * b[1] + a[14] * b[2] + a[15] * b[3];
	return c;
}

function mat4ToInverseMat3(a) {
	var c = a[0], d = a[1], e = a[2], g = a[4], f = a[5], h = a[6], i = a[8], j = a[9], k = a[10], l = k * f - h * j, o = -k * g + h * i, m = j * g - f * i, n = c * l + d * o + e * m;
	if (n == 0)
		return null;
	n = 1 / n;
	var b = new Float32Array(9);
	b[0] = l * n;
	b[1] = (-k * d + e * j) * n;
	b[2] = (h * d - e * f) * n;
	b[3] = o * n;
	b[4] = (k * c - e * i) * n;
	b[5] = (-h * c + e * g) * n;
	b[6] = m * n;
	b[7] = (-j * c + d * i) * n;
	b[8] = (f * c - d * g) * n;
	return b;
}

function mat3Traspose(a) {
	var b = new Float32Array(9);
	b[0] = a[0];
	b[1] = a[3];
	b[2] = a[6];
	b[3] = a[1];
	b[4] = a[4];
	b[5] = a[7];
	b[6] = a[2];
	b[7] = a[5];
	b[8] = a[8];
	return b;
}