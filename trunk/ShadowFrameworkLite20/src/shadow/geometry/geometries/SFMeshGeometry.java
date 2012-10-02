package shadow.geometry.geometries;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFMesh;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;

/**
 * Responsibility: draw.
 * 
 * @author Alessandro Martinelli
 */
public class SFMeshGeometry  extends SFGeometry {

	protected SFPrimitive primitive;
	private SFMesh mesh = new SFMesh(-1, -1);
	private int compiledIndex=-1;
	
	public SFMeshGeometry() {
		super();
	}

	public void setPrimitive(SFPrimitive primitive) {
		this.primitive=primitive;
	}

	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}

	@Override
	public void drawGeometry(int lod) {
		//lod is still ignored
		//this is much ok...
		if(compiledIndex!=-1)
			SFPipeline.getSfPipelineGraphics().drawCompiledPrimitives(getArray(), compiledIndex);
		else if(mesh.getFirstElement()!=-1)
			SFPipeline.getSfPipelineGraphics().drawPrimitives(mesh.getArray(),mesh.getFirstElement(),mesh.getLastElement()-mesh.getFirstElement());
	}
	
	@Override
	public void compile() {
	}
	
	@Override
	public void update() {
		//Do nothing
	}
	

	@Override
	public void decompile() {
		SFPrimitiveArray array=getArray();
		SFPipeline.getSfPipelineMemory().destroyPrimitiveArray(array);
	}
	
	@Override
	public void init() {
		if(mesh.getFirstElement()==-1){

			verifyArray();
			
			setFirstElement(getArray().getElementsCount());
			int[] rangePositions=new int[getPrimitive().getGridsCount()];
			for (int i = 0; i < rangePositions.length; i++) {
				rangePositions[i]=getArray().getPrimitiveData(i).getElementsCount();
			}
			
			compile();
			
			setLastElement(getArray().getElementsCount());
			SFIndexRange[] ranges=new SFIndexRange[getPrimitive().getGridsCount()];
			for (int i = 0; i < rangePositions.length; i++) {
				int rangePosition=getArray().getPrimitiveData(i).getElementsCount();
				ranges[i]=new SFIndexRange(rangePositions[i], rangePosition-rangePositions[i]);
			}
			
			getMesh().setPrimitiveDataRanges(ranges);

			update();

			compiledIndex=SFPipeline.getSfPipelineGraphics().compilePrimitiveArray(
					getArray(),getFirstElement(), getLastElement());

		}
	}

	public void verifyArray() {
		if(getArray()==null)			
			setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(getPrimitive()));
	}
	
	@Override
	public void destroy() {
		if(mesh.getFirstElement()!=-1){
			decompile();	
		}
	}
	
	protected SFMeshGeometry(SFPrimitive primitive) {
		super();
		this.primitive=primitive;
	}

	
	protected int getCompiledIndex() {
		return compiledIndex;
	}

	public SFMesh getMesh() {
		return mesh;
	}

	public int getFirstElement() {
		return mesh.getFirstElement();
	}

	public int getElementsCount() {
		return mesh.getLastElement()-mesh.getFirstElement();
	}


	public void setFirstElement(int firstElement) {
		this.mesh.setFirstElement(firstElement);
	}


	public int getLastElement() {
		return mesh.getLastElement();
	}


	public void setLastElement(int lastElement) {
		this.mesh.setLastElement(lastElement);
	}

	public SFPrimitiveArray getArray() {
		return mesh.getArray();
	}
	
	public void setArray(SFPrimitiveArray array) {
		this.mesh.setArray(array);
	}

}
