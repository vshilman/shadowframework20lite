package shadow.geometry.geometries;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;

/**
 * Responsibility: draw.
 * 
 * @author Alessandro Martinelli
 */
public class SFMeshGeometry extends SFGeometry {

	private SFPrimitive primitive;
	//private SFPrimitiveData primitiveData=new SFPrimitiveData();
	private SFPrimitiveArray array;
	private int firstElement=-1;
	private int lastElement=-1;
	
	public SFMeshGeometry() {
		super();
	}

	@Override
	public void drawGeometry(int lod) {
		//lod is still ignored
		//this is much ok...
		if(firstElement!=-1)
			SFPipeline.getSfPipelineGraphics().drawPrimitives(array,firstElement,lastElement-firstElement);
	}
	
	@Override
	public void compile() {
		//Nothing to Do; SFMeshGeometry can be used for directly built geometries.
	}
	
	@Override
	public void init() {
		if(firstElement==-1){
			compile();
		}
	}
	
	protected SFMeshGeometry(SFPrimitive primitive) {
		super();
		this.primitive=primitive;
	}

	public void setPrimitive(SFPrimitive primitive) {
		this.primitive=primitive;
	}

	public int getFirstElement() {
		return firstElement;
	}

	public int getElementsCount() {
		return lastElement-firstElement;
	}


	public void setFirstElement(int firstElement) {
		this.firstElement=firstElement;
	}


	public int getLastElement() {
		return lastElement;
	}


	public void setLastElement(int lastElement) {
		this.lastElement=lastElement;
	}


	public SFPrimitiveArray getArray() {
		return array;
	}
	
	public void setArray(SFPrimitiveArray array) {
		this.array=array;
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}

	
}
