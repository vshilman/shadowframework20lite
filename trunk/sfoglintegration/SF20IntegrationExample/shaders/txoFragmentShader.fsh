//precision mediump float;
uniform vec4 color;
varying vec3 varyingTxCoord;
void main(){
	gl_FragColor=vec4(varyingTxCoord,1);
}