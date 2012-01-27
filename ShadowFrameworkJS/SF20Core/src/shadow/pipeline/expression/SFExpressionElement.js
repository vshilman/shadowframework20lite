

SFExpressionElement.prototype = {

	getElement:function(){
		return this.element;
	},

	setElement:function(element){
		this.element=element;
	},

	getElementSize:function(){
	return list.size();//Warning: Not well Identified 
	},

	addElement:function(element){
	if(element==null)			throw new NullPointerException();//Warning: Not well Identified 
	list.add(element);//Warning: Not well Identified 
	},

	removeElement:function(element){
	if(element==null)			throw new NullPointerException();//Warning: Not well Identified 
	list.remove(element);//Warning: Not well Identified 
	},

	getType:function(){
		return this.type;
	},

	setType:function(type){
		this.type=type;
	},

	traverse:function(intepreter){
	intepreter.startElement(this);//Warning: Not well Identified 
	intepreter.closeElement(this);//Warning: Not well Identified 
	}

};