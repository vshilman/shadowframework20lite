package codeconverter.elements;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.IMethod;

public class ClassDefinition {

	private String name;
	private List<CodePattern> attributoDeclarations=new ArrayList<CodePattern>();
	private List<IMethod> methods=new ArrayList<IMethod>();
	private List<IMethod> constructors=new ArrayList<IMethod>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public List<CodePattern> getAttributoDeclarations() {
		return attributoDeclarations;
	}
	
	public List<IMethod> getMethods() {
		return methods;
	}
	
	public List<IMethod> getConstructors() {
		return constructors;
	}
	
}
