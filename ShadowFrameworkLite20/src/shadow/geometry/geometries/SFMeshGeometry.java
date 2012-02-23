package shadow.geometry.geometries;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.renderer.data.SFPrimitiveData;

/**
 * Responsibility: draw.
 * 
 * @author Alessandro Martinelli
 */
public abstract class SFMeshGeometry extends SFGeometry{

	//private SFPrimitive primitive;
	private SFPrimitiveData primitiveData=new SFPrimitiveData();
	private SFPrimitiveArray array;
	private int firstElement=-1;
	private int lastElement=-1;
	
	public SFMeshGeometry() {
		super();
	}
	
	protected SFMeshGeometry(SFPrimitive primitive) {
		super();
		this.primitiveData.setPrimitive(primitive);
	}
	
	
	protected SFPrimitiveData getPrimitiveData() {
		return primitiveData;
	}

	protected void setPrimitive(SFPrimitive primitive) {
		this.primitiveData.setPrimitive(primitive);
		if(firstElement==-1){
			compile();
		}
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


	@Override
	public void drawGeometry(int lod) {
		//lod is still ignored
		//this is much ok...
		SFPipeline.getSfPipelineGraphics().drawPrimitives(array,firstElement,lastElement-firstElement);
	}

	
	@Override
	public SFPipelineRegister[] getGeometricRegisters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		return primitiveData.getPrimitive();
	}
	
	
	@Override
	public String getTessellator() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void allocateGraphicsMemory() {
		this.array=SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitiveData.getPrimitive());
	}
	
	@Override
	public void freeGraphicsMemory() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() {
		System.err.println("Compiling?");
		if(firstElement==-1){
			System.err.println("Yes!");
			compile();
		}
	}
	
}
