

function SFGL20AbstractProgram(){
	this.frShader=0;
	this.vxShader=0;
	this.program=0;
	this.initialized=false;
	this.fragmentShader=new Array();
	this.light=new SFProgramComponent();
	this.registeredUniforms=false;
	this.data=new SFGL20UniformData();
}

inherit(SFGL20AbstractProgram,SFGLSLSet);


SFGL20AbstractProgram.prototype["clearFragmentShader"]=function(token){
			this.fragmentShader.length=0;
		};
		
//SFGL20AbstractProgram.prototype["addToFragmentShader"]=function(component,register){
//			this.fragmentShader.push(new SFPrimitiveProgramAssociation(register,component));
//		};

SFGL20AbstractProgram.prototype["addToFragmentShader"]=function(module){
		for (var i = 0; i < module.getComponents().length; i++) {
			this.fragmentShader.push(new SFPrimitiveProgramAssociation(null,module.getComponents()[i]));
		}
	};

SFGL20AbstractProgram.prototype["loadFragmentShaderText"]=function(component,register){
			return this.getShaderText(this.fragmentShader,false);
		};

SFGL20AbstractProgram.prototype["write"]=function(){
			for (var i=0; i < fragmentShader.length; i++) {
				System.err.println("\t\tfragmentShader(i) " + this.fragmentShader.get(i));
			}
		};

SFGL20AbstractProgram.prototype["getShaderText"]=function(list,useTessellationParameters){
			return SFGL20AbstractProgram_getShaderText(this,list,useTessellationParameters);
		};

SFGL20AbstractProgram.prototype["setLightStep"]=function(lightStep){
			if(lightStep===undefined)
				throw "Cannot Assign a Null LightStep";
			this.addToFragmentShader(lightStep);
			this.light=lightStep;
		};


SFGL20AbstractProgram.prototype["setMaterial"]=function(material){
			this.addToFragmentShader(material);
			this.materials=(material);
		};

SFGL20AbstractProgram.prototype["toString"]=function(){
			var vShader=this.loadVertexShaderText();
			var fShader=this.loadFragmentShaderText();
			var toString="---------\nvShader\n---------:\n" + vShader
					+ "---------\nfShader\n---------:\n" + fShader;
			return toString;
		};

function SFGL20AbstractProgram_getRegisterName(list,i){
		var register=list[i].getRegister();
		var suffix="";
		if (register != null)
			suffix=register.getName();
		return suffix;
}

	
function SFGL20AbstractProgram_getRegister(list,i){
		var register=list[i].getRegister();
		return register;
}

SFGL20AbstractProgram.prototype["load"]=function(){

			this.init();
			this.apply();

			if (!this.registeredUniforms) {
				this.getData().evaluateUniforms(this);
				this.registeredUniforms=true;
			}
		};

SFGL20AbstractProgram.prototype["setDataValues"]=function(module,index,texture){
			this.data.setData(module,index,texture);
		};

SFGL20AbstractProgram.prototype["setTextureData"]=function(module,index,texture){
			this.data.setTextureData(module,index,texture);
		};

SFGL20AbstractProgram.prototype["setIndexedData"]=function(indices,datas,primitive){
			this.getData().setIndexedData(indices,datas,primitive);
		};

SFGL20AbstractProgram.prototype["sendVertex"]=function(value){
			this.getData().sendVertex(value);
		};
		
SFGL20AbstractProgram.prototype["setTransformData"]=function(transform){
			this.getData().setTransformData(transform);
		};

SFGL20AbstractProgram.prototype["setupProjection"]=function(projection){
			this.getData().setupProjetion(projection);
		};

		
