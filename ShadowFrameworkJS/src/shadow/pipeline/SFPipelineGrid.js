//Java to JS on 21/03/2012

function SFPipelineGrid(n,model,params){
	this.n=n;
	this.model=model;
	
	if(params.length!=model.getGridSize(n)){
		throw "SFPipelineGrid malformed, "+model.getGridSize(n)+" parameters was attended, "+params.length+" was given";
	}
	
	this.params=new Array();
	for (var i = 0; i < params.length; i++) {
		this.params[i]=params[i];
	}

	this.prepareFunctionsArrays();
	
}

inherit(SFPipelineGrid,SFPipelineStructure);

SFPipelineGrid.prototype["prepareFunctionsArrays"]=function(){
	//if(this.n==1){
	//	this.edgeFunctions=new Array();
	//	this.internalsFunctions=new Array();
		
	//	return;
	//}
	//var edgeFunctionsSize=this.model.getEdges()*(this.n-2);
	//var cornerSize=this.model.getCorners();
	//var internalsFunctionsSize=this.getGridSize()-cornerSize-edgeFunctionsSize;
	
	this.edgeFunctions=new Array();
	this.internalsFunctions=new Array();
	
};

SFPipelineGrid.prototype["getN"]=function(){
	return this.n;
};


SFPipelineGrid.prototype["getParameters"]=function(){
	return this.params;
};


SFPipelineGrid.prototype["getGridSize"]=function(){
	return this.model.getGridSize(this.n);
};

SFPipelineGrid.prototype["size"]=function(){
	return this.params.length;
};

SFPipelineGrid.prototype["getModel"]=function(){
	return this.model;
};

SFPipelineGrid.prototype["isTriangular"]=function(){
	return this.model==SFGridModel_Triangle;
};

SFPipelineGrid.prototype["addFunction"]=function(func,wrote){
	var index=0;
	for (var i = this.model.getEdges(); i < this.params.length; i++) {
		if(this.params[i].getName()===wrote.getName()){
			index=i;
			i=this.params.length;
		}
	}

	func.compileFunction(this.params);

	if(index<this.model.getCorners()+this.edgeFunctions.length) {
		index-=this.model.getCorners();
		this.edgeFunctions.push(func);
	}else{
		index-=(this.model.getCorners()+this.edgeFunctions.length);
		this.internalsFunctions.push(func);
	}
};

SFPipelineGrid.prototype["getEdgeFunctions"]=function(){
	return this.edgeFunctions;
};

SFPipelineGrid.prototype["getInternalsFunctions"]=function(){
	return this.internalsFunctions;
};

SFPipelineGrid.prototype["getParameterType"]=function(parameterIndex){
	return this.params[parameterIndex].getType();
};
