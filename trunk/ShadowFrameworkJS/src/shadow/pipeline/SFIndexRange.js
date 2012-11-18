
function SFIndexRange(position,size) {
	this.position = position;
	this.size = size;
}


SFIndexRange.prototype["getPosition"]=function(){
	return this.position;
};

SFIndexRange.prototype["getSize"]=function(){
	return this.size;
};

SFIndexRange.prototype["setPosition"]=function(position){
	this.position=position;
};

SFIndexRange.prototype["setSize"]=function(size){
	this.size=size;
};

SFIndexRange.prototype["insertIndex"]=function(index){
	if(this.position==-1){
		this.position=index;
		this.size=1;
	}else{
		var last=this.position+this.size;
		if(index<this.position){
			this.position=index;
			this.size=last-this.position;
		}else if(index>=this.position+this.size){
			this.size=index-this.position+1;
		}
	}
};
