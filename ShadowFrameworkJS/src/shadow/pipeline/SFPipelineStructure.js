//Java to JS on 21/03/2012

function SFPipelineStructure(){
	this.parameters=new Array();
	this.name="";
}

inherit(SFPipelineStructure,SFPipelineElement);

SFPipelineStructure.prototype["getName"]=function(){
		return this.name;
	};

SFPipelineStructure.prototype["setName"]=function(name){
		this.name=name;
	};
	
SFPipelineStructure.prototype["size"]=function(){
		return this.parameters.length;
	};
	
SFPipelineStructure.prototype["getAllParameters"]=function(){
		return this.parameters;
	};
	
SFPipelineStructure.prototype["addParameter"]=function(param){
		this.parameters.push(param);
	};
	
SFPipelineStructure.prototype["addParameters"]=function(parameters){
		for(var i=0;i<parameters.length;i++){
			this.parameters.push(parameters[i]);
		}
	};

SFPipelineStructure.prototype["floatSize"]=function(){
		var  floatSize = 0;
		for(var i=0;i<this.parameters.length;i++){
			var parameter=this.parameters[i];
			
			switch(parameter.getType()){
				case SFParameteri_GLOBAL_FLOAT:
					floatSize++;
					break;
				case SFParameteri_GLOBAL_FLOAT2:
					floatSize+=2;
					break;
				case SFParameteri_GLOBAL_FLOAT3:
					floatSize+=3;
					break;
				case SFParameteri_GLOBAL_FLOAT4:
					floatSize+=4;
					break;
				case SFParameteri_GLOBAL_TEXTURE:
					floatSize+=4;
					break;		
			}
		}
		return floatSize;
	};
	