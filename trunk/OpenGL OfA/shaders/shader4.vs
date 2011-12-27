attribute vec3 aVertexPosition;
attribute vec3 aVertexNormal;

uniform mat4 uMVMatrix;
uniform mat4 uPMatrix;
uniform mat3 uNMatrix;

uniform vec3 uAmbientColor;

uniform vec3 uPointLightingLocation;
uniform vec3 uPointLightingColor;

uniform bool uShader;

varying vec3 vTransformedNormal;
varying vec4 vPosition;
varying vec3 vLightWeighting;


void main(void) {	
	if(uShader){
	    vPosition = uMVMatrix * vec4(aVertexPosition, 1.0);
	    gl_Position = uPMatrix * vPosition;
	    vTransformedNormal = uNMatrix * aVertexNormal;
    }else{
    	vec4 mvPosition = uMVMatrix * vec4(aVertexPosition, 1.0);
	    gl_Position = uPMatrix * mvPosition;
	
	    vec3 lightDirection = normalize(uPointLightingLocation - mvPosition.xyz);
	
	    vec3 transformedNormal = uNMatrix * aVertexNormal;
	    float directionalLightWeighting = max(dot(transformedNormal, lightDirection), 0.0);
	    vLightWeighting = uAmbientColor + uPointLightingColor * directionalLightWeighting;
    }
}