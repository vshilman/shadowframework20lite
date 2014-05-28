#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/renderer/SFStructureArrayResource.h"
#include "shadow/renderer/SFStructureReference.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataCenter.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFOutputStream.h"
#include "shadow/system/data.SFWritableDataObject.h"
#include "shadow/system/data.objects.SFBinaryVertexArrayList.h"
#include "shadow/system/data.objects.SFPrimitiveType.h"
#include "shadow/system/data.objects.SFString.h"

class SFStructureReferenceDataObject extends SFPrimitiveType implements SFWritableDataObject{

	SFLibraryReference<SFStructureArrayResource> tableData=new SFLibraryReference<SFStructureArrayResource>();
	int refIndex;

	SFLibraryReference<SFStructureArrayResource> getTableData() {
		return tableData;
	}

	void setTableData(SFLibraryReference<SFStructureArrayResource> tableData) {
		this->tableData = tableData;
	}

	int getRefIndex() {
		return refIndex;
	}

	void setRefIndex(int refIndex) {
		this->refIndex = refIndex;
	}

	
	void readFromStream(SFInputStream stream) {
		tableData.readFromStream(stream);
		refIndex=stream.readShort();
	}

	
	void writeOnStream(SFOutputStream stream) {
		tableData.writeOnStream(stream);
		stream.writeShort((short)refIndex);
		SFString string=tableData.getDataset().getParameter("structure");
	}

	
	@SuppressWarnings("unchecked")
	void setStringValue(String value) {
		String[] split=value.split(":");
		if(split.length==2){
			tableData.setReference(split[0]);
			refIndex=new Integer(split[1]);
		}
			String structure=split[0];
			String type=split[1];
			String data=split[2];

			SFDataAsset<SFStructureArrayResource> dataAsset=(SFDataAsset<SFStructureArrayResource>)SFDataCenter.getDataCenter().createDataset(type);
			SFString structure_=dataAsset.getParameter("structure");
			structure_.setString(structure);
			SFBinaryVertexArrayList<?> values=dataAsset.getParameter("values");
			values.addCharSetObjects(data);
			tableData.setDataset(dataAsset);
			tableData.setReference(SFLibraryReference.NULL_REFERENCE);
			refIndex=-1;

		}
	}

	
	String toStringValue() {
		if(refIndex==-1){
			SFDataAsset<SFStructureArrayResource> dataAsset=tableData.getDataset();
			SFString structure_=dataAsset.getParameter("structure");
			SFBinaryVertexArrayList<?> values=dataAsset.getParameter("values");
			String structure=structure_.getString();
			String type=dataAsset.getType();
			String data=values.getCharSetObjectString(0);
			return structure+":"+type+":"+data;
		}
		return tableData.getReference()+":"+refIndex;
	}

	
	SFStructureReferenceDataObject clone() {
		return new SFStructureReferenceDataObject();
	}

	SFStructureReference buildReference() {

		SFStructureReference reference=new SFStructureReference();
		reference.setMaterialIndex(refIndex>=0?refIndex:0);

		SFStructureArrayResource array=tableData.getResource();//((SFDataAsset<SFStructureArray>)dataset).getResource();
		reference.setTable(array);

		return reference;
	}

}
;
}
#endif
