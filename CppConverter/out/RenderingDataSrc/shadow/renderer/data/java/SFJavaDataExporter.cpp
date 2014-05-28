#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

#include "shadow/system/data.SFCharsetObject.h"
#include "shadow/system/data.SFCharsetObjectList.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFDataModule.h"
#include "shadow/system/data.SFDataObject.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.SFObjectsLibrary.h"
#include "shadow/system/data.SFObjectsLibrary.RecordData.h"
#include "shadow/system/data.objects.SFBinaryObject.h"

class SFJavaDataExporter {

	static final String LIBRARY = "library";
	static final String REFERENCE = "reference";
	static final String LIST_ELEMENTS = "_element";
	static final String NOSTRING = "";

	SFIDataExporter dataExporter;


	SFJavaDataExporter(SFIDataExporter dataExporter) {
		super();
		this->dataExporter = dataExporter;
	}

	void evaluateObjectsList(String name, SFCharsetObjectList list) {
		dataExporter.startListEvaluation(name);
			for (int i = 0; i < list.getSize(); i++) {
				String data = list.getCharSetObjectString(i);
				dataExporter.insertElement(name+LIST_ELEMENTS, data);
			}
		dataExporter.endListEvaluation(name);
	}

	void manageAsset(String name, SFDataModule<?> asset) {
		String type=asset.getType();
		dataExporter.startAssetEvaluation(type,name);
		manageDataObject(asset.getSFDataObject());
		dataExporter.endAssetEvaluation(type);
	}

	void writeLibrary(SFObjectsLibrary library){
		this->dataExporter.startListEvaluation(LIBRARY);
		for (RecordData recordData : library) {
			String name=recordData.getName();
			SFDataModule<?> asset=(SFDataModule<?>)(recordData.getDataset());
			manageAsset(name, asset);
		}
		this->dataExporter.endListEvaluation(LIBRARY);
	}

	void writeAsset(SFDataModule<?> asset){
		manageAsset(NOSTRING, asset);
	}

	void evaluateDataAssetList(String name,SFDataAssetList<?> dataAssetList){
		dataExporter.startListEvaluation(name);
			for (int i = 0; i < dataAssetList.size(); i++) {
				SFDataModule<?> asset=dataAssetList.get(i);
				manageAsset(NOSTRING, asset);
			}
		dataExporter.endListEvaluation(name);
	}

	void evaluateDataAssetObject(String name,SFDataAssetObject<?> dataAssetObject){
		SFDataModule<?> asset=dataAssetObject.getDataset();
		manageAsset(name, asset);
	}

	void evaluateLibraryReferenceList(String name,SFLibraryReferenceList<?> libraryReference){
		dataExporter.startLibraryReferenceList(name);
		for (int i = 0; i < libraryReference.size(); i++) {
			SFLibraryReference<?> asset=libraryReference.get(i);
			evaluateLibraryReference("reference",asset);
		}
		dataExporter.endLibraryReferenceList();
	}

	 void evaluateSFDataObjectList(String name,SFDataObjectsList<?> objectsList){
//		for (int i = 0; i < objectsList.size(); i++) {
//			SFDataObject object=objectsList.get(i);
//			evaluateDataObject(name, object);
}
		dataExporter.startListEvaluation(name);
			for (int i = 0; i < objectsList.size(); i++) {
				SFDataObject object=objectsList.get(i);
				if(object instanceof SFCharsetObject){
					String data = ((SFCharsetObject)object).toStringValue();
					dataExporter.insertElement(name+LIST_ELEMENTS, data);
				}
			}
		dataExporter.endListEvaluation(name);
	}

	void evaluateLibraryReference(String name,SFLibraryReference<?> reference){
		SFDataAsset<?> asset=reference.getDataset();
		if(asset!=null && reference.isNullReference()){
			manageAsset(name, asset);
		}
			dataExporter.insertElement(name, reference.getReference());
		}
	}

	void evaluateBinaryObject(String name,SFBinaryObject<?> binaryObject){
		dataExporter.insertElement(name,(binaryObject.getBinaryValue()).toStringValue());
	}

	void manageDataObject(SFNamedParametersObject dataObject) {
		for (int i = 0; i < dataObject.size(); i++) {
			SFDataObject object=dataObject.getDataObject(i);
			String name=dataObject.getName(i);
			evaluateDataObject(name, object);
		}
	}

	void evaluateDataObject(String name, SFDataObject object) {
		if(object instanceof SFCharsetObject){
			dataExporter.insertElement(name, ((SFCharsetObject)object).toStringValue());
		}
			evaluateSFDataObjectList(name,(SFDataObjectsList<?>)object);
		}
			evaluateBinaryObject(name,(SFBinaryObject<?>)object);
		}
			evaluateDataAssetList(name,(SFDataAssetList<?>)object);
		}
			evaluateDataAssetObject(name,(SFDataAssetObject<?>)object);
		}
			evaluateLibraryReference(name,(SFLibraryReference<?>)object);
		}
			evaluateLibraryReferenceList(name,(SFLibraryReferenceList<?>)object);
		}
			evaluateObjectsList(name,(SFCharsetObjectList)object);
		}
			dataExporter.reportError(name, object.getClass().getSimpleName());
			//System.out.println("\t"+name+"= ?? ("+object.getClass().getSimpleName()+")");
		}
	}
}
;
}
#endif
