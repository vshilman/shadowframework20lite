package shadow.pipeline.expression;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionVector extends SFExpressionOperator{

	private static ArrayList<Short[]> consecutives=new ArrayList<Short[]>();
	
	static{
		Short[] consecutive5={SFParameteri.GLOBAL_FLOAT,SFParameteri.GLOBAL_FLOAT};
		consecutives.add(consecutive5);
	}
	
	public SFExpressionVector() {
		super(",", SIZE_ALL);
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
		
		switch (list.size()) {
			case 1: this.setType(SFParameteri.GLOBAL_FLOAT); break;
			case 2: this.setType(SFParameteri.GLOBAL_FLOAT2); break;
			case 3: this.setType(SFParameteri.GLOBAL_FLOAT3); break;
			default: this.setType(SFParameteri.GLOBAL_FLOAT4); break;
		}
		this.setType(list.getFirst().getType());
	}
	
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionVector();
	}
}