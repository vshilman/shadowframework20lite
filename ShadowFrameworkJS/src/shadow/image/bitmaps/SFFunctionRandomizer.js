

function SFFunctionRandomizer(seed){
	this.randomizer=new SFRandomizer(seed);
}


SFFunctionRandomizer.prototype["getValue"]=function(u,v){
		return this.randomizer.randomFloat();
};

SFFunctionRandomizer.prototype["init"]=function(){
	// do nothing
};

SFFunctionRandomizer.prototype["destroy"]=function(){
	// do nothing
};





