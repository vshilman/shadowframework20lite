function generateModelAtPosition(modelName, position) {

		var node = new SFObjectModel();		
		var	index = 2;

		var array = getAlreadyAvailableDataset("Materials").getArray();
		var materialReference = new SFStructureReference(array, index);

		node.getModel().getMaterialComponent().setProgram("BasicMat");
		node.getModel().getMaterialComponent().addData(materialReference);

		var geometry = (getAlreadyAvailableDataset(modelName)).getResource();
		node.getModel().setRootGeometry(geometry);

		node.getModel().getTransformComponent().setProgram("BasicPNTransform");

		node.getTransform().setPosition(position);

		return node;
	}
