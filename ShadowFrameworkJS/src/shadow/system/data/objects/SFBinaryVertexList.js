
function SFBinaryVertexList(type){
	this.type=type;
	this.bitSize = type.getBitSize();
	this.dataObject=new Array();
}

inherit(SFBinaryVertexList,SFPrimitiveType);


SFBinaryVertexList.prototype["setType"]=function(type){
			this.type = type;
		};
		
SFBinaryVertexList.prototype["setType"]=function(type){
	this.type = type;
};

SFBinaryVertexList.prototype["getBitSize"]=function(){
			return this.bitSize;
		};
		
SFBinaryVertexList.prototype["getVertexCount"]=function(){
			return this.dataObject.length/this.totalSize;
		};

SFBinaryVertexList.prototype["setVertexSize"]=function(vertexSize){
			this.vertexSize = vertexSize;
		};

SFBinaryVertexList.prototype["addValue"]=function(value){
			this.addVertex(value.get());
		};	

SFBinaryVertexList.prototype["addValue"]=function(value){
			this.addVertex(value.get());
		};	

SFBinaryVertexList.prototype["getValue"]=function(index,value){
	for (var i = 0; i < this.vertexSize && i<value.get().length; i++) {
		value.get()[i]=this.dataObject[index*this.vertexSize+i].getFloat();
	}
	return value;
};	
	
SFBinaryVertexList.prototype["getDataObject"]=function(){
	return this.dataObject;
};	
	

SFBinaryVertexList.prototype["elementsSize"]=function(){
	return this.dataObject.length;
};	
	
SFBinaryVertexList.prototype["getType"]=function(){
			return this.type;
		};

SFBinaryVertexList.prototype["readFromStream"]=function(stream){
			var n = stream.readShort();
			this.vertexSize=stream.readByte();
			// int bitSize=stream.readInt();
			var data = stream.readBinaryData(n, this.bitSize);
			for (var i = 0; i < n; i++) {
				this.dataObject.push(this.type.clone());
				this.dataObject[i].setValue(data[i]);
			}
		};

SFBinaryVertexList.prototype["clone"]=function(){
			return new SFBinaryVertexList(this.type);
		};

SFBinaryVertexList.prototype["getSize"]=function(){
			return this.dataObject.length/this.vertexSize;
		};

SFBinaryVertexList.prototype["addVertex"]=function(fs){
			for (var i = 0; i < fs.length; i++) {
				var t=type.clone();
				t.setFloat(fs[i]);
				this.dataObject.push(t);
			}
			if(this.vertexSize<=0){
				this.vertexSize=fs.length;
			}
		};
