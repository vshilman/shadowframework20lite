


function SFCompositeAnimationData(){
		this.animations=new SFDataAssetList();
		var namedParameters=new SFNamedParametersObject();
		namedParameters.addObject( this.animations);
		this.setData(namedParameters);	
}

inherit(SFCompositeAnimationData,SFDataAsset);

SFCompositeAnimationData.prototype["buildResource"]=function(){
	var animation=new SFCompositeAnimation();
		
	for (var i = 0; i < this.animations.length; i++) {
		animation.add(this.animations[i].getResource());
	}
		
	return animation;
};

SFCompositeAnimationData.prototype["generateNewDatasetInstance"]=function(){
			return new SFCompositeAnimationData();
		};
		