package codeconverter.elements;

public class MethodDeclaration {
	
	private ModifiersSet set=new ModifiersSet();
	private NamedElement methodName=new NamedElement("");
	private VariableList list=new VariableList();
	
	public ModifiersSet getSet() {
		return set;
	}
	public void setSet(ModifiersSet set) {
		this.set = set;
	}
	public NamedElement getMethodName() {
		return methodName;
	}
	public void setMethodName(NamedElement methodName) {
		this.methodName = methodName;
	}
	public VariableList getList() {
		return list;
	}
	public void setList(VariableList list) {
		this.list = list;
	}
	
}
