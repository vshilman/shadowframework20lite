var gl;

var drawer=new Test_sv9Drawer();
var mouseDown = false;

function webGLStart() {
	var canvas = document.getElementById("mycanvas");
	initGL(canvas);
	drawer.initShaders(gl)
	drawer.initBuffers(gl);
	drawer.initTexture(gl)

	tick();

	document.onkeydown = keyPressed;
	document.onkeyup = keyReleased;
	canvas.onmousedown = mousePressed;
	document.onmouseup = mouseReleased;
	document.onmousemove = mouseMoved;
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

function mousePressed(e) {
	mouseDown = true;
	drawer.mousePressed(e);
}

function mouseReleased (e) {
	mouseDown = false;
}

function mouseMoved (e) {
	if (!mouseDown) {
		return;
	}
	drawer.mouseDragged(e);
}

function keyPressed(e) {
	drawer.keyPressed(e);
}

function keyReleased(e) {
	drawer.keyReleased(e);
}