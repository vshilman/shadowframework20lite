
function SFAssetModule(){

}

SFAssetModule.prototype["getSFDataObject"]=function(){
			return this.sfDataObject;
		};

SFAssetModule.prototype["getType"]=function(){
			return this.constructor;
		};

SFAssetModule.prototype["setData"]=function(sfDataObject){
			this.sfDataObject=sfDataObject;
		};

SFAssetModule.prototype["invalidate"]=function(){
		};
