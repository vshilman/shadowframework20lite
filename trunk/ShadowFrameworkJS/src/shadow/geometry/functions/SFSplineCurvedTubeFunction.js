

function SFSplineCurvedTubeFunction(){
	this.curves=new Array();
	this.tempVertex=new SFVertex3f();	
}

inherit(SFSplineCurvedTubeFunction,SFUnoptimizedSurfaceFunction);


SFSplineCurvedTubeFunction.prototype["init"]=function(){
	//do nothing
};

SFSplineCurvedTubeFunction.prototype["destroy"]=function(){
	//do nothing
};

SFSplineCurvedTubeFunction.prototype["evaluateCurve"]=function(index,v){
	var tmpVertex=new SFVertex3f();
	this.curves[index].getVertex(v, tmpVertex);
	return tmpVertex;
};

SFSplineCurvedTubeFunction.prototype["getCurves"]=function(){
	return this.curves;
};

SFSplineCurvedTubeFunction.prototype["getX"]=function(T,v){
	var v_index=Math.floor(T*this.curves.length);
		if(v_index==this.curves.length)
			v_index--;
		
		var t=(T*this.curves.length)-v_index;
		
		if(v_index==0){
			var A=this.evaluateCurve(0,v);
			var B=SFValuenf_middle(this.evaluateCurve(0,v),this.evaluateCurve(1,v));
			this.tempVertex.set(A);
			this.tempVertex.mult(1-t);
			this.tempVertex.addMult(t, B);
		}else if(v_index==this.curves.length-1){
			var A=SFValuenf_middle(this.evaluateCurve(v_index-1,v),this.evaluateCurve(v_index,v));
			var B=this.evaluateCurve(v_index,v);
			this.tempVertex.set(A);
			this.tempVertex.mult(1-t);
			this.tempVertex.addMult(t, B);
		}else{
			var A=SFValuenf_middle(this.evaluateCurve(v_index-1,v), this.evaluateCurve(v_index,v));
			var B=this.evaluateCurve(v_index,v);
			var C=SFValuenf_middle(this.evaluateCurve(v_index,v), this.evaluateCurve(v_index+1,v));
			this.tempVertex.set(A);
			this.tempVertex.mult((1-t)*(1-t));
			this.tempVertex.addMult(2*t*(1-t), B);
			this.tempVertex.addMult(t*t, C);
		}
		return this.tempVertex.getX();
};


SFSplineCurvedTubeFunction.prototype["getY"]=function(T,v){
	return this.tempVertex.getY();
};

SFSplineCurvedTubeFunction.prototype["getZ"]=function(T,v){
	return this.tempVertex.getZ();
};	
