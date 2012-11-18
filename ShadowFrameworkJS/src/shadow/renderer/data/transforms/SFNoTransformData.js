

function SFNoTransformData(){
	this.voidTransform=new SFTransform3f();
	this.setData(new SFNamedParametersObject());
}

inherit(SFNoTransformData,SFDataAsset);

SFNoTransformData.prototype["generateNewDatasetInstance"]=function(){
			return this;
		};
		
SFNoTransformData.prototype["buildResource"]=function(){
			return this.voidTransform;
		};
