
function SFGL20ListData(){
	this.data=new Array();
}

SFGL20ListData.prototype["eraseElements"]=function(index,elementsCount){
	for (var i = 0; i < elementsCount; i++) {
		this.data.remove(index);
	}
};

SFGL20ListData.prototype["getValue"]=function(index){
	return this.data[index];
};

SFGL20ListData.prototype["generateElement"]=function(index){
	var e=this.generateGenericElement();
	this.data.push(e);
	return this.data.length-1;
};
	
SFGL20ListData.prototype["generateElements"]=function(count){
	var index=this.data.length;
	for (var i = 0; i < count; i++) {
		this.data.push(this.generateGenericElement());
	}
	return index;
};
	
SFGL20ListData.prototype["getElement"]=function(index,element){
		this.assignValues(element, this.data[index]);
	};
	
SFGL20ListData.prototype["setElement"]=function(index,element){
		this.assignValues(this.data[index],element);
	};
	
SFGL20ListData.prototype["getElementsCount"]=function(index,element){
		return this.data.length;
	};

SFGL20ListData.prototype["generateSample"]=function(){
		return this.generateGenericElement();
	};