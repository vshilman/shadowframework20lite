package shadow.pipeline.expression;

import java.util.Iterator;
import java.util.LinkedList;


public abstract class SFExpressionElement {
	
	protected LinkedList<SFExpressionElement> list=new LinkedList<SFExpressionElement>();
	private String element;
	private short type;//SFParameter.type. 

	public SFExpressionElement(String element) {
		super();
		this.element = element;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	protected int getElementSize(){
		return list.size();
	}
	
	protected void addElement(SFExpressionElement element){
		if(element==null)
			throw new NullPointerException();
		list.add(element);
	}
	
	protected void removeElement(SFExpressionElement element){
		if(element==null)
			throw new NullPointerException();
		list.remove(element);
	}
	
	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}
	
	public abstract void evaluateType() throws SFExpressionException;
	
	public abstract void addSubExpression(SFExpressionElement element) 
			throws ArrayIndexOutOfBoundsException;

	public void traverse(SFExpressionElementInterpreter intepreter){
		intepreter.startElement(this);
			for (Iterator<SFExpressionElement> iterator = list.iterator(); iterator.hasNext();) {
				iterator.next().traverse(intepreter);	
				if(iterator.hasNext()){
					intepreter.refreshElement(this);
				}
			}
		intepreter.closeElement(this);
	}  

}
