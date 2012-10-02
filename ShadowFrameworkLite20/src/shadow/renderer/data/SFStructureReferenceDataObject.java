package shadow.renderer.data;

import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFStructureReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;
import shadow.system.data.objects.SFPrimitiveType;

public class SFStructureReferenceDataObject extends SFPrimitiveType implements SFWritableDataObject{
	
	private SFLibraryReference<SFStructureArray> tableData=new SFLibraryReference<SFStructureArray>();
	private int refIndex;
	
	public void setTableData(SFLibraryReference<SFStructureArray> tableData) {
		this.tableData = tableData;
	}
	
	public void setRefIndex(int refIndex) {
		this.refIndex = refIndex;
	}
	
	public void setTableName(String name){
		this.tableData.setReference(name);
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		tableData.setReference(stream.readName());
		refIndex=stream.readShort();
	}
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeName(tableData.getReference());
		stream.writeShort((short)refIndex);
	}
	@Override
	public void setStringValue(String value) {
		int split=value.indexOf(':');
		tableData.setReference(value.substring(0, split));
		refIndex=new Integer(value.substring(split+1));
	}
	@Override
	public String toStringValue() {
		return tableData.getReference()+":"+refIndex;
	}
	@Override
	public SFStructureReferenceDataObject clone() {
		return new SFStructureReferenceDataObject();
	}

	public SFStructureReference buildReference() {
		final SFStructureReference reference=new SFStructureReference();
		reference.setMaterialIndex(refIndex);
		tableData.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFStructureArray>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFStructureArray> dataset) {
				SFStructureArray array=((SFDataAsset<SFStructureArray>)dataset).getResource();
				reference.setTable(array);
			}
		});
		return reference;
	}
	
}
