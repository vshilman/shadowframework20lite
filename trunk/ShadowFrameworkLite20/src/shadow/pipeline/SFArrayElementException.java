package shadow.pipeline;

public class SFArrayElementException extends Exception{

	//constant 0xffadf=== 0xshadow where s,h and w are truncated to f
	public static final long serialVersionUID=0xffadf+SFArrayElementException.class.hashCode();
	
	private static final String genericMessage=" has been used on incompatible SFArray.";

	private Object element;
	private String additionalMessage;
	
	public SFArrayElementException(Object element,String additionalMessage) {
		super(element.getClass().getSimpleName()+genericMessage+":"+additionalMessage);
		this.element=element;
		this.additionalMessage=additionalMessage;
	}

	public Object getElement() {
		return element;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}
}