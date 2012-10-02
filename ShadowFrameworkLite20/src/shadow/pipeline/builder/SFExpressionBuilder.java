package shadow.pipeline.builder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.pipeline.expression.SFExpressionOperator;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFExpressionBuilder implements SFIExpressionBuilder {

	private static final boolean debug = false;
	
	private String operatorsSum = ",/*-+";
	private List<String> availableFunctions = new ArrayList<String>();
	{
		availableFunctions.add("dot");
		availableFunctions.add("sqrt");
		availableFunctions.add("sample");
		availableFunctions.add("clamp");
		availableFunctions.add("sin");
		availableFunctions.add("cos");
		availableFunctions.add("cross");
		availableFunctions.add("normalize");
		availableFunctions.add("inversesqrt");
		availableFunctions.add("pow");
	}

	// private SFExpressionOperator symbol;
	private SFExpressionOperator lastSymbol;
	SFExpressionBuilderData data = new SFExpressionBuilderData();
	private SFExpressionsBuilderStack stack = new SFExpressionsBuilderStack(data);
	private boolean dispatchValue = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.pipeline.expression.data.SFIExpressionBuilder#setup()
	 */
	@Override
	public void setup() {
		if(debug){
			System.err.println("SFExpressionBuilder : setup....");
		}
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
		if(debug){
			System.err.println("SFExpressionBuilder : getOperatorsSum....");
		}
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
		if(debug){
			System.err.println("SFExpressionBuilder : getAvailableFunctions....");
		}
		return availableFunctions;
	}

	private boolean hasPriority(String operator, String otherOperator) {
		int indexOfOperator = operatorsSum.indexOf(operator);
		int indexOfOtherOperator = operatorsSum.indexOf(otherOperator);
		if(debug){
			System.err.println("SFExpressionBuilder : hasPriority "+operator+" "+otherOperator);
		}
		return indexOfOtherOperator > indexOfOperator;
	}

	private int firstIndexOfSymbolInExpressions(SFExpressionOperator symbol) {
		if(debug){
			System.err.println("SFExpressionBuilder : firstIndexOfSymbolInExpressions "+symbol );
		}
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
	public void generateValue(String element, SFParameteri[] set) {
		if(debug){
			System.err.println("SFExpressionBuilder : generateValue "+element);
		}
		
		short type = SFParameteri.GLOBAL_FLOAT;
		try {
			SFPipelineRegister register = SFPipelineRegister.getFromName(element);
			type = register.getType();
		} catch (SFException e) {
			// Nothing to Do: the right type has been set up in the Constructor
			SFParameteri parameter = null;
			
			for (int i = 0; i < set.length; i++) {
				SFParameteri sfParameteri = set[i];
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
		if(debug){
			System.err.println("SFExpressionBuilder : getBuiltExpression ");
		}
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
		if(debug){
			System.err.println("SFExpressionBuilder : dispatchFunction "+operator);
		}
		SFExpressionOperator symbol = SFExpressionGeneratorKeeper.getKeeper().getGenerator()
				.getFunction(operator);
		
		if (data.getExpressions().size() == 0) {
			if (symbol != null)
				data.getExpressions().add(symbol);
			data.setlSymbol(symbol);
		} else {
			SFExpressionOperator lastSymbol = data.getExpressions().getLast();
			// greater priority
			lastSymbol.addElement(symbol);
			data.getExpressions().add(symbol);
			data.setlSymbol(symbol);
		}
		dispatchValue = false;
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
		if(debug){
			System.err.println(data.getExpressions());
			System.err.println("SFExpressionBuilder : dispatchOperator "+operator);
		}
//		if (operator.equalsIgnoreCase(",")) {//do not like...
//			operator = data.getExpressions().getLast().getElement();
//		}
		
		SFExpressionOperator symbol = SFExpressionGeneratorKeeper.getKeeper().getGenerator()
				.getOperator(operator);
		
		if (data.getExpressions().size() == 0) {
			if (data.getLastValue() != null)
				symbol.addElement(data.getLastValue());
			if (symbol != null)
				data.getExpressions().add(symbol);
			data.setlSymbol(symbol);
		} else {

			String lastOperator = data.getExpressions().getLast().getElement();
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shadow.pipeline.expression.data.SFIExpressionBuilder#openExpression()
	 */
	@Override
	public void openExpression() {
		if(debug){
			System.err.println("SFExpressionBuilder : openExpression ");
		}
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
		if(debug){
			System.err.println("SFExpressionBuilder : closeExpression ");
		}
		if (dispatchValue) {
			data.setupLastSymbol(this);
		}
		stack.popExpressions();
		dispatchValue = true;
	}

}
