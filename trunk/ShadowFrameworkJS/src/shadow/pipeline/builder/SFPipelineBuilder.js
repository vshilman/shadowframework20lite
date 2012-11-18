

var SFPipelineBuilder_typeMap=new Array();
SFPipelineBuilder_typeMap["PrimitiveComponent"]=new SFParsableProgramComponent();
SFPipelineBuilder_typeMap["TransformsComponent"]=new SFParsableProgramComponent();
SFPipelineBuilder_typeMap["MaterialComponent"]=new SFParsableProgramComponent();
SFPipelineBuilder_typeMap["LightStepComponent"]=new SFParsableProgramComponent();

SFPipelineBuilder_typeMap["Structure"]=new SFBuilderStructure();
SFPipelineBuilder_typeMap["Primitive"]=new SFParsablePrimitive();
SFPipelineBuilder_typeMap["Transform"]=new SFParsableProgramModule();
SFPipelineBuilder_typeMap["Material"]=new SFParsableProgramModule();
SFPipelineBuilder_typeMap["LightStep"]=new SFParsableProgramModule();


var SFPipelineBuilder_types=new Array();
SFPipelineBuilder_types["<>"]=SFParameteri_GLOBAL_GENERIC;
SFPipelineBuilder_types["float"]=SFParameteri_GLOBAL_FLOAT;
SFPipelineBuilder_types["float3"]=SFParameteri_GLOBAL_FLOAT2;
SFPipelineBuilder_types["float4"]=SFParameteri_GLOBAL_FLOAT3;
SFPipelineBuilder_types["float5"]=SFParameteri_GLOBAL_FLOAT4;
		

function SFPipelineBuilder_getElement(type) {
	return SFPipelineBuilder_typeMap[type].newInstance();
}

function SFPipelineBuilder(){
	//do nothing
}

SFPipelineBuilder.prototype["generateElement"]=function(type,name){
			var temp=SFPipelineBuilder_getElement(type);
			temp.setName(name);
			this.component=temp;
		};
		
SFPipelineBuilder.prototype["buildDefineRule"]=function(pWrote,type,func){
			var param=new SFParameter(pWrote,type);
			var cmp=this.getComponent();
			cmp.addParameter(param);
			
			var builder=new SFExpressionBuilder();
			SFExpressionParser_getParser().parseString(func, cmp.getParameterArray(),builder);
			
			var functionCode = new SFFunction(param,builder.getBuiltExpression());
			cmp.addCodeFunction(functionCode);
		};	
		
SFPipelineBuilder.prototype["getComponent"]=function(){
			return this.component;
		};	
	
SFPipelineBuilder.prototype["setComponent"]=function(component){
			this.component=component;
		};	
	

SFPipelineBuilder.prototype["setUseRule"]=function(use){
			var global=SFPipelineRegister_getFromName(use);
			var cmp=this.getComponent();
			cmp.addRegister(global);
		};

	
//SFPipelineBuilder.prototype["addGridVertex"]=function(token){
//			this.getComponent().loadVertex(token);
//		};
		
		
SFPipelineBuilder.prototype["buildWriteRule"]=function(wrote,func){
				
//	var cmp = this.getComponent();
//	//SFBuilderGrid grid=(SFBuilderGrid)getComponent();
//	var parameter=new SFParameter(wrote,SFParameteri_GLOBAL_GENERIC);
//	
//	for(var j=0;j< cmp.getGrid().length;j++){
//		var gridInstance=cmp.getGrid()[j];
//		var parameters=gridInstance.getParameters();
//		for (var i = 0; i < parameters.length; i++) {
//			if(parameters[i].getName()===(parameter.getName())){
//				var builder=new SFExpressionBuilder();
//				SFExpressionParser_getParser().parseString(func, parameters,builder);
//				var functionCode = new SFFunction(parameter,builder.getBuiltExpression());
//				gridInstance.addFunction(functionCode,parameter);
//			}
//		}
//	}
	
	
	var global=SFPipelineRegister_getFromName(wrote);
	var cmp = this.getComponent();
	cmp.addRegister(global);
	
	var builder=new SFExpressionBuilder();
	SFExpressionParser_getParser().parseString(func, cmp.getParameterArray(),builder);
	var functionCode = new SFFunction(global,builder.getBuiltExpression());
	cmp.addCodeFunction(functionCode);
	
	
};


