package shadow.pipeline;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionException;
import shadow.pipeline.parameters.SFParameteri;

/**
 * @author Alessandro Martinelli
 */
public class SFFunction {

	private SFParameteri parameter;
	private SFExpressionElement function;
	
	public SFFunction(SFParameteri globalV, SFExpressionElement function) {
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
	
	public void compileFunction(SFParameteri[] parameters){
		this.function=this.function.cloneAsIndexed(parameters);
	}
	
	public void setFunction(SFExpressionElement function) {
		this.function = function;
		try {
			this.function.evaluateType();
			//System.err.println(function);//{*(3):[color(3), {wr2(2):{*(2):[intensity(2), tmp3(2)]}}]}
		} catch (SFExpressionException e) {
			e.printStackTrace();
		}
	}
}
