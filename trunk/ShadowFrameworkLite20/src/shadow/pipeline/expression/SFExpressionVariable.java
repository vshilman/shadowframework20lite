package shadow.pipeline.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFExpressionVariable extends SFExpressionElement{

	Collection<SFParameteri> parameters=new LinkedList<SFParameteri>();
	
	public SFExpressionVariable(String element,Collection<SFParameteri> parameters) {
		super(element);
		this.parameters.addAll(parameters);
	}

	public SFParameteri getParameter(Collection<SFParameteri> set,String name){
		
		for (Iterator<SFParameteri> iterator = set.iterator(); iterator.hasNext();) {
			SFParameteri sfParameteri = iterator.next();
			if(sfParameteri.getName().equalsIgnoreCase(name)){
				return sfParameteri;
			}
			
		}
		return null;
	}	
	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		//Nothing to do
	}
	
	@Override
	public void evaluateType() throws SFExpressionException{
		try {
			SFPipelineRegister register=SFPipelineRegister.getFromName(this.getElement());
			setType(register.getType());
		} catch (SFException e) {
			
			SFParameteri parameter=getParameter(parameters,this.getElement());
			
			if(parameter!=null){
				setType(parameter.getType());
			}else{
				try {
					Double f=new Double(this.getElement());
					this.setElement(""+f);
					setType(SFParameteri.GLOBAL_FLOAT);
				} catch (NumberFormatException e1) {
					throw new SFExpressionException("Unknown Parameter "+this.getElement());
				}
			}
		}
	}
}
