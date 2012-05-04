package shadow.renderer.data;

import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFStructureReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.objects.SFInt;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

/**
 * A Reference to a SFStructureArray.
 * Keeps a pointer to 1 structure instance into the array
 * 
 * @author Alessandro Martinelli
 */
public class SFStructureReferenceData extends SFDataAsset<SFStructureReference> implements SFDataCenterListener<SFStructureArrayData>{
	
	private SFLibraryReference<SFStructureArrayData> tableData=new SFLibraryReference<SFStructureArrayData>();
	private SFInt refIndex=new SFInt(0);
	private SFStructureReference reference;
	
	public SFStructureReferenceData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(tableData,refIndex));
	}
	
	public SFStructureReferenceData(String name,int index) {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(tableData,refIndex));
		tableData.setReference(name);
		refIndex.setIntValue(index);
	}
	
	public SFStructureReferenceData(SFStructureArrayData data,int index) {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(tableData,refIndex));
		tableData.setDataset(data);
		refIndex.setIntValue(index);
	}
	
	@Override
	public void onDatasetAvailable(String name, SFStructureArrayData dataset) {
		if(name.equalsIgnoreCase(tableData.getReference())){
			SFStructureArray array=((SFStructureArrayData)dataset).getResource();
			reference.setTable(array);
		}
	}
	
	@Override
	protected SFStructureReference buildResource() {
		reference=new SFStructureReference();
		reference.setMaterialIndex(refIndex.getIntValue());
		tableData.retrieveDataset(this);
		return reference;
	}
	
	public void setTableData(String array){
		this.tableData.setReference(array);
	}
	
	public void setRefIndex(int refIndex){
		this.refIndex.setIntValue(refIndex);
	}
}
