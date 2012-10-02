package shadow.pipeline.builder;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionOperator;

public class SFExpressionsBuilderStack {
	public ArrayList<LinkedList<SFExpressionOperator>> storedExpressiond=new ArrayList<LinkedList<SFExpressionOperator>>();
	public ArrayList<SFExpressionElement> storedlSymbol=new ArrayList<SFExpressionElement>();
	public ArrayList<Integer> storedlastIndexOf=new ArrayList<Integer>();
	private SFExpressionBuilderData sfExpressionBuilderData;

	public SFExpressionsBuilderStack(SFExpressionBuilderData sfExpressionBuilderData) {
		this.sfExpressionBuilderData=sfExpressionBuilderData;
	}

	public void pushExpressions() {
		storedExpressiond.add(sfExpressionBuilderData.getExpressions());
		storedlSymbol.add(sfExpressionBuilderData.getlSymbol());
		storedlastIndexOf.add(sfExpressionBuilderData.getIndexOfLastOperation());
		
		sfExpressionBuilderData.setExpressions(new LinkedList<SFExpressionOperator>());
		sfExpressionBuilderData.setlSymbol(null);
		sfExpressionBuilderData.setLastValue(null);
		sfExpressionBuilderData.setIndexOfLastOperation(-1);
		//sfExpressionBuilderData.setFunctionOperator(null);
	}

	public void popExpressions(){
		if(sfExpressionBuilderData.getExpressions().size()>0)
			sfExpressionBuilderData.setLastValue(sfExpressionBuilderData.getExpressions().get(0));
		int position=storedExpressiond.size()-1;
		sfExpressionBuilderData.setExpressions(storedExpressiond.remove(position));
		sfExpressionBuilderData.setlSymbol(storedlSymbol.remove(position));
		sfExpressionBuilderData.setIndexOfLastOperation(storedlastIndexOf.remove(position));
	}
}