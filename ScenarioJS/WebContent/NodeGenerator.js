function generateModelAtPosition(modelName, position, direction) {

		
		var node = (getAlreadyAvailableDataset(modelName)).getResource();

		node.getTransform().setPosition(position);
		node.getTransform().setOrientation(direction);
		return node;
	}
