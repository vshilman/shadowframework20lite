package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.math.SFValue1f;
import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionDot extends SFExpressionOperator{

	private static ArrayList<Short[]> consecutives=new ArrayList<Short[]>();
	
	static{
		Short[] consecutive2={SFParameteri.GLOBAL_FLOAT4,SFParameteri.GLOBAL_FLOAT4};
		consecutives.add(consecutive2);
		Short[] consecutive3={SFParameteri.GLOBAL_FLOAT3,SFParameteri.GLOBAL_FLOAT3};
		consecutives.add(consecutive3);
		Short[] consecutive4={SFParameteri.GLOBAL_FLOAT2,SFParameteri.GLOBAL_FLOAT2};
		consecutives.add(consecutive4);
		Short[] consecutive5={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive5);
		Short[] consecutive6={SFParameteri.GLOBAL_FLOAT4,SFParameteri.GLOBAL_FLOAT3};
		consecutives.add(consecutive6);
		Short[] consecutive7={SFParameteri.GLOBAL_FLOAT3,SFParameteri.GLOBAL_FLOAT4};
		consecutives.add(consecutive7);
	}
	
	public SFExpressionDot() {
		super("?", 2);
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
		return new SFExpressionDot();
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		SFValuenf value1=getExpressionElement(0).evaluate(values);
		SFValuenf value2=getExpressionElement(1).evaluate(values);
		return new SFValue1f(value1.dot(value2));
	}
}