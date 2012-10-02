package shadow.geometry.geometries;

import java.util.ArrayList;

import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.system.SFException;

public class SFCompositeMeshGeometry extends SFMeshGeometry{

	private ArrayList<SFMeshGeometry> geometries=new ArrayList<SFMeshGeometry>();

	public SFCompositeMeshGeometry() {
		super();
	}
	
	public void addGeometry(SFMeshGeometry geometry){
		geometries.add(geometry);
	}
	
	public void addMeshGeometry(SFMeshGeometry geometry) throws SFException {
		this.geometries.add(geometry);
	}
	
	@Override
	public void compile() {
		
		ArrayList<SFPrimitiveBlock> writingBlock=new ArrayList<SFPrimitiveBlock>();
		
		for (int i = 0; i < geometries.size(); i++) {
			if(geometries.get(i).getArray()==null){
				geometries.get(i).compile();
			}
		}
		
		int firstElement=geometries.get(0).getFirstElement();
		int lastElement=geometries.get(0).getLastElement();
		
		for (int i = 0; i < geometries.size(); i++) {
			for (int j = 0; j < geometries.get(i).getPrimitive().getBlocks().length; j++) {
				SFPrimitiveBlock block=geometries.get(i).getPrimitive().getBlocks()[j];
				if(writingBlock.contains(block))
					throw new SFException("SFCompositeMesh Trying to write "+block+" Two Times");
			}
			System.err.println(geometries.get(i).getFirstElement()+" "+geometries.get(i).getLastElement());
			if(firstElement!=geometries.get(i).getFirstElement() || lastElement!=geometries.get(i).getLastElement() )
				throw new SFException("SfCompositeMesh applied to geometries with a different number of elements");
		}

		SFPrimitiveArray[] arrays=new SFPrimitiveArray[geometries.size()];
			
		for (int i = 0; i < geometries.size(); i++) {
			arrays[i]=geometries.get(i).getArray();
		}
		
		SFPrimitiveArray array=SFPipeline.getSfPipelineMemory().getMixArray(arrays,getPrimitive());
		
		setPrimitive(array.getPrimitive());
		
		SFIndexRange[] ranges=new SFIndexRange[getPrimitive().getGridsCount()];
	
		int geometryIndex=0;
		int inGeometryIndex=0;
		for (int gridIndex = 0; gridIndex < ranges.length; gridIndex++) {
			ranges[gridIndex]=geometries.get(geometryIndex).getMesh().getPrimitiveDataRanges()[inGeometryIndex];
			inGeometryIndex++;
			if(inGeometryIndex>=geometries.get(geometryIndex).getPrimitive().getGridsCount()){
				geometryIndex++;
				inGeometryIndex=0;
			}
		}
		
		getMesh().setArray(array);
		getMesh().setPrimitiveDataRanges(ranges);
		
		setFirstElement(firstElement);
		setLastElement(lastElement);
	}
	
	
	@Override
	public void update() {
		super.update();
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).update();
		}
	}
}
