package shadow.pipeline.expression;

import java.util.LinkedList;

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
}
