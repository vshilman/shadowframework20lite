package shadow.pipeline.data;

import java.util.LinkedList;

import shadow.math.SFValuenf;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionException;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.pipeline.expression.SFExpressionOperator;
import shadow.pipeline.expression.SFValuesMap;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDataExpressionOperator extends SFExpressionOperator implements SFDataObject{

	public SFDataExpressionOperator() {
		super("+", SIZE_ALL);
	}
	
	public SFDataExpressionOperator(String operatorSymbol) {
		super(operatorSymbol, SIZE_ALL);
	}

	@Override
	public SFDataExpressionOperator clone(){
		return new SFDataExpressionOperator(this.getElement());
	}
	
	@Override
	public SFExpressionOperator cloneOperator() {
		return clone();
	}
	
	@Override
	public void evaluateType() throws SFExpressionException {
		//Do nothing
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		setElement(stream.readString());
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			String element=stream.readString();
			SFExpressionElement expression=(SFExpressionData.getExpressionElement(element));
			SFDataObject dataset=(SFDataObject)expression;
			dataset.readFromStream(stream);
			this.list.add(expression);
		}
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		return getExpressionElement(0).evaluate(values);
	}
	
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(getElement());
		LinkedList<SFExpressionElement> list=this.list;
		stream.writeShort((short)(list.size()));
		for (SFExpressionElement sfExpressionElement : list) {
			String element=SFExpressionData.getExpressionElement(sfExpressionElement);
			stream.writeString(element);
			((SFDataObject)sfExpressionElement).writeOnStream(stream);
		}
	}
	
	
	public SFExpressionElement retrieveEffectiveElement(){
		SFExpressionElement element=list.get(0);
		SFExpressionElement newElement=getElement(element);
		getCloneElements(element, newElement);
		return newElement;
	}
	
	public void getCloneElements(SFExpressionElement element,SFExpressionElement newElement){
		for (int i = 0; i < element.getElementSize(); i++) {
			SFExpressionElement newSubElement=getElement(element.getExpressionElement(i));
			getCloneElements(element.getExpressionElement(i),newSubElement);
			newElement.addElement(newSubElement);
		}
	}
	
	private SFExpressionElement getElement(SFExpressionElement element){
		if(element instanceof SFDataExpressionElement){
			return SFExpressionGeneratorKeeper.getKeeper().getGenerator().getExpressionElement(element.getElement(), element.getType());
		}else if(element instanceof SFDataExpressionTypeWrapper){
			return SFExpressionGeneratorKeeper.getKeeper().getGenerator().getWrapper(element.getType());
		}else{
			return SFExpressionGeneratorKeeper.getKeeper().getGenerator().getOperator(element.getElement());
		}
	}
	
}
