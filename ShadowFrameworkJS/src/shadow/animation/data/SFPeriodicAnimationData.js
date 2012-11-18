


function SFPeriodicAnimationData(){
	this.animation=new SFDataAssetObject(null);
	this.startTime=new SFInt(0);	
	this.period=new SFInt(0);
		var namedParameters=new SFNamedParametersObject();
		namedParameters.addObject( this.startTime);
		namedParameters.addObject( this.period);
		namedParameters.addObject( this.animation);
		this.setData(namedParameters);	
}

inherit(SFPeriodicAnimationData,SFDataAsset);

SFPeriodicAnimationData.prototype["buildResource"]=function(){
	var periodicAnimation=new SFPeriodicAnimation(this.animation.getDataset().getResource(),
				this.period.getIntValue(),this.startTime.getIntValue());
	return periodicAnimation;
};

SFPeriodicAnimationData.prototype["generateNewDatasetInstance"]=function(){
			return new SFPeriodicAnimationData();
		};
		
