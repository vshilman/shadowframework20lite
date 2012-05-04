package shadow.operational;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.operational.grid.SFGridOperations;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveGrid;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;

//TODO : to be completely reworked
public class SFExtruder {
	
	public enum BevelFunction{
		EXTRUDE(false,false,1){
			@Override
			public float getK1(int index, int gridDimension) {
				return (index/(float)gridDimension);
			}
			
			@Override
			public float getK2(int index, int gridDimension) {
				return 0;
			}
			
			@Override
			public float getK3(int index, int gridDimension) {
				return 1;
			}
			@Override
			public float getK4(int index, int gridDimension) {
				return 0;
			}
		},
		BEVELED_EXTRUDE(true,true,1){
			@Override
			public float getK1(int index, int gridDimension) {
				return (index/(float)gridDimension);
			}
			
			@Override
			public float getK2(int index, int gridDimension) {
				return 0;
			}
			
			@Override
			public float getK3(int index, int gridDimension) {
				return 1;
			}
			@Override
			public float getK4(int index, int gridDimension) {
				return 0;
			}
		},
		BEVELED_SIMPLE(true,true,2){
			@Override
			public float getK1(int index, int gridDimension) {
				if(index==1){
					return 0;
				}
				if(index==2*gridDimension-1){
					return 1;
				}
				return ((index-1)/(float)(2*gridDimension-1));
			}
			
			@Override
			public float getK2(int index, int gridDimension) {
				return 0.05f;
			}
			
			@Override
			public float getK3(int index, int gridDimension) {
				if(index==1){
					return 0.5f;
				}
				if(index==2*gridDimension-1){
					return 0.5f;
				}
				return 1;
			}
			@Override
			public float getK4(int index, int gridDimension) {
				if(index==1){
					return -0.5f;
				}
				if(index==2*gridDimension-1){
					return +0.5f;
				}
				return 0;
			}
		};
		
		private BevelFunction(boolean sideUseBack, boolean sideUseFront, int M) {
			this.sideUseBack = sideUseBack;
			this.sideUseFront = sideUseFront;
			this.M=M;
		}
		public boolean sideUseBack;
		public boolean sideUseFront;
		public int M;
		
		public abstract float getK1(int index,int gridDimension);
		public abstract float getK2(int index,int gridDimension);
		public abstract float getK3(int index,int gridDimension);
		public abstract float getK4(int index,int gridDimension);
	}
	
	
	private static BevelFunction bevelFunction=BevelFunction.EXTRUDE;

	//TODO : to be completely reworked
	public static int[] extrudeModel(SFPrimitiveArray array,int positionInArray,SFArray<SFValuenf> primitiveData[],
			List<SFPrimitiveIndices> indices,SFVertex3f extrusionVector){
		
		SFPrimitive primitive=array.getPrimitive();
		
		//System.err.println("indices "+indices.size());
		int gridIndex=primitive.getPositionBlockIndex();//TODO : we are supposing to work only with first grid - or better that its unique.
		
		SFPipelineGrid grid=primitive.getGridInstances()[gridIndex].getGrid();
		int size=grid.getEdges()[0].length;
		
		//Back Part
		int back=array.generateElements(indices.size());
		int[] backPositions=generateBack(array, positionInArray, primitiveData, primitive, indices, extrusionVector,
				back);
		int backData[]=new int[2];
		backData[0]=back;
		backData[1]=indices.size();
		
		//Side Part
		Map<Short,short[]> sideIndices=new TreeMap<Short,short[]>();
		List<short[]> sideEdges=new ArrayList<short[]>();
		evaluateSideEdges(primitive, indices, gridIndex, grid, sideIndices, sideEdges);

		SFValuenf[] derivatives=evaluateDerivatives(sideIndices,primitiveData[gridIndex]);
		int txoIndex=primitive.findTxoBlockPosition();
		SFValuenf[] txoDerivatives=null;
		if(txoIndex!=-1){
			txoDerivatives=evaluateDerivatives(sideIndices,primitiveData[txoIndex]);
		}
		
		List<Short> sidesElement=new ArrayList<Short>();
		sidesElement.addAll(sideIndices.keySet());
		
		int sideData[]=generateSide(array, positionInArray, primitiveData, primitive, extrusionVector,
				size,backPositions,sidesElement,sideEdges,derivatives,txoDerivatives);
		
		return sideData;
	}
	
	
	private static SFValuenf[] evaluateDerivatives(Map<Short,short[]> sideIndices,SFArray<SFValuenf> primitiveData){
		
		SFValuenf[] derivatives=new SFValuenf[sideIndices.keySet().size()];
		SFValuenf temp=primitiveData.generateSample();
		
		Iterator<Short> iterator=sideIndices.keySet().iterator();
		for (int i = 0; i < derivatives.length; i++) {
			short index=iterator.next();
			short[] neighbours=sideIndices.get(index);
			Short index1=neighbours[0];
			Short index2=neighbours[1];
			
			primitiveData.getElement(index1, temp);
			derivatives[i]=temp.cloneValue();
			primitiveData.getElement(index2, temp);
			derivatives[i].addMult(-1, temp);
		}
		
		return derivatives;
	}
	
	
	/**
	 * Compares two edges, and verify if they are equal
	 * @param edge1Index first edge to be compared
	 * @param edge2Index second edge to be compared
	 * @return true if the edges are using the same points
	 */
	private static boolean equals(short[] edge1Index,short[] edge2Index){
		
		int size=edge2Index.length;
		
		for (int i = 0; i < size; i++) {
			if(edge2Index[i]!=edge1Index[i]){
				break;
			}
			if(i==size-1){
				return true;
			}
		}
		for (int i = 0; i < size; i++) {
			if(edge2Index[i]!=edge1Index[size-1-i]){
				break;
			}
			if(i==size-1){
				return true;
			}
		}
		
		return false;
	}
	
