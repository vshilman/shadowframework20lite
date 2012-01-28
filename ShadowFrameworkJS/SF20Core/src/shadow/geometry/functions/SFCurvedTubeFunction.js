
function SFCurvedTubeFunction(){
}

SFCurvedTubeFunction.prototype = {

	getX:function(u, v){
	evalAll(v);//Warning: Not well Identified 
	cos=(float)(Math.cos(2*Math.PI*u));//Warning: Not well Identified 
	sin=(float)(Math.sin(2*Math.PI*u));//Warning: Not well Identified 
	return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();//Warning: Not well Identified 
	},

	getY:function(u, v){
	return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();//Warning: Not well Identified 
	},

	getZ:function(u, v){
	return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();//Warning: Not well Identified 
	},

	generateNewDatasetInstance:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	getCode:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	readFromStream:function(stream){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	},

	writeOnStream:function(stream){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	}

};