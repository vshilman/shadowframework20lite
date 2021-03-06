package shadow.renderer.data.java;

import shadow.renderer.data.SFAssetModule;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetList;
import shadow.renderer.data.SFDataAssetObject;
import shadow.renderer.data.SFDataObjectsList;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFLibraryReferenceList;
import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFObjectsLibrary.RecordData;
import shadow.system.data.objects.SFBinaryObject;

public class SFJavaDataExporter {
	
	public static final String LIBRARY = "library";
	public static final String REFERENCE = "reference";
	public static final String LIST_ELEMENTS = "_element";
	public static final String NOSTRING = "";
	
	private SFIDataExporter dataExporter;

	
	public SFJavaDataExporter(SFIDataExporter dataExporter) {
		super();
		this.dataExporter = dataExporter;
	}
	
	public void evaluateObjectsList(String name, SFCharsetObjectList list) {
		dataExporter.startListEvaluation(name);
			for (int i = 0; i < list.getSize(); i++) {
				String data = list.getCharSetObjectString(i);
				dataExporter.insertElement(name+LIST_ELEMENTS, data);
			}
		dataExporter.endListEvaluation(name);
	}

	public void manageAsset(String name, SFAssetModule asset) {
		String type=asset.getType();
		dataExporter.startAssetEvaluation(type,name);
		manageDataObject(asset.getSFDataObject());
		dataExporter.endAssetEvaluation(type);
	}
	
	public void writeLibrary(SFObjectsLibrary library){
		this.dataExporter.startListEvaluation(LIBRARY);
		for (RecordData recordData : library) {
			String name=recordData.getName();
			SFAssetModule asset=(SFAssetModule)(recordData.getDataset());
			manageAsset(name, asset);
		}
		this.dataExporter.endListEvaluation(LIBRARY);
	}
	
	public void writeAsset(SFAssetModule asset){
		manageAsset(NOSTRING, asset);
	}

	public void evaluateDataAssetList(String name,SFDataAssetList<?> dataAssetList){
		dataExporter.startListEvaluation(name);
			for (int i = 0; i < dataAssetList.size(); i++) {
				SFDataAsset<?> asset=dataAssetList.get(i);
				manageAsset(NOSTRING, asset);
			}
		dataExporter.endListEvaluation(name);
	}

	public void evaluateDataAssetObject(String name,SFDataAssetObject<?> dataAssetObject){
		SFDataAsset<?> asset=dataAssetObject.getDataset();
		manageAsset(name, asset);
	}

	public void evaluateLibraryReferenceList(String name,SFLibraryReferenceList<?> libraryReference){
		dataExporter.startLibraryReferenceList(name);
		for (int i = 0; i < libraryReference.size(); i++) {
			SFLibraryReference<?> asset=libraryReference.get(i);
			evaluateLibraryReference("reference",asset);
		}
		dataExporter.endLibraryReferenceList();
	}

	public  void evaluateSFDataObjectList(String name,SFDataObjectsList<?> objectsList){
//		for (int i = 0; i < objectsList.size(); i++) {
//			SFDataObject object=objectsList.get(i);
//			evaluateDataObject(name, object);
//		}
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

	public void evaluateLibraryReference(String name,SFLibraryReference<?> reference){
		SFDataAsset<?> asset=reference.getDataset();
		if(asset!=null){
			manageAsset(name, asset);
		}else{
			dataExporter.insertElement(name, reference.getReference());
		}	
	}

	public void evaluateBinaryObject(String name,SFBinaryObject<?> binaryObject){
		dataExporter.insertElement(name,(binaryObject.getBinaryValue()).toStringValue());
	}

	public void manageDataObject(SFNamedParametersObject dataObject) {
		for (int i = 0; i < dataObject.size(); i++) {
			SFDataObject object=dataObject.getDataObject(i);
			String name=dataObject.getName(i);
			evaluateDataObject(name, object);
		}
	}

	public void evaluateDataObject(String name, SFDataObject object) {
		if(object instanceof SFCharsetObject){
			dataExporter.insertElement(name, ((SFCharsetObject)object).toStringValue());
		}else if(object instanceof SFDataObjectsList<?>){
			evaluateSFDataObjectList(name,(SFDataObjectsList<?>)object);
		}else if(object instanceof SFBinaryObject<?>){
			evaluateBinaryObject(name,(SFBinaryObject<?>)object);
		}else if(object instanceof SFDataAssetList<?>){
			evaluateDataAssetList(name,(SFDataAssetList<?>)object);
		}else if(object instanceof SFDataAssetObject<?>){
			evaluateDataAssetObject(name,(SFDataAssetObject<?>)object);
		}else if(object instanceof SFLibraryReference<?>){
			evaluateLibraryReference(name,(SFLibraryReference<?>)object);
		}else if(object instanceof SFLibraryReferenceList<?>){
			evaluateLibraryReferenceList(name,(SFLibraryReferenceList<?>)object);
		}else if(object instanceof SFCharsetObjectList){
			evaluateObjectsList(name,(SFCharsetObjectList)object);
		}else{
			dataExporter.reportError(name, object.getClass().getSimpleName());
			//System.out.println("\t"+name+"= ?? ("+object.getClass().getSimpleName()+")");
		}
	}
}
