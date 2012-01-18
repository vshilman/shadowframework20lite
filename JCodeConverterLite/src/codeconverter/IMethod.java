package codeconverter;

import java.util.List;

import codeconverter.elements.Method;

public interface IMethod {

	public Method getMethodDeclaration();
	
	public List<ICodeTemplate> getCodeLines();
	
	public void setup();
}
