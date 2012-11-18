
function SFMoveAnimationData(){
	this.startingPosition=new SFVertex3fData();
	this.endingPosition=new SFVertex3fData();
	this.duration=new SFInt(0);
	this.startingTime=new SFInt(0);
	this.node=new SFLibraryReference();
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject( this.startingPosition);
	parameters.addObject( this.endingPosition);
	parameters.addObject( this.duration);
	parameters.addObject( this.startingTime);
	parameters.addObject( this.node);
	this.setData(parameters);
}

inherit(SFMoveAnimationData,SFDataAsset);

SFMoveAnimationData.prototype["buildResource"]=function(){
	
		var moveAnimation=new SFMoveAnimation(this.startingPosition.getVertex3f(),
				this.endingPosition.getVertex3f(),this.duration.getIntValue(),this.startingTime.getIntValue(),
				null,SFStandardTweeners_CUBIC);
		
		var dataset=this.node.retrieveDataset();
		moveAnimation.setTransformNode(dataset.getResource());
		
		return moveAnimation;
};



SFMoveAnimationData.prototype["generateNewDatasetInstance"]=function(){
	return new SFMoveAnimationData();
};

	