	private static class SFEdgesIterator implements Iterator<short[]>{

		private SFPrimitive primitive;
		private List<SFPrimitiveIndices> indices=new ArrayList<SFPrimitiveIndices>();
		private short[][] edgesModel;
		private int gridIndex;
		private int size;
		private int index=0;
		
		public SFEdgesIterator(SFPrimitive primitive,List<SFPrimitiveIndices> indices,short[][] edgesModel,int gridIndex) {
			super();
			this.indices.addAll(indices);
			this.size=edgesModel[0].length;
			this.edgesModel=edgesModel;
			this.gridIndex=gridIndex;
			this.primitive=primitive;
		}

		@Override
		public boolean hasNext() {
			return index<3*indices.size();
		}
		
		@Override
		public short[] next() {
			int primitiveIndicesIndex=index/3;
			int relativeIndex=index-primitiveIndicesIndex*3;
			short[] value=new short[size];
			int[] indices=this.indices.get(primitiveIndicesIndex).getPrimitiveIndices();
			int position=primitive.getIndicesPositions()[gridIndex];
			for (int i = 0; i < value.length; i++) {
				int edgeIndex=edgesModel[relativeIndex][i];
				value[i]=(short)(indices[position+edgeIndex]);
			}
			index++;
			return value;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public int getIndex() {
			return index;
		}
		
	}

	/* Retrieve all the edges being on the side, that is edges not staying on the same side  
	 * at a time
	 */
	private static void evaluateSideEdges(SFPrimitive primitive,List<SFPrimitiveIndices> indices,
			int gridIndex, SFPipelineGrid grid, Map<Short,short[]> freeIndices, List<short[]> freeEdges) {
		//Set<Short> freeIndicesTemp=new TreeSet<Short>();
		SFEdgesIterator iterator=new SFEdgesIterator(primitive,indices,grid.getEdges(),gridIndex);
		while(iterator.hasNext()) {
			short[] edge = iterator.next();
			//stampEdge(edge);
			SFEdgesIterator iterator2=new SFEdgesIterator(primitive,indices,grid.getEdges(),gridIndex);
			boolean found=false;
			while(iterator2.hasNext()) {
				short[] edge2 = iterator2.next();
				if(iterator.getIndex()!=iterator2.getIndex() && equals(edge, edge2)){
					found=true;
					break;
				}
			}
			if(!found){
				System.err.println();
				freeEdges.add(edge);
				for (int i = 0; i < edge.length; i++) {
					short[] neighbours=freeIndices.get(edge[i]);
					if(neighbours==null){
						neighbours=new short[2];
						freeIndices.put(edge[i], neighbours);
					}
					for (int j = 0; j < edge.length; j++) {
						if((j-i)==1){
							neighbours[1]=edge[j];
						}	else if((i-j==1)){
							neighbours[0]=edge[j];
						}
					}
				}
			}
		}
	}
	
	private static List<Short> indexMap(List<Short> sideIndices){
		List<Short> map=new ArrayList<Short>();
		short index=0;
		for (Short short1 : sideIndices) {
			while(map.size()<=short1){
				map.add(index);
			}
			index++;
		}
		return map;
	}
	
