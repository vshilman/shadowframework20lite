


function SFRotateAnimationData(){
	this.direction=new SFVertex3fData();
	this.startAngle=new SFFloat(0);
	this.lastAngle=new SFFloat(0);
	this.duration=new SFInt(0);
	this.startingTime=new SFInt(0);
	this.node=new SFLibraryReference();
	
		var namedParameters=new SFNamedParametersObject();
		namedParameters.addObject( this.direction);
		namedParameters.addObject( this.startAngle);
		namedParameters.addObject( this.lastAngle);
		namedParameters.addObject( this.duration);
		namedParameters.addObject( this.startingTime);
		namedParameters.addObject( this.node);
		this.setData(namedParameters);	
}

inherit(SFRotateAnimationData,SFDataAsset);

SFRotateAnimationData.prototype["buildResource"]=function(){
	var moveAnimation=new SFRotateAnimation(this.direction.getVertex3f(),
				this.startAngle.getFloatValue(),this.lastAngle.getFloatValue(),
				this.duration.getIntValue(),this.startingTime.getIntValue(),
				null,SFStandardTweeners_CUBIC);
		
		var dataset=node.retrieveDataset();
		moveAnimation.setTransformNode(dataset.getResource());
		
		return moveAnimation;
};

SFRotateAnimationData.prototype["generateNewDatasetInstance"]=function(){
			return new SFRotateAnimationData();
		};
		