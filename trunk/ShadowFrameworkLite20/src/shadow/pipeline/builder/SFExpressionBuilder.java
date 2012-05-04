package shadow.pipeline.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.pipeline.expression.SFExpressionOperator;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFExpressionBuilder implements SFIExpressionBuilder {

	private String operatorsSum = "#?:/*-+%,";
	private List<String> availableFunctions = new ArrayList<String>();
	{
		availableFunctions.add("dot");
		availableFunctions.add("sqrt");
		availableFunctions.add("sample");
		availableFunctions.add("clamp");
	}

	// private SFExpressionOperator symbol;
	private SFExpressionOperator lastSymbol;
	SFExpressionBuilderData data = new SFExpressionBuilderData();
	private SFExpressionsBuilderStack stack = new SFExpressionsBuilderStack(data);
	private boolean dispatchValue = false;
	private String lastOperator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.pipeline.expression.data.SFIExpressionBuilder#setup()
	 */
	@Override
	public void setup() {
		data.setExpressions(new LinkedList<SFExpressionOperator>());
		dispatchValue = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#getOperatorsSum()
	 */
	@Override
	public String getOperatorsSum() {
		return operatorsSum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#getAvailableFunctions
	 * ()
	 */
	@Override
	public List<String> getAvailableFunctions() {
		return availableFunctions;
	}

	private boolean hasPriority(String operator, String otherOperator) {
		return operatorsSum.indexOf(otherOperator) > operatorsSum.indexOf(operator);
	}

	private int firstIndexOfSymbolInExpressions(SFExpressionOperator symbol) {
		for (int i = 0; i < data.getExpressions().size(); i++) {
			if (data.getExpressions().get(i).getElement().equalsIgnoreCase(symbol.getElement())) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#generateValue(java
	 * .lang.String, java.util.List)
	 */
	@Override
	public void generateValue(String element, List<SFParameteri> set) {
		short type = SFParameteri.GLOBAL_FLOAT;
		try {
			SFPipelineRegister register = SFPipelineRegister.getFromName(element);
			type = register.getType();
		} catch (SFException e) {
			// Nothing to Do: the right type has been set up in the Constructor
			SFParameteri parameter = null;

			for (Iterator<SFParameteri> iterator = set.iterator(); iterator.hasNext();) {
				SFParameteri sfParameteri = iterator.next();
				if (sfParameteri.getName().equalsIgnoreCase(element)) {
					parameter = sfParameteri;
				}
			}

			if (parameter != null) {
				type = parameter.getType();
			} else {
				try {
					Double f = new Double(element);
					element = "" + f;
					type = SFParameteri.GLOBAL_FLOAT;
				} catch (NumberFormatException e1) {
					// throw new RuntimeException("Unknown Parameter "+element);
				}
			}
		}

		data.setLastValue(SFExpressionGeneratorKeeper.getKeeper().getGenerator()
				.getExpressionElement(element, type));
		dispatchValue = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#getBuiltExpression()
	 */
	@Override
	public SFExpressionElement getBuiltExpression() {
		if (dispatchValue) {
			if (data.getlSymbol() != null)
				data.setupLastSymbol(this);
			else {
				return data.getLastValue();
			}
		}
		return data.getExpressions().get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#dispatchFunction
	 * (java.lang.String)
	 */
	@Override
	public void dispatchFunction(String operator) {
		data.setFunctionOperator(operator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#dispatchOperator
	 * (java.lang.String)
	 */
	@Override
	public void dispatchOperator(String operator) {
		if (operator.equalsIgnoreCase(",") && data.getFunctionOperator() != null) {
			operator = data.getFunctionOperator();
		}
		
		SFExpressionOperator symbol = SFExpressionGeneratorKeeper.getKeeper().getGenerator()
				.getOperator(operator);
		if (data.getExpressions().size() == 0) {
			if (data.getLastValue() != null)
				symbol.addElement(data.getLastValue());
			if (symbol != null)
				data.getExpressions().add(symbol);
			data.setlSymbol(symbol);
		} else {
			if (hasPriority(operator, lastOperator)) {
				SFExpressionOperator lastSymbol = data.getExpressions().getLast();
				// greater priority
				lastSymbol.addElement(symbol);
				data.getExpressions().add(symbol);
				symbol.addElement(data.getLastValue());
				data.setlSymbol(symbol);
			} else if (hasPriority(lastOperator, operator)) {

				this.lastSymbol = data.getExpressions().getLast();
				int j = firstIndexOfSymbolInExpressions(symbol);
				if (j >= 0) {
					lastSymbol.addElement(data.getLastValue());
					for (int k = data.getExpressions().size() - 1; k > j; k--) {
						data.getExpressions().remove(k);
					}
					data.setlSymbol(data.getExpressions().get(j));
				} else {
					symbol.addElement(data.getExpressions().get(0));
					data.getExpressions().clear();
					lastSymbol.addElement(data.getLastValue());
					data.getExpressions().add(symbol);
					data.setlSymbol(symbol);
				}
			} else {
				SFExpressionOperator lastSymbol = data.getExpressions().get(
						data.getExpressions().size() - 1);
				lastSymbol.addElement(data.getLastValue());
			}
		}
		dispatchValue = false;
		lastOperator = operator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#openExpression()
	 */
	@Override
	public void openExpression() {
		stack.pushExpressions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#closeExpression()
	 */
	@Override
	public void closeExpression() {
		if (dispatchValue) {
			data.setupLastSymbol(this);
		}
		stack.popExpressions();
		dispatchValue = true;
	}

}
