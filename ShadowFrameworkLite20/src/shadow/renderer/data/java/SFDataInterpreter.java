package shadow.renderer.data.java;

public interface SFDataInterpreter {
	
	public abstract void pushElement(String type,String name);
	
	public abstract void popElement(String type);
	
	public abstract void insertElement(String name,String info);
}
