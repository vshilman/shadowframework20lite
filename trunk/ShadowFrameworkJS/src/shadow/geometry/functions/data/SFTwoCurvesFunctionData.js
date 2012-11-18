
function SFTwoCurvesFunctionData(){
	this.setup();
}

inherit(SFTwoCurvesFunctionData,SFDataAsset);


SFTwoCurvesFunctionData.prototype["setup"]=function(){
			this.firstCurve=new SFLibraryReference();
			this.secondCurve=new SFLibraryReference();
			var namedParameters=new SFNamedParametersObject();
			namedParameters.addObject( this.firstCurve);
			namedParameters.addObject( this.secondCurve);
			this.setData(namedParameters);
			this.setReferences(this.firstCurve,this.secondCurve);
		};

SFTwoCurvesFunctionData.prototype["getFirstCurve"]=function(){
			return this.firstCurve;
		};

SFTwoCurvesFunctionData.prototype["getSecondCurve"]=function(){
			return this.secondCurve;
		};

SFTwoCurvesFunctionData.prototype["generateNewDatasetInstance"]=function(){
			return new SFTwoCurvesFunctionData();
		};