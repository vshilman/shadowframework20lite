


function SFGL20UniformData(){
	this.program=0;
	this.mainUniforms=0;
	this.structureUniforms=new Array();
	this.gridUniforms=new Array();
	this.mainUniforms=new Array();
}

SFGL20UniformData.prototype["getUniforms"]=function(prefix,instance,textures){
		var data=new Array();//instance.size()
		var parameters=instance.getParameters();
		for (var i = 0; i < parameters.length; i++) {
			var param=parameters[i];
			var name=prefix+param.getName();
			data[i]=SFGL2_getGL().getUniformLocation(this.program.getProgram(),name);
			if(param.getType()==SFParameteri_GLOBAL_TEXTURE){
				textures.push(data[i]);
			}
		}
	
		return data;
	};

SFGL20UniformData.prototype["getUniformsPrimitive"]=function(prefix,instance){
		var data=new Array();//instance.size()
		var parameters=instance.getParameters();
		for (var i = 0; i < instance.size(); i++) {
			var param=parameters[i];
			var name=prefix+param.getName();
			data[i]=SFGL2_getGL().getUniformLocation(this.program.getProgram(),name);
		
		}
		return data;
	};
	

SFGL20UniformData.prototype["evaluateUniforms"]=function(program){
	
		this.program=program;
		
		this.gridUniforms.length=0;
		
		var primitive=program.getPrimitive();
		
		if(!(primitive===undefined)){

			for (var i = 0; i < primitive.getBlocks().length; i++) {
				var component=primitive.getComponents()[i];
				var register=primitive.getBlocks()[i].getRegister();
				
				var grids=component.getGrid();
				
				for (var sfPipelineGridInstanceIndex in grids) {
					var sfPipelineGridInstance=grids[sfPipelineGridInstanceIndex];
					this.gridUniforms.push(this.getUniformsPrimitive(register.getName(), sfPipelineGridInstance));
				}
			}
		}

		var textures=new Array();
		this.transformStructureUniforms=this.evaluateStructureUniforms(this.program.getTransforms(),textures);
		this.transformTextures=textures;
		textures=new Array();
		this.materialStructureUniforms=this.evaluateStructureUniforms(this.program.getMaterials(),textures);
		this.materialTextures=textures;
		textures=new Array();
		this.lightStructureUniforms=this.evaluateStructureUniforms(this.program.getLight(),textures);
		this.lightTextures=textures;
		
		
		this.mainUniforms=new Array();
		this.mainUniforms[0]=SFGL2_getGL().getUniformLocation(this.program.getProgram(),"projection");
		this.mainUniforms[1]=SFGL2_getGL().getUniformLocation(this.program.getProgram(),"modelview");
		this.mainUniforms[2]=SFGL2_getGL().getUniformLocation(this.program.getProgram(),"vectorsModelview");
		
		this.program.parametersAttrib=SFGL2_getGL().getAttribLocation(this.program.getProgram(),"tessellationParameters");

	};
	
	

SFGL20UniformData.prototype["evaluateStructureUniforms"]=function(module,textures){
		if(module!=null){
			
			var allStructures=new Array();
			
			for (var i = 0; i < module.getComponents().length; i++) {
				var component=module.getComponents()[i];
				for(var j=0;j<component.getStructures().length;j++){
					allStructures.push(component.getStructures()[j]);
				}
			}
			
			var uniforms=new Array();
	
			for (var i = 0; i < allStructures.length; i++) {
				var sfPipelineStructureInstance=allStructures[i];
				uniforms[i]=this.getUniforms("", sfPipelineStructureInstance,textures);
			}
			
			
			return uniforms;
			
		}
		
		return new Array();
	};

	

SFGL20UniformData.prototype["evaluateUniformsImageProgram"]=function(program){
		this.program=program;
		//Primitive Uniforms
		
		gridUniforms=[];
		var textures=new Array();
		this.materialStructureUniforms=this.evaluateStructureUniforms(program.getMaterials(),textures);
		this.materialTextures=textures;
		textures=new Array();
		this.lightStructureUniforms=evaluateStructureUniforms(program.getLight(),textures);
		this.lightTextures=textures;
	};

	
SFGL20UniformData.prototype["setTextureData"]=function(module,index,texture){
	
	var level=index;
	var textures=this.transformTextures;
	if(module===SFPipelineGraphics_Module_LIGHT){
		level+=this.transformTextures.length+this.materialTextures.length;
		textures=this.lightTextures;
	}else if(module==SFPipelineGraphics_Module_MATERIAL){
		level+=this.transformTextures.length;
		textures=this.materialTextures;
	}
	
	texture.apply(level);
	
	//with its related uniform..
	SFGL2_getGL().uniform1i(textures[index], level);
};

