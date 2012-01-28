
function SFVertex2f(x, y){
	v[0]=x;//Warning: Not well Identified 
	v[1]=y;//Warning: Not well Identified 
}
function SFVertex2f(x, y){
	v[0]=(float)x;//Warning: Not well Identified 
	v[1]=(float)y;//Warning: Not well Identified 
}

SFVertex2f.prototype = {

	cloneValue:function(){
	return new SFVertex2f(v[0],v[1]);//Warning: Not well Identified 
	}

};