package codeconverter;

public class CodeElementProfile {

	public ICodeTemplate element;
	public ElementCardinality cardinality;
	
	public CodeElementProfile(ICodeTemplate element,
			ElementCardinality cardinality) {
		super();
		this.element=element;
		this.cardinality=cardinality;
	}
	public ICodeTemplate getElement() {
		return element;
	}
	public void setElement(ICodeTemplate element) {
		this.element=element;
	}
	public ElementCardinality getCardinality() {
		return cardinality;
	}
	public void setCardinality(ElementCardinality cardinality) {
		this.cardinality=cardinality;
	}
	
}
