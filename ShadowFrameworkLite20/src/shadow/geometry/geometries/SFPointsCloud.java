package shadow.geometry.geometries;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;

/**
 * Responsibility: draw.
 * 
 * @author Alessandro Martinelli
 */
public class SFPointsCloud  extends SFGeometry {

	protected SFPrimitive primitive;
	private SFPrimitiveArray array;
	private int firstPoint;
	private int pointsSize;
	private int compiledIndex=-1;

	public SFPointsCloud() {
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
			SFPipeline.getSfPipelineGraphics().drawCompiledPointsCloud(getArray(), compiledIndex);
		else if(firstPoint!=-1)
			SFPipeline.getSfPipelineGraphics().drawPointsCloud(array,firstPoint,pointsSize);
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
		if(firstPoint==-1){

			vertifyArray();
			
			firstPoint=(getArray().getElementsCount());
			int[] rangePositions=new int[getPrimitive().getGridsCount()];
			for (int i = 0; i < rangePositions.length; i++) {
				rangePositions[i]=getArray().getPrimitiveData(i).getElementsCount();
			}
			
			compile();
			
			pointsSize=(getArray().getElementsCount())-firstPoint;
			SFIndexRange[] ranges=new SFIndexRange[getPrimitive().getGridsCount()];
			for (int i = 0; i < rangePositions.length; i++) {
				int rangePosition=getArray().getPrimitiveData(i).getElementsCount();
				ranges[i]=new SFIndexRange(rangePositions[i], rangePosition-rangePositions[i]);
			}
			
			update();

			compiledIndex=SFPipeline.getSfPipelineGraphics().compilePrimitiveArray(
					getArray(),getFirstPoint(), getFirstPoint()+getPointsSize());

		}
	}

	public void vertifyArray() {
		if(getArray()==null)			
			setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(getPrimitive()));
	}
	
	@Override
	public void destroy() {
		if(firstPoint!=-1){
			decompile();	
		}
	}
	
	protected SFPointsCloud(SFPrimitive primitive) {
		super();
		this.primitive=primitive;
	}

	public SFPrimitiveArray getArray() {
		return array;
	}

	public void setArray(SFPrimitiveArray array) {
		this.array = array;
	}

	public int getFirstPoint() {
		return firstPoint;
	}

	public void setFirstPoint(int firstPoint) {
		this.firstPoint = firstPoint;
	}

	public int getPointsSize() {
		return pointsSize;
	}

	public void setPointsSize(int pointsSize) {
		this.pointsSize = pointsSize;
	}

}
