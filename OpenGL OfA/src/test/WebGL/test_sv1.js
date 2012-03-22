var gl;

var drawer=new Test_sv1Drawer();

function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	drawer.initShaders(gl)
	drawer.initBuffers(gl);

	tick();
}

function tick() {
	requestAnimFrame(tick);
	drawer.drawScene(gl);
}

function initGL(canvas) {
	try {
		gl = canvas.getContext("experimental-webgl");
	} catch (e) {
		alert("Could not initialise WebGL, sorry :-(");
	}
}