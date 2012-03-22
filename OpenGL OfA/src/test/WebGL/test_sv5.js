var gl;

var drawer=new Test_sv5Drawer();

function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	drawer.initShaders(gl)
	drawer.initBuffers(gl);
	drawer.initTexture(gl);

	tick();

	document.onkeydown = keyPressed;
	document.onkeyup = keyReleased;
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

function keyPressed(e) {
	drawer.keyPressed(e);
}

function keyReleased(e) {
	drawer.keyReleased(e);
}