	private static int[] generateSide(SFPrimitiveArray array, int positionInArray,
			SFArray<SFValuenf> primitiveData[], SFPrimitive primitive,
			SFVertex3f extrusionVector,int size, int[] backPositions, List<Short> sideIndices,List<short[]> sideEdges,
			SFValuenf[] derivatives,SFValuenf[] txoDerivatives) {
		
		SFPrimitiveGrid[] grids=primitive.getGridInstances();
		
		int M=bevelFunction.M;//number of lateral subdivisions
		int gridDimension=size-1;
		
		
		float step=1.0f/(gridDimension*M);
		int[] sizePositions=new int[grids.length];
		
		for (int gridIndex = 0; gridIndex < grids.length; gridIndex++) {
			SFArray<SFValuenf> values=array.getPrimitiveData( gridIndex);
			int sideSize=(gridDimension*M+1-(bevelFunction.sideUseFront?1:0)-(bevelFunction.sideUseBack?1:0))*sideIndices.size();
			PrimitiveBlock block=grids[gridIndex].getBlock();
			sizePositions[gridIndex]=values.generateElements(sideSize);
			SFValuenf value=primitiveData[gridIndex].generateSample();
			int delta=sizePositions[gridIndex];
			
			int sideArrayIndex=0;
			for (int k = 0; k < M; k++) {
				int istart=(k==0 && bevelFunction.sideUseFront)?1:0;//If k is 0, i need to skip something
				int iend=((k==M-1 && bevelFunction.sideUseBack)?(size -1):size);//
				for (int i = istart; i < iend;i++ ){
					for (int k2 = 0; k2 < sideIndices.size(); k2++) {
						int relIndex=sideArrayIndex*sideIndices.size()+k2;
						int originalIndex=sideIndices.get(k2);
						values.getElement(originalIndex, value);
						SFValuenf dir=derivatives[k2];
						SFVertex3f value3f=new SFVertex3f();
						value3f.set(dir);
						SFVertex3f normal=value3f.cross(extrusionVector);
						normal.normalize3f();
						switch (block) {//this is not the best, but we can work on it...
							case POSITION: 
									//value.addMult(step*(sideArrayIndex+1), extrusionVector); 
									value.addMult(bevelFunction.getK1(sideArrayIndex+istart,gridDimension), extrusionVector);
									value.addMult(bevelFunction.getK2(sideArrayIndex+istart,gridDimension), normal);
								break;
							case NORMAL:
									value.set(normal);
									value.mult(bevelFunction.getK3(sideArrayIndex+istart,gridDimension));
									value.addMult(bevelFunction.getK4(sideArrayIndex+istart,gridDimension),extrusionVector);
								break;
							case TXO: 
								dir=txoDerivatives[k2];
								dir.mult((float)(1.0f/Math.sqrt(dir.dot(dir))));
								float mult=step*(sideArrayIndex+1);
								value.get()[0]+=(-dir.get()[1]*mult);
								value.get()[1]+=(dir.get()[0]*mult);
							//case DU: value.set(extrusionVector);//this should be more like a value. cross; or something like that..
						}
						values.setElement(relIndex+delta, value);
					}
					sideArrayIndex++;
				}	
				sideArrayIndex--;
			}
		}
		
		int side=array.generateElements(sideEdges.size()*(2*M));
		
		List<Short> map=indexMap(sideIndices);
		
		int indexSides=side;
		for (short[] edge : sideEdges) {
			int[][] outputGP=new int[2*M][];
			for (int k1 = 0; k1 < M; k1++) {
				for (int i = 0; i < 2; i++) {
					outputGP[i+2*k1]=new int[grids.length*size*(size+1)/2];
				}
			}
			for (int gridIndex = 0; gridIndex < grids.length; gridIndex++) {
				SFPipelineGrid grid=grids[gridIndex].getGrid();
				
				int[][] indices = generateOrderedQuadIndices(size, map, edge, backPositions[gridIndex],
						sizePositions[gridIndex], sideIndices.size(), M, bevelFunction.sideUseFront, bevelFunction.sideUseBack);
				
				for (int k1 = 0; k1 < M; k1++) {
					int output[][]=new int[2][size*(size+1)/2];
					int position=primitive.getIndicesPositions()[gridIndex];
					int gridSize=primitive.getIndicesSizes()[gridIndex];
					SFGridOperations.generateQuadTrianglesIndices(grid,output, 0, size, indices[k1]);
					for (int i = 0; i < output.length; i++) {
						for (int j = 0; j < gridSize; j++) {
							outputGP[i+2*k1][j+position]=output[i][j];	
						}
					}
				}
			}
			
			placeAllPrimitiveIndices(array, indexSides, outputGP);

			indexSides+=outputGP.length;
		}
		
		int[] ret=new int[2];
		ret[0]=side;
		ret[1]=indexSides;
		
		return ret;
	}


