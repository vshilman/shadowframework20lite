
function SFPipelineStructure(){
}

SFPipelineStructure.prototype = {

	getName:function(){
		return ,name;
	},

	setName:function(name){
		this.name    = name;
	},

	size:function(){
		  return  parameters.size (  );
	},

	getAllParameters:function(){
		return ,parameters;
	},

	addParameter:function(param){
		parameters.add(param);
	},

	addParameters:function(parameters){
		this.parameters.addAll(parameters);
	},

	floatSize:function(){
		 int  floatSize = 0;
		for ( int  i=0 ; i   < parameters.size() ; i++ ){
		 SFParameteri  parameter = parameters.get(i);
		switch (parameter.getType()){
	case SFParameteri.GLOBAL_FLOAT:					floatSize++;//Warning: Not well Identified 
		break;
	case SFParameteri.GLOBAL_FLOAT2:					floatSize+=2;//Warning: Not well Identified 
		break;
	case SFParameteri.GLOBAL_FLOAT3:					floatSize+=3;//Warning: Not well Identified 
		break;
	case SFParameteri.GLOBAL_FLOAT4:					floatSize+=4;//Warning: Not well Identified 
		break;
	case SFParameteri.GLOBAL_TEXTURE:					floatSize++;//Warning: Not well Identified 
		break;
	}
	}
		return ,floatSize;
	}

};