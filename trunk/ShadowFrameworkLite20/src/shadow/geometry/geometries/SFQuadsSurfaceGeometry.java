package shadow.geometry.geometries;

import shadow.geometry.SFGeometryEngine;
import shadow.geometry.geometries.structures.SFQuadsGrid;
import shadow.math.SFValuenf;
import shadow.operational.grid.SFGridEngine;
import shadow.operational.grid.SFQuadrilateralGrid;
import shadow.operational.grid.SFRectangularGrid;
import shadow.operational.grid.SFTriangularGrid;
import shadow.pipeline.SFPipelineGrid;
import shadow.system.SFArray;


public class SFQuadsSurfaceGeometry extends SFSurfaceMeshGeometry{

	private SFQuadsGrid quadsGrid;
	private int[] gridSizes;
	private int maxGridSize;
	private float gridUS[];
	private float gridVS[];
	private SFRectangularGrid<SFValuenf[]> values;
	private SFRectangularGrid<Integer>[] indices;
	
	public SFQuadsSurfaceGeometry(){
		
	}
	
	public void setQuadsGrid(SFQuadsGrid quadsGrid) {
		this.quadsGrid = quadsGrid;
	}


	@Override
	@SuppressWarnings("unchecked")
	public void compile() {
		super.compile();
		
		gridSizes=new int[primitive.getGridsCount()];
		maxGridSize=SFGeometryEngine.getMaxGridsSize(primitive, gridSizes);
		
		int primitivesSize=((quadsGrid.getNu()-1)*(quadsGrid.getNv()-1))<<(primitive.isQuad()?0:1);
		int primitiveIndex=getArray().generateElements(primitivesSize);
		
		SFValuenf[] samples=new SFValuenf[gridSizes.length];
		
		indices=(SFRectangularGrid<Integer>[])(new SFRectangularGrid<?>[gridSizes.length]);
		
		for (int gridIndex = 0; gridIndex < gridSizes.length; gridIndex++) {
			
			SFPipelineGrid pipelineGrid=primitive.getGrid(gridIndex);
			
			int n1=gridSizes[gridIndex]-1;
			int width=SFQuadsGrid.getPartitionedSplitsSize(n1,quadsGrid.getNu());
			int height=SFQuadsGrid.getPartitionedSplitsSize(n1,quadsGrid.getNv());
			
			SFRectangularGrid<Integer> indices=new SFRectangularGrid<Integer>(width,height);
			this.indices[gridIndex]=indices;
		
			SFArray<SFValuenf> arrayValues=getArray().getPrimitiveData(gridIndex);
			
			arrayValues.generateElements(width*height);
			samples[gridIndex]=arrayValues.generateSample();
			SFGridEngine.generateIndices(indices);
			
			if(quadsGrid.isCloseU() || quadsGrid.isCloseV()){
				SFGridEngine.closeRectangle(indices, quadsGrid.isCloseU()?1:0,  quadsGrid.isCloseV()?1:0);
			}
	
			SFQuadrilateralGrid<Integer>[] quads=SFGridEngine.breakRectangularGrid(indices, pipelineGrid.getN());
			if(pipelineGrid.isTriangular()){
				SFTriangularGrid<Integer>[] triangles=(SFTriangularGrid<Integer>[])SFGridEngine.sliceQuads(quads);
				SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, triangles);
			}else{
				SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, quads);	
			}
		}


		int n1=maxGridSize-1;
		float stepn1=1.0f/n1;
		this.gridVS=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getV_splits());
		this.gridUS=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getU_splits());
		values=new SFRectangularGrid<SFValuenf[]>(gridUS.length,gridVS.length);
		for (int i = 0; i < gridUS.length; i++) {
			for (int j = 0; j < gridVS.length; j++) {
				SFValuenf[] samplesList=new SFValuenf[samples.length];
				for (int j2 = 0; j2 < samples.length; j2++) {
					samplesList[j2]=samples[j2].cloneValue();
				}
				values.setValue(j, i, samplesList);
			}
		}
		
	}

	
	@Override
	public void update() {
		super.update();
		
		update(values,gridUS,gridVS);
		
		for (int gridIndex = 0; gridIndex < gridSizes.length; gridIndex++) {
			
			SFArray<SFValuenf> arrayValues=getArray().getPrimitiveData(gridIndex);

			SFRectangularGrid<Integer> indices=this.indices[gridIndex];
			
			if(this.values.getWidth()==indices.getWidth()){
				for (int i = 0; i < indices.getWidth(); i++) {
					for (int j = 0; j < indices.getHeight(); j++) {
						arrayValues.setElement(indices.getValue(j, i), values.getValue(j, i)[gridIndex]);
					}
				}
			}else{
				for (int i = 0; i < indices.getWidth(); i++) {
					for (int j = 0; j < indices.getHeight(); j++) {
						
						int bigI=i*(values.getWidth()-1);
						int bigJ=j*(values.getHeight()-1);
						
						int iValues=bigI/(indices.getWidth()-1);
						int jValues=bigJ/(indices.getHeight()-1);
						
						int iresidual=bigI-iValues*(indices.getWidth()-1);
						int jresidual=bigJ-jValues*(indices.getHeight()-1);
						
						if(iresidual==0 && jresidual==0){
							arrayValues.setElement(indices.getValue(j, i), values.getValue(jValues, iValues)[gridIndex]);
						}else{
							int jPlus=jValues+1;
							int iPlus=iValues+1;
							if(jresidual==0)
								jPlus=jValues;
							if(iresidual==0)
								iPlus=iValues;
							SFValuenf value00=values.getValue(jValues, iValues)[gridIndex];
							SFValuenf value01=values.getValue(jValues, iPlus)[gridIndex];
							SFValuenf value10=values.getValue(jPlus, iValues)[gridIndex];
							SFValuenf value11=values.getValue(jPlus, iPlus)[gridIndex];
							float U=(iresidual*1.0f)/(indices.getWidth()-1);
							float V=(jresidual*1.0f)/(indices.getHeight()-1);
							SFValuenf interpolatedValue=new SFValuenf(value00);
							interpolatedValue.mult((1-U)*(1-V));
							interpolatedValue.addMult(U*(1-V), value10);
							interpolatedValue.addMult(V*(1-U), value01);
							interpolatedValue.addMult(V*U, value11);
							arrayValues.setElement(indices.getValue(j, i), interpolatedValue);
						}
					}
				}
				
			}
		}
		
		SFGridEngine.correctValues(getMesh());
	}
}
