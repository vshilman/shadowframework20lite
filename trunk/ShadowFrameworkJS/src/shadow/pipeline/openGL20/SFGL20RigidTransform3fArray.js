
function SFGL20RigidTransform(){
	this.transform=new SFTransform3f();
	this.effectiveTransform=new SFTransform3f();
	this.sons=new Array();
};

SFGL20RigidTransform.prototype["update"]=function(){
	if(this.father === undefined){
		this.effectiveTransform.setValue(this.transform);
	}else{
		sonUpdate();
	}
};

SFGL20RigidTransform.prototype["sonUpdate"]=function(){
	this.effectiveTransform.setValue(this.father.effectiveTransform);
	this.effectiveTransform.mult(this.transform);
	sonsUpdate();
};

SFGL20RigidTransform.prototype["sonsUpdate"]=function(){
	for (var sonIndex in this.sons) {
		this.sons[sonIndex].sonUpdate();
	}
};

SFGL20RigidTransform.prototype["attach"]=function(father){
	if (this.father != null) {		
		this.father.sons.remove(this);
	}
	this.father = father;
	this.father.sons.push(this);
	update();
};



function SFGL20RigidTransforms3fArray(){
	this.transforms=new Array();
};


SFGL20RigidTransforms3fArray.prototype["apply"]=function(index){
	SFPipeline_getSfPipelineGraphics().setupTransform(this.transforms[index].effectiveTransform.get());
};

SFGL20RigidTransforms3fArray.prototype["attach"]=function(trueSon,sonIndex,fatherIndex){
	trueSon.transforms[sonIndex].attach(this.transforms[fatherIndex]);
	trueSon.transforms[sonIndex].update();
};


SFGL20RigidTransforms3fArray.prototype["eraseElements"]=function(index,elementsCount){
	for (var i = 0; i < elementsCount; i++) {
		this.transforms.remove(index);
	}
};


SFGL20RigidTransforms3fArray.prototype["generateSample"]=function(){
	return new SFTransform3f();
};

SFGL20RigidTransforms3fArray.prototype["generateElement"]=function(){
	this.transforms.push(new SFGL20RigidTransform());
	return this.transforms.length-1;
};



SFGL20RigidTransforms3fArray.prototype["generateElements"]=function(){
	var size = this.transforms.length;
	for (var i = 0; i < count; i++) {
		this.transforms.push(new SFGL20RigidTransform());
	}
	return size;
};

SFGL20RigidTransforms3fArray.prototype["getElement"]=function(index,element){
	element.setValue(this.transforms[index].transform);
};

SFGL20RigidTransforms3fArray.prototype["getElementsCount"]=function(){
	return this.transforms.length;
};

SFGL20RigidTransforms3fArray.prototype["setElement"]=function(index,element){
	this.transforms[index].transform.setValue(element);
	this.transforms[index].update();
};

SFGL20RigidTransforms3fArray.prototype["getElementOrientation"]=function(index,matrix){
	this.transforms[index].transform.getMatrix(matrix);
};

SFGL20RigidTransforms3fArray.prototype["getElementPosition"]=function(index,vertex){
	this.transforms[index].transform.getPosition(vertex);
};

SFGL20RigidTransforms3fArray.prototype["setElement"]=function(index,vertex){
	this.transforms[index].transform.getPosition(vertex);
};

SFGL20RigidTransforms3fArray.prototype["setElementOrientation"]=function(index,matrix){
	this.transforms[index].transform.setMatrix(matrix);
	this.transforms[index].update();
};

SFGL20RigidTransforms3fArray.prototype["setElementPosition"]=function(index,vertex){
	this.transforms[index].transform.setPosition(vertex);
	this.transforms[index].update();
};
