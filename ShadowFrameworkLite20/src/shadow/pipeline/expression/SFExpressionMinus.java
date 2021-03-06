package shadow.pipeline.expression;

import java.util.LinkedList;

import shadow.math.SFValuenf;

public class SFExpressionMinus extends SFExpressionOperator{
	
	public SFExpressionMinus() {
		super("-", SIZE_ALL);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void evaluateType()  throws SFExpressionException{
		updateSubExpressions();

		//Get a list of all Elements which have a different type from previous one
		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();
			
		if(cElements.size()>1){
			//Tells if there are only vectors.
			boolean onlyVectors = hasOnlyVectors(cElements);
			if(onlyVectors){
				short maxElement=separateAndWrap(cElements);
				this.setType(maxElement);
				return;
			}else{
				throwBadOperandsType(cElements);
			}	
		}
		
		this.setType(list.getFirst().getType());
	}
	
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionMinus();
	}
	
	@Override
	public SFValuenf evaluate(SFExpressionValuesList values) {
		SFValuenf sum=getExpressionElement(0).evaluate(values);
		if(getElementSize()==1){
			sum.mult(-1);
		}
		for (int i = 1; i < getElementSize(); i++) {
			sum.addMult(-1, getExpressionElement(i).evaluate(values));
		}
		return sum;
	}
}
