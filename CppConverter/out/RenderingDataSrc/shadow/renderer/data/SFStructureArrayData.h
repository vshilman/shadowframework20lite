#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPipelineStructure.h"
#include "shadow/pipeline/SFStructureArray.h"
#include "shadow/pipeline/SFStructureData.h"
#include "shadow/renderer/SFStructureArrayResource.h"
#include "shadow/system/SFInitiator.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryVertexArrayList.h"
#include "shadow/system/data.objects.SFGenericFixedFloat.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFStructureArrayData<T extends SFGenericFixedFloat> extends SFGraphicsAsset<SFStructureArrayResource> implements SFDataset{

	SFString structure=new SFString();
	Array array;
	ArrayList<T> values;

	SFStructureArrayData(T t){
		values=new SFBinaryVertexArrayList<T>(t);
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("structure", structure);
		parameters.addObject("values", values);
		setData(parameters);
	}

	void setStructure(String structureName){
		this->structure.setString(structureName);
	}

	SFStructureArray getArray() {
		if(array==null){
			String structureName=structure.getString();
			SFPipelineStructure materialStructureInstance=((SFPipelineStructure)(SFPipeline.getModule(structureName)));
			array=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
			int n=values.getSize();
			SFStructureData data=new SFStructureData(materialStructureInstance);

			for (int i = 0; i < n; i++) {
				SFValuenf[] value=data.getValues();
				for (int j = 0; j < value.length; j++) {
					values.getValue(i, j, value[j]);
				}
				array.generateElement();
				array.setElement(i, data);
			}
		}
		return array;

	}

	void setArray(SFStructureArray array) {
		this->array = array;
		values.getDataObject().clear();
		SFStructureData data=new SFStructureData(array.getPipelineStructure());
		for (int i = 0; i < array.getElementsCount(); i++) {
			array.getElement(i, data);
			SFValuenf[] value=data.getValues();
			values.addValue(value);
		}
	}

	
	SFStructureArrayResource buildResource() {
		SFStructureArrayResource resource=new SFStructureArrayResource(getArray());
		return resource;
	}

	
	void updateResource(SFStructureArrayResource resource) {
		SFInitiator.addDestroyable(array);
		array=null;
		resource.setArray(getArray());
	}

	void invalidate() {
	}

}
;
}
#endif
