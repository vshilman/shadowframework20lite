
function SFGL20ExpressionGenerator(){
}

SFGL20ExpressionGenerator.prototype = {

	getAsFunction:function(operator){
		return ,functionsOperator.get(operator);
	},

	getTypeWrapOpenString:function(wrappedType, wrappingType){
		switch (wrappingType){
	case SFParameteri.GLOBAL_GENERIC:				return getTypeWrapOpenString(wrappedType,refParameter.getType());//Warning: Not well Identified 
	//case SFParameteri.GLOBAL_FLOAT:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT2: return "(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return "(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return "(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_MATRIX4: return "(";//Warning: Not well Identified 
	//}
	//case SFParameteri.GLOBAL_FLOAT2:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT: return "vec2(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return "(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return "(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_MATRIX4: return "(";//Warning: Not well Identified 
	//}
	//case SFParameteri.GLOBAL_FLOAT3:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT: return "vec3(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT2: return "vec3(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return "(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_MATRIX4: return "(";//Warning: Not well Identified 
	//}
	//case SFParameteri.GLOBAL_FLOAT4:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT: return "vec4(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT2: return "vec4(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return "vec4(";//Warning: Not well Identified 
	//}
	//case SFParameteri.GLOBAL_MATRIX4:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return "vec4(";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return "(";//Warning: Not well Identified 
	//}
	}
		return ,"";
	},

	getTypeWrapCloseString:function(wrappedType, wrappingType){
		switch (wrappingType){
	case SFParameteri.GLOBAL_GENERIC:				return getTypeWrapCloseString(wrappedType,refParameter.getType());//Warning: Not well Identified 
	//case SFParameteri.GLOBAL_FLOAT:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT2: return ").x";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return ").x";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return ").x";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_MATRIX4: return ").x";//Warning: Not well Identified 
	//}
		break;
	//case SFParameteri.GLOBAL_FLOAT2:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT: return ")";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return ").xy";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return ").xy";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_MATRIX4: return ").xy";//Warning: Not well Identified 
	//}
		break;
	//case SFParameteri.GLOBAL_FLOAT3:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT: return ")";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT2: return ",0)";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return ").xyz";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_MATRIX4: return ").xyz";//Warning: Not well Identified 
	//}
		break;
	//case SFParameteri.GLOBAL_FLOAT4:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT: return ")";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT2: return ",0,1)";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return ",1)";//Warning: Not well Identified 
	//}
		break;
	//case SFParameteri.GLOBAL_MATRIX4:				switch (wrappedType);//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT3: return ",1)";//Warning: Not well Identified 
	case SFParameteri.GLOBAL_FLOAT4: return ")";//Warning: Not well Identified 
	//}
	}
		return ,"";
	},

	closeElement:function(element){
	//if(element==parameters.getLast());//Warning: Not well Identified 
	//System.out.println("ENTER!");//Warning: Not well Identified 
		 String  function = getAsFunction(parameters.getLast().getElement());
	if(function!=null)				value+=")";//Warning: Not well Identified 
	//if(!(parameters.getLast() instanceof SFExpressionVariable));//Warning: Not well Identified 
		parameters.removeLast();
	//}
		value + = getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
	//}
		else{
		 String  function = getAsFunction(parameters.getLast().getElement());
	//if(function==null && element instanceof SFExpressionVariable);//Warning: Not well Identified 
		value + = getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
	//}
	}
	},

	refreshElement:function(element){
		 String  function = getAsFunction(element.getElement());
		 if ( function==null ){
		value + = element.getElement();
	}
		else{
	value+=",";//Warning: Not well Identified 
	}
	},

	startElement:function(element){
	//System.out.println("Start "+element.getElement());//Warning: Not well Identified 
	//System.out.println("starting element "+element.getElement()+" last "+parameters.getLast().getElement()+" "+element.getType()+" "+parameters.getLast().getType()+" ");//Warning: Not well Identified 
	//if(element.getType()!=parameters.getLast().getType() && element.getType()>=0);//Warning: Not well Identified 
	//System.out.println(" Type?? "+element.getType());//Warning: Not well Identified 
	//wrap!!			String function=getAsFunction(parameters.getLast().getElement());//Warning: Not well Identified 
		 if ( function==null ){
	//System.out.println("Wrapper!! "+parameters.getLast().getElement());//Warning: Not well Identified 
		value + = getTypeWrapOpenString(element.getType(),parameters.getLast().getType());
	if(!(element instanceof SFExpressionVariable))					parameters.add(element);//Warning: Not well Identified 
	}
	//}
		if(element instanceof SFExpressionOperator){
		 String  function = getAsFunction(element.getElement());
		 if ( function!=null ){
	value+=function+"(";//Warning: Not well Identified 
	}
	if(parameters.getLast()!=element)				parameters.add(element);//Warning: Not well Identified 
	}
		if(element instanceof SFExpressionVariable){
		 String  data = element.getElement();
		 String  renameMapData = renameMap.get(data);
	////TODO			if(renameMapData!=null);//Warning: Not well Identified 
		value + = renameMapData;
	//}
		else{
		value + = data;
	}
	}
	},

	getOperator:function(operatorSymbol){
		if(operatorSymbol.equalsIgnoreCase("+")){
		return ,new ,SFExpressionSum();
	}
		if(operatorSymbol.equalsIgnoreCase("*")){
		return ,new ,SFExpressionMult();
	}
		if(operatorSymbol.equalsIgnoreCase("/")){
		return ,new ,SFExpressionDivide();
	}
		if(operatorSymbol.equalsIgnoreCase("-")){
		return ,new ,SFExpressionMinus();
	}
	//if(operatorSymbol.equalsIgnoreCase(":"));//Warning: Not well Identified 
		return ,new ,SFExpressionClamp();
	//}
	//if(operatorSymbol.equalsIgnoreCase("°"));//Warning: Not well Identified 
		return ,new ,SFExpressionDot();
	//}
		if(operatorSymbol.equalsIgnoreCase("#")){
		return ,new ,SFExpressionSqrt();
	}
	//if(operatorSymbol.equalsIgnoreCase("%"));//Warning: Not well Identified 
		return ,new ,SFExpressionTextureEvaluation();
	//}
		if(operatorSymbol.equalsIgnoreCase(",")){
		return ,new ,SFExpressionVector();
	}
		return ,new ,SFExpressionSum();
	},

	getExpressionElement:function(value, set){
		return ,new ,SFExpressionVariable(value,set);
	},

	getWrapper:function(type){
		return ,new ,SFExpressionTypeWrapper(type);
	},

	getRefParameter:function(){
		return ,refParameter;
	},

	setRefParameter:function(refParameter){
		SFGL20ExpressionGenerator.refParameter  = refParameter;
	},

	getGenerator:function(outputParameter){
		generator.parameters.clear();
		generator.parameters.add(new SFExpressionTypeWrapper(outputParameter.getType()));
		generator.value  = "";
		return ,generator;
	},

	getFunctionString:function(){
		return ,generator.value;
	}

};