//Java to JS on 22/03/2012

function SFGL20ExpressionGenerator(){
	this.value="";
	this.parameters=new Array();
}

var SFGL20ExpressionGenerator_generator=undefined;
var SFGL20ExpressionGenerator_renameMap=new Array();
var SFGL20ExpressionGenerator_refParameter="";
var SFGL20ExpressionGenerator_functionsOperator=new Array();

{
	SFGL20ExpressionGenerator_functionsOperator["clamp"]="clampf";
	SFGL20ExpressionGenerator_functionsOperator["cos"]="cos";
	SFGL20ExpressionGenerator_functionsOperator["sin"]="sin";
	SFGL20ExpressionGenerator_functionsOperator["dot"]="dot";
	SFGL20ExpressionGenerator_functionsOperator["sqrt"]="sqrt";
	SFGL20ExpressionGenerator_functionsOperator["sample"]="texture2D";
	SFGL20ExpressionGenerator_functionsOperator["cross"]="cross";
	SFGL20ExpressionGenerator_functionsOperator["normalize"]="normalize";
}

function SFGL20ExpressionGenerator_getAsFunction(operator){
	return SFGL20ExpressionGenerator_functionsOperator[operator];
}

function SFGL20ExpressionGenerator_getRenameMap(){
	return SFGL20ExpressionGenerator_renameMap;
}

function SFGL20ExpressionGenerator_setRenameMap(renameMap){
	SFGL20ExpressionGenerator_renameMap=renameMap;
}
	
function SFGL20ExpressionGenerator_getRefParameter(){
	return SFGL20ExpressionGenerator_refParameter;
}
	
function SFGL20ExpressionGenerator_setRefParameter(refParameter){
	SFGL20ExpressionGenerator_refParameter=refParameter;
}

function SFGL20ExpressionGenerator_getGenerator(outputParameter){
	SFGL20ExpressionGenerator_generator.parameters.length=0;
	SFGL20ExpressionGenerator_generator.parameters.push(new SFExpressionTypeWrapper(outputParameter.getType()));
	SFGL20ExpressionGenerator_generator.value="";
	
	return SFGL20ExpressionGenerator_generator;
}
	
function SFGL20ExpressionGenerator_getFunctionString(){
	return SFGL20ExpressionGenerator_generator.value;
}	

	
SFGL20ExpressionGenerator.prototype["getTypeWrapOpenString"]=function(wrappedType,wrappingType){

			//No wrap needed!
			if(SFParameteri_getExpressionDimension(wrappedType)==SFParameteri_getExpressionDimension(wrappingType))
				return "";
			
			if(wrappingType==SFParameteri_GLOBAL_GENERIC){
				return this.getTypeWrapOpenString(wrappedType,SFGL20ExpressionGenerator_refParameter.getType());
			}
			
			if(wrappingType<=SFParameteri_GLOBAL_MATRIX4){
				if(wrappedType<wrappingType)
					return "vec"+SFParameteri_getExpressionDimension(wrappingType)+"(";
				else if(wrappedType!=SFParameteri_GLOBAL_GENERIC)
					return "(";
			}
			
			return "";
		};
	

