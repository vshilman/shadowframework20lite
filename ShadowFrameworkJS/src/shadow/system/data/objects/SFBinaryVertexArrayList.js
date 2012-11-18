


function SFBinaryVertexArrayList(type){
	this.type=type;
	this.bitSize = type.getBitSize();
	this.dataObject=new Array();
}

inherit(SFBinaryVertexArrayList,SFPrimitiveType);


SFBinaryVertexArrayList.prototype["setType"]=function(type){
			this.type = type;
		};

SFBinaryVertexArrayList.prototype["getBitSize"]=function(){
			return this.bitSize;
		};
		
SFBinaryVertexArrayList.prototype["getVertexCount"]=function(){
			return this.dataObject.length/this.totalSize;
		};

SFBinaryVertexArrayList.prototype["setVertexSize"]=function(vertexSize){
			this.vertexSize = vertexSize;
		};

SFBinaryVertexArrayList.prototype["addValue"]=function(value){
			var values=new Array();
			for (var i = 0; i < value.length; i++) {
				values[i]=value[i].get();
			}
			addVertex(values);
		};

SFBinaryVertexArrayList.prototype["getValue"]=function(index,vertexIndex,value){
			var pos=index*this.totalSize;
			for (var i = 0; i < vertexIndex; i++) {
				pos+=this.vertexSize[i];
			}
			for (var i = 0; i < this.vertexSize[vertexIndex]; i++) {
				value.get()[i]=this.dataObject[pos+i].getFloat();
			}
			return value;
		};
	

SFBinaryVertexArrayList.prototype["getDataObject"]=function(){
			return this.dataObject;
		};

SFBinaryVertexArrayList.prototype["elementsSize"]=function(){
			return this.dataObject.length;
		};

SFBinaryVertexArrayList.prototype["getType"]=function(){
			return this.type;
		};


SFBinaryVertexArrayList.prototype["readFromStream"]=function(stream){
			var n = stream.readShort();
			var vertexSizeLength=stream.readByte();
			this.vertexSize=new Array();
			this.totalSize=0;
			for (var i = 0; i < vertexSizeLength; i++) {
				this.vertexSize[i]=stream.readByte();
				this.totalSize+=this.vertexSize[i];
			}
			// int bitSize=stream.readInt();
			var data = stream.readBinaryData(n, this.bitSize);
			for (var i = 0; i < n; i++) {
				this.dataObject.push(this.type.clone());
				this.dataObject[i].setValue(data[i]);
			}
		};

SFBinaryVertexArrayList.prototype["clone"]=function(){
			return new SFBinaryVertexArrayList(type);
		};

SFBinaryVertexArrayList.prototype["getSize"]=function(){
			return this.dataObject.length/this.totalSize;
		};

SFBinaryVertexArrayList.prototype["addVertex"]=function(fs){
			for (var i = 0; i < fs.length; i++) {
				for (var j = 0; j < fs[i].length; j++) {
					var t=type.clone();
					t.setFloat(fs[i][j]);
					this.dataObject.push(t);
				}
			}
			if(this.vertexSize.length==0){
				this.vertexSize=new Array();
				this.totalSize=0;
				for (var i = 0; i < fs.length; i++) {
					this.vertexSize[i]=fs[i].length;
					this.totalSize+=this.vertexSize[i];
				}
			}
		};
