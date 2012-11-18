
function SFGridEngine_closeRectangle(mainGrid,closeX,closeY){
	for (var j = 0; j < closeX; j++) {
		var otherIndex=mainGrid.getWidth()-closeX+j;
		for (var i = 0; i < mainGrid.getHeight(); i++) {
			mainGrid.setValue(i, otherIndex, mainGrid.getValue(i, j));
		}
	}
	
	for (var i = 0; i < closeY; i++) {
		var otherIndex=mainGrid.getHeight()-closeY+i;
		for (var j = 0; j < mainGrid.getWidth(); j++) {
			mainGrid.setValue(otherIndex, j, mainGrid.getValue(i, j));
		}
	}
}
	
function SFGridEngine_correctValues(mesh){

		var primitive=mesh.getPrimitive();
		var indices=mesh.getArray().generateSample();
		
		//var tempValues=new SFValuenf[primitive.getGridsCount()][];
		var tempValues=new Array();
		for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			//tempValues[gridIndex]=new SFValuenf[mesh.getPrimitiveDataRanges()[gridIndex].getSize()];
			tempValues[gridIndex]=new Array();
		}
		
		for (var i = mesh.getFirstElement(); i < mesh.getLastElement(); i++) {
			mesh.getArray().getElement(i, indices);
			var valueList=new SFPrimitiveArrayValueList(indices.getPrimitiveIndices(),mesh.getArray());
			for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
				valueList.setGridIndex(gridIndex);
				var grid=primitive.getGrid(gridIndex);
				var valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
				var corners=grid.getModel().getCorners();
				var edgeFunction=grid.getEdgeFunctions();
				for (var j = 0; j < edgeFunction.length; j++) {
					var elementIndex=valueList.getDataIndex(corners+j)-valuePosition;
					if(edgeFunction[j]!=null && tempValues[gridIndex][elementIndex]==null){
						var value=edgeFunction[j].getFunction().evaluate(valueList);
						tempValues[gridIndex][elementIndex]=value;
					}
				}
			}
		}

		
		for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			var valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
			var data=mesh.getArray().getPrimitiveData(gridIndex);
			for (var i = 0; i < tempValues[gridIndex].length; i++) {
				if(tempValues[gridIndex][i]!=null){
					data.setElement(i+valuePosition, tempValues[gridIndex][i]);	
				}
				tempValues[gridIndex][i]=null;
			}
		}
		
		for (var i = mesh.getFirstElement(); i < mesh.getLastElement(); i++) {
			mesh.getArray().getElement(i, indices);
			var valueList=new SFPrimitiveArrayValueList(indices.getPrimitiveIndices(),mesh.getArray());
			for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
				valueList.setGridIndex(gridIndex);
				var valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
				var grid=primitive.getGrid(gridIndex);
				var internalsPosition=grid.getModel().getEdges()*(grid.getN()-1);
				var internalFunction=grid.getInternalsFunctions();
				for (var j = 0; j < internalFunction.length; j++) {
					var elementIndex=valueList.getDataIndex(internalsPosition+j)-valuePosition;
					if(internalFunction[j]!=null && tempValues[gridIndex][elementIndex]==null){
						var value=internalFunction[j].getFunction().evaluate(valueList);
						tempValues[gridIndex][elementIndex]=value;
					}
				}
			}
		}

		
		for (var gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			var valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
			var data=mesh.getArray().getPrimitiveData(gridIndex);
			for (var i = 0; i < tempValues[gridIndex].length; i++) {
				if(tempValues[gridIndex][i]!=null){
					data.setElement(i+valuePosition, tempValues[gridIndex][i]);	
				}
			}
		}
		
	}


function SFGridEngine_generateElementsXLinear(mainGrid, n) {
	var elementsX=n==2?(mainGrid.getN()-1):mainGrid.getN()/(n-1);
	if(elementsX*(n-1)+1!=mainGrid.getN()){
		throw "Cannot break a Quad Grid with width="+mainGrid.getN()+" into quads with size "+n+" ";
	}
	return elementsX;
}
	
