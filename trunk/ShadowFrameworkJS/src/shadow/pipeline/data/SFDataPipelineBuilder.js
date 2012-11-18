

function SFDataPipelineBuilder(){
	this.dataObject = new Array();
	this.generateData();
	//SFExpressionGeneratorKeeper_getKeeper().setGenerator(new SFBasicExpressionGenerator());
	this.pipelineBuilder=new SFPipelineBuilder();
}

inherit(SFDataPipelineBuilder,SFCompositeDataArray);


SFDataPipelineBuilder.prototype["generateData"]=function(){
			this.allInformations = new SFDataList(new SFPipelineInstructionObjects());
			this.addDataObject(this.allInformations);
		};	


SFDataPipelineBuilder.prototype["apply"]=function(alternativeBuilder){
			
			for (var i = 0; i < this.allInformations.size(); i++) {
				
				var dataObject=this.allInformations.getDataObject()[i];
				
				var parameters = dataObject.getParameters();
				var command = dataObject.commandName();
				
				// Not the best code in the world...
				if (command===("Begin")) {
					alternativeBuilder.generateElement(dataObject.getData(0),
							dataObject.getData(1));
				}
//				if (command===("Vertex")) {
//					alternativeBuilder.addGridVertex(dataObject.getData(0));
//				}
//				if (command===("Edge")) {
//					alternativeBuilder.buildEdge(parameters);
//				}
				if (command===("Define")) {
					alternativeBuilder.buildDefineRule(dataObject.getData(0),
							dataObject.getData(1), dataObject.getData(2));
				}
				if (command===("Grid")) {
					alternativeBuilder.buildGrid(parameters, dataObject.getData(0),
							dataObject.getData(1),dataObject.getData(2));
				}
				
//				if (command===("Internal")) {
//					alternativeBuilder.buildGridInternals(parameters);
//				}
				if (command===("Param")) {
					alternativeBuilder.buildParamRuleString(dataObject.getData(0),
							dataObject.getData(1));
				}
				if (command===("Param2")) {
					alternativeBuilder.buildParamRule(dataObject.getData(0), parameters);
				}
				if (command===("Path")) {
					alternativeBuilder.buildPath(parameters);
				}
				if (command===("Write")) {
					alternativeBuilder.buildWriteRule(dataObject.getData(0),
							dataObject.getData(1));
				}
				if (command===("Rewrite")) {
					alternativeBuilder.buildRewriteRule(dataObject.getData(0),
							dataObject.getData(1));
				}
				if (command===("Close")) {
					alternativeBuilder.closeElement();
				}
				if (command===("Use")) {
					alternativeBuilder.setUseRule(dataObject.getData(0));
				}
				if (command===("Component")) {
					alternativeBuilder.buildComponent(dataObject.getData(0));
				}
				if (command===("Domain")) {
					alternativeBuilder.buildDomain(dataObject.getData(0));
				}
				if (command===("Block")) {
					alternativeBuilder.buildBlock(dataObject.getData(0),dataObject.getData(1));
				}
				
			}
		};	
