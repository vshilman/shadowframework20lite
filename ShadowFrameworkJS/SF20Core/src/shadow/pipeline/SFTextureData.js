
function SFTextureData(width, height){
		this.width=width;
		this.height=height;
}

SFTextureData.prototype = {

	getWidth:function(){
		return this.width;
	},

	getHeight:function(){
		return this.height;
	}

};