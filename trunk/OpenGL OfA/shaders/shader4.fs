#ifdef GL_ES
precision highp float;
#endif

varying vec3 vTransformedNormal;
varying vec4 vPosition;
varying vec3 vLightWeighting;

uniform vec3 uAmbientColor;

uniform vec3 uPointLightingLocation;
uniform vec3 uPointLightingColor;

uniform sampler2D uSampler;

uniform bool uShader;


void main(void) {
	vec4 fragmentColor = vec4(0.31, 0.78, 0.47, 1.0);
	if(uShader){
	    vec3 lightDirection = normalize(uPointLightingLocation - vPosition.xyz);
	
	    float directionalLightWeighting = max(dot(normalize(vTransformedNormal), lightDirection), 0.0);
	    vec3 lightWeighting = uAmbientColor + uPointLightingColor * directionalLightWeighting;
	   
	    gl_FragColor = vec4(fragmentColor.rgb * lightWeighting, fragmentColor.a);
    }else{
    	gl_FragColor = vec4(fragmentColor.rgb * vLightWeighting, fragmentColor.a);
    }
}