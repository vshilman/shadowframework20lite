package shadow.pipeline.expression;

import java.util.Iterator;
import java.util.LinkedList;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;


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
	
	public SFExpressionElement getExpressionElement(int index) {
		return list.get(index);
	}

	public void setElement(String element) {
		this.element = element;
	}

	public int getElementSize(){
		return list.size();
	}
	
	public void addElement(SFExpressionElement element){
		if(element==null)
			throw new NullPointerException();
		list.add(element);
	}
	
	public void removeElement(SFExpressionElement element){
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
	
	public abstract SFValuenf evaluate(SFExpressionValuesList values);

	public abstract SFExpressionElement cloneAsIndexed(SFParameteri[] toBeIndexed);
	
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

	@Override
	public String toString() {
		String elString=getElement()+"("+getType()+")";
		if(list.size()>1)
			return "{"+elString+":"+list+"}";
		else if (list.size()==1)
			return "{"+elString+":"+list.get(0)+"}";
		
		return elString;
	}
}
