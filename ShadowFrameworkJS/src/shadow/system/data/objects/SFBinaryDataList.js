//Java to JS on 20/03/2012

function SFBinaryDataList(type){
	this.dataObject = new Array();
	this.type = type;
	this.bitSize= type.getBitSize();
}

SFBinaryDataList.prototype["elementsSize"]=function(){
			return this.dataObject.length;
		};

SFBinaryDataList.prototype["getBitSize"]=function(){
			return this.bitSize;
		};
		
SFBinaryDataList.prototype["getDataObject"]=function(){
			return this.dataObject;
		};
				
SFBinaryDataList.prototype["getType"]=function(){
			return this.type;
		};
		
SFBinaryDataList.prototype["readFromStream"]=function(stream){

			var n = stream.readInt();
			//var bitSize = stream.readInt();
			//alert("Data are n="+n+" bitsize="+bitSize);
			var data = stream.readBinaryData(n, this.bitSize);
			
			for (var i = 0; i < n; i++) {
				this.dataObject[i]=this.type.clone();
				this.dataObject[i].setValue(data[i]);
			}
			
		};
		
SFBinaryDataList.prototype["writeOnStream"]=function(stream){
			stream.writeInt(this.dataObject.length);
			//stream.writeInt(this.getBitSize());
			var data=new Array();
			for (var i = 0; i < this.dataObject.length; i++) {
				data[i]=this.dataObject[i].getValue();
			}
			stream.writeBinaryData(data, bitSize);
		};
		
SFBinaryDataList.prototype["clone"]=function(){
			return new SFBinaryDataList(this.type);
		};
		
