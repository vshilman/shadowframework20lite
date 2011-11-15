package shadow.pipeline.openGL20;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.expression.SFExpressionClamp;
import shadow.pipeline.expression.SFExpressionDivide;
import shadow.pipeline.expression.SFExpressionDot;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.expression.SFExpressionGeneratori;
import shadow.pipeline.expression.SFExpressionMinus;
import shadow.pipeline.expression.SFExpressionMult;
import shadow.pipeline.expression.SFExpressionOperator;
import shadow.pipeline.expression.SFExpressionSqrt;
import shadow.pipeline.expression.SFExpressionSum;
import shadow.pipeline.expression.SFExpressionTypeWrapper;
import shadow.pipeline.expression.SFExpressionVariable;
import shadow.pipeline.parameters.SFParameteri;

public class SFGL20ExpressionGenerator implements SFExpressionGeneratori,SFExpressionElementInterpreter{

	private static SFGL20ExpressionGenerator generator=new SFGL20ExpressionGenerator();
	private String value;
	
	private LinkedList<SFExpressionElement> parameters=new LinkedList<SFExpressionElement>();
	
	private static SFParameteri refParameter;
	
	private static HashMap<String, String> renameMap=new HashMap<String, String>();
	
	private static HashMap<String, String> functionsOperator=new HashMap<String, String>();
	
	static{
		functionsOperator.put(":","clampf");
		functionsOperator.put("�","dot");
		functionsOperator.put("#","sqrt");
	}
	
	/* return null if operator string is not a function operator*/
	private static String getAsFunction(String operator){
		return functionsOperator.get(operator);
	}
	
	private String getTypeWrapOpenString(short wrappedType,short wrappingType){
		switch (wrappingType) {
			case SFParameteri.GLOBAL_GENERIC:
				return getTypeWrapOpenString(wrappedType,refParameter.getType());
			case SFParameteri.GLOBAL_FLOAT:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT2: return "(";
					case SFParameteri.GLOBAL_FLOAT3: return "(";
					case SFParameteri.GLOBAL_FLOAT4: return "(";
					case SFParameteri.GLOBAL_MATRIX4: return "(";
				}
			case SFParameteri.GLOBAL_FLOAT2:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT: return "vec2(";
					case SFParameteri.GLOBAL_FLOAT3: return "(";
					case SFParameteri.GLOBAL_FLOAT4: return "(";
					case SFParameteri.GLOBAL_MATRIX4: return "(";
				}
			case SFParameteri.GLOBAL_FLOAT3:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT: return "vec3(";
					case SFParameteri.GLOBAL_FLOAT2: return "vec3(";
					case SFParameteri.GLOBAL_FLOAT4: return "(";
					case SFParameteri.GLOBAL_MATRIX4: return "(";
				}
			case SFParameteri.GLOBAL_FLOAT4:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT: return "vec4(";
					case SFParameteri.GLOBAL_FLOAT2: return "vec4(";
					case SFParameteri.GLOBAL_FLOAT3: return "vec4(";
				}
			case SFParameteri.GLOBAL_MATRIX4:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT3: return "vec4(";
					case SFParameteri.GLOBAL_FLOAT4: return "(";
				}
		}
		return "";
	}

	private String getTypeWrapCloseString(short wrappedType,short wrappingType){
		switch (wrappingType) {
			case SFParameteri.GLOBAL_GENERIC:
				return getTypeWrapCloseString(wrappedType,refParameter.getType());
			case SFParameteri.GLOBAL_FLOAT:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT2: return ").x";
					case SFParameteri.GLOBAL_FLOAT3: return ").x";
					case SFParameteri.GLOBAL_FLOAT4: return ").x";
					case SFParameteri.GLOBAL_MATRIX4: return ").x";
				}break;
			case SFParameteri.GLOBAL_FLOAT2:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT: return ")";
					case SFParameteri.GLOBAL_FLOAT3: return ").xy";
					case SFParameteri.GLOBAL_FLOAT4: return ").xy";
					case SFParameteri.GLOBAL_MATRIX4: return ").xy";
				}break;
			case SFParameteri.GLOBAL_FLOAT3:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT: return ")";
					case SFParameteri.GLOBAL_FLOAT2: return ",0)";
					case SFParameteri.GLOBAL_FLOAT4: return ").xyz";
					case SFParameteri.GLOBAL_MATRIX4: return ").xyz";
				}break;
			case SFParameteri.GLOBAL_FLOAT4:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT: return ")";
					case SFParameteri.GLOBAL_FLOAT2: return ",0,1)";
					case SFParameteri.GLOBAL_FLOAT3: return ",1)";
				}break;
			case SFParameteri.GLOBAL_MATRIX4:
				switch (wrappedType) {
					case SFParameteri.GLOBAL_FLOAT3: return ",1)";
					case SFParameteri.GLOBAL_FLOAT4: return ")";
				}
		}
		return "";
	}
	
	@Override
	public void closeElement(SFExpressionElement element) {
		
		if(element==parameters.getLast()){
			//System.out.println("ENTER!");
			String function=getAsFunction(parameters.getLast().getElement());
			if(function!=null)
				value+=")";
			if(!(parameters.getLast() instanceof SFExpressionVariable)){
				parameters.removeLast();
			}
			value+=getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
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
		
		//System.out.println("Start "+element.getElement());
		//System.out.println("starting element "+element.getElement()+" last "+parameters.getLast().getElement()+" "+element.getType()+" "+parameters.getLast().getType()+" ");
		if(element.getType()!=parameters.getLast().getType() && element.getType()>=0){
			//System.out.println(" Type?? "+element.getType());
			//wrap!!
			String function=getAsFunction(parameters.getLast().getElement());
			if(function==null){
				//System.out.println("Wrapper!! "+parameters.getLast().getElement());
				value+=getTypeWrapOpenString(element.getType(),parameters.getLast().getType());
				if(!(element instanceof SFExpressionVariable))
					parameters.add(element);
			}
		}
		if(element instanceof SFExpressionOperator){
			String function=getAsFunction(element.getElement());
			if(function!=null){
				value+=function+"(";
			}
			if(parameters.getLast()!=element)
				parameters.add(element);
		}
		if(element instanceof SFExpressionVariable){
			
			String data=element.getElement();
			
			String renameMapData=renameMap.get(data);
			
			//TODO
			if(renameMapData!=null){
				value+=renameMapData;
			}else{
				value+=data;
			}
		}
	}
	
	
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
		if(operatorSymbol.equalsIgnoreCase("�")){
			return new SFExpressionDot();
		}
		if(operatorSymbol.equalsIgnoreCase("#")){
			return new SFExpressionSqrt();
		}
		return new SFExpressionSum();
	}
	
	

	public static HashMap<String, String> getRenameMap() {
		return renameMap;
	}

	public static void setRenameMap(HashMap<String, String> renameMap) {
		SFGL20ExpressionGenerator.renameMap = renameMap;
	}

	@Override
	public SFExpressionElement getExpressionElement(String value, List<SFParameteri> set){
		return new SFExpressionVariable(value,set);
	}
	
	@Override
	public SFExpressionTypeWrapper getWrapper(short type) {
		return new SFExpressionTypeWrapper(type);
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