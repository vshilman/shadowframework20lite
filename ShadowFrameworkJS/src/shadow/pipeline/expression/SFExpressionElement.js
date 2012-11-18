//Java to JS on 21/03/2012

var SFExpressionElement_SIZE_ALL = -1;

function SFExpressionElement(element) {
	this.element = element;
	this.list = new Array();
	this.type = 0;
}

SFExpressionElement.prototype["getElement"] = function() {
	return this.element;
};

SFExpressionElement.prototype["getExpressionElement"] = function(index) {
	return this.list.get(index);
};

SFExpressionElement.prototype["setElement"] = function(element) {
	this.element = element;
};

SFExpressionElement.prototype["addElement"] = function(element) {
	if (element == undefined)
		throw "Null Pointer Exception";
	this.list.push(element);
};

SFExpressionElement.prototype["removeElement"] = function(element) {
	if (element == null)
		throw "Null Pointer Exception";
	for ( var i = 0; i < this.list.length; i++) {
		if (this.list[i] == element) {
			this.list.splice(i, 1);
			break;
		}
	}
};

SFExpressionElement.prototype["getType"] = function() {
	return this.type;
};

SFExpressionElement.prototype["setType"] = function(type) {
	this.type = type;
};

SFExpressionElement.prototype["traverse"] = function(interpreter) {
	interpreter.startElement(this);
	var size = this.list.length;
	for ( var i = 0; i < size - 1; i++) {
		this.list[i].traverse(interpreter);
		interpreter.refreshElement(this);
	}
	if (size > 0)
		this.list[size - 1].traverse(interpreter);
	interpreter.closeElement(this);
};


SFExpressionElement.prototype["toString"] = function() {
	var elString=this.getElement()+"("+this.getType()+")";
	for(var i=0;i<this.list.length;i++) {
		elString=elString+","+this.list[i];
	}
//	if(this.list.length>1)
//		return "{"+elString+":"+this.list+"}";
//	else if (list.size()==1)
//		return "{"+elString+":"+this.list[0]+"}";
	
	return elString;
};


