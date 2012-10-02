package shadow.geometry.geometries;

import shadow.geometry.geometries.structures.SFQuadsGrid;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.operational.grid.SFGridEngine;
import shadow.operational.grid.SFQuadrilateralGrid;
import shadow.operational.grid.SFRectangularGrid;
import shadow.operational.grid.SFTriangularGrid;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitive;
import shadow.system.SFArray;
import shadow.system.SFException;


public class SFQuadsGridGeometry extends SFMeshGeometry{

	public SFQuadsGrid quadsGrid = new SFQuadsGrid();

	public SFQuadsGridGeometry() {
		
	}
	
	public SFQuadsGridGeometry(SFPrimitive primitive,int N_u,int N_v,boolean closeU,boolean closeV) {
		super(primitive);
		if(primitive.getGridsCount()!=1){
			throw new SFException("SFQuadsGridGeometry must be initialized with a primitive having size == 1 and with PrimitiveBlock[0]==UV");
		}
		quadsGrid.setNu( N_u);
		quadsGrid.setNv( N_v);
		this.quadsGrid.setCloseU(closeU);
		this.quadsGrid.setCloseV(closeV);
	}

	public SFQuadsGrid getQuadsGrid() {
		return quadsGrid;
	}

	@Override
	public void compile() {
		super.compile();
		
		verifyArray();
		SFPrimitive primitive=getPrimitive();
		
		int primitivesSize=((quadsGrid.getNu()-1)*(quadsGrid.getNv()-1))<<(primitive.isQuad()?0:1);
		int primitiveIndex=getArray().generateElements(primitivesSize);
//		this.setFirstElement(primitiveIndex);
//		this.setLastElement(primitiveIndex+primitivesSize);

		//KEEP THIS COMMENTED LINES FOR TIME-ANALISYS!
//		List<Long> times=new ArrayList<Long>();
//		times.add(System.nanoTime());
		
	//	SFIndexRange primitiveDataRanges[]=new SFIndexRange[primitive.getGridsCount()];
		int gridIndex=0;
		
		SFPipelineGrid pipelineGrid=primitive.getGrid(gridIndex);
			
		int n1=pipelineGrid.getN()-1;
		float stepn1=1.0f/n1;
		float[] vs=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getV_splits());
		float[] us=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getU_splits());
		
		SFRectangularGrid<Integer> indices=new SFRectangularGrid<Integer>(us.length,vs.length);
		SFRectangularGrid<SFValuenf> values=new SFRectangularGrid<SFValuenf>(us.length,vs.length);
		
		for (int i = 0; i < us.length; i++) {
			for (int j = 0; j < vs.length; j++) {
				values.setValue(j, i, new SFVertex2f(us[i],vs[j]));
			}
		}
		
		SFArray<SFValuenf> arrayValues=getArray().getPrimitiveData(gridIndex);
		
		int position=arrayValues.generateElements(values.getWidth()*values.getHeight());
		SFGridEngine.loadGridAndGenerateIndices(values, indices, arrayValues, position);
		
		if(quadsGrid.isCloseU() || quadsGrid.isCloseV()){
			//TODO: test this
			//SFGridEngine.closeRectangle(values, closeU?1:0,  closeV?1:0);
			SFGridEngine.closeRectangle(indices, quadsGrid.isCloseU()?1:0,  quadsGrid.isCloseV()?1:0);
		}

		SFQuadrilateralGrid<Integer>[] quads=SFGridEngine.breakRectangularGrid(indices, pipelineGrid.getN());
		if(pipelineGrid.isTriangular()){
			SFTriangularGrid<Integer>[] triangles=(SFTriangularGrid<Integer>[])SFGridEngine.sliceQuads(quads);
			SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, triangles);
		}else{
			SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, quads);	
		}
	//	primitiveDataRanges[gridIndex]=new SFIndexRange(position, values.getWidth()*values.getHeight());
	
		//getMesh().setPrimitiveDataRanges(primitiveDataRanges);

//		times.add(System.nanoTime());//1
//		SFGridEngine.correctValues(getMesh());
//
//		//KEEP THIS COMMENTED LINES FOR TIME-ANALISYS!
//		times.add(System.nanoTime());//1
//		for (int i = 1; i < times.size(); i++) {
//			long t1=times.get(i-1);
//			long t2=times.get(i);
//			System.out.println("\t\t T["+i+"]="+((t2-t1)*0.001*0.001)+"ms");
//		}
	}
	
	
}