SFGL20UniformData.prototype["setData"]=function(module,index,data){
	
	switch (module) {
		case SFPipelineGraphics_Module_LIGHT:
				this.setDataInt(this.lightStructureUniforms[index], data);
			break;
		case SFPipelineGraphics_Module_MATERIAL:
				this.setDataInt(this.materialStructureUniforms[index], data);
			break;
		case SFPipelineGraphics_Module_TRANSFORM:
				this.setDataInt(this.transformStructureUniforms[index], data);
			break;
	}
};
	

SFGL20UniformData.prototype["setDataInt"]=function(uniforms,data){

	if(uniforms!=null){
		for (var i=0; i < uniforms.length; i++) {
			var v=data.getValue(i);
			if(v.getSize()==3){
				SFGL2_getGL().uniform3f(uniforms[i],v.get()[0],v.get()[1],v.get()[2]);
			}else if(v.getSize()==1){
				SFGL2_getGL().uniform1f(uniforms[i],v.get()[0]);
			}
		}
	}
};


SFGL20UniformData.prototype["sendVertex"]=function(value){
	var uniformIndex=0;
	var uniform=gridUniforms.get(uniformIndex);
	switch (value.getSize()) {
		case 1:
			SFGL2_getGL().uniform1f(uniform[0],value.get()[0]);
		break;
		case 2:
			SFGL2_getGL().uniform2f(uniform[0],value.get()[0],value.get()[1]);
			break;
		case 3:
			SFGL2_getGL().uniform3f(uniform[0],value.get()[0],value.get()[1],value.get()[2]);
			break;
		default:
			break;
	}
};
	

	
SFGL20UniformData.prototype["setIndexedData"]=function(indices,datas,primitive){
		
		var uniformIndex=0;

		var idx=indices.getPrimitiveIndices();
		var positions=primitive.getIndicesPositions();
		var sizes=primitive.getIndicesSizes();
		
		for (var i = 0; i < datas.length; i++) {
			
			var position=positions[i];
			var size=sizes[i];
			var data=datas[i];
		
			var uniform=this.gridUniforms[uniformIndex];
			var type=primitive.getType(i);
			
			switch (type) {
				case SFParameteri_GLOBAL_FLOAT:
					for (var j=0; j < size; j++) {
						var pv=(data.getValue(idx[j+position]));
						SFGL2_getGL().uniform1f(uniform[j],pv.get()[0]);
					}
				break;
				case SFParameteri_GLOBAL_FLOAT3:
						for (var j=0; j < size; j++) {
							var pv=(data.getValue(idx[j+position]));
							//alert("This is the data j:"+j+" i:"+i+" "+pv.get()[0]+" "+pv.get()[1]+" "+pv.get()[2]);
							SFGL2_getGL().uniform3f(uniform[j],pv.get()[0],pv.get()[1],pv.get()[2]);
						}
					break;
				case SFParameteri_GLOBAL_FLOAT2:
					for (var j=0; j < size; j++) {
						var pv=(data.getValue(idx[j+position]));
						SFGL2_getGL().uniform2f(uniform[j],pv.get()[0],pv.get()[1]);
					}
				break;
				default:
					break;
			}
			uniformIndex++;
		}
		
	};

	
SFGL20UniformData.prototype["setTransformData"]=function(transform){
	
		var v1=new SFVertex3f(transform[0],transform[1],transform[2]);
		var v2=new SFVertex3f(transform[3],transform[4],transform[5]);
		var v3=new SFVertex3f(transform[6],transform[7],transform[8]);

		var vModelview=[//you know this is not going to work properly, but let's say it's right most of the times..
				v1.getX(),	v2.getX(),	v3.getX(),0,
				v1.getY(),	v2.getY(),	v3.getY(),0,
				v1.getZ(),	v2.getZ(),	v3.getZ(),0,
				0,0,0,1
		];
		var modelview=[//you know this is not going to work properly, but let's say it's right most of the times..
				transform[0],	transform[3],	transform[6],0,
				transform[1],	transform[4],	transform[7],0,
				transform[2],	transform[5],	transform[8],0,
				transform[9],	transform[10],	transform[11],1
		];

		//alert("setTransformData:this.mainUniforms[1] "+this.mainUniforms[1]);
		//alert("setTransformData:this.mainUniforms[2] "+this.mainUniforms[2]);
		//alert("setTransformData:vModelview "+vModelview);
		//alert("setTransformData:modelview "+modelview);
		//All transforms are identity no more..
		SFGL2_getGL().uniformMatrix4fv(this.mainUniforms[1],false,new Float32Array(modelview));
		SFGL2_getGL().uniformMatrix4fv(this.mainUniforms[2],false,new Float32Array(vModelview));
	};

SFGL20UniformData.prototype["setupProjetion"]=function(projection){

		//alert("setTransformData:this.mainUniforms[0] "+this.mainUniforms[0]);
		//alert("setTransformData:projection "+projection);
		SFGL2_getGL().uniformMatrix4fv(this.mainUniforms[0],false,new Float32Array(projection));
	};
