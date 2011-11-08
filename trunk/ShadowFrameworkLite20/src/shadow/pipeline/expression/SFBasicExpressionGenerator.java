package shadow.pipeline.expression;

import java.util.List;

import shadow.pipeline.parameters.SFParameteri;


public class SFBasicExpressionGenerator implements SFExpressionGeneratori {

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
		if(operatorSymbol.equalsIgnoreCase(":")){
			return new SFExpressionClamp();
		}
		if(operatorSymbol.equalsIgnoreCase("°")){
			return new SFExpressionDot();
		}
		return new SFExpressionSum();
	}

	@Override
	public SFExpressionElement getExpressionElement(String value, List<SFParameteri> set){
		return new SFExpressionVariable(value,set);
	}
	
	@Override
	public SFExpressionTypeWrapper getWrapper(short type){
		return new SFExpressionTypeWrapper(type);
	}
}
