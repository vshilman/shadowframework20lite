package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionSqrt extends SFExpressionOperator{

	private static ArrayList<Short[]> consecutives=new ArrayList<Short[]>();
	
	static{
		Short[] consecutive2={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive2);
	}
	
	public SFExpressionSqrt() {
		super("#", 2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void evaluateType()  throws SFExpressionException{
		updateSubExpressions();
		//Get a list of all Elements which have a different type from previous one
		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();
		checkConsecutives(cElements,consecutives);		
		//This must not be separated and wrapped
		//short maxElement=separateAndWrap(cElements);
		this.setType(SFParameteri.GLOBAL_FLOAT);
	}
	
	@Override
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionSqrt();
	}
}