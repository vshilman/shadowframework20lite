

function SFGeometryEngine_getMaxGridsSize(primitive,gridSize){

	var maxGridSize=0;
	for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
		gridSizes[gridIndex]=primitive.getGrid(gridIndex).getN();
		if(maxGridSize<gridSizes[gridIndex])
			maxGridSize=gridSizes[gridIndex];
	}
	return maxGridSize;	
}

function SFGeometryEngine_getGridsSize(primitive,gridSize){
	for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
		gridSizes[gridIndex]=primitive.getGrid(gridIndex).getN();
	}	
}