function SFGridEngine_generateElementsX(mainGrid, n){
	var elementsX=n==2?(mainGrid.getWidth()-1):mainGrid.getWidth()/(n-1);
	elementsX=Math.floor(elementsX);
	if((elementsX*(n-1)+1)!=mainGrid.getWidth()){
		throw "Cannot break a Quad Grid with width="+mainGrid.getWidth()+" into quads with size "+n+" ";
	}
	return elementsX;
}

function SFGridEngine_generateElementsY(mainGrid, n){
	var elementsY=n==2?(mainGrid.getHeight()-1):mainGrid.getHeight()/(n-1);
	elementsY=Math.floor(elementsY);
	if(elementsY*(n-1)+1!=mainGrid.getHeight()){
		throw "Cannot break a Quad Grid with height="+mainGrid.getHeight()+" into quads with size "+n;
	}
	return elementsY;
}
	

function SFGridEngine_breakLinearGrid(mainGrid,n) {

		var elementsX = generateElementsX(mainGrid, n);
		var quadsGrid=new SFLinearGrid(n);
		var newGrids=new Array();
		
		var index=0;
		for (var j = 0; j < elementsX; j++) {
			var quadsGridClone=quadsGrid.sameGrid();
			for (var j1 = 0; j1 < n; j1++) {
				quadsGridClone.setValue( j1, mainGrid.getValue(j*(n-1)+j1));
			}
			newGrids[index]=quadsGridClone;
			index++;
		}
		
		return newGrids;
	};
	
function SFGridEngine_breakRectangularGrid(mainGrid,n) {

		var elementsX = SFGridEngine_generateElementsX(mainGrid, n);
		var elementsY = SFGridEngine_generateElementsY(mainGrid, n);
		
		var quadsGrid=new SFQuadrilateralGrid(n);
		var newGrids=new Array();
		
		var index=0;
		for (var i = 0; i < elementsY; i++) {
			for (var j = 0; j < elementsX; j++) {
				var quadsGridClone=quadsGrid.sameGrid();
				for (var i1 = 0; i1 < n; i1++) {
					for (var j1 = 0; j1 < n; j1++) {
						quadsGridClone.setValue(i1, j1, mainGrid.getValue(i*(n-1)+i1,j*(n-1)+j1));
					}
				}
				newGrids[index]=quadsGridClone;
				index++;
			}
		}
		
		return newGrids;
	};
	
function SFGridEngine_sliceQuads(quadsGrids){
		
		var n=quadsGrids[0].getN(); 
		
		var newGrids=new Array();
		
		var index=0;
		for (var k=0;k<quadsGrids.length;k++) {
			var quadGrid=quadsGrids[k];
			//first triangle
			var triangleGrid=new SFTriangularGrid(n);
			for (var i = 0; i < n; i++) {
				for (var j = 0; j < n-i; j++) {
					triangleGrid.setValue(i, j, quadGrid.getValue(i, j));
				}
			}
			newGrids[index]=triangleGrid;
			triangleGrid=new SFTriangularGrid(n);
			for (var i = 0; i < n; i++) {
				for (var j = 0; j < n-i; j++) {
					triangleGrid.setValue(i, j, quadGrid.getValue(n-1-i, n-1-j));
				}
			}
			newGrids[index+1]=triangleGrid;
			index+=2;
		}
		
		return newGrids;
	};

function SFGridEngine_loadPrimitiveIndicesT(array,primitiveIndex,gridIndex,indicesVector){

		var thisTmpIndices=array.generateSample();
		
		var position=array.getPrimitive().getIndicesPositions()[gridIndex];
		
		for (var m = 0; m < indicesVector.length; m++) {
			array.getElement(primitiveIndex+ m, thisTmpIndices);	
			var indices=indicesVector[m];
			
			SFGridEngine_loadPrimitiveIndicesPrTr(array, thisTmpIndices, position, primitiveIndex+ m,
					indices);
		};
	};
	
