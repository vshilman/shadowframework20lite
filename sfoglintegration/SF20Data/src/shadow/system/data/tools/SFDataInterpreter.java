package shadow.system.data.tools;

public interface SFDataInterpreter {
	
	public abstract void pushElement(String type,String name);
	
	public abstract void popElement(String type);
	
	public abstract void insertElement(String name,String info);
}
