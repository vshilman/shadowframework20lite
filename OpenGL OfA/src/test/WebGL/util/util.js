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