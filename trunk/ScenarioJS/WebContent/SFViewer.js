function SFViewer() {
		
       this.renderer = new SFRenderer();
       var camera = new SFCamera(new SFVertex3f(0, 0, 0), new SFVertex3f(0, 0,
				1), new SFVertex3f(1, 0, 0), new SFVertex3f(0, 1, 0), 1, 1, 20);
		
		camera.setF(new SFVertex3f(0, 0, -3));
		camera.setDir(new SFVertex3f(0, 0, 1));
		camera.setLeft(new SFVertex3f(1, 0, 0));
		camera.setUp(new SFVertex3f(0, 1, 0));
		camera.setPerspective(true);
		camera.update();

		camera.extractTransform();
		this.renderer.setCamera(camera);

		var programAsset = new SFProgramModuleStructures("BasicLSPN2");

		var lightStructure = SFPipeline_getProgramModule("BasicLSPN2")
				.getComponents()[0].getStructures()[0].getStructure();

		var lightArray = SFPipeline_getSfPipelineMemory()
				.generateStructureData(lightStructure);
		var lightData = [ new SFVertex3f(1, 1, 1), new SFVertex3f(1, 1, -1) ];

		var lightReference = new SFStructureReference(lightArray, lightArray
				.generateElement());

		var mat = new SFStructureData(lightReference.getStructure());
		for ( var i = 0; i < lightData.length; i++) {
			mat.getValue(i).setValue(lightData[i]);
		}
		try {
			lightReference.setStructureData(mat);
		} catch (exception) {
			e.printStackTrace();
		}
		programAsset.getData().push(lightReference);

		this.renderer.setLight(programAsset);
		
	    this.rootSceneNode = new SFReferenceNode();
		housenames = [ "House01",
					 "House02",
					 "House03",
					 "House04",
					 "House05",
					 "House06",
					 "House07"
					 ];
		
		for (var i = 0; i < 10; i++){
			var index = Math.floor(Math.random() * housenames.length); 
			var nome = housenames[index];
			node = (getAlreadyAvailableDataset(nome)).getResource();
			   node.getTransform().setPosition(new SFVertex3f(-2 + i*3 ,0,2));
				this.rootSceneNode.addNode(node.copyNode());

			   
		}
		for (var i = 0; i < 10; i++){
			var index = Math.floor(Math.random() * housenames.length); 
			var nome = housenames[index];
			node = (getAlreadyAvailableDataset(nome)).getResource();
			   node.getTransform().setPosition(new SFVertex3f(-2 + i*3 ,0,-10));
				this.rootSceneNode.addNode(node.copyNode());

			   
		}
		

		
	}

	SFViewer.prototype["init"] = function() {
		SFPipeline_getSfPipelineGraphics().init();
	};

	SFViewer.prototype["getNode"] = function() {
		return this.node;
	};

	SFViewer.prototype["draw"] = function() {

		SFInitiator_solveInitiables();
		SFUpdater_refresh();

		gl.clearColor(0.5, 0.5, 1, 1);
		gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

		var matrix = this.renderer.getCamera().extractTransform();
/*
		if (!this.renderer.getCamera().isPerspective) {

			var unitVolumeSize = 600.0;
			var viewport = gl.getParameter(gl.VIEWPORT);
			var width = viewport[2];
			var height = viewport[3];
			for ( var i = 0; i < 4; i++) {
				matrix[i] *= unitVolumeSize / width;
				matrix[i + 4] *= unitVolumeSize / height;
			}
		}
*/
		gl.enable(gl.DEPTH_TEST);

		SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
		SFPipeline_getSfPipelineGraphics().setupTransform(matrix);

		var avatarsHandler = new AvatarsHandler();
		for(var i = 0; i < avatarsHandler.charList.length; i++){
		
			this.rootSceneNode.addNode(avatarsHandler.charList[i].node);
		
		}
		
		
	
		

	   //	node.getTransform().setOrientation()
		var copynode = this.rootSceneNode.copyNode();

		this.renderer.render(copynode);
		
	};
	
	