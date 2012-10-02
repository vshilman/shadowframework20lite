package shadow.pipeline.expression;


public class SFBasicExpressionGenerator implements SFIExpressionGenerator {

	@Override
	public SFExpressionOperator getOperator(String operatorSymbol){
		if(operatorSymbol.equalsIgnoreCase("+")){
			return new SFExpressionSum();
		}
		if(operatorSymbol.equalsIgnoreCase("*")){
			return new SFExpressionMult();
		}
		if(operatorSymbol.equalsIgnoreCase("/")){
			return new SFExpressionDivide();
		}
		if(operatorSymbol.equalsIgnoreCase("-")){
			return new SFExpressionMinus();
		}
		if(operatorSymbol.equalsIgnoreCase(",")){
			return new SFExpressionVector();
		}
		return new SFExpressionSum();
	}
	
	@Override
	public SFExpressionElement getExpressionElement(String value, short type){
		return new SFExpressionVariable(value,type);
	}
	
	@Override
	public SFExpressionTypeWrapper getWrapper(short type){
		return new SFExpressionTypeWrapper(type);
	}
	
	@Override
	public SFExpressionOperator getFunction(String functionSymbol) {
		return new SFFunctionOperator(functionSymbol);
	}
}
