package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import shadow.pipeline.parameters.SFParameteri;


public abstract class SFExpressionOperator extends SFExpressionElement{

	public static int SIZE_ALL=-1;
	
	private int maxSize;

	public SFExpressionOperator(String operatorSymbol, int maxSize) {
		super(operatorSymbol);
		this.maxSize = maxSize;
	}
	
	@Override
	public SFExpressionElement cloneAsIndexed(SFParameteri[] toBeIndexed) {
		SFExpressionOperator operator=cloneOperator();
		for (int i = 0; i < list.size(); i++) {
			operator.list.add(list.get(i).cloneAsIndexed(toBeIndexed));
		}
		return operator;
	}

	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		if(!(maxSize==SIZE_ALL) && getElementSize()==maxSize)
			throw new ArrayIndexOutOfBoundsException("Too much elements in SFExpressionOperator "+getElement());
		addElement(element);
	}
	
	
	protected void updateSubExpressions() throws SFExpressionException{
		for (SFExpressionElement expression : list) {
			expression.evaluateType();
		}
	}
	
	
	protected void throwBadOperandsType(LinkedList<SFExpressionElement> cElements)
			throws SFExpressionException {
				Iterator<SFExpressionElement> iElements;
				String s="";
				iElements=cElements.iterator();
				s+=iElements.next().getElement();
				while (iElements.hasNext()) {
					s+=";"+iElements.next().getElement();
				}
				throw new SFExpressionException(" Cannot apply "+this.getElement()+" with the following operands: "+s);
			}

	@SuppressWarnings("unchecked")
	protected short separateAndWrap(LinkedList<SFExpressionElement> cElements) {
		
		ArrayList<SFExpressionElement>[] typeElements=new ArrayList[cElements.size()];
		Iterator<SFExpressionElement> iElements=list.iterator();
		typeElements[0]=new ArrayList<SFExpressionElement>();
		
		if(iElements.hasNext()){
			int index=0;
			typeElements[0].add(iElements.next());
			while(iElements.hasNext()){
				SFExpressionElement element=iElements.next();
				if(element.getType()!=typeElements[index].get(typeElements[index].size()-1).getType()){
					index++;
					typeElements[index]=new ArrayList<SFExpressionElement>();
					typeElements[index].add(element);
				}else{
					typeElements[index].add(element);
				}
			}
		}
		
		list.clear();
		short maxElement=0;
		for (int i = 0; i < typeElements.length; i++) {
			ArrayList<SFExpressionElement> elements=typeElements[i];
			short type=elements.get(0).getType();
			if(type>maxElement){
				maxElement=type;
			}
			if(elements.size()>1){
				SFExpressionTypeWrapper wrapper=SFExpressionGeneratorKeeper.getKeeper().
						getGenerator().getWrapper(type);
				SFExpressionOperator clone=cloneOperator();
				clone.setType(type);
				if(type>maxElement){
					maxElement=type;
				}
				clone.list.addAll(elements);
				wrapper.list.add(clone);
				list.add(wrapper);
			}else{
				list.add(elements.get(0));
			}
		}
		
		return maxElement;
	}
	
	protected abstract SFExpressionOperator cloneOperator();

	protected LinkedList<SFExpressionElement> getTypesSeparatorList() {
		LinkedList<SFExpressionElement> cElements=new LinkedList<SFExpressionElement>();
		Iterator<SFExpressionElement> iElements=list.iterator();
		
		if(iElements.hasNext()){
			cElements.add(iElements.next());
			while(iElements.hasNext()){
				SFExpressionElement tmp=iElements.next();
				if(tmp.getType()!=cElements.getLast().getType()){
					cElements.add(tmp);
				}
			}
		}
		return cElements;
	}

	protected void checkConsecutives(LinkedList<SFExpressionElement> cElements, ArrayList<Short[]> consecutives)
			throws SFExpressionException {
				Iterator<SFExpressionElement> iElements=cElements.iterator();
				if(iElements.hasNext()){
					SFExpressionElement tmp=null;
					SFExpressionElement tmpPrev=iElements.next();
					while (iElements.hasNext()) {
						tmp=iElements.next();
						boolean foundConsecutive=false;
						int i=0;
						while(i<consecutives.size() && !foundConsecutive){
							Short tps[]=consecutives.get(i);  
							if(tmpPrev.getType()==tps[0] && tmp.getType()==tps[1]){
								foundConsecutive=true;
							}   
							i++;
						}
						if(!foundConsecutive){
							throw new SFExpressionException("Uncompatible operands:"+tmpPrev.getElement()+"("+tmpPrev.getType()+")"+
									getElement()+tmp.getElement()+"("+tmp.getType()+")");
						}
					}
				}
			}

	protected static boolean hasOnlyVectors(LinkedList<SFExpressionElement> cElements) {
		Iterator<SFExpressionElement> iElements;
		boolean onlyVectors=true;
		iElements=cElements.iterator();
		while(iElements.hasNext()){
			short type=iElements.next().getType();
			if(type<SFParameteri.GLOBAL_FLOAT || type>SFParameteri.GLOBAL_FLOAT4)
				onlyVectors=false;
		}
		return onlyVectors;
	}
}
