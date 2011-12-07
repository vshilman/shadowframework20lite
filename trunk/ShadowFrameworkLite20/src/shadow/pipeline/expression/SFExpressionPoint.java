package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionPoint extends SFExpressionOperator{

	private static ArrayList<Short[]> consecutives=new ArrayList<Short[]>();
	
	static{
		Short[] consecutive2={SFParameteri.GLOBAL_TEXTURE,SFParameteri.GLOBAL_FLOAT2};
		consecutives.add(consecutive2);
	}
	
	public SFExpressionPoint() {
		super(".", 2);
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
		this.setType(SFParameteri.GLOBAL_FLOAT4);
	}
	
	@Override
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionPoint();
	}
}