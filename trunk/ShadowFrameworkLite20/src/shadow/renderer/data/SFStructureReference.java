package shadow.renderer.data;

import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.system.SFArrayElementException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

/**
 * A Reference to a SFStructureArray.
 * Keeps a pointer to 1 structure instance into the array
 * 
 * @author Alessandro Martinelli
 */
public class SFStructureReference extends SFCompositeDataArray implements SFDataCenterListener{
	
	private SFPipelineStructure structure;
	private SFStructureArrayData tableData;
	private SFString structureDataReference;
	private SFInt refIndex;
	
	@Override
	public void generateData() {
		refIndex=new SFInt(0);
		addDataObject(refIndex);
		structureDataReference=new SFString("");
		addDataObject(structureDataReference);
	}
	
	@Override
	public SFStructureReference clone() {
		return new SFStructureReference();
	}
	
	public SFStructureReference(){
		
	}
	
	public SFStructureReference(String tableDataName,int index) {
		super();//remember: super() will call generateData(); that's way class attribute are initialized there, or things are not going to work.
		this.structureDataReference.setLabel(tableDataName);
		this.refIndex.setIntValue(index);
		SFDataCenter.getDataCenter().makeDatasetAvailable(tableDataName, this);
	}
	
	public SFStructureReference(SFStructureArray table,int index) {
		super();//remember: super() will call generateData(); that's way class attribute are initialized there, or things are not going to work.
		this.tableData=new SFStructureArrayData();
		this.tableData.setArray(table);
		this.structure = tableData.getArray().getPipelineStructure();
		this.refIndex.setIntValue(index);
	}
	
	
	@Override
	public void onDatasetAvailable(String name, SFDataset dataset) {
		this.tableData = (SFStructureArrayData)(dataset);
		this.structure = tableData.getArray().getPipelineStructure();
	}
	
	@Override
	public void onDatasetUnAvailable(String name) {
		// TODO Auto-generated method stub	
	}
	
	public void setValue(SFStructureData data) throws SFArrayElementException{
		tableData.getArray().setElement(refIndex.getIntValue(), data);
	}
	
	public SFPipelineStructure getStructure() {
		return structure;
	}

	public int getMaterialIndex() {
		return refIndex.getIntValue();
	}

	public void setMaterialIndex(int materialIndex) {
		this.refIndex.setIntValue(materialIndex);
	}
	
	
	public SFStructureArray getTable() {
		if(tableData==null){
			SFDataCenter.getDataCenter().makeDatasetAvailable(structureDataReference.getLabel(), this);
			this.structure = tableData.getArray().getPipelineStructure();
		}
		if(tableData==null){//STILL 
			throw new RuntimeException("SFStructureReference is not ready");
		}
		return tableData.getArray();
	}

	public void setStructureData(SFStructureData data) throws SFArrayElementException{
		tableData.getArray().setElement(refIndex.getIntValue(),data); 
	}
	
	public void getStructureData(SFStructureData data) throws SFArrayElementException{
		tableData.getArray().getElement(refIndex.getIntValue(),data); 
	}
}
