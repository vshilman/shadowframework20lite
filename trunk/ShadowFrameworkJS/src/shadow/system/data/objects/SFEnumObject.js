
//SFEnumObject will not have names
function SFEnumObject(elements){
	this.elements=elements;
	this.index=0;
}

inherit(SFEnumObject,SFPrimitiveType);


SFEnumObject.prototype["getIndex"]=function(){
			return this.index;			
		};

SFEnumObject.prototype["clone"]=function(){
			return new SFEnumObject(this.elements);
		};

SFEnumObject.prototype["setIndex"]=function(index){
			this.index=index;			
		};

SFEnumObject.prototype["readFromStream"]=function(stream){
			this.index=stream.readShort();			
		};

SFEnumObject.prototype["getElement"]=function(){
			return this.elements[this.index];	
		};