package shadow.operational.grid;

import shadow.math.SFValuenf;
import shadow.operational.SFPrimitiveArrayValueList;
import shadow.pipeline.SFFunction;
import shadow.pipeline.SFMesh;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;

public class SFGridEngine {

	/**
	 * Circularly Close a QuadGrid, by copying its first columns/row to its last columns/rows  
	 *  
	 * @param mainGrid The grid to be closed
	 * @param closeX the number of columns to be copied
	 * @param closeY the number of rows to be copied
	 */
	public static <S> void closeRectangle(SFQuadGrid<S> mainGrid,int closeX,int closeY){
		for (int j = 0; j < closeX; j++) {
			
			int otherIndex=mainGrid.getWidth()-closeX+j;
			for (int i = 0; i < mainGrid.getHeight(); i++) {
				mainGrid.setValue(i, otherIndex, mainGrid.getValue(i, j));
			}
		}
		
		for (int i = 0; i < closeY; i++) {
			int otherIndex=mainGrid.getHeight()-closeY+i;
			for (int j = 0; j < mainGrid.getWidth(); j++) {
				mainGrid.setValue(otherIndex, j, mainGrid.getValue(i, j));
			}
		}
	}
	
	
	
	public static void correctValues(SFMesh mesh){

		SFPrimitive primitive=mesh.getPrimitive();
		SFPrimitiveIndices indices=mesh.getArray().generateSample();
		
		SFValuenf[][] tempValues=new SFValuenf[primitive.getGridsCount()][];
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			tempValues[gridIndex]=new SFValuenf[mesh.getPrimitiveDataRanges()[gridIndex].getSize()];
		}
		
