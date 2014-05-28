#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/vertices.SFValueListData.h"
#include "shadow/geometry/vertices.SFVertexListData16.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/pipeline/SFPrimitiveIndices.h"
#include "shadow/renderer/SfPrimitiveArrayResource.h"
#include "shadow/system/SFArray.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShortArray.h"
#include "shadow/system/data.objects.SFString.h"

/**
 * 
 * @author Alessandro Martinelli
 */
namespace sf{
class SFPrimitiveArrayData extends SFGraphicsAsset<SfPrimitiveArrayResource> implements SFDataset{

	SFString primitive=new SFString();
	Array array;
	SFLibraryReferenceList<SFValuesList<SFValuenf>> primitiveData=
		new SFLibraryReferenceList<SFValuesList<SFValuenf>>(new SFLibraryReference<SFValuesList<SFValuenf>>());
	Array> primitives=new SFDataObjectsList<SFShortArray>(new SFShortArray(new short[0]));

	//sample is used only on setArray.
	SFValueListData<?> sample=new SFVertexListData16();

	SFPrimitiveArrayData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("primitiveData", primitiveData);
		parameters.addObject("primitives", primitives);
		setData(parameters);
	}

	void setPrimitive(String primitive){
		this->primitive.setString(primitive);
	}

	void setArray(SFPrimitiveArray array) {
		this->array = array;

		for (int gridIndex = 0; gridIndex < array.getPrimitive().getGridsCount(); gridIndex++) {
			SFArray<SFValuenf> data=array.getPrimitiveData(gridIndex);

			SFValueListData<?> dataList=(SFValueListData<?>)sample.generateNewDatasetInstance();

			SFValuenf value=data.generateSample();
			for (int i = 0; i < data.getElementsCount(); i++) {
				data.getElement(i, value);
				dataList.addVertices(value.getV());
			}

			SFLibraryReference<SFValuesList<SFValuenf>> reference=new SFLibraryReference<SFValuesList<SFValuenf>>();
			reference.setDataset(dataList);
			this->primitiveData.add(reference);
		}

		SFPrimitiveIndices indices=array.generateSample();
		for (int i = 0; i < array.getElementsCount(); i++) {
			array.getElement(i, indices);
			short[] primitiveIndices=new short[indices.getPrimitiveIndices().length];
			for (int j = 0; j < primitiveIndices.length; j++) {
				primitiveIndices[j]=(short)(indices.getPrimitiveIndices()[j]);
			}
			primitives.add(new SFShortArray(primitiveIndices));
		}
	}

	
	SfPrimitiveArrayResource buildResource() {

		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());

		array = SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);

		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {

			final SFArray<SFValuenf> values = array.getPrimitiveData(gridIndex);
//			
//			primitiveData.get(gridIndex).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFValuesList<SFValuenf>>>() {

//				void onDatasetAvailable(String name,
//						SFDataAsset<SFValuesList<SFValuenf>> dataset) {
//					
}
}
//			
			SFValuesList<SFValuenf> list=primitiveData.get(gridIndex).getResource();
			values.generateElements(list.getSize());
			SFValuenf sample = values.generateSample();
			for (int i = 0; i < list.getSize(); i++) {
				list.getValue(i, sample);
				values.setElement(i, sample);
			}
		}

		SFPrimitiveIndices indices = array.generateSample();
		array.generateElements(primitives.size());
		for (int i = 0; i < primitives.size(); i++) {
			short[] primitiveIndices=primitives.get(i).getShortValues();
			for (int j = 0; j < primitiveIndices.length; j++) {
				indices.getPrimitiveIndices()[j]=primitiveIndices[j];
			}
			array.setElement(i, indices);
		}

		return new SfPrimitiveArrayResource(array);
	}

	
	void updateResource(SfPrimitiveArrayResource resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
