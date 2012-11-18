

function SFProgramAssetData(){
	this.initialize();
}

inherit(SFProgramAssetData,SFDataAsset);

SFProgramAssetData.prototype["initialize"]=function(){
			this.program= new  SFString("");
			this.structures= new  SFDataObjectsList(new SFStructureReferenceDataObject());
			this.usedTexture= new  SFDataObjectsList(new SFTextureData());
			var parameters=new SFNamedParametersObject();
			parameters.addObject( this.program);
			parameters.addObject( this.structures);
			parameters.addObject( this.usedTexture);
			this.setData(parameters);
		};

SFProgramAssetData.prototype["buildResource"]=function(){
			var element=new SFProgramModuleStructures();
			
			element.setProgram(this.program.getString());
			
			for(var i=0;i<this.structures.size();i++){
				element.getData().push(this.structures.get(i).buildReference());
			}
			
			//texturesCount=0;
			for (var i = 0; i < this.usedTexture.size(); i++) {
				element.getTextures().push(this.usedTexture.get(i).buildTextureReference());
			}
			
			return element;
		};
		
SFProgramAssetData.prototype["generateNewDatasetInstance"]=function(){
	return new SFProgramAssetData();
};