	//TODO : move to array somehow
	/*
	 * NOTE : same for triangular and quad indices
	 */
	private static void placeAllPrimitiveIndices(SFPrimitiveArray array, int indexSides,
			int[][] allPrimitivesIndices) {
		SFPrimitiveIndices indices=new SFPrimitiveIndices(); 
		for (int i = 0; i < allPrimitivesIndices.length; i++) {
			indices.setPrimitiveIndices(allPrimitivesIndices[i]);
			array.setElement(indexSides+i, indices);
		}
	}


	/**
	 * Generates an ordered matrix of indices 
	 * on a quad; this does not generate a quad primitive indices,
	 * If its used for quad primitives generation,
	 * you still need a re-arrange of vertices
	 * 
	 * @param size
	 * @param map
	 * @param edge
	 * @param backPosition
	 * @param sidePosition
	 * @param sideIndicesSize
	 * @return
	 */
	/*
	 * NOTE : same for triangular and quad indices
	 */
	private static int[][] generateOrderedQuadIndices(int size, List<Short> map, short[] edge,
			int backPosition, int sidePosition, int sideIndicesSize,int M,boolean fronts,boolean backs) {
		int[][] indices=new int[M][size*size];
		for (int i = 0; i < size; i++) {
			if(fronts)
				indices[0][0+i]=edge[i];
			if(backs)
				indices[M-1][(size)*(size-1)+i]=backPosition+edge[i];
		}
		int sideArrayIndex=0;
		for (int k = 0; k < M; k++) {
			int istart=(k==0 && fronts)?1:0;//If k is 0, i need to skip something
			int iend=((k==M-1 && backs)?(size -1):size);//
			for (int i = istart; i < iend;i++ ){
				for (int jj = 0; jj < size; jj++) {
					indices[k][size*i+jj]=sidePosition+map.get(edge[jj])+(sideArrayIndex)*sideIndicesSize;
				}
				sideArrayIndex++;
			}	
			sideArrayIndex--;
		}
		
		return indices;
	}
	

	private static int[] generateBack(SFPrimitiveArray array, int positionInArray,
			SFArray<SFValuenf> primitiveData[], SFPrimitive primitive,
			List<SFPrimitiveIndices> indices, SFVertex3f extrusionVector, int back) {
		
		SFPrimitiveGrid[] grids=primitive.getGridInstances();
		
		//Back Values
		int[] backPositions=new int[grids.length];
		for (int gridIndex = 0; gridIndex < grids.length; gridIndex++) {
			SFArray<SFValuenf> values=array.getPrimitiveData( gridIndex);
			PrimitiveBlock block=grids[gridIndex].getBlock();
			int max = getIndicesInterval(primitive,indices, gridIndex);
			int backSize=max-positionInArray+1;
			backPositions[gridIndex]=values.generateElements(backSize);
			SFValuenf value=primitiveData[gridIndex].generateSample();
			int delta=backPositions[gridIndex]-positionInArray;
			for (int i = 0; i < backSize; i++) {
				values.getElement(positionInArray+i, value);
				switch (block) {//this is not the best, but we can work on it...
					case POSITION: value.addMult(1, extrusionVector); break;
					case NORMAL: value.mult(-1);
						case DU:  //value.mult(-1);
				}
				values.setElement(positionInArray+i+delta, value);
			}
		}

		//Back Indices
		for (int i = 0; i < indices.size(); i++) {
			SFPrimitiveIndices clone=indices.get(i).clone();
			array.getElement(i+positionInArray, clone);
			int[] allPrimitiveIndices=clone.getPrimitiveIndices();
			
			for (int gridIndex = 0; gridIndex < grids.length; gridIndex++) {
				int delta=backPositions[gridIndex]-positionInArray;
				int position=primitive.getIndicesPositions()[gridIndex];
				for (int l = 0; l < primitive.getIndicesSizes()[gridIndex]; l++) {
					allPrimitiveIndices[position+l]+=delta;
				}
			}

			array.setElement(i+back, clone);
		}
		
		return backPositions;
	}

	private static int getIndicesInterval(SFPrimitive primitive,List<SFPrimitiveIndices> indices,
			int gridIndex) {
		int max;
		max=indices.get(0).getPrimitiveIndices()[0];
		for (int i = 0; i < indices.size(); i++) {
			SFPrimitiveIndices primitiveIndices = indices.get(i);
			int[] positionIndices=primitiveIndices.getPrimitiveIndices();
			int first=primitive.getIndicesPositions()[gridIndex];
			int count=primitive.getIndicesSizes()[gridIndex];
			for (int j = first; j < first+count; j++) {
				int index=positionIndices[j];
				if(index>max)
					max=index;
			}
		}
		
		return max;
	}

}