		for (int i = mesh.getFirstElement(); i < mesh.getLastElement(); i++) {
			mesh.getArray().getElement(i, indices);
			SFPrimitiveArrayValueList valueList=new SFPrimitiveArrayValueList(indices.getPrimitiveIndices(),mesh.getArray());
			for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
				valueList.setGridIndex(gridIndex);
				SFPipelineGrid grid=primitive.getGrid(gridIndex);
				int valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
				int corners=grid.getModel().getCorners();
				SFFunction[] edgeFunction=grid.getEdgeFunctions();
				for (int j = 0; j < edgeFunction.length; j++) {
					int elementIndex=valueList.getDataIndex(corners+j)-valuePosition;
					if(edgeFunction[j]!=null && tempValues[gridIndex][elementIndex]==null){
						SFValuenf value=edgeFunction[j].getFunction().evaluate(valueList);
						tempValues[gridIndex][elementIndex]=value;
					}
				}
			}
		}

		
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			int valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
			SFArray<SFValuenf> data=mesh.getArray().getPrimitiveData(gridIndex);
			for (int i = 0; i < tempValues[gridIndex].length; i++) {
				if(tempValues[gridIndex][i]!=null){
					data.setElement(i+valuePosition, tempValues[gridIndex][i]);	
				}
				tempValues[gridIndex][i]=null;
			}
		}
		
		for (int i = mesh.getFirstElement(); i < mesh.getLastElement(); i++) {
			mesh.getArray().getElement(i, indices);
			SFPrimitiveArrayValueList valueList=new SFPrimitiveArrayValueList(indices.getPrimitiveIndices(),mesh.getArray());
			for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
				valueList.setGridIndex(gridIndex);
				int valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
				SFPipelineGrid grid=primitive.getGrid(gridIndex);
				int internalsPosition=grid.getModel().getEdges()*(grid.getN()-1);
				SFFunction[] internalFunction=grid.getInternalsFunctions();
				for (int j = 0; j < internalFunction.length; j++) {
					int elementIndex=valueList.getDataIndex(internalsPosition+j)-valuePosition;
					if(internalFunction[j]!=null && tempValues[gridIndex][elementIndex]==null){
						SFValuenf value=internalFunction[j].getFunction().evaluate(valueList);
						tempValues[gridIndex][elementIndex]=value;
					}
				}
			}
		}

		
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			int valuePosition=mesh.getPrimitiveDataRanges()[gridIndex].getPosition();
			SFArray<SFValuenf> data=mesh.getArray().getPrimitiveData(gridIndex);
			for (int i = 0; i < tempValues[gridIndex].length; i++) {
				if(tempValues[gridIndex][i]!=null){
					data.setElement(i+valuePosition, tempValues[gridIndex][i]);	
				}
			}
		}
		
		//C++
		//delete tempValues
	}


	public static <S> int generateElementsX(SFLinearGrid<S> mainGrid, int n) throws SFGridSizeException{
		int elementsX=n==2?(mainGrid.getN()-1):mainGrid.getN()/(n-1);
		if(elementsX*(n-1)+1!=mainGrid.getN()){
			throw new SFGridSizeException("Cannot break a Quad Grid with width="+mainGrid.getN()+" into quads with size "+n+" ");
		}
		return elementsX;
	}
	
	public static <S> int generateElementsX(SFQuadGrid<S> mainGrid, int n) throws SFGridSizeException{
		int elementsX=n==2?(mainGrid.getWidth()-1):mainGrid.getWidth()/(n-1);
		if(elementsX*(n-1)+1!=mainGrid.getWidth()){
			throw new SFGridSizeException("Cannot break a Quad Grid with width="+mainGrid.getWidth()+" into quads with size "+n+" ");
		}
		return elementsX;
	}

	public static <S> int generateElementsY(SFQuadGrid<S> mainGrid, int n) throws SFGridSizeException{
		int elementsY=n==2?(mainGrid.getHeight()-1):mainGrid.getHeight()/(n-1);
		if(elementsY*(n-1)+1!=mainGrid.getHeight()){
			throw new SFGridSizeException("Cannot break a Quad Grid with height="+mainGrid.getHeight()+" into quads with size "+n);
		}
		return elementsY;
	}
	
	
	/**
	 * Break any QuadGrid into QuadrilateralGrids, using a specific grid size.
	 * 
	 * @param mainGrid
	 * @param n
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S> SFLinearGrid<S>[] breakLinearGrid(SFLinearGrid<S> mainGrid,int n) throws SFGridSizeException{

		int elementsX = generateElementsX(mainGrid, n);
		
		SFLinearGrid<S> quadsGrid=new SFLinearGrid<S>(n);
		
		SFLinearGrid<?>[] newGrids=new SFLinearGrid<?>[elementsX];
		
		int index=0;
		for (int j = 0; j < elementsX; j++) {
			SFLinearGrid<S> quadsGridClone=quadsGrid.sameGrid();
			for (int j1 = 0; j1 < n; j1++) {
				quadsGridClone.setValue( j1, mainGrid.getValue(j*(n-1)+j1));
			}
			newGrids[index]=quadsGridClone;
			index++;
		}
		
		return (SFLinearGrid<S>[])newGrids;
	}
	
	/**
	 * Break any QuadGrid into QuadrilateralGrids, using a specific grid size.
	 * 
	 * @param mainGrid
	 * @param n
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S> SFQuadrilateralGrid<S>[] breakRectangularGrid(SFQuadGrid<S> mainGrid,int n) throws SFGridSizeException{

		int elementsX = generateElementsX(mainGrid, n);
		int elementsY = generateElementsY(mainGrid, n);
		
		SFQuadrilateralGrid<S> quadsGrid=new SFQuadrilateralGrid<S>(n);
		
		SFQuadrilateralGrid<?>[] newGrids=new SFQuadrilateralGrid<?>[elementsX*elementsY];
		
		int index=0;
		for (int i = 0; i < elementsY; i++) {
			for (int j = 0; j < elementsX; j++) {
				SFQuadrilateralGrid<S> quadsGridClone=quadsGrid.sameGrid();
				for (int i1 = 0; i1 < n; i1++) {
					for (int j1 = 0; j1 < n; j1++) {
						quadsGridClone.setValue(i1, j1, mainGrid.getValue(i*(n-1)+i1,j*(n-1)+j1));
					}
				}
				newGrids[index]=quadsGridClone;
				index++;
			}
		}
		
		return (SFQuadrilateralGrid<S>[])newGrids;
	}
	
	
	/**
	 * Slice an array of Quads into an array of Triangle. For each Quad two triangles
	 * are generated.
	 * 
	 * @param quadsGrids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <S> SFTriangularGrid<S>[] sliceQuads(SFQuadrilateralGrid<S>[] quadsGrids){
		
		int n=quadsGrids[0].getN(); 
		
		SFTriangularGrid<?>[] newGrids=new SFTriangularGrid<?>[quadsGrids.length<<1];
		
		int index=0;
		for (SFQuadrilateralGrid<S> quadGrid : quadsGrids) {
			//first triangle
			SFTriangularGrid<S> triangleGrid=new SFTriangularGrid<S>(n);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-i; j++) {
					triangleGrid.setValue(i, j, quadGrid.getValue(i, j));
				}
			}
			newGrids[index]=triangleGrid;
			triangleGrid=new SFTriangularGrid<S>(n);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-i; j++) {
					triangleGrid.setValue(i, j, quadGrid.getValue(n-1-i, n-1-j));
				}
			}
			newGrids[index+1]=triangleGrid;
			index+=2;
		}
		
		return (SFTriangularGrid<S>[])newGrids;
	}


	public static <S> void printLinearGrid(SFLinearGrid<S> indices){
		System.out.println("[");
		for (int i = 0; i < indices.getN(); i++) {
			System.out.print(" \t"+indices.getValue(i));
		}
		System.out.println("]");
	}
	
	public static <S> void printTriangularGrid(SFTriangularGrid<S> indices){
		System.out.println("[");
		for (int i = 0; i < indices.getN(); i++) {
			for (int j = 0; j < indices.getN()-i; j++) {
				System.out.print(" \t"+indices.getValue(i, j));
			}
			System.out.print(" \n");
		}
		System.out.println("]");
	}
	
	public static <S> void printQuadsGrid(SFQuadGrid<S> indices){
		System.out.println("[");
		for (int i = 0; i < indices.getHeight(); i++) {
			for (int j = 0; j < indices.getWidth(); j++) {
				System.out.print(" \t"+indices.getValue(i, j));
			}
			System.out.print(" \n");
		}
		System.out.println("]");
	}

	public static void loadPrimitiveIndices(SFPrimitiveArray array,int primitiveIndex,int gridIndex,SFTriangularGrid<Integer>[] indicesVector){

		SFPrimitiveIndices thisTmpIndices=array.generateSample();
		
		int position=array.getPrimitive().getIndicesPositions()[gridIndex];
		
		for (int m = 0; m < indicesVector.length; m++) {
			array.getElement(primitiveIndex+ m, thisTmpIndices);	
			SFTriangularGrid<Integer> indices=indicesVector[m];
			
			loadPrimitiveIndices(array, thisTmpIndices, position, primitiveIndex+ m,
					indices);
		}
	}
	
	public static void loadPrimitiveIndices(SFPrimitiveArray array,int primitiveIndex,int gridIndex,SFQuadrilateralGrid<Integer>[] indicesVector){
		
		SFPrimitiveIndices thisTmpIndices=array.generateSample();
		
		int position=array.getPrimitive().getIndicesPositions()[gridIndex];
		//int primitiveIndex=array.generateElements(indicesVector.length);
		
		for (int m = 0; m < indicesVector.length; m++) {
			array.getElement(primitiveIndex+ m, thisTmpIndices);	
			SFQuadrilateralGrid<Integer> indices=indicesVector[m];
			
			loadPrimitiveIndices(array, thisTmpIndices, position, primitiveIndex+ m,
					indices);
		}
	}


	public static void loadPrimitiveIndices(SFPrimitiveArray array,
			SFPrimitiveIndices thisTmpIndices, int position, int primitiveIndex,
			SFTriangularGrid<Integer> indices) {
		int gridSize=indices.getN()-1;
		int[] tmpIndices=thisTmpIndices.getPrimitiveIndices();
		
		int inGridIndex=0;
		tmpIndices[position]=indices.getValue(0, 0);
		tmpIndices[position+1]=indices.getValue(0, gridSize);
		tmpIndices[position+2]=indices.getValue(gridSize, 0);
		inGridIndex+=3;
		try {
			for (int i = 1; i < gridSize; i++) {
				tmpIndices[position+2+i]=indices.getValue(0, i);
				tmpIndices[position+2+i+gridSize-1]=indices.getValue(i,gridSize-i);
				tmpIndices[position+2+i+2*(gridSize-1)]=indices.getValue(gridSize-i,0);
				inGridIndex+=3;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int k = 1; k < gridSize-1; k++) {
			for (int l = 1; l < gridSize-1; l++) {
				tmpIndices[position+inGridIndex]=indices.getValue(k, l);
				inGridIndex++;
			}
		}
		array.setElement(primitiveIndex, thisTmpIndices);
	}
	
	public static void loadPrimitiveIndices(SFPrimitiveArray array,
			SFPrimitiveIndices thisTmpIndices, int position, int primitiveIndex,
			SFQuadrilateralGrid<Integer> indices) {
		int gridSize=indices.getN()-1;
		int[] tmpIndices=thisTmpIndices.getPrimitiveIndices();
		
		int inGridIndex=0;
		tmpIndices[position]=indices.getValue(0, 0);
		tmpIndices[position+1]=indices.getValue(0, gridSize);
		tmpIndices[position+2]=indices.getValue(gridSize, gridSize);
		tmpIndices[position+3]=indices.getValue(gridSize, 0);
		inGridIndex+=4;
		try {
			for (int i = 1; i < gridSize; i++) {
				tmpIndices[position+3+i]=indices.getValue(0, i);
				tmpIndices[position+3+i+gridSize-1]=indices.getValue(i,gridSize);
				tmpIndices[position+3+i+2*(gridSize-1)]=indices.getValue(gridSize,gridSize-i);
				tmpIndices[position+3+i+3*(gridSize-1)]=indices.getValue(gridSize-i,0);
				inGridIndex+=4;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int k = 1; k < gridSize; k++) {
			for (int l = 1; l < gridSize; l++) {
				tmpIndices[position+inGridIndex]=indices.getValue(k, l);
				inGridIndex++;
			}
		}
		array.setElement(primitiveIndex, thisTmpIndices);
	}

	public static void loadGridAndGenerateIndices(SFQuadGrid<SFValuenf> values,
			SFQuadGrid<Integer> indices, SFArray<SFValuenf> arrayValues, int position) {
		int index=0;
		for (int i = 0; i < indices.getHeight(); i++) {
			for (int j = 0; j < indices.getWidth(); j++) {
				arrayValues.setElement(position+index,values.getValue(i, j));
				indices.setValue(i, j, index);
				index++;
			}
		}
	}
	
	public static void generateIndices(SFQuadGrid<Integer> indices) {
		int index=0;
		for (int i = 0; i < indices.getHeight(); i++) {
			for (int j = 0; j < indices.getWidth(); j++) {
				//arrayValues.setElement(position+index,values.getValue(i, j));
				indices.setValue(i, j, index);
				index++;
			}
		}
	}
}

