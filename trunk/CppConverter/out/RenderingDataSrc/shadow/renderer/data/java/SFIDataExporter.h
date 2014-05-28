#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

interface SFIDataExporter {

	abstract void startAssetEvaluation(String type,String name);

	abstract void endAssetEvaluation(String type);

	abstract void startListEvaluation(String name);

	abstract void endListEvaluation(String name);

	abstract void startLibraryReferenceList(String name);

	abstract void endLibraryReferenceList();

	abstract void insertElement(String name,String info);

	abstract void reportError(String name,String moduleName);
}
;
}
#endif
