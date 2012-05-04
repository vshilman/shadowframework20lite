package shadow.pipeline;

import java.util.List;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionException;
import shadow.pipeline.parameters.SFParameteri;

/**
 * @author Alessandro Martinelli
 */
public class SFFunction {

	private SFParameteri parameter;
	private SFExpressionElement function;
	
	public SFFunction(SFParameteri globalV, SFExpressionElement function, List<SFParameteri> set) {
		super();
		this.parameter = globalV;
		if(function!=null)
			setFunction(function);
	}
	
	public SFParameteri getParameter() {
		return parameter;
	}
	
	public void setParameter(SFParameteri globalV) {
		this.parameter = globalV;
	}
	
	public SFExpressionElement getFunction() {
		return function;
	}
	
	public void setFunction(SFExpressionElement function) {
		this.function = function;
		try {
			this.function.evaluateType();
		} catch (SFExpressionException e) {
			e.printStackTrace();
		}
	}
}
