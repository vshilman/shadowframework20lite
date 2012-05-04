package shadow.renderer;

import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.system.SFArrayElementException;
import shadow.system.SFInitiable;

/**
 * A Reference to a SFStructureArray.
 * Keeps a pointer to 1 structure instance into the array
 * 
 * @author Alessandro Martinelli
 */
public class SFStructureReference implements SFInitiable{
	
	private SFPipelineStructure structure;
	private SFStructureArray tableData;
	private int refIndex;
	
	public SFStructureReference(){
		
	}
	
	public SFStructureReference(int index) {
		super();//remember: super() will call generateData(); that's way class attribute are initialized there, or things are not going to work.
		this.refIndex=index;
		//SFDataCenter.getDataCenter().makeDatasetAvailable(tableDataName, this);
	}
	
	public SFStructureReference(SFStructureArray table,int index) {
		super();//remember: super() will call generateData(); that's way class attribute are initialized there, or things are not going to work.
		this.tableData=table;
		this.structure = tableData.getPipelineStructure();
		this.refIndex=index;
	}
	
	public void setArray(){
		
	}
	
	public void setValue(SFStructureData data) throws SFArrayElementException{
		tableData.setElement(refIndex, data);
	}
	
	public SFPipelineStructure getStructure() {
		return structure;
	}

	public int getMaterialIndex() {
		return refIndex;
	}

	public void setMaterialIndex(int materialIndex) {
		this.refIndex=materialIndex;
	}
	

	public void setRefIndex(int refIndex) {
		this.refIndex = refIndex;
	}

	public SFStructureArray getTable() {
		return tableData;
	}
	
	public void setTable(SFStructureArray table) {
		this.tableData=table;
	}
	
	@Override
	public void init() {
		
	}

	public void setStructureData(SFStructureData data) throws SFArrayElementException{
		tableData.setElement(refIndex,data); 
	}
	
	public void getStructureData(SFStructureData data) throws SFArrayElementException{
		tableData.getElement(refIndex,data); 
	}
	
	
}
