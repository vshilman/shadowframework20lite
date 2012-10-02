package shadow.renderer.data;


/**
 * A Reference to a SFStructureArray.
 * Keeps a pointer to 1 structure instance into the array
 * 
 * @author Alessandro Martinelli
 */

////DROP!!
//public class SFStructureReferenceData extends SFDataAsset<SFStructureReference> implements SFDataCenterListener<SFDataAsset<SFStructureArray>>{
//	
//	private SFLibraryReference<SFStructureArray> tableData=new SFLibraryReference<SFStructureArray>();
//	private SFInt refIndex=new SFInt(0);
//	private SFStructureReference reference;
//	
//	public SFStructureReferenceData() {
//		prepare();
//		setReferences(tableData);
//	}
//	public SFStructureReferenceData(String name,int index) {
//		prepare();
//		tableData.setReference(name);
//		refIndex.setIntValue(index);
//	}
//	
//	public SFStructureReferenceData(SFDataAsset<SFStructureArray> data,int index) {
//		prepare();
//		tableData.setDataset(data);
//		refIndex.setIntValue(index);
//	}
//
//	private void prepare() {
//		SFNamedParametersObject parameters=new SFNamedParametersObject();
//		parameters.addObject("tableData", tableData);
//		parameters.addObject("refIndex", refIndex);
//		setData(parameters);
//	}
//	
//	@Override
//	public void onDatasetAvailable(String name, SFDataAsset<SFStructureArray> dataset) {
//		if(name.equalsIgnoreCase(tableData.getReference())){
//			SFStructureArray array=((SFDataAsset<SFStructureArray>)dataset).getResource();
//			reference.setTable(array);
//		}
//	}
//	
//	@Override
//	protected SFStructureReference buildResource() {
//		reference=new SFStructureReference();
//		reference.setMaterialIndex(refIndex.getIntValue());
//		tableData.retrieveDataset(this);
//		return reference;
//	}
//	
//	public void setTableData(String array){
//		this.tableData.setReference(array);
//	}
//	
//	public void setRefIndex(int refIndex){
//		this.refIndex.setIntValue(refIndex);
//	}
//}
