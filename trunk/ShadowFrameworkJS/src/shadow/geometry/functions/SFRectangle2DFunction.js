
function SFRectangle2DFunction(x,y,w,h){
	
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
}

inherit(SFRectangle2DFunction,SFUnoptimizedSurfaceFunction);

SFRectangle2DFunction.prototype["getX"]=function(u,v){
	return this.x+u*this.w;
};

SFRectangle2DFunction.prototype["getY"]=function(u,v){
	return this.y+u*this.h;
};

SFRectangle2DFunction.prototype["getZ"]=function(u,v){
	return 0;
};
	
SFRectangle2DFunction.prototype["init"]=function(){
	//do nothing
};

SFRectangle2DFunction.prototype["destroy"]=function(){
	//do nothing
};
	
	
SFRectangle2DFunction.prototype["getX"]=function(){
	return this.x;
};
	
SFRectangle2DFunction.prototype["getY"]=function(){
	return this.y;
};

SFRectangle2DFunction.prototype["getW"]=function(){
	return this.w;
};

SFRectangle2DFunction.prototype["getH"]=function(){
	return this.h;
};


SFRectangle2DFunction.prototype["setX"]=function(x){
	this.x=x;
};

SFRectangle2DFunction.prototype["setY"]=function(y){
	this.y=y;
};

SFRectangle2DFunction.prototype["setW"]=function(w){
	this.w=w;
};

SFRectangle2DFunction.prototype["setH"]=function(h){
	this.h=h;
};
