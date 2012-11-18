

function SFTexture(textureSet,index){
	this.textureSet=textureSet;
	this.index=index;
}


SFTexture.prototype["getTexture"]=function(){
	return this.textureSet.getTexture(this.index);
};

SFTexture.prototype["getTexturesSet"]=function(){
	return this.textureSet;
};

SFTexture.prototype["getIndex"]=function(){
	return this.index;
};

SFTexture.prototype["setIndex"]=function(index){
	this.index=index;
};

SFTexture.prototype["setTexturesSet"]=function(textureSet){
	this.textureSet=textureSet;
};


	