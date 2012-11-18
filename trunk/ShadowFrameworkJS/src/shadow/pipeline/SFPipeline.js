//Java to JS on 21/03/2012

var SFPipelineGraphics_Module_TRANSFORM=0;
var SFPipelineGraphics_Module_MATERIAL=1;
var SFPipelineGraphics_Module_LIGHT=2;


function SFPipeline(){
	this.components=new Array();
	this.structures=new Array();
	this.modules=new Array();
	this.primitives=new Array();
	this.traces=new Array();
}

var SFPipeline_pipeline=new SFPipeline();

function SFPipeline_getPipeline(){
			return SFPipeline_pipeline;
		};

function SFPipeline_setSfTexturePipeline(sfTexturePipeline){
			SFPipeline_pipeline.sfTexturePipeline  = sfTexturePipeline;
		};

function SFPipeline_getSfProgramBuilder(){
			return SFPipeline_pipeline.sfProgramBuilder;
		};

function SFPipeline_setSfProgramBuilder(sfProgramBuilder){
			SFPipeline_pipeline.sfProgramBuilder  = sfProgramBuilder;
		};

function SFPipeline_getSfTexturePipeline(){
			return SFPipeline_pipeline.sfTexturePipeline;
		};
	
function SFPipeline_getSfPipelineMemory(){
			return SFPipeline_pipeline.sfPipelineMemory;
		};	

function SFPipeline_setSfPipelineMemory(sfPipelineMemory){
			SFPipeline_pipeline.sfPipelineMemory=sfPipelineMemory;
		};	

function SFPipeline_getSfPipelineGraphics(){
			return SFPipeline_pipeline.sfPipelineGraphics;
		};		

function SFPipeline_setSfPipelineGraphics(sfPipelineGraphics){
			SFPipeline_pipeline.sfPipelineGraphics  = sfPipelineGraphics;
		};		

function SFPipeline_loadPrimitive(code,component){
			SFPipeline_pipeline.primitives[code]  = component;
		};		

function SFPipeline_loadShaderModule(code,component){
			SFPipeline_pipeline.modules[code]  = component;
		};		

function SFPipeline_loadShaderComponent(code,component){
			SFPipeline_pipeline.components[code]=component;
		};	

function SFPipeline_getModule(structureCode){
			var structure=SFPipeline_pipeline.structures[structureCode];
			if (structure != null) {
				return structure;
			}
			return SFPipeline_pipeline.components[structureCode];
	};		

function SFPipeline_getProgramModule(name){
			var module=SFPipeline_pipeline.modules[name];
			if(module===undefined){
				var component=SFPipeline_getModule(name);
				if(component!=null){
					module=new SFProgramModule();
					var components=[component];
					module.setComponents(components);
				};
			}
			return module;
		};		

function SFPipeline_getPrimitive(name){
			return SFPipeline_pipeline.primitives[name];
		};	
	
function SFPipeline_getStructure(structureCode){
			return SFPipeline_pipeline.structures[structureCode];
		};	

function SFPipeline_loadStructure(code,component){
			SFPipeline_pipeline.structures[code]=component;
		};	

function SFPipeline_traceIndexOf(traces,trace){
	for(tr in traces){
		if(tr===trace){
			tr;
		}
	}
	return -1;
}
		
function SFPipeline_getStaticProgram(primitive, transform, material,light){

			var trace=SFPipeline_generateTrace(primitive.getName(), transform, material, light);
			var program=SFPipeline_pipeline.traces[trace];
			
			if(!(program===undefined)){
				return program;	
			}else{

				var program=SFPipeline_pipeline.sfProgramBuilder.generateNewProgram();
				
				program.setPrimitive(primitive);
				
				var transformP=SFPipeline_getProgramModule(transform);
				var materialP=SFPipeline_getProgramModule(material);
				var lightP=SFPipeline_getProgramModule(light);
				
				program.setTransform(transformP);
				
				program.setMaterial(materialP);

				program.setLightStep(lightP);
				
				SFPipeline_pipeline.traces[trace]=program;
				
				return program;
			}
		};	
		

function SFPipeline_getStaticImageProgram(material,light){

			var trace=SFPipeline_generateTrace(material, light);
			var program=SFPipeline_pipeline.traces[trace];
			
			if(!(program===undefined)){
				return program;	
			}else{
				var program=SFPipeline_pipeline.sfProgramBuilder.generateImageProgram();
		
				var materialP=SFPipeline_getProgramModule(material);
				var lightP=SFPipeline_getProgramModule(light);
				program.setMaterial(materialP);
				program.setLightStep(lightP);
				
				SFPipeline_pipeline.traces[trace]=program;
				
				return program;
			}
		};	
		

		
function SFPipeline_generateTrace(primitive, transform, material,light){
	var trace=primitive+transform+material+light;
	return trace;
};	
		

function SFPipeline_generateTrace(material, light){
	var trace=material+light;
	return trace;
};	
		