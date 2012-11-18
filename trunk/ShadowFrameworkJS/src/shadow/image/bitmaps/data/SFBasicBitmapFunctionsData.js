
var SFBasicBitmapFunctionsData_functions=SFBasicBitmapFunctions_values();


function SFBasicBitmapFunctionsData(){
	
	this.func=new SFEnumObject(SFBasicBitmapFunctionsData_functions);
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.func);
	this.setData(parameters);
}

inherit(SFBasicBitmapFunctionsData,SFDataAsset);


SFBasicBitmapFunctionsData.prototype["buildResource"]=function(){
	return funct.getElement();
};


SFBasicBitmapFunctionsData.prototype["generateNewDatasetInstance"]=function(){
	return new SFBasicBitmapFunctionsData();
};



