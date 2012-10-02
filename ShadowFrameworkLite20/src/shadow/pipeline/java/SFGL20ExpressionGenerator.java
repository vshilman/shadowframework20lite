package shadow.pipeline.java;

import java.util.HashMap;
import java.util.LinkedList;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.expression.SFExpressionOperator;
import shadow.pipeline.expression.SFExpressionTypeWrapper;
import shadow.pipeline.expression.SFExpressionVariable;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFGL20ExpressionGenerator implements SFExpressionElementInterpreter{

	private static SFGL20ExpressionGenerator generator=new SFGL20ExpressionGenerator();
	private String value;
	
	private LinkedList<SFExpressionElement> parameters=new LinkedList<SFExpressionElement>();
	
	private static SFParameteri refParameter;
	
	private static HashMap<String, String> renameMap=new HashMap<String, String>();
	
	private static HashMap<String, String> functionsOperator=new HashMap<String, String>();
	
	static{
		functionsOperator.put("clamp","clamp");
		functionsOperator.put("cos","cos");
		functionsOperator.put("sin","sin");
		functionsOperator.put("dot","dot");
		functionsOperator.put("sqrt","sqrt");
		functionsOperator.put("sample","texture2D");
		functionsOperator.put("cross","cross");
		functionsOperator.put("normalize","normalize");
		functionsOperator.put("inversesqrt","inversesqrt");
		functionsOperator.put("pow","pow");
	}
	
	/* return null if operator string is not a function operator*/
	private static String getAsFunction(String operator){
		return functionsOperator.get(operator);
	}
	
	private String getTypeWrapOpenString(short wrappedType,short wrappingType){
		//No wrap needed!
		if(SFParameteri.getExpressionDimension(wrappedType)==SFParameteri.getExpressionDimension(wrappingType))
			return "";
		
		if(wrappingType==SFParameteri.GLOBAL_GENERIC){
			return getTypeWrapOpenString(wrappedType,refParameter.getType());
		}
		
		if(wrappingType<=SFParameteri.GLOBAL_MATRIX4){
			if(wrappedType<wrappingType)
				return "vec"+SFParameteri.getExpressionDimension(wrappingType)+"(";
			else if(wrappedType!=SFParameteri.GLOBAL_GENERIC)
				return "(";
		}
		
		return "";
	}

	private String getTypeWrapCloseString(short wrappedType,short wrappingType){

		if(SFParameteri.getExpressionDimension(wrappedType)==SFParameteri.getExpressionDimension(wrappingType))
			return "";

		if(wrappedType==SFParameteri.GLOBAL_FLOAT){
			return ")";
		}

		switch (wrappingType) {
			case SFParameteri.GLOBAL_GENERIC:
				if(refParameter.getType()==SFParameteri.GLOBAL_GENERIC)
					throw new SFException("Bad Code");
				return getTypeWrapCloseString(wrappedType,refParameter.getType());
			case SFParameteri.GLOBAL_FLOAT:
				return ").x";
			case SFParameteri.GLOBAL_FLOAT2:
				if(wrappedType<SFParameteri.GLOBAL_MATRIX4){
					return ").xy";
				}break;
			case SFParameteri.GLOBAL_FLOAT3:
				if(wrappedType==SFParameteri.GLOBAL_FLOAT2)
					return ",0)";
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT4: return ").xyz";
					case SFParameteri.GLOBAL_MATRIX4: return ").xyz";
				}break;
			case SFParameteri.GLOBAL_FLOAT4:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT2: return ",0,1)";
					case SFParameteri.GLOBAL_FLOAT3: return ",1)";
				}break;
			case SFParameteri.GLOBAL_MATRIX4:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT3: return ",1)";
				}
		}
		return "";
	}
	
	@Override
	public void closeElement(SFExpressionElement element) {
		
		if(element==parameters.getLast()){
			//System.out.println("ENTER!");
			String function=getAsFunction(parameters.getLast().getElement());
			if(function!=null){
				value+=")";
			}else{
				if(element.getElement().length()==1 && !element.getElement().equalsIgnoreCase(",")){
					value+=")";
				}
			}
			if(!(parameters.getLast() instanceof SFExpressionVariable)){
				parameters.removeLast();
			}
			if(function==null && !element.getElement().equalsIgnoreCase(",")){
				value+=getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
			}else{
				if(element.getType()!=parameters.getLast().getType()){
					function=getAsFunction(parameters.getLast().getElement());
					if(function==null){
						value+=getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
					}	
				}
			}
		}else{
			String function=getAsFunction(parameters.getLast().getElement());
			if(function==null && element instanceof SFExpressionVariable){
				value+=getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
			}
		}
	}
	
	@Override
	public void refreshElement(SFExpressionElement element) {
		String function=getAsFunction(element.getElement());
		if(function==null){
			value+=element.getElement();
		}else{
			value+=",";
		}
	}
	
	@Override
	public void startElement(SFExpressionElement element) {
		
		if(element.getType()!=parameters.getLast().getType() && element.getType()>=0){
			String function=getAsFunction(parameters.getLast().getElement());
			if(function==null){
				value+=getTypeWrapOpenString(element.getType(),parameters.getLast().getType());
				if(!(element instanceof SFExpressionVariable))
					parameters.add(element);
			}
		}
		if(element instanceof SFExpressionOperator){
			String function=getAsFunction(element.getElement());
			if(function!=null){
				value+=function+"(";
			}else if(!element.getElement().equalsIgnoreCase(",")){
				if(element.getElement().equalsIgnoreCase("-"))
					if(element.getElementSize()==1)
						value+="-";
				value+="(";
			}
			if(parameters.getLast()!=element)
				parameters.add(element);
		}
		if(element instanceof SFExpressionVariable){
			
			String data=element.getElement();
			
			String renameMapData=renameMap.get(data);
			
			if(renameMapData!=null){
				value+=renameMapData;
			}else{
				value+=data;
			}
		}
	}
	

	public static HashMap<String, String> getRenameMap() {
		return renameMap;
	}

	public static void setRenameMap(HashMap<String, String> renameMap) {
		SFGL20ExpressionGenerator.renameMap = renameMap;
	}
	
	public static SFParameteri getRefParameter() {
		return refParameter;
	}

	public static void setRefParameter(SFParameteri refParameter) {
		SFGL20ExpressionGenerator.refParameter=refParameter;
	}

	public static SFGL20ExpressionGenerator getGenerator(SFParameteri outputParameter) {
		generator.parameters.clear();
		generator.parameters.add(new SFExpressionTypeWrapper(outputParameter.getType()));
		generator.value="";
		
		return generator;
	}
	
	public static String getFunctionString(){
		return generator.value;
	}
}
