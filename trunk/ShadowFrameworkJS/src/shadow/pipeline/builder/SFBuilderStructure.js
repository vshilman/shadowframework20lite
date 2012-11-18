
var SFBuilderStructure_loadingParameters=new Array();

var SFBuilderStructure_allCommands=[
	"begin",
	"float",
	"float2",
	"float3",
	"float4",
	"matrix2",
	"matrix3",
	"matrix4",
	"transform2",
	"transform3",
	"texture",
	"end"
];


function SFBuilderStructure(){
	this.parameters=new Array();
}

inherit(SFBuilderStructure,SFPipelineStructure);

SFBuilderStructure.prototype["clone"]=function(){
	return new SFBuilderStructure();
};

SFBuilderStructure.prototype["finalize"]=function(){
			this.addParameters(SFBuilderStructure_loadingParameters);
			SFBuilderStructure_loadingParameters.length=0;
			SFPipeline_loadStructure(this.getName(), this);
		};

SFBuilderStructure.prototype["addParameter"]=function(parameter){
			SFBuilderStructure_loadingParameters.push(parameter);
		};
		
SFBuilderStructure.prototype["getAllCommands"]=function(){
			return SFBuilderStructure_allCommands;
		};
		
SFBuilderStructure.prototype["newInstance"]=function(){
		return new SFBuilderStructure();
	};