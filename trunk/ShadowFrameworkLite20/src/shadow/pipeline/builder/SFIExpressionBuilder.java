package shadow.pipeline.builder;

import java.util.List;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.parameters.SFParameteri;

public interface SFIExpressionBuilder {

	/**
	 * Prepare this builder
	 */
	public void setup();

	/**
	 * Retrieve a String containing all algebraic operators
	 */
	public String getOperatorsSum();
	/**
	 * Retrieve the list of all functions
	 */
	public List<String> getAvailableFunctions();
	/**
	 * Generate a value
	 */
	public void generateValue(String token, List<SFParameteri> set);
	/**
	 * Generate an operator
	 */
	public SFExpressionElement getBuiltExpression();
	/**
	 * Manage an operator symbol
	 */
	public void dispatchFunction(String operator);
	/**
	 * Generate a function
	 */
	public void dispatchOperator(String operator);
	/**
	 * Open Brackets
	 */
	public void openExpression();
	/**
	 * Close Brackets
	 */
	public void closeExpression();

}