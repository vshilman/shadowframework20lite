function SFViewer() {
		
       this.renderer = new SFRenderer();
       var camera = new SFCamera(new SFVertex3f(0, 0, 0), new SFVertex3f(0, 0,
				1), new SFVertex3f(1, 0, 0), new SFVertex3f(0, 1, 0), 1, 1, 20);
		
		camera.setF(new SFVertex3f(0, 0, -1));
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
		var rootSceneNode = new SFReferenceNode();

		var charhandler = new CharHandler();
		for(var i = 0; i < charhandler.charList.length; i++){
		
			rootSceneNode.addNode(charhandler.charList[i].node);
		
		}
		
		var copynode = rootSceneNode.copyNode();
		this.renderer.render(copynode);
		
	};
	
	