

function SFCompositeMeshGeometry(){
	this.geometries=new Array();
}

inherit(SFCompositeMeshGeometry,SFMeshGeometry);

SFCompositeMeshGeometry.prototype["addGeometry"]=function(geometry){
	this.geometries.push(geometry);
};

SFCompositeMeshGeometry.prototype["compile"]=function(){
	
		var writingBlock=new Array();
		
		for (var i = 0; i < this.geometries.size(); i++) {
			if(this.geometries[i].getArray()==null){
				this.geometries[i].compile();
			}
		}
		
		var firstElement=this.geometries[0].getFirstElement();
		var lastElement=this.geometries[0].getLastElement();
		
		for (var i = 0; i < this.geometries.length; i++) {
			for (var j = 0; j < this.geometries[i].getPrimitive().getBlocks().length; j++) {
				var block=geometries[i].getPrimitive().getBlocks()[j];
				if(writingBlock.contains(block))
					throw "SFCompositeMesh Trying to write "+block+" Two Times";
			}
			if(firstElement!=geometries.get(i).getFirstElement() || lastElement!=geometries.get(i).getLastElement() )
				throw "SfCompositeMesh applied to geometries with a different number of elements";
		}

		var arrays=new Array();
			
		for (var i = 0; i < geometries.length; i++) {
			arrays[i]=geometries.get(i).getArray();
		}
		
		var array=SFPipeline_getSfPipelineMemory().getMixArray(arrays,getPrimitive());
		
		this.setPrimitive(array.getPrimitive());
		
		var ranges=new Array();
	
		var geometryIndex=0;
		var inGeometryIndex=0;
		for (var gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
			ranges[gridIndex]=this.geometries[geometryIndex].getMesh().getPrimitiveDataRanges()[inGeometryIndex];
			inGeometryIndex++;
			if(inGeometryIndex>=this.geometries[geometryIndex].getPrimitive().getGridsCount()){
				geometryIndex++;
				inGeometryIndex=0;
			}
		}
		
		this.getMesh().setArray(array);
		this.getMesh().setPrimitiveDataRanges(ranges);
		
		this.setFirstElement(firstElement);
		this.setLastElement(lastElement);
};
	
	
	
SFCompositeMeshGeometry.prototype["compile"]=function(){
	for (var i = 0; i < this.geometries.length; i++) {
		this.geometries[i].update();
	}
};
	