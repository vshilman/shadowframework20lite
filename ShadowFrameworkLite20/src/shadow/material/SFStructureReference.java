package shadow.material;

import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;

public class SFStructureReference {
	
	private SFPipelineStructure structure;
	private SFStructureArray table;
	private int refIndex;
	
	private SFStructureReference() {
		super();
	}
	
	public SFStructureReference(SFStructureArray table) {
		super();
		this.table = table;
		this.refIndex = table.generateElement();
	}
	

	public void setValue(SFStructureData data) throws SFArrayElementException{
		table.setElement(refIndex, data);
	}
	
	public SFPipelineStructure getStructure() {
		return structure;
	}

	public int getMaterialIndex() {
		return refIndex;
	}

	public void setMaterialIndex(int materialIndex) {
		this.refIndex = materialIndex;
	}
	
	
	public SFStructureArray getTable() {
		return table;
	}

	public void setStructureData(SFStructureData data) throws SFArrayElementException{
		table.setElement(refIndex,data); 
	}
	
	public void getStructureData(SFStructureData data) throws SFArrayElementException{
		table.getElement(refIndex,data); 
	}
}
