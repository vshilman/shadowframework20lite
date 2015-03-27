//precision mediump float;
uniform mat4 transform;
uniform mat4 projection;
uniform mat4 vTransform;
attribute vec3 position;
attribute vec3 normal;
attribute vec3 txCoord;
varying vec3 varyingPosition;
varying vec3 varyingVertex;
varying vec3 varyingNormal;
varying vec3 varyingTxCoord;

void main(){
	//gl_Position=projection*transform*vec4(position,1);
    //vec3 tempNormal=normalize(normal);
    //tempNormal=normalize((transform*vec4(tempNormal,0)).xyz);
    //varyingNormal=tempNormal;
    //varyingPosition=gl_Position.xyz;
    //varyingVertex=position;
    //varyingTxCoord=txCoord;
    
    gl_Position=projection*transform*vec4(position,1);
    vec3 tempNormal=normal;
    //vec3 tempNormal=normalize(normal+vec3(0.35)*position);
    tempNormal=normalize((transform*vec4(tempNormal,0)).xyz);
    //Great workaround...
    //tempNormal=vec3(0.0,0.0,-sign(tempNormal.z));
    tempNormal=tempNormal*vec3(-sign(tempNormal.z));
    varyingNormal=tempNormal;
    varyingPosition=gl_Position.xyz;
    varyingVertex=position;
    varyingTxCoord=txCoord;
}