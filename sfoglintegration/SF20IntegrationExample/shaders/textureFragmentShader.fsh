//precision mediump float;
uniform vec4 color;
varying vec3 varyingTxCoord;
uniform sampler2D textureMaterial;
void main(){
    vec4 color1=color*texture2D(textureMaterial,varyingTxCoord.xy);
	gl_FragColor=color1;
}