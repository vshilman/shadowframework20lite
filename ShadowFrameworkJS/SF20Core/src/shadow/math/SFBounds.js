
function SFBounds(basePoint, extension){
		this.basePoint=basePoint;
		this.extension=extension;
}

SFBounds<T>.prototype = {

	getBasePoint:function(){
		return this.basePoint;
	},

	setBasePoint:function(basePoint){
		this.basePoint=basePoint;
	},

	getExtension:function(){
		return this.extension;
	},

	setExtension:function(extension){
		this.extension=extension;
	}

};