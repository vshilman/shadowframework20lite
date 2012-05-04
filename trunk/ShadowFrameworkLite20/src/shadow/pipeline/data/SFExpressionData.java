package shadow.pipeline.data;

import shadow.pipeline.expression.SFExpressionElement;

public class SFExpressionData {

	public static SFExpressionElement getExpressionElement(String type){
		if(type.equalsIgnoreCase("A")){
			return new SFDataExpressionElement();
		}
		if(type.equalsIgnoreCase("B")){
			return new SFDataExpressionTypeWrapper();
		}
		return new SFDataExpressionOperator();
	}
	
	public static String getExpressionElement(SFExpressionElement message){
		if(message instanceof SFDataExpressionElement){
			return "A";
		}else if(message instanceof SFDataExpressionTypeWrapper){
			return "B";
		}
		return "C";
	}
}
