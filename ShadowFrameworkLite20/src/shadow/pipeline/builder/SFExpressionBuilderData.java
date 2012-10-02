package shadow.pipeline.builder;

import java.util.LinkedList;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionOperator;

public class SFExpressionBuilderData {
	private LinkedList<SFExpressionOperator> expressions;
	private SFExpressionElement lSymbol;
	private SFExpressionElement lastValue;
	private int indexOfLastOperation;
	
	public SFExpressionBuilderData() {
	}

	public void setupLastSymbol(SFIExpressionBuilder sfExpressionBuilder) {
		if(getlSymbol()!=null)
			getlSymbol().addElement(getLastValue());
	}

	public LinkedList<SFExpressionOperator> getExpressions() {
		return expressions;
	}

	public void setExpressions(LinkedList<SFExpressionOperator> expressions) {
		this.expressions = expressions;
	}

	public SFExpressionElement getlSymbol() {
		return lSymbol;
	}

	public void setlSymbol(SFExpressionElement lSymbol) {
		this.lSymbol = lSymbol;
	}

	public SFExpressionElement getLastValue() {
		return lastValue;
	}

	public void setLastValue(SFExpressionElement lastValue) {
		this.lastValue = lastValue;
	}

	int getIndexOfLastOperation() {
		return indexOfLastOperation;
	}

	void setIndexOfLastOperation(int indexOfLastOperation) {
		this.indexOfLastOperation = indexOfLastOperation;
	}
	
}