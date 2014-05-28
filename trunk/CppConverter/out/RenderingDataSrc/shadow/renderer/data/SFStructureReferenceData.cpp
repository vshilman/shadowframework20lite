#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_


/**
 * A Reference to a SFStructureArray.
 * Keeps a pointer to 1 structure instance into the array
 * 
 * @author Alessandro Martinelli
 */

////DROP!!
namespace sf{
class SFStructureReferenceData extends SFDataAsset<SFStructureReference> implements SFDataCenterListener<SFDataAsset<SFStructureArray>>{
//	
Array> tableData=new SFLibraryReference<SFStructureArray>();
//	SFInt refIndex=new SFInt(0);
//	SFStructureReference reference;
//	
//	SFStructureReferenceData() {
//		prepare();
//		setReferences(tableData);
}
//	SFStructureReferenceData(String name,int index) {
//		prepare();
//		tableData.setReference(name);
//		refIndex.setIntValue(index);
}
//	
//	SFStructureReferenceData(SFDataAsset<SFStructureArray> data,int index) {
//		prepare();
//		tableData.setDataset(data);
//		refIndex.setIntValue(index);
}
//
//	void prepare() {
//		SFNamedParametersObject parameters=new SFNamedParametersObject();
//		parameters.addObject("tableData", tableData);
//		parameters.addObject("refIndex", refIndex);
//		setData(parameters);
}
//	

//	void onDatasetAvailable(String name, SFDataAsset<SFStructureArray> dataset) {
//		if(name.equalsIgnoreCase(tableData.getReference())){
//			SFStructureArray array=((SFDataAsset<SFStructureArray>)dataset).getResource();
//			reference.setTable(array);
}
}
//	

//	SFStructureReference buildResource() {
//		reference=new SFStructureReference();
//		reference.setMaterialIndex(refIndex.getIntValue());
//		tableData.retrieveDataset(this);
//		return reference;
}
//	
//	void setTableData(String array){
//		this->tableData.setReference(array);
}
//	
//	void setRefIndex(int refIndex){
//		this->refIndex.setIntValue(refIndex);
}
}
;
}
#endif
