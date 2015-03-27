package shadow.system.data.tools;

public interface SFIDataExporter {
	
	public abstract void startAssetEvaluation(String type,String name);
	
	public abstract void endAssetEvaluation(String type);

	public abstract void startListEvaluation(String name);
	
	public abstract void endListEvaluation(String name);

	public abstract void startLibraryReferenceList(String name);
	
	public abstract void endLibraryReferenceList();
	
	public abstract void insertElement(String name,String info);
	
	public abstract void reportError(String name,String moduleName);
}
