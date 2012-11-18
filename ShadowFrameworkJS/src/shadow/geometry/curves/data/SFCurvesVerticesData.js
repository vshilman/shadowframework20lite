

function SFCurvesVerticesData(){
	this.setup();
}

inherit(SFCurvesVerticesData,SFDataAsset);//TODO: verify constructors

SFCurvesVerticesData.prototype["setup"]=function(){
			this.vertices=new SFLibraryReference();
			this.closed=new SFShort();
			var parameters=new SFNamedParametersObject();
			parameters.addObject(this.vertices);
			parameters.addObject(this.closed);
			this.setData(parameters);
			//setReferences(vertices);
		};

SFCurvesVerticesData.prototype["getClosed"]=function(){
			return this.closed.getShortValue()==1;
		};