SFGL20ExpressionGenerator.prototype["getTypeWrapCloseString"]=function(wrappedType,wrappingType){

			if(typeof wrappedType == 'string')
				wrappedType=parseInt(wrappedType);
				
			if(typeof wrappingType == 'string')
				wrappingType=parseInt(wrappingType);
				
			if(SFParameteri_getExpressionDimension(wrappedType)==SFParameteri_getExpressionDimension(wrappingType))
				return "";
		
			if(wrappedType==SFParameteri_GLOBAL_FLOAT){
				return ")";
			}
			
			switch (wrappingType) {
				case SFParameteri_GLOBAL_GENERIC:
					if(SFGL20ExpressionGenerator_refParameter.getType()==SFParameteri_GLOBAL_GENERIC)
						throw "Bad Code";
					return this.getTypeWrapCloseString(wrappedType,refParameter.getType());
				case SFParameteri_GLOBAL_FLOAT:
					return ").x";
				case SFParameteri_GLOBAL_FLOAT2:
					if(wrappedType<SFParameteri_GLOBAL_MATRIX4){
						return ").xy";
					}break;
				case SFParameteri_GLOBAL_FLOAT3:
					if(wrappedType==SFParameteri_GLOBAL_FLOAT2)
						return ",0)";
					switch (wrappedType) {
						case SFParameteri_GLOBAL_FLOAT4: return ").xyz";
						case SFParameteri_GLOBAL_MATRIX4: return ").xyz";
					}break;
				case SFParameteri_GLOBAL_FLOAT4:
					switch (wrappedType) {
						case SFParameteri_GLOBAL_FLOAT2: return ",0,1)";
						case SFParameteri_GLOBAL_FLOAT3: return ",1)";
					}break;
				case SFParameteri_GLOBAL_MATRIX4:
					switch (wrappedType) {
						case SFParameteri_GLOBAL_FLOAT3: return ",1)";
					}
			}
			return "";
		};
	
	
SFGL20ExpressionGenerator.prototype["closeElement"]=function(element){
			
			if(element==getLast(this.parameters)){
		
				var func=SFGL20ExpressionGenerator_getAsFunction(getLast(this.parameters).getElement());
				if(func!=null){
					this.value+=")";
				}else{
					if(element.getElement().length==1 && !element.getElement()===","){
						this.value+=")";
					}
				}
				if(!(getLast(this.parameters) instanceof SFExpressionVariable)){
					this.parameters.length--;
				}
				if(func==null && !element.getElement()===","){
					this.value+=this.getTypeWrapCloseString(element.getType(),parameters.getLast().getType());
				}else{
					if(element.getType()!=getLast(this.parameters).getType()){
						func=SFGL20ExpressionGenerator_getAsFunction(getLast(this.parameters).getElement());
						if(func===undefined){
							this.value+=this.getTypeWrapCloseString(element.getType(),getLast(this.parameters).getType());
						}	
					}
				}
			}else{
				var func=SFGL20ExpressionGenerator_getAsFunction(getLast(this.parameters).getElement());
				if(func===undefined && element instanceof SFExpressionVariable){
					this.value+=this.getTypeWrapCloseString(element.getType(),getLast(this.parameters).getType());
				}
			}
		};
	
	
SFGL20ExpressionGenerator.prototype["refreshElement"]=function(element){
			var func=SFGL20ExpressionGenerator_getAsFunction(element.getElement());
			if(func==null){
				this.value+=element.getElement();
			}else{
				this.value+=",";
			}
		};
	
SFGL20ExpressionGenerator.prototype["startElement"]=function(element){
			if(element.getType()!=getLast(this.parameters).getType() && element.getType()>=0){
				var func=SFGL20ExpressionGenerator_getAsFunction(getLast(this.parameters).getElement());
				
				if(func===undefined){
					if(!(getLast(this.parameters).getElement()===',')){
						this.value+=this.getTypeWrapOpenString(element.getType(),getLast(this.parameters).getType());
						if(!(element instanceof SFExpressionVariable))
							this.parameters.push(element);
					}
				}
			}
			if(!(element.operatorSymbol===undefined)){
				var func=SFGL20ExpressionGenerator_getAsFunction(element.getElement());
				if(func!=undefined){
					this.value+=func+"(";
				}else if(!element.getElement()===","){
					if(element.getElement().equalsIgnoreCase("-"))
						if(element.getElementSize()==1)
							value+="-";
					this.value+="(";
				}
				if(getLast(this.parameters)!=element)
					this.parameters.push(element);
			}else if(element instanceof SFExpressionVariable){
				
				var data=element.getElement();
				
				var renameMapData=SFGL20ExpressionGenerator_renameMap[data];
				
				if(renameMapData!=null){
					this.value+=renameMapData;
				}else{
					this.value+=data;
				}
			}
			
			
		};

SFGL20ExpressionGenerator_generator=new SFGL20ExpressionGenerator();	

	