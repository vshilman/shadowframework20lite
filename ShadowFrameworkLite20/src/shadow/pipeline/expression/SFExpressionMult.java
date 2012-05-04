package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionMult extends SFExpressionOperator{

	private static ArrayList<Short[]> consecutives=new ArrayList<Short[]>();
	
	static{
		Short[] consecutive1={SFParameteri.GLOBAL_MATRIX4,SFParameteri.GLOBAL_FLOAT4};
		consecutives.add(consecutive1);
		Short[] consecutive2={SFParameteri.GLOBAL_FLOAT4,SFParameteri.GLOBAL_FLOAT4};
		consecutives.add(consecutive2);
		Short[] consecutive3={SFParameteri.GLOBAL_FLOAT3,SFParameteri.GLOBAL_FLOAT3};
		consecutives.add(consecutive3);
		Short[] consecutive4={SFParameteri.GLOBAL_FLOAT2,SFParameteri.GLOBAL_FLOAT2};
		consecutives.add(consecutive4);
		Short[] consecutive5={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive5);
		Short[] consecutive6={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT2};
		consecutives.add(consecutive6);
		Short[] consecutive7={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT3};
		consecutives.add(consecutive7);
		Short[] consecutive8={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT4};
		consecutives.add(consecutive8);
		Short[] consecutive9={SFParameteri.GLOBAL_FLOAT2,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive9);
		Short[] consecutive10={SFParameteri.GLOBAL_FLOAT3,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive10);
		Short[] consecutive11={SFParameteri.GLOBAL_FLOAT4,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive11);
		Short[] consecutive12={SFParameteri.GLOBAL_MATRIX4,SFParameteri.GLOBAL_FLOAT3};
		consecutives.add(consecutive12);
		Short[] consecutive13={SFParameteri.GLOBAL_FLOAT4,SFParameteri.GLOBAL_FLOAT3};
		consecutives.add(consecutive13);
		Short[] consecutive14={SFParameteri.GLOBAL_FLOAT3,SFParameteri.GLOBAL_FLOAT4};
		consecutives.add(consecutive14);
		Short[] consecutive15={SFParameteri.GLOBAL_GENERIC,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive15);
		Short[] consecutive16={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_GENERIC};
		consecutives.add(consecutive16);
	}
	
	public SFExpressionMult() {
		super("*", SIZE_ALL);
	}

	@Override
	public void evaluateType()  throws SFExpressionException{
		updateSubExpressions();
		
		//Get a list of all Elements which have a different type from previous one
		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();
			
		if(cElements.size()>1){
			checkConsecutives(cElements,consecutives);
			
			short maxElement=separateAndWrap(cElements);
			this.setType(maxElement);
			return;
		}
		
		this.setType(list.getFirst().getType());
	}
	
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionMult();
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		SFValuenf mult=getExpressionElement(0).evaluate(values);
		for (int i = 1; i < getElementSize(); i++) {
			mult.mult( getExpressionElement(i).evaluate(values));
		}
		return mult;
	}
}