function SFGL20AbstractProgram_generateShaderParameters(shaderParametersSet){
		return SFGlobalVSetGL20Implementor_generateShaderParameters(shaderParametersSet);
}
	
			
function SFGL20AbstractProgram_translateExpression(expression,parameter,
					parameterExpression,functionParameters){

		SFGL20ExpressionGenerator_setRefParameter(parameter);

		var renameMap=new Array();

		if (parameterExpression instanceof SFPipelineRegister
				&& parameterExpression.getUse() == SFPipelineRegister_WROTE_BY_PRIMITIVE) {

			for(var i=0;i<functionParameters.length;i++){
				var sfParameteri=functionParameters[i];
				if(!SFGL20GlobalV_requiresDeclaration(sfParameteri)){
					renameMap[sfParameteri.getName()]=parameterExpression.getName()
							+ sfParameteri.getName();
				}
			}
		}
		
		SFGL20ExpressionGenerator_setRenameMap(renameMap);

		try {
			expression.traverse(SFGL20ExpressionGenerator_getGenerator(parameter));
		} catch (e) {
			throw e;
		}
		
		var expr=SFGL20ExpressionGenerator_getFunctionString();
		
		return expr;
}




	
function SFGL20AbstractProgram_getShaderText(program,list,vertex){
		
		var writer="";
		
		writer+=("\tprecision mediump float;\n");
		
		var alreadySetRegisters=new Array();
		var alreadyDeclaredElements=new Array();
		var alreadyMappedParameters=new Array();
		for (var i=0; i < list.length; i++) {

			var programComponent=list[i].getProgram();
			
			var register=programComponent.getRegisters();

			var regSet=new Array();
			for(var j=0;j<register.length;j++){
				var reg=register[j];
				if(!contains(alreadySetRegisters,reg)){
					regSet.push(reg);	
					alreadySetRegisters.push(reg);
				}
			}

			writer+=(SFGL20AbstractProgram_generateShaderParameters(regSet));

			var global=SFGL20AbstractProgram_getRegister(list,i);
			var globalName="";
			if (global != null)
				globalName=global.getName();
			var instances=programComponent.getStructures();
			
			for (var index in instances) {
				writer+=(SFGlobalVSetGL20Implementor_generateInstancedStructures(instances[index],
								SFGL20AbstractProgram_getRegister(list,i),globalName,alreadyMappedParameters));
			}

			var gridInstance=programComponent.getGrid();
			
			for (var gridIndex in gridInstance) {
				var sfPipelineGridInstance=gridInstance[gridIndex];
				var type=sfPipelineGridInstance.getParameterType(0);
				if(type==SFParameteri_GLOBAL_GENERIC)
					type=SFGL20AbstractProgram_getRegister(list, i).getType();
				writer+=(SFGlobalVSetGL20Implementor_generateInstancedGrids(sfPipelineGridInstance,
								type,globalName));
			}

			// SFPipelineStructureInstance[]
			// instances=list.get(i).getStructures();
			// for (int j = 0; j < instances.length; j++) {
			// SFPipelineStructureInstance instance=instances[j];
			//
			// }
		}

		if(vertex)
			writer+=("attribute vec3 tessellationParameters;");
		writer+=("\n");

		writer+=("\n\nvoid main(void){\n");
		var doneTessellation=false;
		for (var i=0; i < list.length; i++) {
			// System.err.println(i+" "+list.get(i)+" list.get(i).getShaderCode() "
			// + list.get(i).getShaderCode());

			var programComponent=list[i].getProgram();
			var writeSuffix=SFGL20AbstractProgram_getRegisterName(list,i);

			var params=programComponent.getRegisters();
			for (var index in params) {
				var param=params[index];
				if (param instanceof SFPipelineRegister && !doneTessellation) {
					var reg=param;
					if (reg.getName()==="uvw") {
						writer+=("\tfloat u=tessellationParameters.x;\n");
						writer+=("\tfloat v=tessellationParameters.y;\n");
						writer+=("\tfloat w=1.0-tessellationParameters.x-tessellationParameters.y;\n");
					}
					if (reg.getName()==="uv") {
						writer+=("\tfloat u=tessellationParameters.x;\n");
						writer+=("\tfloat v=tessellationParameters.y;\n");
					}
					doneTessellation=true;
				}
			}

			var lines=programComponent.getShaderCodeLines();
			
			for (var j in lines) {
				var sfFunction=lines[j];
				var register=SFGL20AbstractProgram_getRegister(list,i);
				
				var global=sfFunction.getParameter();
				if(global.getName()==="<>")
					global=register;
				var func=sfFunction.getFunction();
				if (func.getType() != SFParameteri_GLOBAL_GENERIC) {
					var declaration="";
					var name=global.getName();
					if(register!=undefined && global!=undefined){
						name=register.getName()+name;
					}
					if (SFGL20GlobalV_declaredOnWrite(global)) {
						if(!contains(alreadyDeclaredElements,name)){
							var def=SFGlobalVSetGL20Implementor_getDeclarationString(global.getType());
							declaration=def + " ";
							alreadyDeclaredElements.push(name);
						}
					}
					global=SFGL20AbstractProgram_getRegister(list,i);// sfFunction.getParameter();
					if (global == null)
						global=sfFunction.getParameter();
					
					if(sfFunction.getParameter().getType()!=SFParameteri_GLOBAL_GENERIC){
						
						writer+=("\t"
								+ declaration+""
								+ name
								+ "="
								+ SFGL20AbstractProgram_translateExpression(func,
										sfFunction.getParameter(),global,programComponent.getParameterSet())
								+ ";\n");
					}else{
						writer+=("\t"
								+ declaration+""
								+ name
								+ "="
								+ SFGL20AbstractProgram_translateExpression(func,
										global,global,programComponent.getParameterSet())
								+ ";\n");	
					}
				} else {

					var type="vec3";
					if (register.getType() == SFParameteri_GLOBAL_FLOAT2) {
						type="vec2";
					}
					
					if(sfFunction.getParameter().getType()!=SFParameteri_GLOBAL_GENERIC){
						global=sfFunction.getParameter();
					}

					writer+=("\t"
							+ type
							+ " "
							+ writeSuffix
							+ "="
							+ SFGL20AbstractProgram_translateExpression(func,
									global,global,programComponent.getParameterSet())
							+ ";\n");
				}
			}

		}
		

		if(vertex){
			writer+=("\n\tgl_Position=vec4(position,1);");
		}
		
		for (var prAssocIndex in list) {
			var prAssoc=list[prAssocIndex];
			if(contains(prAssoc.getProgram().getRegisters(),SFPipelineRegister_getFromName("fColor"))){
				writer+=("\n\tgl_FragColor=fColor;");
			}/*else{
				writer+=("\n\tgl_FragColor=fColor0;");
			}*/
			//NOTE: unfortunately, only 1 fragment value is allowed, and you cannot render multiple textures
			for (var i = 0; i < 1; i++) {
				if(contains(prAssoc.getProgram().getRegisters(),SFPipelineRegister_getFromName("fColor"+i))){
					writer+=("\n\tgl_FragColor=fColor"+i+";");
					//writer+=("\n\tgl_FragData["+i+"]=fColor"+i+";");//this is a big trouble i think
				}
			}
		}
		
		
		writer+=("\n}");

		return writer;
	}

SFGL20AbstractProgram.prototype["setDataObject"]=function(data){
			this.data=data;
		};

SFGL20AbstractProgram.prototype["getData"]=function(){
			return this.data;
		};


SFGL20AbstractProgram.prototype["getMaterials"]=function(){
			return this.materials;
		};
		
SFGL20AbstractProgram.prototype["getLight"]=function(){
			return this.light;
		};
		
SFGL20AbstractProgram.prototype["getTransforms"]=function(){
			return null;
		};
		
SFGL20AbstractProgram.prototype["getPrimitive"]=function(){
			return null;
		};
	