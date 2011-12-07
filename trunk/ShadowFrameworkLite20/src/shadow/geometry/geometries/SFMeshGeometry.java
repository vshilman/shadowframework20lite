package shadow.geometry.geometries;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;


/**
 * Responsibility: draw.
 * 
 * @author Alessandro Martinelli
 */
public abstract class SFMeshGeometry extends SFGeometry{

	private SFPrimitive primitive;
	private SFPrimitiveArray array;
	private int firstElement;
	private int lastElement;
	
	public SFMeshGeometry(SFPrimitive primitive) {
		super();
		this.primitive=primitive;
		this.array=SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);
	}
	
	public int getFirstElement() {
		return firstElement;
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
	public SFDataset generateNewDatasetInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SFPipelineRegister[] getGeometricRegisters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}
	
	
	@Override
	public String getTessellator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void allocateBuffers() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deallocateBuffers() {
		// TODO Auto-generated method stub
		
	}
	
	
}
