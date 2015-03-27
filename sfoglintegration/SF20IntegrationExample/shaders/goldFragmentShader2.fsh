//precision mediump float;
uniform vec4 color;
varying vec3 varyingNormal;
void main(){
    float light_=dot(varyingNormal,vec3(0.8,0.8,+0.8))+0.2;
    float t=(-varyingNormal.z+varyingNormal.x-varyingNormal.y)*0.85;
    float speclight_=0.3*t-0.6*t*t+1.5*t*t*t-0.3*t*t*t*t;
	gl_FragColor=color*vec4(vec3(light_)+vec3(speclight_),1);
    //gl_FragColor=vec4(varyingNormal,1);
}