function SFGridEngine_loadPrimitiveIndicesQ(array,
		 primitiveIndex,gridIndex,indicesVector){
		
		var thisTmpIndices=array.generateSample();
		
		var position=array.getPrimitive().getIndicesPositions()[gridIndex];
		//int primitiveIndex=array.generateElements(indicesVector.length);
		
		for (var m = 0; m < indicesVector.length; m++) {
			array.getElement(primitiveIndex+ m, thisTmpIndices);	
			var indices=indicesVector[m];
			
			loadPrimitiveIndicesPrQ(array, thisTmpIndices, position, primitiveIndex+ m,
					indices);
		};
	};


function SFGridEngine_loadPrimitiveIndicesPrTr(array,
			thisTmpIndices, position, primitiveIndex, indices) {
		var gridSize=indices.getN()-1;
		var tmpIndices=thisTmpIndices.getPrimitiveIndices();
		
		var inGridIndex=0;
		tmpIndices[position]=indices.getValue(0, 0);
		tmpIndices[position+1]=indices.getValue(0, gridSize);
		tmpIndices[position+2]=indices.getValue(gridSize, 0);
		inGridIndex+=3;
		for (var i = 1; i < gridSize; i++) {
			tmpIndices[position+2+i]=indices.getValue(0, i);
			tmpIndices[position+2+i+gridSize-1]=indices.getValue(i,gridSize-i);
			tmpIndices[position+2+i+2*(gridSize-1)]=indices.getValue(gridSize-i,0);
			inGridIndex+=3;
		}
		for (var k = 1; k < gridSize-1; k++) {
			for (var l = 1; l < gridSize-1; l++) {
				tmpIndices[position+inGridIndex]=indices.getValue(k, l);
				inGridIndex++;
			};
		}
		array.setElement(primitiveIndex, thisTmpIndices);
	}
	
function SFGridEngine_loadPrimitiveIndicesPrQ(array,
			thisTmpIndices, position, primitiveIndex,indices) {
		var gridSize=indices.getN()-1;
		var tmpIndices=thisTmpIndices.getPrimitiveIndices();
		
		var inGridIndex=0;
		tmpIndices[position]=indices.getValue(0, 0);
		tmpIndices[position+1]=indices.getValue(0, gridSize);
		tmpIndices[position+2]=indices.getValue(gridSize, gridSize);
		tmpIndices[position+3]=indices.getValue(gridSize, 0);
		inGridIndex+=4;
		for (var i = 1; i < gridSize; i++) {
				tmpIndices[position+3+i]=indices.getValue(0, i);
				tmpIndices[position+3+i+gridSize-1]=indices.getValue(i,gridSize);
				tmpIndices[position+3+i+2*(gridSize-1)]=indices.getValue(gridSize,gridSize-i);
				tmpIndices[position+3+i+3*(gridSize-1)]=indices.getValue(gridSize-i,0);
				inGridIndex+=4;
			}
		for (var k = 1; k < gridSize; k++) {
			for (var l = 1; l < gridSize; l++) {
				tmpIndices[position+inGridIndex]=indices.getValue(k, l);
				inGridIndex++;
			};
		}
		array.setElement(primitiveIndex, thisTmpIndices);
	}

function SFGridEngine_loadGridAndGenerateIndices(values,
			indices, arrayValues, position) {
		var index=0;
		for (var i = 0; i < indices.getHeight(); i++) {
			for (var j = 0; j < indices.getWidth(); j++) {
				arrayValues.setElement(position+index,values.getValue(i, j));
				indices.setValue(i, j, index);
				index++;
			};
		};
	}
	
function SFGridEngine_generateIndices(indices) {
		var index=0;
		for (var i = 0; i < indices.getHeight(); i++) {
			for (var j = 0; j < indices.getWidth(); j++) {
				//arrayValues.setElement(position+index,values.getValue(i, j));
				indices.setValue(i, j, index);
				index++;
			};
		};
	};