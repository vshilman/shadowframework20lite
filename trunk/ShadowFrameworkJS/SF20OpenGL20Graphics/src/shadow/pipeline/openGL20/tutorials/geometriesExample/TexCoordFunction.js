
function TexCoordFunction(){
}

TexCoordFunction.prototype = {

	getTexCoord:function(u, v, x, y, z){
	return new SFVertex2f(u, v);//Warning: Not well Identified 
	}

};