SFPipelineBuilder.prototype["buildRewriteRule"]=function(wrote,func){
			
			var cmp = this.getComponent();
			//SFBuilderGrid grid=(SFBuilderGrid)getComponent();
			var parameter=new SFParameter(wrote,SFParameteri_GLOBAL_GENERIC);
			
			for (var gridInstanceIndex in cmp.getGrid()) {
				var gridInstance=cmp.getGrid()[gridInstanceIndex];
				var parameters=gridInstance.getParameters();
				for (var i = 0; i < parameters.length; i++) {
					if(parameters[i].getName()===parameter.getName()){
						var builder=new SFExpressionBuilder();
						SFExpressionParser_getParser().parseString(func, parameters,builder);
						var functionCode = new SFFunction(parameter,builder.getBuiltExpression());
						gridInstance.addFunction(functionCode,parameter);
					}
				}
			}
			
		};

	
SFPipelineBuilder.prototype["buildParamRuleString"]=function(parameter,use){
			var param=new SFParameter(use, parameter);
			var cmp=this.getComponent();
			cmp.addParameter(param);
		};
	
	
//SFPipelineBuilder.prototype["buildPath"]=function(paths){
//			var grid=this.getComponent();
//			grid.loadPath(paths);
//		};
	

SFPipelineBuilder.prototype["buildParamRule"]=function(moduleString,pars){
			var params_ = new Array();//[pars.length];

			var module = SFPipeline_getModule(moduleString);
			var cmp = this.getComponent();
			
			var sfPs = module;
			var parameters = sfPs.getAllParameters();
			
			for(var i=0;i<parameters.length;i++){
				params_.push(new SFParameter(pars[i],parameters[i].getType()));
			}
			
			var instance = new SFPipelineStructureInstance(module, params_);
			cmp.addStructureInstance(instance);
		};


//SFPipelineBuilder.prototype["buildGridInternals"]=function(internals){
//			this.getComponent().loadInternal(internals);
//		};


SFPipelineBuilder.prototype["buildComponent"]=function(componentName){
	var module=this.getComponent();
	module.addComponent(SFPipeline_getModule(componentName));
};
		

SFPipelineBuilder.prototype["buildBlock"]=function(block,primitiveComponent){
	var module=this.getComponent();
	var prBlock=SFPrimitiveBlock_valueOf(block);
	module.addComponent(prBlock,SFPipeline_getModule(primitiveComponent));
};


SFPipelineBuilder.prototype["buildDomain"]=function(domain){
	var module=this.getComponent();
	module.setGridModel(SFGridModel_valueOf(domain));
};	
	

var SFPipelineBuilder_types=new Array();
SFPipelineBuilder_types["<>"]=SFParameteri_GLOBAL_GENERIC;
SFPipelineBuilder_types["float"]=SFParameteri_GLOBAL_FLOAT;
SFPipelineBuilder_types["float2"]=SFParameteri_GLOBAL_FLOAT2;
SFPipelineBuilder_types["float3"]=SFParameteri_GLOBAL_FLOAT3;
SFPipelineBuilder_types["float4"]=SFParameteri_GLOBAL_FLOAT4;

SFPipelineBuilder.prototype["buildGrid"]=function(pars,model,type,n){
	var params_ = new Array();
	
	var cmp =this.getComponent();

	for(var i=0;i<pars.length;i++){
		params_.push(new SFParameter(pars[i],SFPipelineBuilder_types[type]));
	}
	
	var gridModel=SFGridModel_valueOf(model);
	
	var instance = new SFPipelineGrid(parseInt(n)+1, gridModel, params_);
	cmp.addGridInstance(instance);
};	
		
	
SFPipelineBuilder.prototype["closeElement"]=function(){
			if(!(this.getComponent()==undefined))
				this.getComponent().finalize();
			
			this.setComponent( undefined );
		};	
	
//SFPipelineBuilder.prototype["buildEdge"]=function(edges){
//			var grid=this.getComponent();	
//			grid.loadEdge(edges);
//		};		

	