function House(roofHeight, baseWidth, baseHeight){
		this. roofHeight = roofHeight;
		this. baseWidth = baseWidth;
		this. baseHeight = baseHeight;
}

House.prototype = {

	getRoofHeight:function(){
		return this.roofHeight;
	},

	setRoofHeight:function(roofHeight){
		this. roofHeight = roofHeight;
	},

	getBaseWidth:function(){
		return this.baseWidth;
	},

	setBaseWidth:function(baseWidth){
		this. baseWidth = baseWidth;
	},

	getBaseHeight:function(){
		return this.baseHeight;
	},

	setBaseHeight:function(baseHeight){
		this. baseHeight = baseHeight;
	}

};