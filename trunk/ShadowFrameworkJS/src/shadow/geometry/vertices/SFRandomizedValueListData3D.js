

function SFRandomizedValueListData3D(){

	var seed=new SFInt(9000);
	var size=new SFShort(0);

	var parameters=new SFNamedParametersObject();
	parameters.addObject( size);
	parameters.addObject( seed);
	this.setData(parameters);
}

inherit(SFRandomizedValueListData3D,SFDataAsset);

SFRandomizedValueListData3D.prototype["buildResource"]=function(){
	var vertices=new Array();
		var randomizer=new SFRandomizer(this.seed.getIntValue());
		for (var i = 0; i < this.size.getShortValue(); i++) {
			vertices.push(new SFVertex3f(randomizer.randomFloat(),randomizer.randomFloat(),randomizer.randomFloat()));
		}
		
		return new SFArrayListValueList(vertices);
};

