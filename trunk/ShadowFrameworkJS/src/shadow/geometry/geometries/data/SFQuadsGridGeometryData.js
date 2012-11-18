

function SFQuadsGridGeometryData(){

	this.u_splits=new SFFloatArray(0);
	this.v_splits=new SFFloatArray(0);
	this.NuNv=new SFShortByteField(0);
	this.primitive=new SFString();
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.u_splits);
	parameters.addObject(this.v_splits);
	parameters.addObject(this.NuNv);
	parameters.addObject(this.primitive);
	this.setData(parameters);
}

inherit(SFQuadsGridGeometryData,SFDataAsset);


SFQuadsGridGeometryData.prototype["buildResource"]=function(){

		var gridGeometry=new SFQuadsGridGeometry();
		
		if(this.u_splits.getFloatValues().length!=0){
			gridGeometry.getQuadsGrid().setU_splits(this.u_splits.getFloatValues());
		}else{
			gridGeometry.getQuadsGrid().setNu( this.NuNv.getByte(0));
		}
		if(v_splits.getFloatValues().length!=0){
			gridGeometry.getQuadsGrid().setU_splits( this.v_splits.getFloatValues());
		}else{
			gridGeometry.getQuadsGrid().setNv( this.NuNv.getByte(1));
		}
		
		var surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
		var primitive=SFPipeline_getPrimitive(this.primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
		
		return surfaceGeometry;
};


SFQuadsGridGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFQuadsGridGeometryData();
};





