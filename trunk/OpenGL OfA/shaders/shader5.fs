#ifdef GL_ES
precision highp float;
#endif

varying vec3 vTransformedNormal;
varying vec4 vPosition;

uniform float uMaterialShininess;

uniform vec3 uAmbientColor;

uniform vec3 uPointLightingLocation;
uniform vec3 uPointLightingSpecularColor;
uniform vec3 uPointLightingDiffuseColor;


void main(void) {
    vec3 lightDirection = normalize(uPointLightingLocation - vPosition.xyz);
    vec3 normal = normalize(vTransformedNormal);

    vec3 eyeDirection = normalize(-vPosition.xyz);
    vec3 reflectionDirection = reflect(-lightDirection, normal);

    float specularLightWeighting = pow(max(dot(reflectionDirection, eyeDirection), 0.0), uMaterialShininess);
    
    float diffuseLightWeighting = max(dot(normal, lightDirection), 0.0);
    vec3 lightWeighting = uAmbientColor
        + uPointLightingSpecularColor * specularLightWeighting
        + uPointLightingDiffuseColor * diffuseLightWeighting;

    vec4 fragmentColor = vec4(0.31, 0.78, 0.47, 1.0);
    gl_FragColor = vec4(fragmentColor.rgb * lightWeighting, fragmentColor.a);
}