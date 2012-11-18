
function SFExpressionSurfaceFunction_UVValueList(surfaceFunction){
	this.surfaceFunction=surfaceFunction;
}


SFExpressionSurfaceFunction_UVValueList.prototype["generateValue"]=function(){
	return new SFVertex3f(0,0,0);	
};

SFExpressionSurfaceFunction_UVValueList.prototype["getValue"]=function(index){
	var u=this.surfaceFunction.u;
	var v=this.surfaceFunction.v;
	if(index==0)
		return new SFVertex3f(u,u,u);
	return new SFVertex3f(v,v,v);	
};


function SFExpressionSurfaceFunction(expression){
	this.parameters=[new SFParameter("u", SFParameteri.GLOBAL_FLOAT),
				new SFParameter("v", SFParameteri.GLOBAL_FLOAT)];
	this.expression=expression.cloneAsIndexed(parameters);
	this.valueList=new SFExpressionSurfaceFunction_UVValueList(this);
};

inherit(SFExpressionSurfaceFunction,SFUnoptimizedSurfaceFunction);

SFExpressionSurfaceFunction.prototype["getX"]=function(u,v){
	this.u=u;
	this.v=v;
	this.value=this.expression.evaluate(valueList);
	return value.get()[0];
};

SFExpressionSurfaceFunction.prototype["getY"]=function(u,v){
	return value.get()[1];
}

SFExpressionSurfaceFunction.prototype["getZ"]=function(u,v){
	return value.get()[2];
}

SFExpressionSurfaceFunction.prototype["destroy"]=function(){
	
}

SFExpressionSurfaceFunction.prototype["init"]=function(){
	
}	
	

	