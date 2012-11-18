

function SFQuadsGridGeometry(){
	this.quadsGrid=new SFQuadsGrid();
	this.mesh=new SFMesh(-1,-1);
	this.compiledIndex=-1;
	this.rendering_hint=0;
	this.available_lods=0;
	this.sonGeometries=new Array();
	this.changed=true;
}

inherit(SFQuadsGridGeometry,SFMeshGeometry);
	
		
SFQuadsGridGeometry.prototype["getQuadsGrid"]=function(){
	return this.quadsGrid;
};

SFQuadsGridGeometry.prototype["compile"]=function(){

	
	this.verifyArray();
	var primitive=this.getPrimitive();
	
	var primitivesSize=((this.quadsGrid.getNu()-1)*(this.quadsGrid.getNv()-1))<<(primitive.isQuad()?0:1);
	var primitiveIndex=this.getArray().generateElements(primitivesSize);

	//=new SFIndexRange[primitive.getGridsCount()];
	var gridIndex=0;
	
	var pipelineGrid=primitive.getGrid(gridIndex);
		
	var n1=pipelineGrid.getN()-1;
	var stepn1=1.0/n1;
	var vs=this.quadsGrid.generatePartitionedSplits(n1, stepn1, this.quadsGrid.getV_splits());
	var us=this.quadsGrid.generatePartitionedSplits(n1, stepn1, this.quadsGrid.getU_splits());
	
	
	var indices=new SFRectangularGrid(us.length,vs.length);
	var values=new SFRectangularGrid(us.length,vs.length);
	
	for (var i = 0; i < us.length; i++) {
		for (var j = 0; j < vs.length; j++) {
			values.setValue(j, i, new SFVertex2f(us[i],vs[j]));
		}
	}
	
	var arrayValues=this.getArray().getPrimitiveData(gridIndex);
	
	var position=arrayValues.generateElements(values.getWidth()*values.getHeight());
	SFGridEngine_loadGridAndGenerateIndices(values, indices, arrayValues, position);
	
	if(this.quadsGrid.isCloseU() || this.quadsGrid.isCloseV()){
		//TODO: test this
		//SFGridEngine.closeRectangle(values, closeU?1:0,  closeV?1:0);
		SFGridEngine_closeRectangle(indices, this.quadsGrid.isCloseU()?1:0,  this.quadsGrid.isCloseV()?1:0);
	}

	var quads=SFGridEngine_breakRectangularGrid(indices, pipelineGrid.getN());
	if(pipelineGrid.isTriangular()){
		var triangles=SFGridEngine_sliceQuads(quads);
		SFGridEngine_loadPrimitiveIndicesT(this.getArray(), primitiveIndex, gridIndex, triangles);
	}else{
		SFGridEngine_loadPrimitiveIndicesQ(this.getArray(), primitiveIndex, gridIndex, quads);	
	}
	
	
};
