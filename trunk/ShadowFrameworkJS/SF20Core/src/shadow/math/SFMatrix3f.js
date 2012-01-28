
function SFMatrix3f(){
	A=1;//Warning: Not well Identified 
	E=1;//Warning: Not well Identified 
	I=1;//Warning: Not well Identified 
}
function SFMatrix3f(a, b, c, d, e, f, g, h, i){
	A = a;//Warning: Not well Identified 
	B = b;//Warning: Not well Identified 
	C = c;//Warning: Not well Identified 
	D = d;//Warning: Not well Identified 
	E = e;//Warning: Not well Identified 
	F = f;//Warning: Not well Identified 
	G = g;//Warning: Not well Identified 
	H = h;//Warning: Not well Identified 
	I = i;//Warning: Not well Identified 
}

SFMatrix3f.prototype = {

	cloneValue:function(){
	return new SFMatrix3f(v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7],v[8]);//Warning: Not well Identified 
	}

};